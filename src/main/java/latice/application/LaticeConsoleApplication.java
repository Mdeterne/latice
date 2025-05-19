package latice.application;

import static latice.console.LaticeConsole.entrée;
import static latice.console.LaticeConsole.message;

import java.util.Random;

import latice.model.Joueur;
import latice.model.PiochePrincipal;
import latice.test.exception.PiocheVideException;

public class LaticeConsoleApplication {

	public static void main(String[] args) {
			message("-----------------------------------------------------");
            message("-- Bienvenue dans notre magnifique jeu de latice ! --");
            message("-- développé par Julian Ray-Constanty              --");
            message("-- et par Julian Barre                             --");
            message("-- et par Marty Benjamin                            --");
            message("-----------------------------------------------------");
            
            Random random = new Random();
            
            Boolean continuer = true;
            String i;
            Boolean tourJoueur = random.nextBoolean(); // true ou false
            PiochePrincipal piochePrincipal = new PiochePrincipal();
           
            Joueur joueur1 = new Joueur(entrée("Entrez un nom: "));
            Joueur joueur2 = new Joueur(entrée("Entrez un nom: "));
            
            
            try {
				joueur1.remplirPiochePersonelle(piochePrincipal);
			} catch (PiocheVideException e) {
				e.printStackTrace();
			}
            try {
				joueur2.remplirPiochePersonelle(piochePrincipal);
			} catch (PiocheVideException e) {
				e.printStackTrace();
			}
           
           
            try {
				joueur1.initialiserRack();
			} catch (PiocheVideException e) {
				e.printStackTrace();
			}
            try {
				joueur2.initialiserRack();
			} catch (PiocheVideException e) {
				e.printStackTrace();
			}
           
           
            while (continuer) {
            	
				if (tourJoueur) {
					message(joueur1.nom() + " : " + joueur1.affichertuilesRack());
					message("Voici votre pioche : " + joueur1.afficherPiochePersonelle());
					message("Voici le nombre de tuiles présent dans votre pioche : "
							+ joueur1.taillePiochePersonelle());
					i = entrée("Voulez-vous changer votre rack ? : (oui/non)");
					if (i.equals("oui")) {
						try {
							joueur1.echangerRack();
						} catch (PiocheVideException e) {
							message("impossible d'echanger le rack la pioche est vide");
						}
						message("Votre pioche vien d'etre modifier voici la nouvelle : "
								+ joueur1.affichertuilesRack());
						message("Voici le nombre de tuiles présent dans votre pioche : "
								+ joueur1.taillePiochePersonelle());
					}
					i = entrée("Voulez-vous quitter la partie ? : (oui/non)");
					if (i.equals("oui")) {
						message("Vous quittez la patie !");
						continuer = false;
					}
					message("");
				}
				
				if (!tourJoueur) {
					message(joueur2.nom() + " : " + joueur2.affichertuilesRack());
					message("Voici votre pioche : " + joueur2.afficherPiochePersonelle());
					message("Voici le nombre de tuiles présent : " + joueur2.taillePiochePersonelle());
					i = entrée("Voulez-vous changer votre rack ? : (oui/non)");
					if (i.equals("oui")) {
						try {
							joueur2.echangerRack();
						} catch (PiocheVideException e) {
							message("impossible d'echanger le rack la pioche est vide");
						}
						message("Votre pioche vien d'etre modifier voici la nouvelle : "
								+ joueur2.affichertuilesRack());
						message("Voici le nombre de tuiles présent dans votre pioche : "
								+ joueur2.taillePiochePersonelle());
					}
					i = entrée("Voulez-vous quitter la partie ? : (oui/non)");
					if (i.equals("oui")) {
						message("Vous quittez la patie !");
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