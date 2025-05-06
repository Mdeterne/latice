package latice.model;

import java.util.Collections;

public class PiochePersonelle extends Pioche{
	
	private static final int TAILLEMAX = 36;

	public PiochePersonelle() {
		super();
	}
	
	public void remplirPiochePerso(Pioche piochePrincipal) {
		for (int i=1; i<=TAILLEMAX ;i++) {
			Jeton jeton = piochePrincipal.piocher();
			this.pioche.add(jeton);
		}
		Collections.shuffle(pioche);
	}
	
	
}
