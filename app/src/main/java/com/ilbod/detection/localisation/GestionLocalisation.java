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


    public GestionLocalisation(){
        objetsDetectes = new HashMap<String, Objet>();
        lieuxprobables = new ArrayList<>();
    }

    public void miseAjourObjetsDetectes(HashMap<String, Objet> objetsDetectes){
        this.objetsDetectes = new HashMap<String, Objet>();
        for(HashMap.Entry<String, Objet> entry : objetsDetectes.entrySet()){
            this.objetsDetectes.put(entry.getKey(),entry.getValue());
        }
    }

    /**
     * Met à jour la liste des lieux probables selon les objets détectés.
     */
    public void miseAJourLieuxProbables(){
        lieuxprobables = new ArrayList<>();

        HashMap<String, Lieu> lieux = null;
        int indice;
        LieuProba lieutrouve = null;

        for (Map.Entry<String, Objet> objet : objetsDetectes.entrySet()){
            lieux = objet.getValue().getLieux();
            for (Map.Entry<String, Lieu> lieu : lieux.entrySet()){

                lieutrouve = new LieuProba(lieu.getValue());
                indice = lieuxprobables.indexOf(lieutrouve);

                if (indice == -1){
                    lieuxprobables.add(lieutrouve);
                }
                else{
                    lieuxprobables.get(indice).incrementOccurrence();
                }

            }
        }

    }


    public ArrayList<LieuProba> getLieuxprobables(){
        return lieuxprobables;
    }

    public HashMap<String, Objet> getObjetsDetectes(){
        return objetsDetectes;
    }

    public Lieu lieuPlusProbable(){
        if(lieuxprobables.size()!=0){
            LieuProba max = lieuxprobables.get(0);

            for (LieuProba lieuProba : lieuxprobables) {
                if(max.getOccurrence() < lieuProba.getOccurrence()){
                    max = lieuProba;
                }
            }

            return max.getLieu();
        }
        else{
            return new Lieu("aucun");
        }
    }

}
