package com.ilbod.testsValidations;

import com.ilbod.detection.carte.GestionCarte;
import com.ilbod.detection.carte.Lieu;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestGestionCarte {

    GestionCarte carte;
    Lieu lieu1;
    Lieu lieu2;
    String path = "app/src/main/res/raw/";

    @Before
    public void Initialize() {
        carte = new GestionCarte();
        lieu1 = new Lieu("Lieu 1");
        lieu2 = new Lieu("Lieu 2");

        //Création d'un carte test
        carte.createNoeudDevant(lieu1);
        carte.moveDevant();
        carte.moveDerriere();
        carte.createNoeudGauche(lieu2);
        carte.moveGauche();
        carte.setMemoire();
        carte.moveDroite();
        carte.moveDevant();
        carte.creerLienDevant();
        carte.moveGauche();

    }
    
    @Test
    public void TestConstructeur() {
        GestionCarte gc = new GestionCarte();
        Lieu origine = new Lieu("origine");
        
        Assert.assertNotNull(gc);
        Assert.assertTrue(gc.dansCarte(origine));
    }
    
    @Test
    public void TestDansCarteTest1() {
        GestionCarte cartetest = new GestionCarte();

        Assert.assertFalse(cartetest.dansCarte(lieu1));
        Assert.assertFalse(cartetest.dansCarte(lieu2));
    }

    @Test
    public void TestDansCarteTest2() {
        Assert.assertTrue(carte.dansCarte(lieu1));
        Assert.assertTrue(carte.dansCarte(lieu2));
    }

    @Test
    public void TestSaveCarteTest1() throws Exception {
        carte.saveCarte(path + "testsave2.txt");
        Assert.assertTrue(compareFile(path + "testsave.txt", path + "testsave2.txt"));
    }

    @Test
    public void TestSaveCarteTest2() throws Exception {
        GestionCarte gc = new GestionCarte();
        gc.saveCarte(path + "testsave3.txt");
        Assert.assertTrue(compareFile(path + "testsave.txt", path + "testsave3.txt"));
    }

    @Test
    public void TestLoadCarte() throws Exception {
        GestionCarte gc = GestionCarte.loadCarte(path + "testsave.txt");
        Assert.assertTrue(gc.equals(carte));
    }

    /**
     * Compare le contenu de deux fichiers et retourne true s'ils sont identiques.
     * @param fileName1
     * 					nom du premier fichier.
     * @param fileName2
     * 					nom du second fichier.
     * @return
     * 					true si les deux fichiers ont le même contenu, false sinon.
     * @throws Exception
     * 					toutes les exceptions possibles.
     */
    private static  boolean compareFile(String fileName1, String fileName2) throws Exception {
        FileInputStream fin1 = new FileInputStream(fileName1);
        FileInputStream fin2 = new FileInputStream(fileName2);
        FileChannel fcin1 = fin1.getChannel();
        FileChannel fcin2 = fin2.getChannel();
        ByteBuffer buffer1 = ByteBuffer.allocate(1024);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        int lu1, lu2;
        do {
            buffer1.clear();
            buffer2.clear();
            lu1 = fcin1.read(buffer1);
            lu2 = fcin2.read(buffer2);
            if (lu1 != lu2) {
                return false;
            }
            buffer1.flip();
            buffer2.flip();
            for (int ind=0; ind < lu1; ind++) {
                if (buffer1.get() != buffer2.get()) {
                    return false;
                }
            }

        } while (lu1 == 1024);

        return true;
    }


}
