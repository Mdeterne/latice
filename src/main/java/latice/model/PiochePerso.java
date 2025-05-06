package latice.model;

import java.util.ArrayList;
import java.util.Collections;

public class PiochePerso extends Pioche{
	
	public PiochePerso() {
		super();
	}
	
	public void remplirPiochePerso(Pioche piochePrincipal) {
		int i;
		int moitierPioche = 36;
		for (i=0; i<moitierPioche ;i++) {
			Jeton jeton = piochePrincipal.piocher();
			this.pioche.add(jeton);
		}
		Collections.shuffle(pioche);
	}
	
	
}
