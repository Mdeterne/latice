package latice.model;


import java.util.ArrayList;
import java.util.List;

import latice.util.exception.ActionsInsuffisanteException;
import latice.util.exception.PiocheVideException;
import latice.util.exception.PointInsuffisantException;


public class Joueur {

	private final String nom;
	private int point;
	private Rack rack;
	private PiochePersonelle piochePersonelle;
	private int actions;
	private int nombreTuilesPosées = 0;
	
	public Joueur(String nom) {
		this.nom = nom;
		this.point = 0;
		this.rack = new Rack();
		this.actions = 1;
		this.piochePersonelle = new PiochePersonelle();
	}
	
	public void retirerTuile(Tuile tuile) {
		rack.retirerTuile(tuile);
	}	
	
	public String echangerRack() throws PiocheVideException {
		List<Tuile> ancienstuiles = rack.vider();
		ancienstuiles.forEach(tuile -> piochePersonelle.ajoutertuile(tuile));
		piochePersonelle.mélanger();
		while (rack.afficherTuiles().size() < Rack.TAILLE_MAX && !piochePersonelle.estVide()) {
            rack.ajouterTuile(piochePersonelle.piocher());
        }
		return (this.nom+" à échanger son rack");
	}
	
	public void enleverPoints(int nombrePoints) throws PointInsuffisantException {
		if(point < nombrePoints) {
			throw new PointInsuffisantException("ce joueur n'a pas assez de points");
		}
		else {
			point = point - nombrePoints;
		}
	}

	public void ajouterPoints(int nombresPoints) {
		point = point + nombresPoints;
	}
	
	public void ajoutTuilePosé() {
		nombreTuilesPosées++;
	}
	
	public int getTuilesPosées() {
		return nombreTuilesPosées;
	}
	
	 public String nom() { 
		 return nom;
	}
	 public int point() { 
		 return point; 
	}
	 
	 public Rack rack() { 
		 return rack; 
	}
	 
	 public Rack getRack() {
		    return rack;
		}

	
	public ArrayList<Tuile> tuilesRack() {
		return rack().afficherTuiles();
	}
	
	public String affichertuilesRack() {
		return ""+rack().afficherTuiles();
	}
	
	public void initialiserRack() throws PiocheVideException {
	    rack.vider();
	     
	    for (int i = 0; i < Rack.TAILLE_MAX && !piochePersonelle.estVide(); i++) {
	        Tuile tuile = piochePersonelle.piocher();
	        rack.ajouterTuile(tuile);
	    }
	}
	
	public void remplirRack() throws PiocheVideException {
		int nbTuileARemplir = Rack.TAILLE_MAX-rack.taille();
		for(int i = 0; i < nbTuileARemplir; i++) {
			Tuile tuile = piochePersonelle.piocher();
			rack.ajouterTuile(tuile);
		}
	}
	
	public int taillePiochePersonelle() {
		return piochePersonelle().taille();
	}
	
	public void remplirPiochePersonelle(Pioche piochePrincipal) throws PiocheVideException {
		piochePersonelle().remplirPiochePerso(piochePrincipal);
	}
	 
	public PiochePersonelle piochePersonelle() {
		return piochePersonelle;
	}
	
	public ArrayList<Tuile> afficherPiochePersonelle() {
		return piochePersonelle.pioche();
	}
	
	public int actions() {
		return actions;
	}
	
	public void réinitialiserActions() {
		actions = 1;
	}
	
	public void ajouterUneAction() {
		actions = actions + 1;
	}
	
	public void enleverAction() throws ActionsInsuffisanteException{
		if(actions == 0) {
			throw new ActionsInsuffisanteException("vous n'avez plus d'actions");
		}
		else {
			actions = actions - 1;
		}
	}
	
	public void definir3Joueur() {
		piochePersonelle.definir3Joueur();
	}
	
	public void definir4Joueur() {
		piochePersonelle.definir4Joueur();
	}
	
}
