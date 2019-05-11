package com.ilbod.detection.localisation;

import com.ilbod.detection.carte.Lieu;

public class Localisation {
    /**
     * Lieu courant de l'utilisateur
     */
    Lieu lieu;

    public Localisation(Lieu lieu) {
        lieu = lieu;
    }

    public Localisation(String string) {
        lieu = new Lieu(string);
    }
}
