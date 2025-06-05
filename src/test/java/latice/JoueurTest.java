package latice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import latice.model.Joueur;
import latice.model.PiochePrincipal;
import latice.model.Tuile;
import latice.util.exception.ActionsInsuffisanteException;
import latice.util.exception.PiocheVideException;

class JoueurTest {

    private Joueur joueur;
    private PiochePrincipal piochePrincipal;

    @BeforeEach
    void setUp() {
        joueur = new Joueur("Alice");
        piochePrincipal = new PiochePrincipal();
    }

    @Test
    void testAjouterPoints() {
        joueur.ajouterPoints(5);
        assertEquals(5, joueur.point());
        joueur.ajouterPoints(3);
        assertEquals(8, joueur.point());
    }

    @Test
    void testInitialiserEtRemplirRack() throws PiocheVideException {
        joueur.remplirPiochePersonelle(piochePrincipal);
        assertEquals(36, joueur.taillePiochePersonelle());

        joueur.initialiserRack();
        assertEquals(5, joueur.getRack().taille());
        // pioche perso passe à 31
        assertEquals(31, joueur.taillePiochePersonelle());

        Tuile tuileRetiree = joueur.getRack().afficherTuiles().get(0);
        joueur.getRack().retirerTuile(tuileRetiree);
        assertEquals(4, joueur.getRack().taille());
        // pioche perso à 31 toujours
        assertEquals(31, joueur.taillePiochePersonelle());

        //remplirRack ramène le rack à 5 et réduit pioche perso de 1
        joueur.remplirRack();
        assertEquals(5, joueur.getRack().taille());
        assertEquals(30, joueur.taillePiochePersonelle());
    }

    @Test
    void testEchangerRack() throws PiocheVideException {
        joueur.remplirPiochePersonelle(piochePrincipal);
        joueur.initialiserRack();
        assertEquals(5, joueur.getRack().taille());
        assertEquals(31, joueur.taillePiochePersonelle());

        // récupère le contenu initial du rack
        List<Tuile> ancienneListe = new ArrayList<>(joueur.getRack().afficherTuiles());
        // fais l’échange
        String retour = joueur.echangerRack();
        assertTrue(retour.contains("Alice à échanger son rack"));
        // après échange
        assertEquals(5, joueur.getRack().taille());
        assertEquals(31, joueur.taillePiochePersonelle());

        joueur.remplirPiochePersonelle(new PiochePrincipal());
        // on ne compare pas directement le contenu du rack mais on vérifie qu’aucune des 5 anciennes ne reste dans le rack en même temps
        Set<Tuile> setAnciennes = new HashSet<>(ancienneListe);
        Set<Tuile> setNouvelles = new HashSet<>(joueur.getRack().afficherTuiles());
        setAnciennes.retainAll(setNouvelles);
        assertTrue(setAnciennes.size() < 5);
    }


    @Test
    void testActionsEtResets() {
        // par défaut actions() vaut 1
        assertEquals(1, joueur.actions());

        try {
			joueur.enleverAction();
		} catch (ActionsInsuffisanteException e) {
			System.out.println(e.getMessage());
		}
        assertEquals(0, joueur.actions());

        joueur.ajouterUneAction();
        assertEquals(1, joueur.actions());

        joueur.ajouterUneAction();
        assertEquals(2, joueur.actions());

        // réinitialiserActions remet à 1
        joueur.réinitialiserActions();
        assertEquals(1, joueur.actions());
    }

}
