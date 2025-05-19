package latice.model;

import java.util.Collections;


public class PiochePrincipal extends Pioche{
	
	
	public PiochePrincipal() {
		super();
		for (Couleur couleur : Couleur.values()) {
	        for (Symbole symbole : Symbole.values()) {
	        	pioche.add(new Tuile(couleur, symbole));
	        	pioche.add(new Tuile(couleur, symbole)); 
	        }
	    }
		Collections.shuffle(pioche);
	}
}
