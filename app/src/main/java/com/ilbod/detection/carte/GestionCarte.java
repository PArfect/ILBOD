package com.ilbod.detection.carte;

import java.util.ArrayList;
import java.util.HashMap;

import com.ilbod.detection.Exception.LieuDejaPresent;
import com.ilbod.detection.Exception.NoeudDejaPresent;

/**
 * Classe permettant de gérer les différentes fonctionnalités s'appliquant la carte virtuelle.
 */
public class GestionCarte {

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
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }

        ArrayList<NoeudLieu> lieuxVisites = new ArrayList<NoeudLieu>();
        ArrayList<NoeudLieu> lieuxAVisiter = new ArrayList<NoeudLieu>();
        lieuxAVisiter.add(racine);
        NoeudLieu visited = racine;

        while (!lieuxAVisiter.isEmpty()) {

            if (lieu == visited.getLieu()) {
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
    }

    /**
     * Ajoute un lien à droite du noeud courant et donc à gauche du noeud en argument
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
     * Ajoute un lien à gauche du noeud courant et donc à droite du noeud en argument
     */
    public void creerLienGauche() {
        NoeudLieu node = memoire;
        if (node == null) {
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }
        if (courant.getDroite() != null) {
            throw new NoeudDejaPresent("le noeud gauche existe déjà");
        }
        if (node.getGauche() != null) {
            throw new NoeudDejaPresent("le noeud droite existe déjà");
        }
        courant.ajouterGauche(node);
        node.ajouterDroite(courant);
    }

    /**
     * Ajoute un lien devant le noeud courant et donc derriere le noeud en argument
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
     * Ajoute un lien derriere le noeud courant et donc devant le noeud en argument
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

        createNoeudDevant(new Lieu("cuisine"));
        createNoeudDerriere(new Lieu("salon"));
        moveDerriere();
        assert(courant.getLieu().getNom()=="salon");
        addObjet("chair");
        moveDevant();
        moveDevant();
        assert(courant.getLieu().getNom()=="cuisine");
        addObjet("oven");
        addObjet("refrigerator");



    }

}
