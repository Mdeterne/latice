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
            
            Boolean continuer = true;
            String i;
            Boolean tourJoueur = true;
            PiochePrincipal piochePrincipal = new PiochePrincipal();
           
            Joueur joueur1 = new Joueur(entrée("Entrez un nom: "));
            Joueur joueur2 = new Joueur(entrée("Entrez un nom: "));
          
           
            joueur1.remplirPiochePersonelle(piochePrincipal);
            joueur2.remplirPiochePersonelle(piochePrincipal);
           
           
            joueur1.initialiserRack();
            joueur2.initialiserRack();
           
           
            while (continuer) {
            	
				if (tourJoueur) {
					message(joueur1.nom() + " : " + joueur1.afficherJetonsRack());
					message("voici votre pioche : " + joueur1.afficherPiochePersonelle());
					message("voici le nombre de jetons présent dans votre pioche : "
							+ joueur1.taillePiochePersonelle());
					i = entrée("Voulez-vous changer votre rack ? : (oui/non)");
					if (i.equals("oui")) {
						joueur1.echangerRack();
						message("votre pioche vien d'etre modifier voici la nouvelle : "
								+ joueur1.afficherJetonsRack());
						message("voici le nombre de jetons présent dans votre pioche : "
								+ joueur1.taillePiochePersonelle());
					}
					i = entrée("Voulez-vous quitter la partie ? : (oui/non)");
					if (i.equals("oui")) {
						message("vous quittez la patie !");
						continuer = false;
					}
					message("");
				}
				
				if (!tourJoueur) {
					message(joueur2.nom() + " : " + joueur2.afficherJetonsRack());
					message("voici votre pioche : " + joueur2.afficherPiochePersonelle());
					message("voici le nombre de jetons présent : " + joueur2.taillePiochePersonelle());
					i = entrée("Voulez-vous changer votre rack ? : (oui/non)");
					if (i.equals("oui")) {
						joueur2.echangerRack();
						message("votre pioche vien d'etre modifier voici la nouvelle : "
								+ joueur2.afficherJetonsRack());
						message("voici le nombre de jetons présent dans votre pioche : "
								+ joueur2.taillePiochePersonelle());
					}
					i = entrée("Voulez-vous quitter la partie ? : (oui/non)");
					if (i.equals("oui")) {
						message("vous quittez la patie !");
						continuer = false;
					}
					message("");
				}
				if (tourJoueur) {
					tourJoueur = false;
				}
				else {tourJoueur = true;}
			}
           
    }
}