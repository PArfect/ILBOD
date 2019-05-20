package com.ilbod.detection.carte;

import java.io.IOException;

public class MainTest {

    public static void main(String[] args) {

        final String path = "app/src/main/res/raw/save.txt";

        GestionCarte carte = new GestionCarte();
        Lieu lieu1 = new Lieu("Lieu 1");
        Lieu lieu2 = new Lieu("Lieu 2");

        boolean res1 = carte.dansCarte(lieu1);
        boolean res2 = carte.dansCarte(lieu2);
        System.out.println("lieu1 et lieu2 dans la carte : " + res1 + " " + res2);
        System.out.println();

        carte.createNoeudDevant(lieu1);
        carte.moveDevant();

        res1 = carte.dansCarte(lieu1);
        res2 = carte.dansCarte(lieu2);
        System.out.println("lieu1 et lieu2 dans la carte 2 : " + res1 + " " + res2);
        //System.out.println("courant : " + carte.courant);
        System.out.println();

        carte.moveDerriere();
        carte.createNoeudGauche(lieu2);
        carte.moveGauche();
      //  System.out.println("courant : " + carte.courant);
        System.out.println();
        carte.setMemoire();
        carte.moveDroite();
        carte.moveDevant();
        carte.creerLienDevant();
        carte.moveGauche();
      //  System.out.println("courant : " + carte.courant);
        System.out.println();

        res1 = carte.dansCarte(lieu1);
        res2 = carte.dansCarte(lieu2);
        System.out.println("lieu1 et lieu2 dans la carte : " + res1 + " " + res2);
       // System.out.println("courant : " + carte.courant);
        System.out.println();

        System.out.println("enregistrement dans : test.txt");
        try {
            carte.saveCarte(path);
            GestionCarte carte2 = GestionCarte.loadCarte(path);
            System.out.println("load de test.txt");
            boolean res11 = carte2.dansCarte(lieu1);
            boolean res22 = carte2.dansCarte(lieu2);
            System.out.println("lieu1 et lieu2 dans la carte  2 : " + res11 + " " + res22);
            carte2.moveGauche();
            System.out.println("courant : " + carte2);
            carte2.moveDerriere();
            System.out.println("courant : " + carte2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
