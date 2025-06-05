package latice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import latice.model.Couleur;
import latice.model.Tuile;
import latice.model.Symbole;

class JetonTest {

	@Test
	void testEstCompatibleQuandMemeSymbole() {
		Tuile jeton = new Tuile(Couleur.BLEU, Symbole.DAUPHIN);
		Tuile jeton2 = new Tuile(Couleur.ROUGE, Symbole.DAUPHIN);

		assertTrue(jeton.estCompatible(jeton2));
	}

	@Test
	void testEstCompatibleQuandMemeCouleur() {
		Tuile jeton = new Tuile(Couleur.BLEU, Symbole.DAUPHIN);
		Tuile jeton2 = new Tuile(Couleur.BLEU, Symbole.FLEUR);

		assertTrue(jeton.estCompatible(jeton2));
	}

	@Test
	void testEstCompatibleQuandPasCompatible() {
		Tuile jeton = new Tuile(Couleur.BLEU, Symbole.DAUPHIN);
		Tuile jeton2 = new Tuile(Couleur.ROUGE, Symbole.FLEUR);

		assertFalse(jeton.estCompatible(jeton2));
	}
}
