package com.ilbod.detection.Exception;

public class NoeudDejaPresent extends UnsupportedOperationException{

    /**
     * numéro de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur.
     *
     * @param message message de l'exeption
     */
    public NoeudDejaPresent(final String message) {
        super(message);
    }
}

