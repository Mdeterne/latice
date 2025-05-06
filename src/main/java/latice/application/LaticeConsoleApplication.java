package latice.application;

import latice.model.Joueur;
import latice.model.Pioche;
import latice.model.PiochePrincipal;

public class LaticeConsoleApplication {

	public static void main(String[] args) {
            System.out.println("-----------------------------------------------------");
            System.out.println("-- Bienvenue dans notre magnifique jeu de latice ! --");
            System.out.println("-- développé par Julian Ray-Constanty              --");
            System.out.println("-- et par Julian Barre                             --");
            System.out.println("-- et par Marty Benjamin                            --");
            System.out.println("-----------------------------------------------------");
            
           PiochePrincipal piochePrincipal = new PiochePrincipal();
           Joueur joueur1 = new Joueur("moi");
           Joueur joueur2 = new Joueur("toi");
          
           
           System.out.println(piochePrincipal.taille());
           System.out.println(joueur1.piochePerso().taille());
           System.out.println(joueur2.piochePerso().taille());
           joueur1.piochePerso().remplirPiochePerso(piochePrincipal);
           joueur2.piochePerso().remplirPiochePerso(piochePrincipal);
           System.out.println(joueur1.piochePerso().taille());
           System.out.println(joueur2.piochePerso().taille());
           
           joueur1.initialiserRack();
           joueur2.initialiserRack();
           
           System.out.println(joueur1.rack().afficherJetons());
           System.out.println(joueur2.rack().afficherJetons());
           
    }
}
