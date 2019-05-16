package com.ilbod.detection.carte;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

import com.ilbod.detection.Exception.ChaineDeCaractereNullOuVide;
import com.ilbod.detection.Exception.ObjetDejaPresent;

/**
 * Classe référençant un lieu.
 * @author Arthur Oulmi
 */
public class Lieu implements Serializable {
    /**
     * Serialisation.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Nom du lieu.
     **/
    String nom;
    /**
     * Liste des objets que référence le lieu.
     **/
    HashMap<String, Objet> objets;

    /**
     * Constructeur du lieu.
     * @param nom nom du lieu
     * @throws ChaineDeCaractereNullOuVide releve une exception si un string est null ou vide
     **/
    public Lieu(String nom) throws ChaineDeCaractereNullOuVide {
        if (nom == "" || nom == null) {
            throw new ChaineDeCaractereNullOuVide("nom de l'objet null ou vide");
        }
        this.nom = nom;
        this.objets = new HashMap<String, Objet>();

        assert(invariant());
    }

    /**
     * Ajoute un objet au dictionnaire d'objet.
     * @param objet objet à ajouter au lieu
     **/
    public void addObjet(Objet objet) throws ObjetDejaPresent {

        if (objet == null){
            throw new IllegalArgumentException("L'objet à ajouter ne peut être null.");
        }
        if (objets.containsValue(objet)) {
            throw new ObjetDejaPresent("L'objet est déjà dans le lieu");
        }
        objet.addLieu(this);
        objets.put(objet.getNom(),objet);
        assert(invariant());
    }

    /**
     * Getter nom.
     * @return nom du lieu
     */
    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Lieu{" +
                "nom='" + nom + '\'' +
                ", objets=" + objets +
                '}';
    }

    /**
     * Getter objets.
      * @return renvoit objets présent dans le lieu
     */
    public HashMap<String, Objet> getObjets(){
        return objets;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lieu lieu = (Lieu) o;
        return Objects.equals(nom, lieu.nom) &&
                Objects.equals(objets, lieu.objets);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(nom, objets);
    }

    /**
     * condition qui doit être vérifier dans toute la vie de l'objet.
     * @return renvoit true si l'invariant est vérifié et false sinon.
     */
    public boolean invariant(){
        return nom!=null & nom!="" & objets != null;
    }
}
