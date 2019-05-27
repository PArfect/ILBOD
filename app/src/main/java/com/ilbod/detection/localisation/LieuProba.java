package com.ilbod.detection.localisation;


import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

import com.ilbod.detection.carte.Lieu;

public class LieuProba{

    /**
     * lieu concerné.
     */
    private Lieu lieu;
    /**
     * nombre d'ocurrence du lieu.
     */
    private int occurrence;

    /**
     * constructeur
     * @param lieu lieu concerné.
     */
    public LieuProba(Lieu lieu){
        this.lieu = lieu;
        occurrence = 0;
    }

    public Lieu getLieu(){
        return lieu;

    }

    public int getOccurrence(){
        return occurrence;
    }

    /**
     * incrémente la valeur d'occurrence lorsqu'on a trouvé un nouvel objet.
     * @param n valeur d'incrémentation.
     */
    public void incrementOccurrence(int n){
        occurrence = occurrence + n;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LieuProba lieuProba = (LieuProba) o;
        return Objects.equals(lieu, lieuProba.lieu);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(lieu);
    }

}
