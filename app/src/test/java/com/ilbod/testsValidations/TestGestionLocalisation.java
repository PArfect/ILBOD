package com.ilbod.testsValidations;


import com.ilbod.detection.carte.Lieu;
import com.ilbod.detection.carte.Objet;
import com.ilbod.detection.localisation.GestionLocalisation;
import com.ilbod.detection.localisation.LieuProba;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestGestionLocalisation {

    private GestionLocalisation gestionLoca;

    @Before
    public void setup() {
        GestionLocalisation gestionLoca = new GestionLocalisation();
    }

    @Test
    public void Testconstructeur() {
        GestionLocalisation gestionLoca = new GestionLocalisation();

        Assert.assertNotNull(gestionLoca);
        Assert.assertNotNull(gestionLoca.getLieuTrouve());
        Assert.assertFalse(gestionLoca.getLieuTrouveUpdated());
    }


    @Test
    public void TestmiseAjourLieuxProbablesTest1(){

        Lieu lieu1 = new Lieu("lieu1");
        Lieu lieu2 = new Lieu("lieu2");
        Objet objet1 = new Objet("objet1");
        Objet objet2 = new Objet("objet2");

        lieu1.addObjet(objet1);
        lieu2.addObjet(objet2);

        gestionLoca.ajoutObjetDetecte(objet1);
        gestionLoca.ajoutObjetDetecte(objet2);

        ArrayList<LieuProba> lieuxProbables = gestionLoca.getLieuxProbables();

        Assert.assertEquals(lieuxProbables.get(0).getOccurrence(), 1);
        Assert.assertEquals(lieuxProbables.get(0).getLieu(),lieu1);

        Assert.assertEquals(lieuxProbables.get(1).getOccurrence(), 1);
        Assert.assertEquals(lieuxProbables.get(1).getLieu(),lieu2);


    }


}
