/*
 * Copyright 2019 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ilbod.detection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.media.ImageReader.OnImageAvailableListener;
import android.os.SystemClock;
import android.util.Size;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.ilbod.detection.carte.Lieu;
import com.ilbod.detection.customview.OverlayView;
import com.ilbod.detection.customview.OverlayView.DrawCallback;
import com.ilbod.detection.env.BorderedText;
import com.ilbod.detection.env.ImageUtils;
import com.ilbod.detection.env.Logger;
import com.ilbod.detection.localisation.LieuProba;
import com.ilbod.detection.tflite.Classifier;
import com.ilbod.detection.tflite.TFLiteObjectDetectionAPIModel;
import org.tensorflow.demo.tracking.MultiBoxTracker;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.ilbod.detection.carte.Objet;

/**
 * An activity that uses a TensorFlowMultiBoxDetector and ObjectTracker to detect and then track
 * objects.
 */
public class DetectorActivity extends CameraActivity implements OnImageAvailableListener{
  private static final Logger LOGGER = new Logger();

  // Configuration values for the prepackaged SSD model.
  private static final int TF_OD_API_INPUT_SIZE = 300;
  private static final boolean TF_OD_API_IS_QUANTIZED = false;
  private static final String TF_OD_API_MODEL_FILE = "detect.tflite";
  private static final String TF_OD_API_LABELS_FILE = "file:///android_asset/labelmap.txt";
  private static final DetectorMode MODE = DetectorMode.TF_OD_API;
  // Minimum detection confidence to track a detection.
  private static final float MINIMUM_CONFIDENCE_TF_OD_API = 0.5f;
  private static final boolean MAINTAIN_ASPECT = false;
  private static final Size DESIRED_PREVIEW_SIZE = new Size(640, 480);
  private static final boolean SAVE_PREVIEW_BITMAP = false;
  private static final float TEXT_SIZE_DIP = 10;
  OverlayView trackingOverlay;
  private Integer sensorOrientation;

  private Classifier detector;

  private long lastProcessingTimeMs;
  private Bitmap rgbFrameBitmap = null;
  private Bitmap croppedBitmap = null;
  private Bitmap cropCopyBitmap = null;

  private boolean computingDetection = false;

  private long timestamp = 0;

  private Matrix frameToCropTransform;
  private Matrix cropToFrameTransform;

  private MultiBoxTracker tracker;

  private byte[] luminanceCopy;

  private BorderedText borderedText;

  @Override
  public void onPreviewSizeChosen(final Size size, final int rotation) {
    final float textSizePx =
            TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
    borderedText = new BorderedText(textSizePx);
    borderedText.setTypeface(Typeface.MONOSPACE);

    tracker = new MultiBoxTracker(this);

    int cropSize = TF_OD_API_INPUT_SIZE;

    try {
      detector =
              TFLiteObjectDetectionAPIModel.create(
                      getAssets(),
                      TF_OD_API_MODEL_FILE,
                      TF_OD_API_LABELS_FILE,
                      TF_OD_API_INPUT_SIZE,
                      TF_OD_API_IS_QUANTIZED);
      cropSize = TF_OD_API_INPUT_SIZE;
    } catch (final IOException e) {
      e.printStackTrace();
      LOGGER.e("Exception initializing classifier!", e);
      Toast toast =
              Toast.makeText(
                      getApplicationContext(), "Classifier could not be initialized", Toast.LENGTH_SHORT);
      toast.show();
      finish();
    }

    previewWidth = size.getWidth();
    previewHeight = size.getHeight();

    sensorOrientation = rotation - getScreenOrientation();
    LOGGER.i("Camera orientation relative to screen canvas: %d", sensorOrientation);

    LOGGER.i("Initializing at size %dx%d", previewWidth, previewHeight);
    rgbFrameBitmap = Bitmap.createBitmap(previewWidth, previewHeight, Config.ARGB_8888);
    croppedBitmap = Bitmap.createBitmap(cropSize, cropSize, Config.ARGB_8888);

    frameToCropTransform =
            ImageUtils.getTransformationMatrix(
                    previewWidth, previewHeight,
                    cropSize, cropSize,
                    sensorOrientation, MAINTAIN_ASPECT);

    cropToFrameTransform = new Matrix();
    frameToCropTransform.invert(cropToFrameTransform);

    trackingOverlay = (OverlayView) findViewById(R.id.tracking_overlay);
    trackingOverlay.addCallback(
            new DrawCallback() {
              @Override
              public void drawCallback(final Canvas canvas) {
                tracker.draw(canvas);
                if (isDebug()) {
                  tracker.drawDebug(canvas);
                }
              }
            });
  }

  @Override
  protected void processImage() {
    ++timestamp;
    final long currTimestamp = timestamp;
    byte[] originalLuminance = getLuminance();
    tracker.onFrame(
            previewWidth,
            previewHeight,
            getLuminanceStride(),
            sensorOrientation,
            originalLuminance,
            timestamp);
    trackingOverlay.postInvalidate();


    // No mutex needed as this method is not reentrant.
    if (computingDetection || !(detection.isChecked())) {

      readyForNextImage();
      return;
    }
    computingDetection = true;
    LOGGER.i("Preparing image " + currTimestamp + " for detection in bg thread.");

    rgbFrameBitmap.setPixels(getRgbBytes(), 0, previewWidth, 0, 0, previewWidth, previewHeight);

    if (luminanceCopy == null) {
      luminanceCopy = new byte[originalLuminance.length];
    }
    System.arraycopy(originalLuminance, 0, luminanceCopy, 0, originalLuminance.length);
    readyForNextImage();

    final Canvas canvas = new Canvas(croppedBitmap);
    canvas.drawBitmap(rgbFrameBitmap, frameToCropTransform, null);
    // For examining the actual TF input.
    if (SAVE_PREVIEW_BITMAP) {
      ImageUtils.saveBitmap(croppedBitmap);
    }

    runInBackground(
              new Runnable() {
                @Override
                public void run() {
                  LOGGER.i("Running detection on image " + currTimestamp);
                  final long startTime = SystemClock.uptimeMillis();
                  final List<Classifier.Recognition> results = detector.recognizeImage(croppedBitmap);
                  lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime;

                  cropCopyBitmap = Bitmap.createBitmap(croppedBitmap);
                  final Canvas canvas = new Canvas(cropCopyBitmap);
                  final Paint paint = new Paint();
                  paint.setColor(Color.RED);
                  paint.setStyle(Style.STROKE);
                  paint.setStrokeWidth(2.0f);

                  float minimumConfidence = MINIMUM_CONFIDENCE_TF_OD_API;
                  switch (MODE) {
                    case TF_OD_API:
                      minimumConfidence = MINIMUM_CONFIDENCE_TF_OD_API;
                      break;
                  }

                  final List<Classifier.Recognition> mappedRecognitions =
                          new LinkedList<Classifier.Recognition>();
                  for (final Classifier.Recognition result : results) {
                    final RectF location = result.getLocation();

                    if (location != null && result.getConfidence() >= minimumConfidence) {
                      canvas.drawRect(location, paint);
                      String nom = result.getTitle();
                      if(!(gestionLoca.getObjetsDejaDetectes().containsKey(nom))) {
                        Objet obj = gestionCarte.objetsExistants.get(nom);
                        if (obj != null) {
                          gestionLoca.ajoutObjetDetecte(obj);
                        }
                      }
                      cropToFrameTransform.mapRect(location);

                      result.setLocation(location);
                      mappedRecognitions.add(result);
                    }
                  }
                  gestionLoca.miseAJourLieuxProbables();

                  tracker.trackResults(mappedRecognitions, luminanceCopy, currTimestamp);
                  trackingOverlay.postInvalidate();

                  computingDetection = false;

                  gestionLoca.lieuPlusProbable();
                  if(gestionLoca.getLieuTrouveUpdated()){
                    runOnUiThread(
                            new Runnable() {
                              @Override
                              public void run() {
                                  gestionLoca.setLieuTrouveUpdatedFalse();
                                  LieuTrouveInfo(String.valueOf(noeudsDetectesAffiche.size()));
                                  if(!(destination.equals("")) && gestionLoca.getLieuxProbables().size()>0){
                                    clearChemin();
                                    String position = gestionLoca.getLieuxProbables().get(0).getLieu().getNom();
                                    if(!destination.equals(position)){
                                      affichageChemin(gestionCarte.getPlusCourtChemin(position,destination));
                                    }
                                  }
                                  affichageLocalisations();
                              }
                            });
                  }
                }

              });

  }



  /**
   * Affichage du chemin entre le premier lieu et le dernier de lieux.
   * @param lieux Liste des lieux sur le chemin.
   */
  private void affichageChemin(ArrayList<Lieu> lieux){
    String nom;
    Context context = getApplicationContext();
    int id;
    for(int i = 0; i<lieux.size()-1;i++){
      //Le dernier lieu est la position de départ.
      afficheLien(lieux.get(i),lieux.get(i+1));
      nom = lieux.get(i).getNom();
      id = context.getResources().getIdentifier(nom,"id", context.getPackageName());

      noeudsCheminAffiche.put(nom,findViewById(id));
      noeudsCheminAffiche.get(nom).setBackgroundResource(R.drawable.circle);

    }

  }

  /**
   * Affichage du liens entre deux lieux.
   * @param lieu1 lieu à l'extremité du lien
   * @param lieu2 lieu à l'extremité du lien
   */
  private void afficheLien(Lieu lieu1, Lieu lieu2){
    String nom="";
    if(lieu1.getNom().compareTo(lieu2.getNom())< 0){
      nom = nom + lieu1.getNom() + lieu2.getNom();
    }
    else{
      nom = nom + lieu2.getNom() + lieu1.getNom();
    }

    Context context = getApplicationContext();
    int id = context.getResources().getIdentifier(nom,"id", context.getPackageName());

    liensAffiche.put(nom,findViewById(id));
    liensAffiche.get(nom).setVisibility(View.VISIBLE);

  }

  /**
   * Affichage des lieux les plus probables sur la carte.
   */
  private void affichageLocalisations(){
    ArrayList<LieuProba> lieuxProbables = gestionLoca.getLieuxProbables();
    if(lieuxProbables.size()!=0) {
      int max = lieuxProbables.get(0).getOccurrence();
      if (occurrenceLieu != max) {
        resetNoeudsAffiche(max);
      }
      for (LieuProba lieu : gestionLoca.getLieuxProbables()) {
        if (lieu.getOccurrence() != max) {
          return;
        }
        afficheLocalisation(lieu);
      }
    }
  }
  /**
   * Affichage d'un point correspondant à un lieu sur la carte.
   * @param lieu
   */
  private void afficheLocalisation(final LieuProba lieu){
    String nom = lieu.getLieu().getNom();
    if(!(noeudsDetectesAffiche.containsKey(nom))){
      Context context = getApplicationContext();
      int id = context.getResources().getIdentifier(nom,"id", context.getPackageName());

      noeudsDetectesAffiche.put(nom,findViewById(id));
      noeudsDetectesAffiche.get(nom).setBackgroundResource(R.drawable.circle1);
    }
  }


  @Override
  protected int getLayoutId() {
    return R.layout.camera_connection_fragment_tracking;
  }

  @Override
  protected Size getDesiredPreviewFrameSize() {
    return DESIRED_PREVIEW_SIZE;
  }

  // Which detection model to use: by default uses Tensorflow Object Detection API frozen
  // checkpoints.
  private enum DetectorMode {
    TF_OD_API;
  }

  @Override
  protected void setUseNNAPI(final boolean isChecked) {
    runInBackground(() -> detector.setUseNNAPI(isChecked));
  }

  @Override
  protected void setNumThreads(final int numThreads) {
    runInBackground(() -> detector.setNumThreads(numThreads));
  }
}