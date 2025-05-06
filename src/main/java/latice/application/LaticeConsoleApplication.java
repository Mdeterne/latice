package latice.application;

import static latice.console.LaticeConsole.entrée;
import static latice.console.LaticeConsole.message;

import latice.model.Joueur;
import latice.model.Pioche;
import latice.model.PiochePrincipal;

public class LaticeConsoleApplication {

	public static void main(String[] args) {
			message("-----------------------------------------------------");
            message("-- Bienvenue dans notre magnifique jeu de latice ! --");
            message("-- développé par Julian Ray-Constanty              --");
            message("-- et par Julian Barre                             --");
            message("-- et par Marty Benjamin                            --");
            message("-----------------------------------------------------");
            
           PiochePrincipal piochePrincipal = new PiochePrincipal();
           
           Joueur joueur1 = new Joueur(entrée("Entrez un nom: "));
           Joueur joueur2 = new Joueur(entrée("Entrez un nom: "));
          
           
           message(""+piochePrincipal.taille());
           message(""+joueur1.piochePersonelle().taille());
           message(""+joueur2.piochePersonelle().taille());
           joueur1.piochePersonelle().remplirPiochePerso(piochePrincipal);
           joueur2.piochePersonelle().remplirPiochePerso(piochePrincipal);
           message(""+joueur1.piochePersonelle().taille());
           message(""+joueur2.piochePersonelle().taille());
           
           joueur1.initialiserRack();
           joueur2.initialiserRack();
           
           message(""+joueur1.rack().afficherJetons());
           message(""+joueur2.rack().afficherJetons());
           
    }
}
