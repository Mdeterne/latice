package latice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import latice.model.Couleur;
import latice.model.PiochePrincipal;
import latice.model.Symbole;
import latice.model.Tuile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PiochePrincipalTest {

	@Test
	void testConstructeur() {
		PiochePrincipal pioche = new PiochePrincipal();

		assertNotNull(pioche);
		assertFalse(pioche.estVide());

		int tailleAttendue = Couleur.values().length * Symbole.values().length * 2;
		assertEquals(tailleAttendue, pioche.taille());

		for (Couleur couleur : Couleur.values()) {
			for (Symbole symbole : Symbole.values()) {
				long nombreTuiles = pioche.pioche().stream()
						.filter(t -> t.couleur() == couleur && t.symbole() == symbole).count();
				assertEquals(2, nombreTuiles, "Il devrait y avoir 2 tuiles pour chaque combinaison couleur/symbole");
			}
		}

		boolean estMelange = false;
		List<Tuile> tuiles = pioche.pioche();
		for (int i = 0; i < tuiles.size() - 1; i++) {
			if (!tuiles.get(i).equals(tuiles.get(i + 1))) {
				estMelange = true;
				break;
			}
		}
		assertTrue(estMelange, "La pioche devrait être mélangée");
	}

	@Test
	void testContenuInitial() {
		PiochePrincipal pioche = new PiochePrincipal();

		assertEquals(Couleur.values().length * Symbole.values().length * 2, pioche.taille());

		for (Couleur couleur : Couleur.values()) {
			for (Symbole symbole : Symbole.values()) {
				int compteur = 0;
				for (Tuile tuile : pioche.pioche()) {
					if (tuile.couleur() == couleur && tuile.symbole() == symbole) {
						compteur++;
					}
				}
				assertEquals(2, compteur, "Il devrait y avoir 2 tuiles pour " + couleur + "/" + symbole);
			}
		}
	}

	private int comparerTuiles(Tuile t1, Tuile t2) {
		int cmpCouleur = t1.couleur().compareTo(t2.couleur());
		if (cmpCouleur != 0)
			return cmpCouleur;
		return t1.symbole().compareTo(t2.symbole());
	}
}