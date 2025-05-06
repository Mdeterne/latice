package latice.application;

import static latice.console.LaticeConsole.entrée;
import static latice.console.LaticeConsole.message;

import latice.model.Joueur;
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
          
           
           joueur1.piochePersonelle().remplirPiochePerso(piochePrincipal);
           joueur2.piochePersonelle().remplirPiochePerso(piochePrincipal);
           
           
           joueur1.initialiserRack();
           joueur2.initialiserRack();
           
           
           message(joueur1.nom()+" : "+joueur1.rack().afficherJetons());
           message("voici votre pioche : "+joueur1.afficherPiochePersonelle());
           message("voici le nombre de jetons présent dans votre pioche : "+ joueur1.piochePersonelle().taille());
           
           String i = entrée("Voulez-vous changer votre rack ? : (oui/non)");
           
           if(i.equals("oui")) {
        	   joueur1.echangerRack();
        	   message("votre pioche vien d'etre modifier voici la nouvelle : "+joueur1.rack().afficherJetons());
               message("voici le nombre de jetons présent dans votre pioche : "+ joueur1.piochePersonelle().taille());

           }
           message("passage au joueur 2");
           
           
           message("");
           message(joueur2.nom()+" : "+joueur2.rack().afficherJetons());
           message("voici votre pioche : "+joueur2.afficherPiochePersonelle());
           message("voici le nombre de jetons présent : "+ joueur2.piochePersonelle().taille());
           
           i = entrée("Voulez-vous changer votre rack ? : (oui/non)");
           
           if(i.equals("oui")) {
        	   joueur2.echangerRack();
        	   message("votre pioche vien d'etre modifier voici la nouvelle : "+joueur2.rack().afficherJetons());
               message("voici le nombre de jetons présent dans votre pioche : "+ joueur2.piochePersonelle().taille());

           }
           
    }
}
