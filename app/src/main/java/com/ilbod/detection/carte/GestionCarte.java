package com.ilbod.detection.carte;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import com.ilbod.detection.Exception.LieuDejaPresent;
import com.ilbod.detection.Exception.NoeudDejaPresent;

/**
 * Classe permettant de gérer les différentes fonctionnalités s'appliquant la carte virtuelle.
 */
public class GestionCarte implements Serializable {

    /**
     * Serialisation.
     */
    private static final long serialVersionUID = 1L;
    /**
     *Noeud de référence de la carte.
     */
    private NoeudLieu racine;
    /**
     *Noeud permettant de parcourir la carte.
     */
    private NoeudLieu courant;
    /**
     * Noeud à mémorizer pouvant être utilisé ultérieurement
     */
    private NoeudLieu memoire;
    /**
     * Map des objets existants.
     */
    public HashMap<String, Objet> objetsExistants;

    /**
     * Construit le gestionnaire de carte virtuelle.
     */
    public GestionCarte(){
        Lieu lieu = new Lieu("origine");
        racine = new NoeudLieu(lieu);
        objetsExistants = new HashMap<String, Objet>();
        courant = racine;
        memoire = racine;
    }

    /**
     * Ajoute un objet à la map
     */
    public void addObjectMap(Objet objet) {
        objetsExistants.put(objet.getNom(),objet);
    }

    /**
     * Change le lieu d'origine
     */
    public void setOrigine(Lieu lieu) {
        if (courant.equals(racine)) {
            racine = new NoeudLieu(lieu);
            courant = racine;
        } else {
            racine = new NoeudLieu(lieu);
        }

    }

    /**
     * Deplace le noeud courant à droite.
     */
    public void moveDroite() {
        if (courant.getDroite() != null) {
            courant = courant.getDroite();
        }
    }

    /**
     * Deplace le noeud courant à gauche.
     */
    public void moveGauche() {
        if (courant.getGauche() != null) {
            courant = courant.getGauche();
        }
    }

    /**
     * Deplace le noeud courant vers le devant.
     */
    public void moveDevant() {
        if (courant.getDevant() != null) {
            courant = courant.getDevant();
        }
    }

    /**
     * Deplace le noeud courant vers le derriere.
     */
    public void moveDerriere() {
        if (courant.getDerriere() != null) {
            courant = courant.getDerriere();
        }
    }

    /**
     * Vérifie si un lieu est bien dans la carte.
     * @param lieu à verifier si il est dans la carte.
     * @return vrai si le lieu est dans la carte
     */
    public boolean dansCarte(final Lieu lieu) {
        if (lieu == null) {
            throw new IllegalArgumentException("Le lieu ne peut pas être null");
        }

        ArrayList<NoeudLieu> lieuxVisites = new ArrayList<NoeudLieu>();
        ArrayList<NoeudLieu> lieuxAVisiter = new ArrayList<NoeudLieu>();
        lieuxAVisiter.add(racine);
        NoeudLieu visited = racine;

        while (!lieuxAVisiter.isEmpty()) {

            if (lieu.getNom().equals(visited.getLieu().getNom())) {
                return true;
            }
            lieuxVisites.add(visited);
            lieuxAVisiter.remove(0);

            if (visited.getDroite() != null &&
                    !lieuxAVisiter.contains(visited.getDroite()) &&
                    !lieuxVisites.contains(visited.getDroite())) {
                lieuxAVisiter.add(visited.getDroite());
            }
            if (visited.getGauche() != null &&
                    !lieuxAVisiter.contains(visited.getGauche()) &&
                    !lieuxVisites.contains(visited.getGauche())) {
                lieuxAVisiter.add(visited.getGauche());
            }
            if (visited.getDevant() != null &&
                    !lieuxAVisiter.contains(visited.getDevant()) &&
                    !lieuxVisites.contains(visited.getDevant())) {
                lieuxAVisiter.add(visited.getDevant());
            }
            if (visited.getDerriere() != null &&
                    !lieuxAVisiter.contains(visited.getDerriere()) &&
                    !lieuxVisites.contains(visited.getDerriere())) {
                lieuxAVisiter.add(visited.getDerriere());
            }
            if (!lieuxAVisiter.isEmpty()) {
                visited = lieuxAVisiter.get(0);
            }
        }
        return false;
    }

    /**
     * Donne le noeud associer au lieu
     * @param lieu à verifier si il est dans la carte.
     * @return le noeud est dans la carte et sinon null
     */
    public NoeudLieu lieuToNoeud(final Lieu lieu) {
        if (lieu == null) {
            throw new IllegalArgumentException("Le lieu ne peut pas être null");
        }

        ArrayList<NoeudLieu> lieuxVisites = new ArrayList<NoeudLieu>();
        ArrayList<NoeudLieu> lieuxAVisiter = new ArrayList<NoeudLieu>();
        lieuxAVisiter.add(racine);
        NoeudLieu visited = racine;

        while (!lieuxAVisiter.isEmpty()) {

            if (lieu.getNom().equals(visited.getLieu().getNom())) {
                return visited;
            }
            lieuxVisites.add(visited);
            lieuxAVisiter.remove(0);

            if (visited.getDroite() != null &&
                    !lieuxAVisiter.contains(visited.getDroite()) &&
                    !lieuxVisites.contains(visited.getDroite())) {
                lieuxAVisiter.add(visited.getDroite());
            }
            if (visited.getGauche() != null &&
                    !lieuxAVisiter.contains(visited.getGauche()) &&
                    !lieuxVisites.contains(visited.getGauche())) {
                lieuxAVisiter.add(visited.getGauche());
            }
            if (visited.getDevant() != null &&
                    !lieuxAVisiter.contains(visited.getDevant()) &&
                    !lieuxVisites.contains(visited.getDevant())) {
                lieuxAVisiter.add(visited.getDevant());
            }
            if (visited.getDerriere() != null &&
                    !lieuxAVisiter.contains(visited.getDerriere()) &&
                    !lieuxVisites.contains(visited.getDerriere())) {
                lieuxAVisiter.add(visited.getDerriere());
            }
            if (!lieuxAVisiter.isEmpty()) {
                visited = lieuxAVisiter.get(0);
            }
        }
        return null;
    }

    /**
     * Ajoute un nouveau noeud à droite du noeud courant avec le lieu comme argument.
     * @param lieu à verifier si il est dans la carte
     */
    public void createNoeudDroite(Lieu lieu) {
        if (lieu == null) {
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }
        if (courant.getDroite() != null) {
            throw new NoeudDejaPresent("le noeud droit existe déjà");
        }
        if (dansCarte(lieu)) {
            throw new LieuDejaPresent("Le lieu " + lieu + " a déjà été ajouter à la carte");
        }
        NoeudLieu node = new NoeudLieu(lieu);
        courant.ajouterDroite(node);
        node.ajouterGauche(courant);
        lieu.nombreLieux++;
    }

    /**
     * Ajoute un nouveau noeud à gauche du noeud courant avec le lieu comme argument.
     * @param lieu à verifier si il est dans la carte
     */
    public void createNoeudGauche(Lieu lieu) {
        if (lieu == null) {
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }
        if (courant.getGauche() != null) {
            throw new NoeudDejaPresent("le noeud gauche existe déjà");
        }
        if (dansCarte(lieu)) {
            throw new LieuDejaPresent("Le lieu " + lieu + " a déjà été ajouter à la carte");
        }
        NoeudLieu node = new NoeudLieu(lieu);
        courant.ajouterGauche(node);
        node.ajouterDroite(courant);
        lieu.nombreLieux++;
    }

    /**
     * Ajoute un nouveau noeud à devant du noeud courant avec le lieu comme argument.
     * @param lieu à verifier si il est dans la carte
     */
    public void createNoeudDevant(Lieu lieu) {
        if (lieu == null) {
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }
        if (courant.getDevant() != null) {
            throw new NoeudDejaPresent("le noeud de devant existe déjà");
        }
        if (dansCarte(lieu)) {
            throw new LieuDejaPresent("Le lieu " + lieu + " a déjà été ajouter à la carte");
        }
        NoeudLieu node = new NoeudLieu(lieu);
        courant.ajouterDevant(node);
        node.ajouterDerriere(courant);
        lieu.nombreLieux++;
    }

    /**
     * Ajoute un nouveau noeud à derriere du noeud courant avec le lieu comme argument.
     * @param lieu à verifier si il est dans la carte
     */
    public void createNoeudDerriere(Lieu lieu) {
        if (lieu == null) {
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }
        if (courant.getDerriere() != null) {
            throw new NoeudDejaPresent("le noeud de derriere existe déjà");
        }
        if (dansCarte(lieu)) {
            throw new LieuDejaPresent("Le lieu " + lieu + " a déjà été ajouter à la carte");
        }
        NoeudLieu node = new NoeudLieu(lieu);
        courant.ajouterDerriere(node);
        node.ajouterDevant(courant);
        lieu.nombreLieux++;
    }

    /**
     * Ajoute un lien à droite du noeud courant et donc à gauche du noeud en memoire
     */
    public void creerLienDroite() {
        NoeudLieu node = memoire;
        if (node == null) {
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }
        if (courant.getDroite() != null) {
            throw new NoeudDejaPresent("le noeud droit existe déjà");
        }
        if (node.getGauche() != null) {
            throw new NoeudDejaPresent("le noeud gauche existe déjà");
        }
        courant.ajouterDroite(node);
        node.ajouterGauche(courant);
    }

    /**
     * Ajoute un lien à gauche du noeud courant et donc à droite du noeud en memoire
     */
    public void creerLienGauche() {
        NoeudLieu node = memoire;
        if (node == null) {
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }
        if (node.getDroite() != null) {
            throw new NoeudDejaPresent("le noeud droit existe déjà");
        }
        if (courant.getGauche() != null) {
            throw new NoeudDejaPresent("le noeud gaucheexiste déjà");
        }
        courant.ajouterGauche(node);
        node.ajouterDroite(courant);
    }

    /**
     * Ajoute un lien devant le noeud courant et donc derriere le noeud en memoire
     */
    public void creerLienDevant() {
        NoeudLieu node = memoire;
        if (node == null) {
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }
        if (courant.getDevant() != null) {
            throw new NoeudDejaPresent("le noeud de devant existe déjà");
        }
        if (node.getDerriere() != null) {
            throw new NoeudDejaPresent("le noeud de derriere existe déjà");
        }
        courant.ajouterDevant(node);
        node.ajouterDerriere(courant);
    }

    /**
     * Ajoute un lien derriere le noeud courant et donc devant le noeud en memoire
     */
    public void creerLienDerriere() {
        NoeudLieu node = memoire;
        if (node == null) {
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }
        if (courant.getDerriere() != null) {
            throw new NoeudDejaPresent("le noeud de devant existe déjà");
        }
        if (node.getDevant() != null) {
            throw new NoeudDejaPresent("le noeud de derriere existe déjà");
        }
        courant.ajouterDerriere(node);
        node.ajouterDevant(courant);
    }

    /**
     * Memorize le noeud courant
     */
    public void setMemoire() {
        memoire=courant;
    }

    /**
     * Ajout d'un objet au noeud courant.
     * @param string nom de l'objet
     */
    public void addObjet(String string){

        Objet objet = objetsExistants.get(string);
        if (objet == null){
            objet = new Objet(string);
            objetsExistants.put(string,objet);
        }
        courant.getLieu().addObjet(objet);
        objet.addLieu(courant.getLieu());

    }

    public void initCarte(){

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
        addObjectMap(distributeur);
        addObjectMap(extincteur);
        addObjectMap(plan);
        addObjectMap(alarme);
        addObjectMap(poubelle);
        addObjectMap(sortie);
        addObjectMap(fontaine);
        addObjectMap(tableau);
        addObjectMap(tv);
        addObjectMap(wifi);
        addObjectMap(a);
        addObjectMap(b);
        addObjectMap(c);
        addObjectMap(d);
        addObjectMap(e);
        addObjectMap(ascenseur);
        addObjectMap(lecteur);
        addObjectMap(detecteur);
        addObjectMap(grille);
        addObjectMap(fleche);
        addObjectMap(portecf);
        addObjectMap(vitrine);
        addObjectMap(eclair);
        addObjectMap(portee);
        addObjectMap(radiateur);
        addObjectMap(homme);
        addObjectMap(femme);
        addObjectMap(porteb);

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
        setOrigine(F1);
        setMemoire();
        createNoeudGauche(B1);
        moveGauche();
        createNoeudDevant(B2);
        moveDevant();
        createNoeudDevant(B3);
        moveDevant();
        createNoeudDevant(B4);
        moveDevant();
        createNoeudDevant(B5);
        moveDevant();
        createNoeudDevant(B6);
        moveDevant();
        createNoeudDroite(D1);
        moveDroite();
        createNoeudDroite(D2);
        moveDroite();
        createNoeudDroite(D3);
        moveDroite();
        createNoeudDroite(A5);
        moveDroite();
        createNoeudDerriere(A4);
        moveDerriere();
        createNoeudDerriere(A3);
        moveDerriere();
        createNoeudDerriere(A2);
        moveDerriere();
        createNoeudDerriere(A1);
        moveDerriere();
        creerLienGauche();
        moveDevant();
        moveDevant();
        moveDevant();
        moveDevant();
        setMemoire();
        moveDerriere();
        moveDerriere();
        moveDerriere();
        moveDerriere();
        createNoeudDroite(E1);
        moveDroite();
        createNoeudDroite(E2);
        moveDroite();
        createNoeudDevant(E3);
        moveDevant();
        createNoeudDevant(E4);
        moveDevant();
        createNoeudDevant(E5);
        moveDevant();
        createNoeudDevant(E6);
        moveDevant();
        createNoeudGauche(C3);
        moveGauche();
        createNoeudGauche(C2);
        moveGauche();
        createNoeudGauche(C1);
        moveGauche();
        creerLienGauche();



    }

    public void saveCarte(String fileName) throws IOException {
        memoire = racine;
        courant = racine;

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(this);
        oo.close();

        ByteBuffer buffer = ByteBuffer.allocate(bo.size());
        buffer.put(bo.toByteArray());
        buffer.flip();
        bo.close();

        FileOutputStream fout = new FileOutputStream(fileName);
        FileChannel fcout =  fout.getChannel();

        ByteBuffer intBuffer = ByteBuffer.allocate(Integer.SIZE/Byte.SIZE);
        intBuffer.putInt(bo.size());
        intBuffer.flip();

        fcout.write(intBuffer);
        fcout.write(buffer);
        fcout.close();
        fout.close();
        oo.close();
    }

    public static GestionCarte loadCarte(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(fileName);
        FileChannel fcin = fin.getChannel();
        ByteBuffer intBuffer = ByteBuffer.allocate(Integer.SIZE/Byte.SIZE);
        fcin.read(intBuffer);
        intBuffer.flip();
        int sizeObject = intBuffer.getInt();

        ByteBuffer buffer = ByteBuffer.allocate(sizeObject);
        fcin.read(buffer);
        buffer.flip();
        fcin.close();
        fin.close();

        ByteArrayInputStream bi = new ByteArrayInputStream(buffer.array());
        ObjectInputStream oi = new ObjectInputStream(bi);

        GestionCarte carte = (GestionCarte) oi.readObject();
        oi.close();
        bi.close();
        return carte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GestionCarte that = (GestionCarte) o;
        return Objects.equals(racine, that.racine) &&
                Objects.equals(objetsExistants, that.objetsExistants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(racine, objetsExistants);
    }

    @Override
    public String toString() {
        return "GestionCarte{" +
                "racine=" + racine +
                ", courant=" + courant +
                ", memoire=" + memoire +
                ", objetsExistants=" + objetsExistants +
                '}';
    }
}
