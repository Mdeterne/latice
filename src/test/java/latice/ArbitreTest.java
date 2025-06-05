package latice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import latice.model.Arbitre;
import latice.model.Couleur;
import latice.model.Joueur;
import latice.model.Plateau;
import latice.model.Position;
import latice.model.Rack;
import latice.model.Symbole;
import latice.model.Tuile;
import latice.util.exception.ActionsInsuffisanteException;
import latice.util.exception.CaseInaccessibleException;
import latice.util.exception.PiocheVideException;

class ArbitreTest {

	@Test
	void testInitialisationRackEtPioche() {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur j1 = arbitre.getJoueur1();
		Joueur j2 = arbitre.getJoueur2();

		assertEquals(5, j1.getRack().taille());
		assertEquals(5, j2.getRack().taille());

		assertEquals(31, j1.taillePiochePersonelle());
		assertEquals(31, j2.taillePiochePersonelle());

		assertTrue(arbitre.getJoueurCourant() == j1 || arbitre.getJoueurCourant() == j2);
	}

	@Test
	void testChangerTourEtActionsReset() {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur courant = arbitre.getJoueurCourant();
		Joueur autre = (courant == arbitre.getJoueur1()) ? arbitre.getJoueur2() : arbitre.getJoueur1();

		assertEquals(1, arbitre.getActions());
		arbitre.changerTour();

		assertEquals(autre, arbitre.getJoueurCourant());
		assertEquals(1, arbitre.getActions());
	}

	@Test
	void testJouerTuilePositionInvalide() {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur courant = arbitre.getJoueurCourant();

		List<Tuile> rack = courant.getRack().afficherTuiles();
		Tuile t = rack.get(0);

		int ok = arbitre.jouerTuile(new Position(9, 9), t);
		assertEquals(ok, 0);

		assertEquals(1, arbitre.getActions());
	}

	@Test
	void testJouerTuilePremierCoupValideEtRetireDuRack() throws CaseInaccessibleException {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur courant = arbitre.getJoueurCourant();
		assertEquals(5, courant.getRack().taille());

		Tuile t0 = courant.getRack().afficherTuiles().get(0);

		int ok = arbitre.jouerTuile(new Position(4, 4), t0);
		assertEquals(ok, 5);

		assertEquals(4, courant.getRack().taille());

		assertEquals(0, arbitre.getActions());

		Plateau p = arbitre.getPlateau();
		assertEquals(t0, p.getCase(new Position(4, 4)).getTuile());

		assertEquals(0, courant.point());
	}

	@Test
	void testJouerTuileDeuxiemeCoupIncompatibleRetourneFalse() throws CaseInaccessibleException {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur courant = arbitre.getJoueurCourant();

		Tuile t1 = courant.getRack().afficherTuiles().get(0);
		int ok1 = arbitre.jouerTuile(new Position(4, 4), t1);
		assertEquals(ok1, 5);

		courant.réinitialiserActions();

		Tuile tIncompatible = null;
		for (Tuile t : courant.getRack().afficherTuiles()) {
			if (!t.estCompatible(t1)) {
				tIncompatible = t;
				break;
			}
		}
		assertNotNull(tIncompatible);

		int ok2 = arbitre.jouerTuile(new Position(4, 3), tIncompatible);
		assertEquals(ok2, 1);

		assertEquals(4, courant.getRack().taille());
	}

	@Test
	void testTaillePiocheEtRemplireRack() throws PiocheVideException {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur courant = arbitre.getJoueurCourant();

		assertEquals(31, arbitre.taillePioche());

		List<Tuile> toRemove = courant.getRack().afficherTuiles().subList(0, 3);
		toRemove.forEach(t -> courant.getRack().retirerTuile(t));
		assertEquals(2, courant.getRack().taille());

		arbitre.remplireRack();
		assertEquals(5, courant.getRack().taille());
		assertEquals(28, arbitre.taillePioche());
	}

	@Test
	void testChangerRackEtRetirerAction() throws PiocheVideException {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur courant = arbitre.getJoueurCourant();

		assertEquals(5, courant.getRack().taille());
		assertEquals(31, arbitre.taillePioche());
		assertEquals(1, arbitre.getActions());

		arbitre.changerRack();
		try {
			arbitre.retirerAction();
		} catch (ActionsInsuffisanteException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(5, courant.getRack().taille());
		assertEquals(31, arbitre.taillePioche());

		assertEquals(0, arbitre.getActions());

		courant.réinitialiserActions();
		arbitre.remplireRack();
		assertEquals(5, courant.getRack().taille());
	}

	@Test
	void testChangerTourEnAlternantLesJoueurs() {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur courant = arbitre.getJoueurCourant();
		arbitre.changerTour();
		Joueur suivant = arbitre.getJoueurCourant();
		assertNotEquals(courant, suivant);
		arbitre.changerTour();
		assertEquals(courant, arbitre.getJoueurCourant());
	}

	@Test
	void testGetGagnant_Joueur1Gagne() {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur j1 = arbitre.getJoueur1();
		Joueur j2 = arbitre.getJoueur2();

		for (int i = 0; i < 5; i++) {
			j1.ajoutTuilePosé();
		}
		for (int i = 0; i < 3; i++) {
			j2.ajoutTuilePosé();
		}

		assertEquals(j1, arbitre.getGagnant());
	}

	@Test
	void testGetGagnant_Joueur2Gagne() {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur j1 = arbitre.getJoueur1();
		Joueur j2 = arbitre.getJoueur2();

		for (int i = 0; i < 2; i++) {
			j1.ajoutTuilePosé();
		}
		for (int i = 0; i < 4; i++) {
			j2.ajoutTuilePosé();
		}

		assertEquals(j2, arbitre.getGagnant());
	}

	@Test
	void testGetGagnant_Egalite() {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur j1 = arbitre.getJoueur1();
		Joueur j2 = arbitre.getJoueur2();

		for (int i = 0; i < 3; i++) {
			j1.ajoutTuilePosé();
			j2.ajoutTuilePosé();
		}

		assertNull(arbitre.getGagnant());
	}

	

	@Test
	void testEstFinDuJeu_QuandNombreToursAtteintZero() {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur joueurActuel = arbitre.getJoueurCourant();

		for (int i = 0; i < 20; i++) {
			while (joueurActuel.actions() > 0) {
				try {
					joueurActuel.enleverAction();
				} catch (ActionsInsuffisanteException e) {
					System.out.println(e.getMessage());
				}
			}
			arbitre.estFinDuJeu();
			arbitre.changerTour();
			joueurActuel = arbitre.getJoueurCourant();
		}

		assertEquals(0, arbitre.nombreTours());
		assertTrue(arbitre.estFinDuJeu());
	}

	@Test
	void testNombreToursInitial() {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		assertEquals(20, arbitre.nombreTours());
	}

	@Test
	void testNomJoueurs() {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		assertTrue(arbitre.nomJoueur().equals("Marty") || arbitre.nomJoueur().equals("Barre"));
	}

	@Test
	void testRackJoueursEstRempliApresInitialisation() {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		ArrayList<Tuile> rack1 = arbitre.RackJoueur();

		assertNotNull(rack1);
		assertEquals(5, rack1.size());
	}

	@Test
	void testPremierCoupInitialementVrai() {
		Arbitre arbitre = new Arbitre();
		assertTrue(arbitre.premierCoup());
	}

	@Test
	void testChangerRackNeLèvePasExceptionSiPiocheSuffisante() {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		try {
			arbitre.changerRack();
		} catch (PiocheVideException e) {
			fail("La méthode changerRack() ne devrait pas lever d'exception si la pioche est suffisante.");
		}
	}

	@Test
	void testRetirerTuileExistante() {
		Rack rack = new Rack();
		Tuile tuile1 = new Tuile(Couleur.ROUGE, Symbole.FLEUR);
		Tuile tuile2 = new Tuile(Couleur.BLEU, Symbole.LEZARD);

		rack.ajouterTuile(tuile1);
		rack.ajouterTuile(tuile2);

		assertEquals(2, rack.taille());

		rack.retirerTuile(tuile1);

		assertEquals(1, rack.taille());
		assertFalse(rack.afficherTuiles().contains(tuile1));
		assertTrue(rack.afficherTuiles().contains(tuile2));
	}

	@Test
	void testRetirerTuileNonExistante() {
		Rack rack = new Rack();
		Tuile tuile1 = new Tuile(Couleur.ROUGE, Symbole.FLEUR);
		Tuile tuile2 = new Tuile(Couleur.BLEU, Symbole.LEZARD);

		rack.ajouterTuile(tuile1);

		assertEquals(1, rack.taille());

		rack.retirerTuile(tuile2);

		assertEquals(1, rack.taille());
		assertTrue(rack.afficherTuiles().contains(tuile1));
	}

	@Test
	void testRetirerTuileDepuisRackVide() {
		Rack rack = new Rack();
		Tuile tuile1 = new Tuile(Couleur.ROUGE, Symbole.FLEUR);

		assertEquals(0, rack.taille());

		rack.retirerTuile(tuile1);

		assertEquals(0, rack.taille());
	}

	@Test
	void testRetirerDerniereTuile() {
		Rack rack = new Rack();
		Tuile tuile1 = new Tuile(Couleur.ROUGE, Symbole.FLEUR);

		rack.ajouterTuile(tuile1);

		rack.retirerTuile(tuile1);

		assertEquals(0, rack.taille());
		assertTrue(rack.afficherTuiles().isEmpty());
	}

	@Test
	void testTaillePiochePourJoueur1() {
		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur joueur1 = arbitre.getJoueur1();
		arbitre.changerTour();
		if (arbitre.getJoueurCourant() != joueur1) {
			arbitre.changerTour();
		}

		int taillePioche = arbitre.taillePioche();
		int tailleAttendue = joueur1.taillePiochePersonelle();

		assertEquals(tailleAttendue, taillePioche);
	}

	@Test
	void testTaillePiochePourJoueur2() {

		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");

		Joueur joueur2 = arbitre.getJoueur2();
		if (arbitre.getJoueurCourant() != joueur2) {
			arbitre.changerTour();
		}

		int taillePioche = arbitre.taillePioche();
		int tailleAttendue = joueur2.taillePiochePersonelle();

		assertEquals(tailleAttendue, taillePioche);
	}

	@Test
	void testTaillePiocheApresChangementTour() {

		Arbitre arbitre = new Arbitre();
		arbitre.initialiser("Marty", "Barre", "", "");
		Joueur joueur1 = arbitre.getJoueur1();
		Joueur joueur2 = arbitre.getJoueur2();

		int tailleInitiale = arbitre.taillePioche();

		arbitre.changerTour();
		int nouvelleTaille = arbitre.taillePioche();

		if (arbitre.getJoueurCourant() == joueur1) {
			assertEquals(joueur1.taillePiochePersonelle(), nouvelleTaille);
			assertEquals(joueur2.taillePiochePersonelle(), tailleInitiale);
		} else {
			assertEquals(joueur2.taillePiochePersonelle(), nouvelleTaille);
			assertEquals(joueur1.taillePiochePersonelle(), tailleInitiale);
		}
	}

}
