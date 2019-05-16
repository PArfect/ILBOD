package com.ilbod.detection.carte;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

import com.ilbod.detection.Exception.ChaineDeCaractereNullOuVide;
import com.ilbod.detection.Exception.LieuDejaPresent;

/**
 * Classe référençant un objet détectable.
 * @author Arthur Oulmi
 */
public class Objet implements Serializable {
    /**
     * Serialisation.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Nom de l'objet
     */
    String nom;
    /**
     * Lieux dans lesquel l'objet est référencé
     **/
    HashMap<String, Lieu> lieux;

    /**
     * Constructeur Objet
     * @param nom
     * @throws ChaineDeCaractereNullOuVide releve une exception si un string est null ou vide
     */
    public Objet(String nom) throws ChaineDeCaractereNullOuVide {
        if (nom == "" || nom == null) {
            throw new ChaineDeCaractereNullOuVide("nom de l'objet null ou vide");
        }
        this.nom = nom;
        this.lieux = new HashMap<String, Lieu>();

        assert(invariant());
    }

    /**
     * Ajoute un lieu referençant l'objet.
     * @param lieu lieu à ajouter
     */
    public void addLieu(Lieu lieu) throws LieuDejaPresent {

        if (lieu == null){
            throw new IllegalArgumentException("le lieu à ajouter ne peut être null.");
        }
        if (lieux.containsValue(lieu)){
            throw new LieuDejaPresent("le lieu est déjà dans la liste des lieux");
        }
        lieux.put(lieu.getNom(),lieu);
        assert(invariant());
    }

    /**
     * Getter nom.
     * @return nom de l'objet
     **/
    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Objet{" +
                "nom='" + nom + '\'' +
                '}';
    }

    /**
     * Getter lieux
     * @return lieux où l'objet est présent.
     */
    public HashMap<String, Lieu> getLieux() { return lieux; }

    /**
     * condition qui doit être vérifier dans toute la vie de l'objet.
     * @return renvoit true si l'invariant est vérifié et false sinon.
     */
    public boolean invariant(){
        return nom!=null & nom!="" & lieux != null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objet objet = (Objet) o;
        return Objects.equals(nom, objet.nom);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }


}
