package com.ilbod.detection.carte;

import java.io.IOException;
import java.util.ArrayList;

public class MainConstructeur {
    public static void main(String[] args) {
        GestionCarte gc = new GestionCarte();

        //Création des lieux
        Lieu F1 = new Lieu("F1");
        Lieu B1 = new Lieu("B1");
        Lieu B2 = new Lieu("B2");
        Lieu B3 = new Lieu("B3");
        Lieu B4 = new Lieu("B4");
        Lieu B5 = new Lieu("B5");
        Lieu B6 = new Lieu("B6");
        Lieu D1 = new Lieu("D1");
        Lieu D2 = new Lieu("D2");
        Lieu D3 = new Lieu("D3");
        Lieu A1 = new Lieu("A1");
        Lieu A2 = new Lieu("A2");
        Lieu A3 = new Lieu("A3");
        Lieu A4 = new Lieu("A4");
        Lieu A5 = new Lieu("A5");
        Lieu C1 = new Lieu("C1");
        Lieu C2 = new Lieu("C2");
        Lieu C3 = new Lieu("C3");
        Lieu E1 = new Lieu("E1");
        Lieu E2 = new Lieu("E2");
        Lieu E3 = new Lieu("E3");
        Lieu E4 = new Lieu("E4");
        Lieu E5 = new Lieu("E5");
        Lieu E6 = new Lieu("E6");

        //Création des objets
        Objet distributeur = new Objet("distributeur");
        Objet extincteur = new Objet("extincteur");
        Objet plan = new Objet("plan");
        Objet alarme = new Objet("alarme");
        Objet poubelle = new Objet("poubelle");
        Objet sortie = new Objet("sortie");
        Objet fontaine = new Objet("fontaine");
        Objet tableau = new Objet("tableau");
        Objet tv = new Objet("tv");
        Objet wifi = new Objet("wifi");
        Objet a = new Objet("a");
        Objet b = new Objet("b");
        Objet c = new Objet("c");
        Objet d = new Objet("d");
        Objet e = new Objet("e");
        Objet ascenseur= new Objet("ascenseur");
        Objet lecteur = new Objet("lecteur");
        Objet detecteur = new Objet("detecteur");
        Objet grille = new Objet("grille");
        Objet fleche = new Objet("fleche");
        Objet portecf = new Objet("portecf");
        Objet vitrine = new Objet("vitrine");
        Objet eclair = new Objet("eclair");
        Objet portee = new Objet("portee");
        Objet radiateur = new Objet("radiateur");
        Objet homme = new Objet("homme");
        Objet femme = new Objet("femme");
        Objet porteb = new Objet("porteb");

        //Ajout des objets dans la gestion de carte
        gc.addObjectMap(distributeur);
        gc.addObjectMap(extincteur);
        gc.addObjectMap(plan);
        gc.addObjectMap(alarme);
        gc.addObjectMap(poubelle);
        gc.addObjectMap(sortie);
        gc.addObjectMap(fontaine);
        gc.addObjectMap(tableau);
        gc.addObjectMap(tv);
        gc.addObjectMap(wifi);
        gc.addObjectMap(a);
        gc.addObjectMap(b);
        gc.addObjectMap(c);
        gc.addObjectMap(d);
        gc.addObjectMap(e);
        gc.addObjectMap(ascenseur);
        gc.addObjectMap(lecteur);
        gc.addObjectMap(detecteur);
        gc.addObjectMap(grille);
        gc.addObjectMap(fleche);
        gc.addObjectMap(portecf);
        gc.addObjectMap(vitrine);
        gc.addObjectMap(eclair);
        gc.addObjectMap(portee);
        gc.addObjectMap(radiateur);
        gc.addObjectMap(homme);
        gc.addObjectMap(femme);
        gc.addObjectMap(porteb);

        // des objets aux lieux
        F1.addObjetRecursive(distributeur);
        F1.addObjetRecursive(fontaine);
        F1.addObjetRecursive(poubelle);

        B1.addObjetRecursive(ascenseur);
        B1.addObjetRecursive(eclair);
        B1.addObjetRecursive(alarme);
        B1.addObjetRecursive(portee);

        B2.addObjetRecursive(b);
        B2.addObjetRecursive(d);

        B3.addObjetRecursive(grille);
        B3.addObjetRecursive(alarme);
        B3.addObjetRecursive(femme);
        B3.addObjetRecursive(detecteur);
        B3.addObjetRecursive(extincteur);
        B3.addObjetRecursive(sortie);
        B3.addObjetRecursive(homme);
        B3.addObjetRecursive(wifi);
        B3.addObjetRecursive(tv);
        B3.addObjetRecursive(fleche);
        B3.addObjetRecursive(plan);

        B4.addObjetRecursive(lecteur);
        B4.addObjetRecursive(detecteur);
        B4.addObjetRecursive(porteb);
        B4.addObjetRecursive(portecf);

        B5.addObjetRecursive(grille);
        B5.addObjetRecursive(extincteur);
        B5.addObjetRecursive(sortie);
        B5.addObjetRecursive(detecteur);
        B5.addObjetRecursive(plan);
        B5.addObjetRecursive(lecteur);
        B5.addObjetRecursive(fleche);
        B5.addObjetRecursive(wifi);
        B5.addObjetRecursive(portecf);

        B6.addObjetRecursive(vitrine);
        B6.addObjetRecursive(portee);
        B6.addObjetRecursive(b);
        B6.addObjetRecursive(d);
        B6.addObjetRecursive(ascenseur);
        B6.addObjetRecursive(eclair);
        B6.addObjetRecursive(portecf);
        B6.addObjetRecursive(sortie);
        B6.addObjetRecursive(extincteur);
        B6.addObjetRecursive(alarme);

        D1.addObjetRecursive(plan);
        D1.addObjetRecursive(wifi);
        D1.addObjetRecursive(extincteur);
        D1.addObjetRecursive(fleche);
        D1.addObjetRecursive(sortie);
        D1.addObjetRecursive(detecteur);
        D1.addObjetRecursive(portecf);
        D1.addObjetRecursive(porteb);
        D1.addObjetRecursive(grille);

        D2.addObjetRecursive(sortie);
        D2.addObjetRecursive(portecf);
        D2.addObjetRecursive(extincteur);
        D2.addObjetRecursive(fleche);
        D2.addObjetRecursive(detecteur);

        D3.addObjetRecursive(wifi);
        D3.addObjetRecursive(extincteur);
        D3.addObjetRecursive(femme);
        D3.addObjetRecursive(homme);
        D3.addObjetRecursive(portecf);
        D3.addObjetRecursive(lecteur);
        D3.addObjetRecursive(grille);
        D3.addObjetRecursive(sortie);
        D3.addObjetRecursive(plan);
        D3.addObjetRecursive(fleche);

        A5.addObjetRecursive(radiateur);
        A5.addObjetRecursive(c);
        A5.addObjetRecursive(b);
        A5.addObjetRecursive(a);
        A5.addObjetRecursive(eclair);
        A5.addObjetRecursive(vitrine);
        A5.addObjetRecursive(portecf);
        A5.addObjetRecursive(ascenseur);
        A5.addObjetRecursive(extincteur);
        A5.addObjetRecursive(portee);
        A5.addObjetRecursive(alarme);

        A4.addObjetRecursive(grille);
        A4.addObjetRecursive(detecteur);
        A4.addObjetRecursive(sortie);
        A4.addObjetRecursive(vitrine);
        A4.addObjetRecursive(portecf);
        A4.addObjetRecursive(fleche);
        A4.addObjetRecursive(plan);
        A4.addObjetRecursive(extincteur);

        A2.addObjetRecursive(grille);
        A2.addObjetRecursive(portecf);
        A2.addObjetRecursive(tv);
        A2.addObjetRecursive(sortie);
        A2.addObjetRecursive(vitrine);
        A2.addObjetRecursive(plan);
        A2.addObjetRecursive(detecteur);
        A2.addObjetRecursive(extincteur);

        A1.addObjetRecursive(ascenseur);
        A1.addObjetRecursive(portee);
        A1.addObjetRecursive(eclair);
        A1.addObjetRecursive(vitrine);
        A1.addObjetRecursive(extincteur);
        A1.addObjetRecursive(fleche);
        A1.addObjetRecursive(portecf);
        A1.addObjetRecursive(e);
        A1.addObjetRecursive(plan);
        A1.addObjetRecursive(a);
        A1.addObjetRecursive(b);
        A1.addObjetRecursive(c);
        A1.addObjetRecursive(d);
        A1.addObjetRecursive(sortie);
        A1.addObjetRecursive(radiateur);

        E1.addObjetRecursive(vitrine);
        E1.addObjetRecursive(ascenseur);
        E1.addObjetRecursive(e);
        E1.addObjetRecursive(fleche);
        E1.addObjetRecursive(sortie);

        E2.addObjetRecursive(tv);
        E2.addObjetRecursive(distributeur);
        E2.addObjetRecursive(e);
        E2.addObjetRecursive(plan);
        E2.addObjetRecursive(tableau);
        E2.addObjetRecursive(extincteur);
        E2.addObjetRecursive(eclair);
        E2.addObjetRecursive(fontaine);
        E2.addObjetRecursive(ascenseur);
        E2.addObjetRecursive(homme);
        E2.addObjetRecursive(portee);
        E2.addObjetRecursive(portecf);
        E2.addObjetRecursive(sortie);
        E2.addObjetRecursive(detecteur);
        E2.addObjetRecursive(poubelle);

        E3.addObjetRecursive(detecteur);
        E3.addObjetRecursive(femme);
        E3.addObjetRecursive(fleche);
        E3.addObjetRecursive(plan);
        E3.addObjetRecursive(extincteur);
        E3.addObjetRecursive(portecf);

        E4.addObjetRecursive(plan);
        E4.addObjetRecursive(extincteur);
        E4.addObjetRecursive(sortie);
        E4.addObjetRecursive(portecf);
        E4.addObjetRecursive(fleche);
        E4.addObjetRecursive(detecteur);

        E5.addObjetRecursive(ascenseur);
        E5.addObjetRecursive(sortie);
        E5.addObjetRecursive(portecf);
        E5.addObjetRecursive(extincteur);

        E6.addObjetRecursive(ascenseur);
        E6.addObjetRecursive(radiateur);
        E6.addObjetRecursive(alarme);
        E6.addObjetRecursive(portee);
        E6.addObjetRecursive(sortie);
        E6.addObjetRecursive(extincteur);
        E6.addObjetRecursive(portecf);
        E6.addObjetRecursive(plan);

        C3.addObjetRecursive(fleche);
        C3.addObjetRecursive(portecf);
        C3.addObjetRecursive(sortie);
        C3.addObjetRecursive(plan);
        C3.addObjetRecursive(extincteur);
        C3.addObjetRecursive(grille);
        C3.addObjetRecursive(detecteur);

        C2.addObjetRecursive(sortie);
        C2.addObjetRecursive(fleche);
        C2.addObjetRecursive(extincteur);
        C2.addObjetRecursive(detecteur);
        C2.addObjetRecursive(portecf);

        C1.addObjetRecursive(femme);
        C1.addObjetRecursive(homme);
        C1.addObjetRecursive(plan);
        C1.addObjetRecursive(wifi);
        C1.addObjetRecursive(grille);
        C1.addObjetRecursive(detecteur);
        C1.addObjetRecursive(fleche);
        C1.addObjetRecursive(sortie);
        C1.addObjetRecursive(extincteur);
        C1.addObjetRecursive(portecf);



        //Mise en place du graphe des lieux
        gc.setOrigine(F1);
        gc.setMemoire();
        gc.createNoeudGauche(B1);
        gc.moveGauche();
        gc.createNoeudDevant(B2);
        gc.moveDevant();
        gc.createNoeudDevant(B3);
        gc.moveDevant();
        gc.createNoeudDevant(B4);
        gc.moveDevant();
        gc.createNoeudDevant(B5);
        gc.moveDevant();
        gc.createNoeudDevant(B6);
        gc.moveDevant();
        gc.createNoeudDroite(D1);
        gc.moveDroite();
        gc.createNoeudDroite(D2);
        gc.moveDroite();
        gc.createNoeudDroite(D3);
        gc.moveDroite();
        gc.createNoeudDroite(A5);
        gc.moveDroite();
        gc.createNoeudDerriere(A4);
        gc.moveDerriere();
        gc.createNoeudDerriere(A3);
        gc.moveDerriere();
        gc.createNoeudDerriere(A2);
        gc.moveDerriere();
        gc.createNoeudDerriere(A1);
        gc.moveDerriere();
        gc.creerLienGauche();
        gc.moveDevant();
        gc.moveDevant();
        gc.moveDevant();
        gc.moveDevant();
        gc.setMemoire();
        gc.moveDerriere();
        gc.moveDerriere();
        gc.moveDerriere();
        gc.moveDerriere();
        gc.createNoeudDroite(E1);
        gc.moveDroite();
        gc.createNoeudDroite(E2);
        gc.moveDroite();
        gc.createNoeudDevant(E3);
        gc.moveDevant();
        gc.createNoeudDevant(E4);
        gc.moveDevant();
        gc.createNoeudDevant(E5);
        gc.moveDevant();
        gc.createNoeudDevant(E6);
        gc.moveDevant();
        gc.createNoeudGauche(C3);
        gc.moveGauche();
        gc.createNoeudGauche(C2);
        gc.moveGauche();
        gc.createNoeudGauche(C1);
        gc.moveGauche();
        gc.creerLienGauche();


        //sauvegarde :
        try {
            gc.saveCarte("app/src/main/res/raw/savemap.txt");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

     /*   BFS bfs = new BFS();
        ArrayList<NoeudLieu> list = bfs.getChemin(gc.lieuToNoeud(C1),gc.lieuToNoeud(D2));
        for (NoeudLieu nd : list) {
            System.out.println(nd.getLieu().nom);
        }*/
    }
}
