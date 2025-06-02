package latice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import latice.model.Couleur;
import latice.model.Symbole;
import latice.model.Tuile;

class TuileTest {

    private Tuile t1, t2, t3, t4;

    @BeforeEach
    void setUp() {
        // meme couleur symbole différent
        t1 = new Tuile(Couleur.ROUGE, Symbole.FLEUR);
        t2 = new Tuile(Couleur.ROUGE, Symbole.LEZARD);
        // meme symbole couleur différente
        t3 = new Tuile(Couleur.BLEU, Symbole.FLEUR);
        // symbole différent, couleur differrente
        t4 = new Tuile(Couleur.VERT, Symbole.TORTUE);
    }

    @Test
    void testEstCompatible_memeCouleurDevraitRetournerVrai() {
        assertTrue(t1.estCompatible(t2));
    }

    @Test
    void testEstCompatible_memeSymboleDevraitRetournerVrai() {
        assertTrue(t1.estCompatible(t3));
    }

    @Test
    void testEstCompatible_diffCouleurDiffSymboleDevraitRetournerFaux() {
        assertFalse(t1.estCompatible(t4));
    }

    @Test
    void testEstCompatible_symmetricité() {
        // compatibilité symétrique
        assertEquals(t1.estCompatible(t2), t2.estCompatible(t1));
        assertEquals(t1.estCompatible(t3), t3.estCompatible(t1));
        assertEquals(t1.estCompatible(t4), t4.estCompatible(t1));
    }

}
