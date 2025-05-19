package latice.model;


import java.util.ArrayList;
import java.util.List;

import latice.test.exception.CaseInaccessibleException;
import latice.test.exception.PiocheVideException;
import latice.test.exception.PointInsuffisantException;


public class Joueur {

	private final String nom;
	private int point;
	private Rack rack;
	private PiochePersonelle piochePersonelle;
	
	public Joueur(String nom) {
		this.nom = nom;
		this.point = 0;
		this.rack = new Rack();
		this.piochePersonelle = new PiochePersonelle();
	}
	
	public void jouer(Plateau plateau, Tuile tuile, Position position) throws CaseInaccessibleException{
		
		plateau.posertuile(tuile, position);
	    rack.retirertuile(tuile);
	    Tuile tuile2;
		try {
			tuile2 = piochePersonelle.piocher();
			rack.ajoutertuile(tuile2);
		} catch (PiocheVideException e) {
			e.printStackTrace();
		}
	    
	}
	
	public Boolean acheter() throws PointInsuffisantException {
		if (point >= 2) {
			point-=2;
			return true;
		}
		throw new PointInsuffisantException("vous n'avez pas assez de point");
	}
		
	
	public String echangerRack() throws PiocheVideException {
		List<Tuile> ancienstuiles = rack.vider();
		ancienstuiles.forEach(tuile -> piochePersonelle.ajoutertuile(tuile));
		piochePersonelle.mélanger();
		while (rack.affichertuiles().size() < Rack.TAILLE_MAX && !piochePersonelle.estVide()) {
            rack.ajoutertuile(piochePersonelle.piocher());
        }
		return (this.nom+" à échanger son rack");
	}
	
	public String passer() {
		return (this.nom+" à passer sont tour");
	}

	public void ajouterPoints(int nombresPoints) {
		point = point + nombresPoints;
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
	 
	public String affichertuilesRack() {
		return ""+rack().affichertuiles();
	}
	
	public void initialiserRack() throws PiocheVideException {
	    rack.vider();
	     
	    for (int i = 0; i < Rack.TAILLE_MAX && !piochePersonelle.estVide(); i++) {
	        Tuile tuile = piochePersonelle.piocher();
	        rack.ajoutertuile(tuile);
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
	
}
