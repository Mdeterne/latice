package latice.model;

import java.util.ArrayList;
import java.util.Collections;

public class PiochePerso extends Pioche{
	
	public PiochePerso() {
		super();
	}
	
	public void remplirPiochePerso(Pioche piochePrincipal) {
		for (int i=0;i>piochePrincipal.taille()/2;i++) {
			Jeton jeton = piochePrincipal.piocher();
			this.pioche.add(jeton);
		}
		Collections.shuffle(pioche);
	}
	
	
}
