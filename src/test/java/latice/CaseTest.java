package latice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import latice.model.Case;
import latice.model.Couleur;
import latice.model.Tuile;
import latice.model.Position;
import latice.model.Symbole;
import latice.test.exception.CaseInaccessibleException;

class CaseTest {

	@Test
	void testCaseEstVideQuandCaseVide() {
		Position position = new Position(0,0);
		Case maCase = new Case(position, false, false);

		assertTrue(maCase.estVide());
	}
	
	@Test
	void testCaseEstVideQuandCaseRempli() throws CaseInaccessibleException {
		Position position = new Position(0,0);
		Case maCase = new Case(position, true, false);
		Tuile tuile = new Tuile(Couleur.ROUGE, Symbole.DAUPHIN);
		
		maCase.posertuile(tuile);

		assertFalse(maCase.estVide());
	}
	
    @Test
    void testGettuile() throws CaseInaccessibleException {
		Tuile tuile = new Tuile(Couleur.ROUGE, Symbole.DAUPHIN);
        Position position = new Position(0, 0);
        Case maCase = new Case(position, false, false);
        maCase.posertuile(tuile);

        assertEquals(tuile, maCase.gettuile());
    }

    @Test
    void testEstSoleil() {
        Position position = new Position(0, 0);
        Case maCase = new Case(position, true, false);
        assertTrue(maCase.estSoleil());
    }

    @Test
    void testEstLune() {
        Position position = new Position(0, 0);
        Case maCase = new Case(position, false, true);
        assertTrue(maCase.estLune());
    }

    @Test
    void testGetPosition() {
        Position position = new Position(0, 0);
        Case maCase = new Case(position, false, false);
        assertEquals(position, maCase.getPosition());
    }
}