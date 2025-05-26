package latice.application.JavaFX;


import latice.model.Joueur;
import latice.model.PiochePrincipal;
import latice.test.exception.PiocheVideException;

public class Arbitre {

	private Joueur joueur1;
	private Joueur joueur2;
	
	private PiochePrincipal piochePrincipal;
	
	
	public void initialiser(String nomJoueur1, String nomJoueur2) {
		joueur1 = new Joueur(nomJoueur1);
        joueur2 = new Joueur(nomJoueur2);
        
        try {
			joueur1.remplirPiochePersonelle(piochePrincipal);
		} catch (PiocheVideException e) {
			e.printStackTrace();
		}
        try {
			joueur2.remplirPiochePersonelle(piochePrincipal);
		} catch (PiocheVideException e) {
			e.printStackTrace();
		}
        
        try {
			joueur1.initialiserRack();
		} catch (PiocheVideException e) {
			e.printStackTrace();
		}
        try {
			joueur2.initialiserRack();
		} catch (PiocheVideException e) {
			e.printStackTrace();
		}
	}
}
