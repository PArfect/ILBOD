package com.ilbod.testsUnitaires;

import org.junit.Test;
import org.junit.Assert;

import com.ilbod.detection.carte.Lieu;
import com.ilbod.detection.Exception.ChaineDeCaractereNullOuVide;
import com.ilbod.detection.Exception.ObjetDejaPresent;
import com.ilbod.detection.carte.Objet;

public class TestLieu {

    @Test(expected = ChaineDeCaractereNullOuVide.class)
    public void TestconstructeurTest1Jeu1() {
        Lieu lieu = new Lieu("");
    }

    @Test(expected = ChaineDeCaractereNullOuVide.class)
    public void TestconstructeurTest1Jeu2() {
        Lieu lieu = new Lieu(null);
    }

    @Test
    public void TestconstructeurTest2() {
        Lieu lieu = new Lieu("lieu");

        Assert.assertNotNull(lieu);
        Assert.assertNotNull(lieu.getObjets());
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestaddObjet1(){
        Lieu lieu = new Lieu("lieu");
        lieu.addObjet(null);
    }

    @Test (expected = ObjetDejaPresent.class)
    public void TestaddObjet2(){
        Lieu lieu = new Lieu("lieu");
        Objet objet = new Objet("extincteur");
        lieu.addObjet(objet);
        lieu.addObjet(objet);

    }

    @Test
    public void TestaddObjet3(){
        Lieu lieu = new Lieu("lieu");
        Objet objet = new Objet("extincteur");

        lieu.addObjet(objet);
        Assert.assertNotNull(lieu.getObjets().get(objet.getNom()));
        Assert.assertEquals(objet, lieu.getObjets().get(objet.getNom()));
    }

}
