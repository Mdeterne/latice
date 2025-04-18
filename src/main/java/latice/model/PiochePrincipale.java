package latice.model;

import java.util.ArrayList;


public class PiochePrincipale extends Pioche{
	
	
	public PiochePrincipale() {
		super();
		for (Couleur couleur : Couleur.values()) {
	        for (Symbole symbole : Symbole.values()) {
	        	pioche.add(new Jeton(couleur, symbole));
	        	pioche.add(new Jeton(couleur, symbole)); 
	        }
	    }
	}
}
