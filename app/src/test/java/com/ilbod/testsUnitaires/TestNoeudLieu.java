package com.ilbod.testsUnitaires;

import org.junit.Test;
import org.junit.Assert;
import carte.NoeudLieu;
import carte.Lieu;
import Exception.MemeLieu;


public class TestNoeudLieu {

    @Test(expected = IllegalArgumentException.class)
    public void TestconstructeurTest1() {
        NoeudLieu noeud = new NoeudLieu(null);

    }

    @Test
    public void TestconstructeurTest2() {
        Lieu lieu = new Lieu("lieu1");
        NoeudLieu noeud = new NoeudLieu(lieu);

        Assert.assertNotNull(noeud);
        Assert.assertEquals(lieu,noeud.getLieu());

    }

    @Test (expected = IllegalArgumentException.class)
    public void TestajouterDerriereTest1(){
        Lieu lieu = new Lieu("lieu1");
        NoeudLieu noeud = new NoeudLieu(lieu);

        noeud.ajouterDerriere(null);

    }

    @Test (expected = MemeLieu.class)
    public void TestajouterDerriereTest2(){
        Lieu lieu = new Lieu("lieu1");
        NoeudLieu noeud = new NoeudLieu(lieu);
        NoeudLieu noeud2 = new NoeudLieu(lieu);

        noeud.ajouterDerriere(noeud2);
    }

    @Test
    public void TestajouterDerriere2(){
        Lieu lieu = new Lieu("lieu1");
        Lieu lieu2 = new Lieu("lieu2");
        NoeudLieu noeud = new NoeudLieu(lieu);
        NoeudLieu noeud2 = new NoeudLieu(lieu2);

        noeud.ajouterDerriere(noeud2);

        //faire un getter ou pas our assert?
    }


}
