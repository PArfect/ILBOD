package com.ilbod.detection.carte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;


/**
 * Classe permettant de trouver le plus court chemin
 */
public class BFS {
	/**
	 * liste contenant l'integralite des noeuds de la carte
	 */
   private ArrayList<NoeudLieu> listeNoeud;
   /**
    * liste contenant les noeuds visites pendant le parcours du graphe
    */
   private ArrayList<NoeudLieu> pere;
   /**
    * Noeud de depart
    */
   private NoeudLieu depart;
   /**
    * Noeud de destination
    */
   private NoeudLieu dest;
   /**
    * Liste contenant le chemin � suivre
    */
   private ArrayList<NoeudLieu> chemin;
   
   /**
    * Construit l'algo de plus court chemin
    */
   public BFS(){
       listeNoeud = new ArrayList<NoeudLieu>();
       pere = new ArrayList<NoeudLieu>();
       chemin = new ArrayList<NoeudLieu>();

   }

   /**
    * Trouve le plus court chemin � la destination selon l'algorithme de BFS.
    */
   private void trouverChemin() {
	   Objects.requireNonNull(dest, "dest est nulle");
	   Objects.requireNonNull(depart, "depart est nulle");

	   listeNoeud.add(depart);

	   HashMap<NoeudLieu, Boolean> visited = new HashMap<NoeudLieu, Boolean>();
	   LinkedList<NoeudLieu> queue = new LinkedList<NoeudLieu>();
	   Stack<NoeudLieu> pathStack = new Stack<NoeudLieu>();
	   
	   queue.add(listeNoeud.get(0));
	   pathStack.add(listeNoeud.get(0));
	   visited.put(depart, true);
	   
	   while (!queue.isEmpty()) {
		  NoeudLieu cour = queue.poll();
		  neighbors(cour);
		  
		  for (NoeudLieu noeud : pere) {
			  if (!visited.containsKey(noeud)) {
				  
				  queue.add(noeud);
				  visited.put(noeud, true);
				  pathStack.add(noeud);
				  
				  if (cour.equals(dest))
					  break;
			  }
		  }
	   }
	   NoeudLieu currentSrc = dest;
	   chemin.add(dest);
	   while(!pathStack.isEmpty()) {
		   NoeudLieu node = pathStack.pop();
		   if (isNeighbor(currentSrc, node)) {
			   chemin.add(node);
			   currentSrc = node;
			   
			   if (node.equals(depart))
				   break;
		   }   
	   }   
   }
   
   
   /**
    * D�couvre les voisins du param�tres.
    * @param noeud dont on veut les voisins
    */
   private void neighbors(NoeudLieu noeud) {
	   if (noeud.getDevant() != null && !pere.contains(noeud.getDevant())) {
		   pere.add(noeud.getDevant());
	   }
	 
	   if (noeud.getDroite() != null && !pere.contains(noeud.getDroite())) {
		   pere.add(noeud.getDroite());
	   }
	   if (noeud.getGauche() != null && !pere.contains(noeud.getGauche())) {
		   pere.add(noeud.getGauche());
	   }
	   if (noeud.getDerriere() != null && !pere.contains(noeud.getDerriere())) {
		   pere.add(noeud.getDerriere());
	   }
   }
   /**
    * Verifie si un noeud est bien voisin d'un autre.
    * @param src dont veut v�rifier le voisin
    * @param node voisin en question
    * @return vrai si le noeud est bien voisin
    */
   private boolean isNeighbor(NoeudLieu src, NoeudLieu node) {
	   return (node.equals(src.getDevant()) || (node.equals(src.getDerriere()) ||  node.equals(src.getDroite()) || node.equals(src.getGauche())));
   }

	/**
	 * trouve le plus court chemin entre depart et dest
	 * @param dest noeud destination
	 * @param depart noeud source
	 * @return chemin le plus court chemin
	 */
	public ArrayList<NoeudLieu> getChemin(NoeudLieu depart, NoeudLieu dest) {
		this.depart = depart;
		this.dest = dest;
		trouverChemin();
		return chemin;
	}
}
