package latice.application;

import latice.model.Joueur;
import latice.model.Pioche;
import latice.model.PiochePrincipal;

public class LaticeApplicationConsole {

	public static void main(String[] args) {
            System.out.println("-----------------------------------------------------");
            System.out.println("-- Bienvenue dans notre magnifique jeu de latice ! --");
            System.out.println("-- développé par Julian Ray-Constanty              --");
            System.out.println("-- et par Julian Barre                             --");
            System.out.println("-- et par Marty Benjamin                            --");
            System.out.println("-----------------------------------------------------");
            
           PiochePrincipal piochePrincipal = new PiochePrincipal();
           Joueur joueur1 = new Joueur("moi");
           
           System.out.println(piochePrincipal.pioche());
           System.out.println(joueur1.afficherPiochePerso());
           joueur1.piochePerso().remplirPiochePerso(piochePrincipal);
           System.out.println(joueur1.afficherPiochePerso());
           
    }
}
