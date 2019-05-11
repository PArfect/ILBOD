package com.ilbod.detection.Exception;

public class ObjetDejaPresent extends UnsupportedOperationException{

    /**
     * numéro de version pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur.
     *
     * @param message message de l'exeption
     */
    public ObjetDejaPresent(final String message) {
        super(message);
    }
}
