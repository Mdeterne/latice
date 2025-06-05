package latice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import latice.model.Couleur;
import latice.model.Rack;
import latice.model.Symbole;
import latice.model.Tuile;

class RackTest {

	private Rack rack;
	private Tuile t1, t2, t3, t4, t5, t6;

	@BeforeEach
	void setUp() {
		rack = new Rack();
		t1 = new Tuile(Couleur.ROUGE, Symbole.FLEUR);
		t2 = new Tuile(Couleur.BLEU, Symbole.LEZARD);
		t3 = new Tuile(Couleur.VERT, Symbole.PLUME);
		t4 = new Tuile(Couleur.JAUNE, Symbole.OISEAU);
		t5 = new Tuile(Couleur.VIOLET, Symbole.DAUPHIN);
		t6 = new Tuile(Couleur.BLEU_CLAIR, Symbole.TORTUE);
	}

	@Test
	void testAjouterTuile_jusquAlaTailleMax() {
		rack.ajouterTuile(t1);
		rack.ajouterTuile(t2);
		rack.ajouterTuile(t3);
		rack.ajouterTuile(t4);
		rack.ajouterTuile(t5);
		// ajout de 5 tuiles
		assertEquals(5, rack.taille());

		// Tenter ajout d'une 6
		rack.ajouterTuile(t6);
		assertEquals(5, rack.taille(), "Au-delà de TAILLE_MAX, on ne doit pas ajouter");
	}

	@Test
	void testRetirerTuile_existanteEtInexistante() {
		rack.ajouterTuile(t1);
		rack.ajouterTuile(t2);
		assertEquals(2, rack.taille());

		// retire une tuile qui est dans le rack
		rack.retirerTuile(t1);
		assertEquals(1, rack.taille());
		List<Tuile> listes = rack.afficherTuiles();
		assertFalse(listes.contains(t1));
		assertTrue(listes.contains(t2));

		// Retirer une tuile qui n’existe pas
		rack.retirerTuile(t3);
		assertEquals(1, rack.taille());
		listes = rack.afficherTuiles();
		assertTrue(listes.contains(t2));
	}

	@Test
	void testVider_rackRempliPuisVide() {
		rack.ajouterTuile(t1);
		rack.ajouterTuile(t2);
		rack.ajouterTuile(t3);
		assertEquals(3, rack.taille());

		// vider renvoie t1, t2, t3 et vider le rack
		List<Tuile> anciennes = rack.vider();
		assertEquals(3, anciennes.size());
		assertTrue(anciennes.contains(t1) && anciennes.contains(t2) && anciennes.contains(t3));
		assertEquals(0, rack.taille());

		// Appeler vider sur un rack vide renvoie liste vide
		List<Tuile> vides = rack.vider();
		assertNotNull(vides);
		assertTrue(vides.isEmpty());
		assertEquals(0, rack.taille());
	}

	@Test
	void testAfficherTuiles_renvoieCopie() {
		rack.ajouterTuile(t1);
		rack.ajouterTuile(t2);
		List<Tuile> listeExterne = rack.afficherTuiles();

		// modifier la liste ne doit pas modifier état interne
		listeExterne.clear();
		assertEquals(2, rack.taille());
	}

}
