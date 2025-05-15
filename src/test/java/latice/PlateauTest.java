package latice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import latice.model.Joueur;
import latice.model.Plateau;
import latice.model.Position;

class PlateauTest {

	@Test
	void testPlateauEstInitialiseCorrectement() {
		Plateau plateau = new Plateau();

		assertEquals(9, plateau.getTaille());

		for (int i = 0; i<9; i++ ) {
			for (int j = 0; i<9; i++) {
				assertNotNull(plateau.getCase(new Position(i, j)));
			}
		}
	}
	
	@Test
	void testSoleilEstInitialiseCorrectement() {
		Plateau plateau = new Plateau();
		
		assertTrue(plateau.getCase(new Position(0,0)).estSoleil());
		assertTrue(plateau.getCase(new Position(4,0)).estSoleil());
		assertTrue(plateau.getCase(new Position(8,0)).estSoleil());
		assertTrue(plateau.getCase(new Position(1,1)).estSoleil());
		assertTrue(plateau.getCase(new Position(7,1)).estSoleil());
		assertTrue(plateau.getCase(new Position(2,2)).estSoleil());
		assertTrue(plateau.getCase(new Position(6,2)).estSoleil());
		assertTrue(plateau.getCase(new Position(0,4)).estSoleil());
		assertTrue(plateau.getCase(new Position(8,4)).estSoleil());
		assertTrue(plateau.getCase(new Position(6,6)).estSoleil());
		assertTrue(plateau.getCase(new Position(2,6)).estSoleil());
		assertTrue(plateau.getCase(new Position(7,7)).estSoleil());
		assertTrue(plateau.getCase(new Position(1,7)).estSoleil());
		assertTrue(plateau.getCase(new Position(8,8)).estSoleil());
		assertTrue(plateau.getCase(new Position(4,8)).estSoleil());
		assertTrue(plateau.getCase(new Position(0,8)).estSoleil());
	}
	
	@Test
	void testLuneEstInitialiseCorrectement() {
		Plateau plateau = new Plateau();

		assertTrue(plateau.getCase(new Position(4,4)).estLune());
	}
	
	@Test
	void testDonnerPoint() {
		Joueur joueur = new Joueur("joueur");
		Plateau plateau = new Plateau();
		
		plateau.donnerPoint(joueur, 5);
		
		assertEquals(5, joueur.point());
	}

	@Test
	void testPoserJeton() {
		//TODO	
	}
}
