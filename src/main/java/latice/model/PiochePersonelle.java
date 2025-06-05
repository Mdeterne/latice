package latice.model;

import java.util.Collections;

import latice.util.exception.PiocheVideException;

public class PiochePersonelle extends Pioche {

	public static int tailleMaxPioche = 36;

	public PiochePersonelle() {
		super();
	}

	public void remplirPiochePerso(Pioche piochePrincipal) throws PiocheVideException {
		for (int i = 1; i <= tailleMaxPioche; i++) {
			Tuile tuile = piochePrincipal.piocher();
			this.pioche.add(tuile);
		}
		Collections.shuffle(pioche);
	}

	public void definir3Joueur() {
		tailleMaxPioche = 24;
	}

	public void definir4Joueur() {
		tailleMaxPioche = 18;
	}

	public static int getTailleMax() {
		return tailleMaxPioche;
	}
}
