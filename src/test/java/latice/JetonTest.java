package latice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import latice.model.Couleur;
import latice.model.Jeton;
import latice.model.Symbole;

class JetonTest {

	@Test
	void testEstCompatibleQuandMemeSymbole() {
		Jeton jeton = new Jeton(Couleur.BLEU, Symbole.DAUPHIN);
		Jeton jeton2 = new Jeton(Couleur.ROUGE, Symbole.DAUPHIN);
		
		assertTrue(jeton.estCompatible(jeton2));
	}

	@Test
	void testEstCompatibleQuandMemeCouleur() {
		Jeton jeton = new Jeton(Couleur.BLEU, Symbole.DAUPHIN);
		Jeton jeton2 = new Jeton(Couleur.BLEU, Symbole.FLEUR);
		
		assertTrue(jeton.estCompatible(jeton2));
	}

	@Test
	void testEstCompatibleQuandPasCompatible() {
		Jeton jeton = new Jeton(Couleur.BLEU, Symbole.DAUPHIN);
		Jeton jeton2 = new Jeton(Couleur.ROUGE, Symbole.FLEUR);
		
		assertFalse(jeton.estCompatible(jeton2));
	}
}
