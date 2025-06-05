package latice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import latice.model.Case;
import latice.model.Couleur;
import latice.model.Joueur;
import latice.model.Plateau;
import latice.model.Position;
import latice.model.Symbole;
import latice.model.Tuile;
import latice.util.exception.CaseInaccessibleException;

class PlateauTest {

	private Plateau plateau;
	private Tuile tRougeFleur;
	private Tuile tRougeLezard;
	private Tuile tBleuFleur;
	private Tuile tVertTortue;

	@BeforeEach
	void setUp() {
		plateau = new Plateau();
		tRougeFleur = new Tuile(Couleur.ROUGE, Symbole.FLEUR);
		tRougeLezard = new Tuile(Couleur.ROUGE, Symbole.LEZARD);
		tBleuFleur = new Tuile(Couleur.BLEU, Symbole.FLEUR);
		tVertTortue = new Tuile(Couleur.VERT, Symbole.TORTUE);
	}

	@Test
	void testPlateauEstInitialiseCorrectement() {
		assertEquals(9, plateau.getTaille());
		// vérifie que chaque case i, j n’est pas nulle
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertNotNull(plateau.getCase(new Position(i, j)));
			}
		}
	}

	@Test
	void testEstPositionValide() {
		assertTrue(plateau.estPositionValide(new Position(0, 0)));
		assertTrue(plateau.estPositionValide(new Position(8, 8)));
		assertTrue(plateau.estPositionValide(new Position(4, 7)));
		assertFalse(plateau.estPositionValide(new Position(-1, 0)));
		assertFalse(plateau.estPositionValide(new Position(0, -1)));
		assertFalse(plateau.estPositionValide(new Position(9, 0)));
		assertFalse(plateau.estPositionValide(new Position(0, 9)));
	}

	@Test
	void testGetCaseValideEtInvalide() {
		// position valide
		Position p = new Position(3, 5);
		Case c = plateau.getCase(p);
		assertNotNull(c);
		assertEquals(p.x(), c.getPosition().x());
		assertEquals(p.y(), c.getPosition().y());
		// position invalide (retourne null)
		assertNull(plateau.getCase(new Position(-1, 0)));
		assertNull(plateau.getCase(new Position(9, 9)));
	}

	@Test
	void testSoleilEstInitialiseCorrectement() {
		// coins
		assertTrue(plateau.getCase(new Position(0, 0)).estSoleil());
		assertTrue(plateau.getCase(new Position(8, 0)).estSoleil());
		assertTrue(plateau.getCase(new Position(0, 8)).estSoleil());
		assertTrue(plateau.getCase(new Position(8, 8)).estSoleil());
		// diagonales proches des coins
		assertTrue(plateau.getCase(new Position(1, 1)).estSoleil());
		assertTrue(plateau.getCase(new Position(7, 1)).estSoleil());
		assertTrue(plateau.getCase(new Position(1, 7)).estSoleil());
		assertTrue(plateau.getCase(new Position(7, 7)).estSoleil());
		// milieu des bords
		assertTrue(plateau.getCase(new Position(4, 0)).estSoleil());
		assertTrue(plateau.getCase(new Position(0, 4)).estSoleil());
		assertTrue(plateau.getCase(new Position(8, 4)).estSoleil());
		assertTrue(plateau.getCase(new Position(4, 8)).estSoleil());
	}

	@Test
	void testLuneEstInitialiseCorrectement() {
		// Le centre 4,4 est la lune
		assertTrue(plateau.getCase(new Position(4, 4)).estLune());
		// pas d’autre case lune
		assertFalse(plateau.getCase(new Position(3, 4)).estLune());
		assertFalse(plateau.getCase(new Position(4, 3)).estLune());
	}

	@Test
	void testPoserTuileEtCaseInaccessible() throws CaseInaccessibleException {
		Position pos = new Position(2, 2);
		// la case doit être vide
		assertNull(plateau.getCase(pos).getTuile());
		// on pose une tuile
		plateau.posertuile(tRougeFleur, pos);
		assertEquals(tRougeFleur, plateau.getCase(pos).getTuile());

		// eposer sur la même case lève CaseInaccessibleException
		assertThrows(CaseInaccessibleException.class, () -> {
			plateau.posertuile(tBleuFleur, pos);
		});
	}

	@Test
	void testNombreTuilesCompatiblesAucunVoisin() {
		// sur une case, sans voisines, renvoie 0
		int result = plateau.nombreTuilesCompatibles(new Position(4, 4), tRougeFleur);
		assertEquals(0, result);
	}

	@Test
	void testNombreTuilesCompatiblesVoisinsCompatibles() throws CaseInaccessibleException {
		// 3,4 = RougeFleur et 3,4 pas encore posé
		plateau.posertuile(tRougeFleur, new Position(4, 4));
		// On appelle nombreTuilesCompatibles sur (3,4) avec tuile compatible sur (4,4)
		int compt = plateau.nombreTuilesCompatibles(new Position(3, 4), tBleuFleur);
		// il y a 1 voisine
		assertEquals(1, compt);
	}

	@Test
	void testNombreTuilesCompatiblesVoisinIncompatibleRetourneZero() throws CaseInaccessibleException {
		// Place une tuile (4,4) tVertTortue
		plateau.posertuile(tVertTortue, new Position(4, 4));
		// Essayer de poser tRougeFleur sur (3,4) pas compatible
		int compt = plateau.nombreTuilesCompatibles(new Position(3, 4), tRougeFleur);
		assertEquals(0, compt);
	}
}