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
}
