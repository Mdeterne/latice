package latice.model;

import java.util.Collections;

import latice.util.exception.PiocheVideException;

public class PiochePersonelle extends Pioche{
	
	private static final int TAILLEMAX = 36;

	public PiochePersonelle() {
		super();
	}
	
	public void remplirPiochePerso(Pioche piochePrincipal) throws PiocheVideException {
		for (int i=1; i<=TAILLEMAX ;i++) {
			Tuile tuile = piochePrincipal.piocher();
			this.pioche.add(tuile);
		}
		Collections.shuffle(pioche);
	}

}
