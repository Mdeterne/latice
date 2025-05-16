package latice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import latice.model.Joueur;
import latice.test.exception.PointInsuffisantException;

class JoueurTest {

	@Test
	void testAjouterPoints() {
		Joueur joueur = new Joueur("joueur");
		joueur.ajouterPoints(2);
		assertEquals(joueur.point(), 2);
	}
	
	
	@Test
	void testAcheterQuandAssezDePoint() throws PointInsuffisantException {
		Joueur joueur = new Joueur("joueur");
		joueur.ajouterPoints(2);
		joueur.acheter();
		assertEquals(joueur.point(), 0);
	}

}
