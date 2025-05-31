package latice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import latice.model.Couleur;
import latice.model.PiochePrincipal;
import latice.model.Symbole;
import latice.model.Tuile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PiochePrincipalTest {

    @Test
    void testConstructeur() {
        PiochePrincipal pioche = new PiochePrincipal();
        
        assertNotNull(pioche);
        assertFalse(pioche.estVide());
        
        int tailleAttendue = Couleur.values().length * Symbole.values().length * 2;
        assertEquals(tailleAttendue, pioche.taille());
        
        for (Couleur couleur : Couleur.values()) {
            for (Symbole symbole : Symbole.values()) {
                long nombreTuiles = pioche.pioche().stream()
                    .filter(t -> t.couleur() == couleur && t.symbole() == symbole)
                    .count();
                assertEquals(2, nombreTuiles, 
                    "Il devrait y avoir 2 tuiles pour chaque combinaison couleur/symbole");
            }
        }
        
        boolean estMelange = false;
        List<Tuile> tuiles = pioche.pioche();
        for (int i = 0; i < tuiles.size() - 1; i++) {
            if (!tuiles.get(i).equals(tuiles.get(i + 1))) {
                estMelange = true;
                break;
            }
        }
        assertTrue(estMelange, "La pioche devrait être mélangée");
    }

    @Test
    void testContenuInitial() {
        PiochePrincipal pioche = new PiochePrincipal();
        
        assertEquals(Couleur.values().length * Symbole.values().length * 2, pioche.taille());
        
        for (Couleur couleur : Couleur.values()) {
            for (Symbole symbole : Symbole.values()) {
                int compteur = 0;
                for (Tuile tuile : pioche.pioche()) {
                    if (tuile.couleur() == couleur && tuile.symbole() == symbole) {
                        compteur++;
                    }
                }
                assertEquals(2, compteur, 
                    "Il devrait y avoir 2 tuiles pour " + couleur + "/" + symbole);
            }
        }
    }

    @Test
    void testMelangeInitial() {
        PiochePrincipal pioche1 = new PiochePrincipal();
        PiochePrincipal pioche2 = new PiochePrincipal();
        
        assertEquals(pioche1.taille(), pioche2.taille(), 
            "Les deux pioches devraient avoir le même nombre de tuiles");
        
        List<Tuile> copie1 = new ArrayList<>(pioche1.pioche());
        List<Tuile> copie2 = new ArrayList<>(pioche2.pioche());
        
        Collections.sort(copie1, this::comparerTuiles);
        Collections.sort(copie2, this::comparerTuiles);
        
        assertEquals(copie1, copie2, 
            "Les pioches devraient contenir les mêmes tuiles");
        
        int positionsIdentiques = 0;
        for (int i = 0; i < pioche1.taille(); i++) {
            if (pioche1.pioche().get(i).equals(pioche2.pioche().get(i))) {
                positionsIdentiques++;
            }
        }
        assertTrue(positionsIdentiques < pioche1.taille(), 
            "Les pioches ne devraient pas avoir toutes les tuiles dans le même ordre");
    }

    private int comparerTuiles(Tuile t1, Tuile t2) {
        int cmpCouleur = t1.couleur().compareTo(t2.couleur());
        if (cmpCouleur != 0) return cmpCouleur;
        return t1.symbole().compareTo(t2.symbole());
    }
}