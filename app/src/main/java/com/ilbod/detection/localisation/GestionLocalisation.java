package com.ilbod.detection.localisation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private ArrayList<LieuProba> lieuxprobables;

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
        lieuxprobables = new ArrayList<>();
        lieuTrouveUpdated = false;
    }

    public void resetObjetsDetectes(){
        objetsDetectes = new HashMap<String, Objet>();
        lieuTrouve = new Lieu("aucun");
        lieuTrouveUpdated = true;
    }

    public void ajoutObjetDetecte(Objet objet){
        objetsDetectes.put(objet.getNom(),objet);
    }

    /**
     * Met à jour la liste des lieux probables selon les objets détectés.
     */
    public void miseAJourLieuxProbables(){
        lieuxprobables = new ArrayList<>();

        HashMap<String, Lieu> lieux = null;
        int indice; //Position du lieu où se trouve un objet detecté.
        int newindice; //Nouvelle position du lieu après incrémentation de son occurrence.
        LieuProba lieutrouve = null;

        for (Map.Entry<String, Objet> objet : objetsDetectes.entrySet()){
            lieux = objet.getValue().getLieux();
            for (Map.Entry<String, Lieu> lieu : lieux.entrySet()){

                lieutrouve = new LieuProba(lieu.getValue());
                indice = lieuxprobables.indexOf(lieutrouve);
                newindice = 0;

                if (indice == -1){
                    lieuxprobables.add(lieutrouve);
                }
                else{
                    lieutrouve= lieuxprobables.remove(indice);
                    lieutrouve.incrementOccurrence();
                    for(newindice = indice - 1;
                        (newindice!=0) && (lieuxprobables.get(newindice).getOccurrence()<lieutrouve.getOccurrence());
                        newindice--){
                    }
                    lieuxprobables.add(newindice,lieutrouve);
                }

            }
        }

    }

    /**
     * Recherche du lieu le plus probable (celui avec le plus d'objets reconnus)
     */
     public void lieuPlusProbable(){
        if(lieuxprobables.size()!=0){
            LieuProba max = lieuxprobables.get(0);

            for (LieuProba lieuProba : lieuxprobables) {
                if(max.getOccurrence() < lieuProba.getOccurrence()){
                    max = lieuProba;
                }
            }

            lieuTrouve = max.getLieu();
            lieuTrouveUpdated = true;
        }
    }

    public Lieu getLieuTrouve(){ return lieuTrouve; }

    public boolean getLieuTrouveUpdated() {return lieuTrouveUpdated;}

    public void setLieuTrouveUpdatedFalse() { lieuTrouveUpdated = false;}

}
