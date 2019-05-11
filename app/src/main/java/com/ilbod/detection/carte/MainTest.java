package com.ilbod.detection.carte;

public class MainTest {
    public static void main(String[] args) {
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
        System.out.println("lieu1 et lieu2 dans la carte : " + res1 + " " + res2);
       // System.out.println("courant : " + carte.courant);
        System.out.println();

        carte.moveDerriere();
        carte.createNoeudGauche(lieu2);
        carte.moveGauche();
      //  System.out.println("courant : " + carte.courant);
        System.out.println();
        carte.setMemoire();
        carte.moveDroite();
        carte.moveDevant();
        carte.creerLienGauche();
        carte.moveGauche();
      //  System.out.println("courant : " + carte.courant);
        System.out.println();

        res1 = carte.dansCarte(lieu1);
        res2 = carte.dansCarte(lieu2);
        System.out.println("lieu1 et lieu2 dans la carte : " + res1 + " " + res2);
       // System.out.println("courant : " + carte.courant);
        System.out.println();


    }
}
