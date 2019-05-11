package com.ilbod.detection.carte;

import com.ilbod.detection.Exception.MemeLieu;

/**
 * Classe représentant les noeuds dans le graphe de la carte du bâtiment.
 */
public class NoeudLieu {
    private Lieu lieu;
    private NoeudLieu devant;
    private NoeudLieu derriere;
    private NoeudLieu droite;
    private NoeudLieu gauche;

    /**
     * Construit un noeud de la carte virtuelle.
     * @param lieu lieu au centre du noeud.
     */
    public NoeudLieu(Lieu lieu){

        if (lieu == null) {
            throw new IllegalArgumentException("Le lieu du noeud ne peut être null");
        }

        this.lieu = lieu;
        devant = null;
        derriere = null;
        droite = null;
        gauche = null;

        assert(invariant());
    }

    @Override
    public String toString() {
        return "NoeudLieu{" +
                "lieu=" + lieu +
                '}';
    }

    /**
     * Remplit le noeud de droite.
     * @param noeud noeud que l'on souhaite ajouter à droite.
     */
    public void ajouterDroite(final NoeudLieu noeud) throws MemeLieu {

        if (noeud == null) {
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }
        if (this.lieu == noeud.lieu){
            throw new MemeLieu("Deux noeuds adjacents ne peuvent pas avoir le même lieu.");
        }

        this.droite = noeud;
        assert(invariant());
    }

    /**
     * Remplit le noeud de gauche.
     * @param noeud noeud que l'on souhaite ajouter à gauche.
     */
    public void ajouterGauche(final NoeudLieu noeud) throws MemeLieu {

        if (noeud == null) {
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }
        if (this.lieu == noeud.lieu){
            throw new MemeLieu("Deux noeuds adjacents ne peuvent pas avoir le même lieu.");
        }

        this.gauche = noeud;
        assert(invariant());
    }

    /**
     * Remplit le noeud de devant.
     * @param noeud noeud que l'on souhaite ajouter devant.
     */
    public void ajouterDevant(final NoeudLieu noeud) throws MemeLieu {

        if (noeud == null) {
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }
        if (this.lieu == noeud.lieu){
            throw new MemeLieu("Deux noeuds adjacents ne peuvent pas avoir le même lieu.");
        }

        this.devant = noeud;
        assert(invariant());

    }

    /**
     * Remplit le noeud de derrière.
     * @param noeud noeud que l'on souhaite ajouter derrière.
     */
    public void ajouterDerriere(final NoeudLieu noeud)throws MemeLieu {

        if (noeud == null) {
            throw new IllegalArgumentException("Le noeud ne peut pas être null");
        }
        if (this.lieu == noeud.lieu){
            throw new MemeLieu("Deux noeuds adjacents ne peuvent pas avoir le même lieu.");
        }

        this.derriere = noeud;
        assert(invariant());
    }

    /**
     * Permet de récupérer le lieu du noeud.
     * @return renvoit le lieu du noeud.
     */
    public Lieu getLieu (){ return lieu;}

    /**
     * condition qui doit être vérifier dans toute la vie de l'objet.
     * @return renvoit true si l'invariant est vérifié et false sinon.
     */
    public boolean invariant(){
        return lieu != null;
    }

    /**
     * getter renvoyant le noeud de devant.
     * @return rencoit le noeud de devant
     */
    protected NoeudLieu getDevant() {
        return devant;
    }

    /**
     * getter renvoyant le noeud de derriere.
     * @return rencoit le noeud de derriere
     */
    protected NoeudLieu getDerriere() {
        return derriere;
    }

    /**
     * getter renvoyant le noeud de droite.
     * @return rencoit le noeud de droite
     */
    protected NoeudLieu getDroite() {
        return droite;
    }

    /**
     * getter renvoyant le noeud de gauche.
     * @return rencoit le noeud de gauche
     */
    protected NoeudLieu getGauche() {
        return gauche;
    }


}
