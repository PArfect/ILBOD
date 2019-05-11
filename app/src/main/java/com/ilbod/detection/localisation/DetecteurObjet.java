package com.ilbod.detection.localisation;

import java.util.HashMap;

import com.ilbod.detection.carte.Objet;

/**
 * Classe responsable de la detection d'objets.
 * @author Arthur Oulmi
 */
public class DetecteurObjet {

    /**
     * Dictionnaire de tous les objets référencés (noms, objet)
     */
    private HashMap<String, Objet> objets;

    /**
     * Constructeur
     */
    public DetecteurObjet() {
        objets = new HashMap<String, Objet>();
    }

    /**
     * Permet de récupérer les objets détectés par le réseau de neuronnes
     * @return renvoit le HashMap des objets.
     */
    public HashMap<String, Objet> getObjets(){
        return objets;
    }

}
