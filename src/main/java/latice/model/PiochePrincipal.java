package latice.model;

import java.util.ArrayList;


public class PiochePrincipal extends Pioche{
	
	
	public PiochePrincipal() {
		super();
		for (Couleur couleur : Couleur.values()) {
	        for (Symbole symbole : Symbole.values()) {
	        	pioche.add(new Jeton(couleur, symbole));
	        	pioche.add(new Jeton(couleur, symbole)); 
	        }
	    }
	}
}
