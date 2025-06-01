package latice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;



import latice.model.Couleur;
import latice.model.Pioche;
import latice.model.Tuile;
import latice.model.Symbole;
import latice.util.exception.PiocheVideException;


class PiocheConcrete extends Pioche { // pour les methodes piocher et mélanger
   
}

class PiocheTest {

    @Test
    void testEstVide_InitialementVide() {
        Pioche pioche = new PiocheConcrete();
        assertTrue(pioche.estVide());
    }

    @Test
    void testEstVide_ApresAjout() {
        Pioche pioche = new PiocheConcrete();
        Tuile tuile = new Tuile(Couleur.BLEU, Symbole.DAUPHIN);
        
        pioche.ajoutertuile(tuile);
        assertFalse(pioche.estVide());
    }

    @Test
    void testAjouterTuile_EtTaille() {
        Pioche pioche = new PiocheConcrete();
        Tuile tuile1 = new Tuile(Couleur.BLEU, Symbole.DAUPHIN);
        Tuile tuile2 = new Tuile(Couleur.ROUGE, Symbole.FLEUR);
        
        assertEquals(0, pioche.taille());
        pioche.ajoutertuile(tuile1);
        assertEquals(1, pioche.taille());
        pioche.ajoutertuile(tuile2);
        assertEquals(2, pioche.taille());
    }

    @Test
    void testPiocher_RetireDernierElement() throws PiocheVideException {
        Pioche pioche = new PiocheConcrete();
        Tuile tuile1 = new Tuile(Couleur.BLEU, Symbole.DAUPHIN);
        Tuile tuile2 = new Tuile(Couleur.ROUGE, Symbole.FLEUR);
        
        pioche.ajoutertuile(tuile1);
        pioche.ajoutertuile(tuile2);
        
        Tuile retiree = pioche.piocher();
        assertEquals(tuile2, retiree);
        assertEquals(1, pioche.taille());
    }

    @Test
    void testPiocher() throws PiocheVideException {
        Pioche pioche = new PiocheConcrete();
        Tuile tuile1 = new Tuile(Couleur.BLEU, Symbole.DAUPHIN);
        Tuile tuile2 = new Tuile(Couleur.ROUGE, Symbole.FLEUR);
        pioche.ajoutertuile(tuile1);
        pioche.ajoutertuile(tuile2);
        
        assertEquals(tuile2, pioche.piocher());
        assertEquals(1, pioche.taille());
    }

    @Test
    void testPiocherSurPiocheVide() {
        Pioche pioche = new PiocheConcrete();
        assertThrows(PiocheVideException.class, () -> pioche.piocher());
    }



    @Test
    void testPioche_Getter() {
        Pioche pioche = new PiocheConcrete();
        assertNotNull(pioche.pioche());
        assertTrue(pioche.pioche().isEmpty());
        
        Tuile tuile1 = new Tuile(Couleur.BLEU, Symbole.DAUPHIN);
        Tuile tuile2 = new Tuile(Couleur.ROUGE, Symbole.FLEUR);
        
        pioche.ajoutertuile(tuile1);
        pioche.ajoutertuile(tuile2);
        
        ArrayList<Tuile> contenu = pioche.pioche();
        assertEquals(2, contenu.size());
        assertTrue(contenu.contains(tuile1));
        assertTrue(contenu.contains(tuile2));
        
        contenu.remove(tuile1);
        assertEquals(1, pioche.taille()); 
    }
}