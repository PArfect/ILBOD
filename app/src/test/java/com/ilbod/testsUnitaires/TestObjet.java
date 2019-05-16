package com.ilbod.testsUnitaires;

import org.junit.Test;
import org.junit.Assert;

import com.ilbod.detection.carte.Lieu;
import com.ilbod.detection.carte.Objet;
import com.ilbod.detection.Exception.ChaineDeCaractereNullOuVide;
import com.ilbod.detection.Exception.LieuDejaPresent;


public class TestObjet {

    @Test(expected = ChaineDeCaractereNullOuVide.class)
    public void TestconstructeurTest1Jeu1(){
        Objet objet = new Objet("");
    }

    @Test(expected = ChaineDeCaractereNullOuVide.class)
    public void TestconstructeurTest1Jeu2(){
        Objet objet = new Objet(null);
    }

    @Test
    public void TestconstructeurTest2(){
        Objet objet = new Objet("extincteur");

        Assert.assertNotNull(objet);
        Assert.assertNotNull(objet.getLieux());
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestaddLieuTest1S(){
        Objet objet = new Objet("extincteur");
        objet.addLieu(null);

    }
    @Test(expected = LieuDejaPresent.class)
    public void TestaddLieu2(){
        Objet objet = new Objet("extincteur");
        Lieu lieu = new Lieu("lieu");

        objet.addLieu(lieu);
        objet.addLieu(lieu);

    }

    @Test
    public void TestaddLieu3(){
        Objet objet = new Objet("extincteur");
        Lieu lieu = new Lieu("lieu");

        objet.addLieu(lieu);

        Assert.assertNotNull(objet.getLieux().get(lieu.getNom()));
        Assert.assertEquals(lieu, objet.getLieux().get(lieu.getNom()));

    }
}
