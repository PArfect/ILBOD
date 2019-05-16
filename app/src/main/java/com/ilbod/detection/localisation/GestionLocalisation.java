package com.ilbod.detection.localisation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.ilbod.detection.carte.Lieu;
import com.ilbod.detection.carte.Objet;

/**
 * Classe permettant de gérer les différentes fonctionnalités permettant de se localiser.
 */
public class GestionLocalisation {
    /**
     * Gestionnaire de la détection des objets.
     */
    private HashMap<String, Objet> objetsDetectes;

    /**
     * Lieux probables de la localisation courante.
     */
    private ArrayList<LieuProba> lieuxProbables;

    /**
     * Lieu détecté par l'application.
     */
    private Lieu lieuTrouve;
    /**
     * Booléen qui permet de savoir si lieuTrouve a ete mis  jour
     */
    private boolean lieuTrouveUpdated;

    public GestionLocalisation(){
        lieuTrouve = new Lieu("aucun");
        objetsDetectes = new HashMap<String, Objet>();
        lieuxProbables = new ArrayList<>();
        lieuTrouveUpdated = false;

        assert(invariant());
    }

    public void resetLocalisation(){
        objetsDetectes = new HashMap<String, Objet>();
        lieuxProbables = new ArrayList<>();
        lieuTrouve = new Lieu("aucun");
        lieuTrouveUpdated = true;

        assert(invariant());
    }

    public void ajoutObjetDetecte(Objet objet){
        objetsDetectes.put(objet.getNom(),objet);
    }

    /**
     * Met à jour la liste des lieux probables selon les objets détectés.
     */
    public void miseAJourLieuxProbables(){

        Objects.requireNonNull(lieuxProbables,"La liste des objets détectées ne peut être nulle");
        Objects.requireNonNull(objetsDetectes,"La liste des lieux probables ne peut être nulle");

        LieuProba lieutrouve = null; //Un des lieux correspondant a un objet détecté.
        HashMap<String, Lieu> lieux = null; //Ensemble des lieux où se situe un objet détecté.
        int indice; //Position du lieu où se trouve un objet détecté.
        int newindice; //Nouvelle position du lieu après incrémentation de son occurrence.

        for (Map.Entry<String, Objet> objet : objetsDetectes.entrySet()){
            lieux = objet.getValue().getLieux();
            for (Map.Entry<String, Lieu> lieu : lieux.entrySet()){

                lieutrouve = new LieuProba(lieu.getValue());
                indice = lieuxProbables.indexOf(lieutrouve);
                newindice = 0;

                if (indice == -1){
                    lieuxProbables.add(lieutrouve);
                }
                else{
                    lieutrouve= lieuxProbables.remove(indice);
                    lieutrouve.incrementOccurrence();
                    for(newindice = indice - 1;
                        (newindice!=0) && (lieuxProbables.get(newindice).getOccurrence()<lieutrouve.getOccurrence());
                        newindice--){
                    }
                    lieuxProbables.add(newindice,lieutrouve);
                }

            }
        }

        assert(invariant());

    }

    /**
     * Recherche du lieu le plus probable (celui avec le plus d'objets reconnus)
     */
     public void lieuPlusProbable(){
        if(lieuxProbables.size()!=0){
            LieuProba max = lieuxProbables.get(0);

            for (LieuProba lieuProba : lieuxProbables) {
                if(max.getOccurrence() < lieuProba.getOccurrence()){
                    max = lieuProba;
                }
            }

            lieuTrouve = max.getLieu();
            lieuTrouveUpdated = true;
        }

        assert(invariant());
    }

    public Lieu getLieuTrouve(){ return lieuTrouve; }

    public boolean getLieuTrouveUpdated() {return lieuTrouveUpdated;}

    public HashMap<String, Objet> getObjetsDetectes() {return objetsDetectes;}

    public ArrayList<LieuProba> getLieuxProbables() {return lieuxProbables;}

    public void setLieuTrouveUpdatedFalse() { lieuTrouveUpdated = false;}
    

    private boolean invariant(){
        return lieuxProbables != null && objetsDetectes != null && lieuTrouve != null;
    }

}
