package com.ilbod.testsValidations;


import com.ilbod.detection.carte.Lieu;
import com.ilbod.detection.carte.Objet;
import com.ilbod.detection.localisation.GestionLocalisation;
import com.ilbod.detection.localisation.LieuProba;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TestGestionLocalisation {

    private GestionLocalisation gestionLoca;

    @Before
    public void setUp() throws Exception{
        gestionLoca = new GestionLocalisation();
    }

    @After
    public void tearDown() {
        gestionLoca = null;
    }

    @Test
    public void Testconstructeur() {
        GestionLocalisation gestionLoca1 = new GestionLocalisation();

        Assert.assertNotNull(gestionLoca1);
        Assert.assertNotNull(gestionLoca1.getLieuTrouve());
        Assert.assertFalse(gestionLoca1.getLieuTrouveUpdated());
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestajoutObjetDetecte(){
        gestionLoca.ajoutObjetDetecte(null);

    }

    @Test
    public void TestmiseAjourLieuxProbablesTest1(){

        Lieu lieu1 = new Lieu("lieu1");
        Lieu lieu2 = new Lieu("lieu2");
        Objet objet1 = new Objet("objet1");
        Objet objet2 = new Objet("objet2");

        objet1.addLieu(lieu1);
        objet2.addLieu(lieu2);

        gestionLoca.ajoutObjetDetecte(objet1);
        gestionLoca.ajoutObjetDetecte(objet2);

        gestionLoca.miseAJourLieuxProbables();

        HashMap<String, Objet> objetsDejaDetectes = gestionLoca.getObjetsDejaDetectes();
        ArrayList<LieuProba> lieuxProbables = gestionLoca.getLieuxProbables();

        Assert.assertEquals(lieuxProbables.get(0).getOccurrence(), 1);
        Assert.assertEquals(lieuxProbables.get(0).getLieu(),lieu1);

        Assert.assertEquals(lieuxProbables.get(1).getOccurrence(), 1);
        Assert.assertEquals(lieuxProbables.get(1).getLieu(),lieu2);

        Assert.assertTrue(objetsDejaDetectes.containsValue(objet1));
        Assert.assertTrue(objetsDejaDetectes.containsValue(objet2));
        Assert.assertTrue(gestionLoca.getObjetsDetectes().size() == 0);

    }


    @Test
    public void TestmiseAjourLieuxProbablesTest2() {
        Lieu lieu1 = new Lieu("lieu1");
        Lieu lieu2 = new Lieu("lieu2");
        Lieu lieu3 = new Lieu("lieu3");
        Lieu lieu4 = new Lieu("lieu4");
        Objet objet1 = new Objet("objet1");
        Objet objet2 = new Objet("objet2");
        Objet objet3 = new Objet("objet3");
        Objet objet4 = new Objet("objet4");
        Objet objet5 = new Objet("objet5");

        objet1.addLieu(lieu1);
        objet2.addLieu(lieu2);
        objet1.addLieu(lieu2);
        objet3.addLieu(lieu3);
        objet3.addLieu(lieu1);
        objet4.addLieu(lieu2);
        objet2.addLieu(lieu4);
        objet5.addLieu(lieu4);

        gestionLoca.ajoutObjetDetecte(objet1);
        gestionLoca.ajoutObjetDetecte(objet2);
        gestionLoca.ajoutObjetDetecte(objet3);
        gestionLoca.ajoutObjetDetecte(objet4);
        gestionLoca.ajoutObjetDetecte(objet5);

        gestionLoca.miseAJourLieuxProbables();

        HashMap<String, Objet> objetsDejaDetectes = gestionLoca.getObjetsDejaDetectes();
        ArrayList<LieuProba> lieuxProbables = gestionLoca.getLieuxProbables();


        Assert.assertEquals(3, lieuxProbables.get(0).getOccurrence());
        Assert.assertEquals(lieu2,lieuxProbables.get(0).getLieu());

        Assert.assertEquals(2, lieuxProbables.get(1).getOccurrence());
        Assert.assertEquals(lieu4,lieuxProbables.get(1).getLieu());

        Assert.assertEquals(2, lieuxProbables.get(2).getOccurrence());
        Assert.assertEquals(lieu1,lieuxProbables.get(2).getLieu());

        Assert.assertEquals(1, lieuxProbables.get(3).getOccurrence());
        Assert.assertEquals(lieu3,lieuxProbables.get(3).getLieu());

        Assert.assertTrue(objetsDejaDetectes.containsValue(objet1));
        Assert.assertTrue(objetsDejaDetectes.containsValue(objet2));
        Assert.assertTrue(objetsDejaDetectes.containsValue(objet3));
        Assert.assertTrue(objetsDejaDetectes.containsValue(objet4));
        Assert.assertTrue(objetsDejaDetectes.containsValue(objet5));
        Assert.assertTrue(gestionLoca.getObjetsDetectes().size() == 0);



    }

}
