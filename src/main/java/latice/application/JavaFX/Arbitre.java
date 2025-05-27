package latice.application.JavaFX;


import java.util.ArrayList;
import java.util.Random;

import latice.model.Joueur;
import latice.model.PiochePrincipal;
import latice.model.Tuile;
import latice.util.exception.PiocheVideException;

public class Arbitre {
	
	Random random = new Random();
	
	private Joueur joueur1;
	private Joueur joueur2;
	
	private PiochePrincipal piochePrincipal = new PiochePrincipal();
	
	private Boolean tourJoueur;
	
	
	public void initialiser(String nomJoueur1, String nomJoueur2) {
		joueur1 = new Joueur(nomJoueur1);
        joueur2 = new Joueur(nomJoueur2);
        
        try {
			joueur1.remplirPiochePersonelle(piochePrincipal);
			joueur2.remplirPiochePersonelle(piochePrincipal);
			joueur1.initialiserRack();
			joueur2.initialiserRack();
		} catch (PiocheVideException e) {
			System.out.println(e.getMessage());
		}
		
        tourJoueur = random.nextBoolean(); 
	}
	
	public String nomJoueur1() {
		return joueur1.nom();
	}
	
	public String nomJoueur2() {
		return joueur2.nom();
	}
	
	public ArrayList<Tuile> RackJoueur1() {
	    return joueur1.tuilesRack();
	}

	public ArrayList<Tuile> RackJoueur2() {
	    return joueur2.tuilesRack();
	}
	
	public Boolean tourJoueur() {
		return tourJoueur;
	}
	
	public void changerRack() throws PiocheVideException {
		if(tourJoueur) {
			joueur1.echangerRack();
		}
		else {
			joueur2.echangerRack();
		}
	}
	
	public int getActions() {
		if(tourJoueur) {
			return joueur1.actions();
		}
		else {
			return joueur2.actions();
		}
	}
	
	public void retirerAction() {
		if(tourJoueur) {
			joueur1.enleverAction();;
		}
		else {
			joueur2.enleverAction();
		}
	}
	
	public void remplireRack() throws PiocheVideException {
		if(tourJoueur) {
			joueur1.remplirRack();;
		}
		else {
			joueur2.remplirRack();
		}
	}
	
	public void changerTour() {
		Boolean changement;
		if(tourJoueur) {
			changement = true;
		}
		else {
			changement = false;
		}
		if(changement) {
			tourJoueur = false;
			joueur2.réinitialiserActions();
		}
		else {
			tourJoueur = true;
			joueur1.réinitialiserActions();
		}
	}
}
