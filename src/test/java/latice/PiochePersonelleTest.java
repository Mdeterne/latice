package latice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import latice.model.Couleur;
import latice.model.Pioche;
import latice.model.PiochePersonelle;
import latice.model.Symbole;
import latice.model.Tuile;
import latice.util.exception.PiocheVideException;

class PiochePersonelleTest {

	private PiochePersonelle piochePerso;
	private Pioche piochePrincipal;

	@BeforeEach
	void setUp() {
		piochePerso = new PiochePersonelle();
		piochePrincipal = new Pioche() {
		};
	}

	@Test
	void testConstructeur() {
		assertNotNull(piochePerso);
		assertTrue(piochePerso.estVide());
	}

	@Test
	void testRemplirPiochePerso() throws PiocheVideException {

		for (int i = 0; i < PiochePersonelle.getTailleMax(); i++) {
			piochePrincipal.ajoutertuile(new Tuile(Couleur.values()[i % Couleur.values().length],
					Symbole.values()[i % Symbole.values().length]));
		}

		piochePerso.remplirPiochePerso(piochePrincipal);

		assertEquals(PiochePersonelle.getTailleMax(), piochePerso.taille());
		assertTrue(piochePrincipal.estVide());

		boolean estMelange = false;
		for (int i = 0; i < piochePerso.taille() - 1; i++) {
			if (!piochePerso.pioche().get(i).equals(piochePerso.pioche().get(i + 1))) {
				estMelange = true;
				break;
			}
		}
		assertTrue(estMelange, "La pioche personnelle devrait être mélangée");
	}

	@Test
	void testRemplirPiochePersoAvecPiocheVide() {

		assertThrows(PiocheVideException.class, () -> piochePerso.remplirPiochePerso(piochePrincipal));
	}

	@Test
	void testRemplirPiochePersoAvecPiocheIncomplete() {

		for (int i = 0; i < 20; i++) {
			piochePrincipal.ajoutertuile(new Tuile(Couleur.BLEU, Symbole.DAUPHIN));
		}

		assertThrows(PiocheVideException.class, () -> piochePerso.remplirPiochePerso(piochePrincipal));
		assertEquals(0, piochePerso.taille()); // Ne doit rien ajouter si exception
	}

	@Test
	void testTAILLEMAXConstant() {
		assertEquals(36, PiochePersonelle.getTailleMax());
	}
}