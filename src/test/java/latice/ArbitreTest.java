package latice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import latice.application.JavaFX.Arbitre;
import latice.model.Joueur;
import latice.model.Plateau;
import latice.model.Position;
import latice.model.Tuile;
import latice.util.exception.CaseInaccessibleException;
import latice.util.exception.PiocheVideException;

class ArbitreTest {

    private Arbitre arbitre;

    @BeforeEach
    void setUp() {
        arbitre = new Arbitre();
        arbitre.initialiser("Alice", "Bob");
    }

    @Test
    void testInitialisationRackEtPioche() {
        Joueur j1 = arbitre.getJoueur1();
        Joueur j2 = arbitre.getJoueur2();
        // Chaque joueur reçoit 36 tuiles en pioche, puis 5 sont passées au rack
        assertEquals(5, j1.getRack().taille());
        assertEquals(5, j2.getRack().taille());
        // pioche personnelle doit faire 31
        assertEquals(31, j1.taillePiochePersonelle());
        assertEquals(31, j2.taillePiochePersonelle());
        // joueur courant est soit j1 soit j2
        assertTrue(arbitre.getJoueurCourant() == j1 || arbitre.getJoueurCourant() == j2);
    }

    @Test
    void testChangerTourEtActionsReset() {
        Joueur courant = arbitre.getJoueurCourant();
        Joueur autre = (courant == arbitre.getJoueur1()) ? arbitre.getJoueur2() : arbitre.getJoueur1();
        // au début chaque joueur a 1 action
        assertEquals(1, arbitre.getActions());
        arbitre.changerTour();
        // le tour est passé à l'autre joueur et ses actions remise à 1
        assertEquals(autre, arbitre.getJoueurCourant());
        assertEquals(1, arbitre.getActions());
    }

    @Test
    void testJouerTuilePositionInvalide() {
        Joueur courant = arbitre.getJoueurCourant();
        // prends une tuile du rack
        List<Tuile> rack = courant.getRack().afficherTuiles();
        Tuile t = rack.get(0);
        // position hors du plateau(9,9)
        boolean ok = arbitre.jouerTuile(new Position(9, 9), t);
        assertFalse(ok);
        // on ne consomne aucune actions
        assertEquals(1, arbitre.getActions());
    }

    @Test
    void testJouerTuilePremierCoupValideEtRetireDuRack() throws CaseInaccessibleException {
        Joueur courant = arbitre.getJoueurCourant();
        assertEquals(5, courant.getRack().taille());

        Tuile t0 = courant.getRack().afficherTuiles().get(0);

        boolean ok = arbitre.jouerTuile(new Position(4, 4), t0);
        assertTrue(ok);

        assertEquals(4, courant.getRack().taille());

        assertEquals(0, arbitre.getActions());
        // La tuile est bien sur le plateau
        Plateau p = arbitre.getPlateau();
        assertEquals(t0, p.getCase(new Position(4, 4)).getTuile());

        // le joueur a gagné un point
        assertEquals(1, courant.point());
    }


    @Test
    void testJouerTuileDeuxiemeCoupIncompatibleRetourneFalse() throws CaseInaccessibleException {
        Joueur courant = arbitre.getJoueurCourant();
        // Premier coup sur (4,4)
        Tuile t1 = courant.getRack().afficherTuiles().get(0);
        boolean ok1 = arbitre.jouerTuile(new Position(4, 4), t1);
        assertTrue(ok1);
        // Pour la simplicité, on réinitialise ses actions pour ce test
        courant.réinitialiserActions();
        // choisir une tuile incompatible au hasard (quand symbole et couleur différents de t1)
        Tuile tIncompatible = null;
        for (Tuile t : courant.getRack().afficherTuiles()) {
            if (!t.estCompatible(t1)) {
                tIncompatible = t;
                break;
            }
        }
        assertNotNull(tIncompatible);
        // tenter de poser en 4,3 (voisin de 4,4)
        boolean ok2 = arbitre.jouerTuile(new Position(4, 3), tIncompatible);
        assertFalse(ok2);
        // rack ne doit pas avoir changé
        assertEquals(4, courant.getRack().taille());
    }

    @Test
    void testTaillePiocheEtRemplireRack() throws PiocheVideException {
        Joueur courant = arbitre.getJoueurCourant();
        // Initialement, pioche perso = 31
        assertEquals(31, arbitre.taillePioche());
        // On retire manuellement 3 tuiles du rack pour simuler un rack de 2 (montrant pioche non affectée)
        List<Tuile> toRemove = courant.getRack().afficherTuiles().subList(0, 3);
        toRemove.forEach(t -> courant.getRack().retirerTuile(t));
        assertEquals(2, courant.getRack().taille());
        // Remplir le rack doit monter à 5, et pioche retomber à 31 - 3 = 28
        arbitre.remplireRack();
        assertEquals(5, courant.getRack().taille());
        assertEquals(28, arbitre.taillePioche());
    }

    @Test
    void testChangerRackEtRetirerAction() throws PiocheVideException {
        Joueur courant = arbitre.getJoueurCourant();
        // avant échange : rack=5, pioche=31, actions=1
        assertEquals(5, courant.getRack().taille());
        assertEquals(31, arbitre.taillePioche());
        assertEquals(1, arbitre.getActions());
        // stocker contenu initial du rack
        List<Tuile> ancien = courant.getRack().afficherTuiles();
        // Effectuer l’échange
        arbitre.changerRack();
        arbitre.retirerAction();
        // Après échange rack doit toujours être de taille 5, et pioche perso = 31
        assertEquals(5, courant.getRack().taille());
        assertEquals(31, arbitre.taillePioche());
        // action consommée
        assertEquals(0, arbitre.getActions());
        // si on appelle remplirRack maintenant on reste cohérent
        courant.réinitialiserActions();
        arbitre.remplireRack();
        assertEquals(5, courant.getRack().taille());
    }

    @Test
    void testChangerTourEnalternantLesJoueurs() {
        Joueur initial = arbitre.getJoueurCourant();
        arbitre.changerTour();
        Joueur suivant = arbitre.getJoueurCourant();
        assertNotEquals(initial, suivant);
        arbitre.changerTour();
        assertEquals(initial, arbitre.getJoueurCourant());
    }

}
