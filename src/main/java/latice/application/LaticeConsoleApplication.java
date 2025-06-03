package latice.application;

import static latice.console.LaticeConsole.entrée;
import static latice.console.LaticeConsole.message;

import java.util.Random;

import latice.application.JavaFX.Arbitre;
import latice.model.Joueur;
import latice.model.PiochePrincipal;
import latice.model.Plateau;
import latice.model.Position;
import latice.model.Tuile;
import latice.util.exception.ActionsInsuffisanteException;
import latice.util.exception.PiocheVideException;

public class LaticeConsoleApplication {

	public static void main(String[] args) {
		message("-----------------------------------------------------");
		message("-- Bienvenue dans notre magnifique jeu de latice ! --");
		message("-- développé par Julian Ray-Constanty              --");
		message("-- et par Julian Barre                             --");
		message("-- et par Marty Benjamin                            --");
		message("-----------------------------------------------------");

		Arbitre arbitre = new Arbitre();
		String nomJ1 = entrée("Nom du joueur 1 : ");
		String nomJ2 = entrée("Nom du joueur 2 : ");
		String nomJ3 = entrée("Nom du joueur 3 : ");
		String nomJ4 = entrée("Nom du joueur 4 : ");
		arbitre.initialiser(nomJ1, nomJ2, nomJ3, nomJ4);

		boolean continuer = true;

		while (continuer) {
			Joueur joueur = arbitre.getJoueurCourant();
			Plateau plateau = arbitre.getPlateau();

			message("\nC'est au tour de " + joueur.nom());
			message("Voici votre rack : " + joueur.affichertuilesRack());
			message("Tuiles restantes dans votre pioche : " + joueur.taillePiochePersonelle());

			plateau.afficherPlateau();

			String action = entrée("Voulez-vous (poser / changer / quitter) ?");
			if (action.equals("quitter")) {
				continuer = false;
				message("Fin de la partie !");
			} else if (action.equals("changer")) {
				try {
					arbitre.changerRack();
					try {
						arbitre.retirerAction();
					} catch (ActionsInsuffisanteException e) {
						
						message(e.getMessage());
					}
					message("Nouveau rack : " + joueur.affichertuilesRack());
				} catch (PiocheVideException e) {
					message("La pioche est vide.");
				}
			} else if (action.equals("poser")) {
				String choix = entrée("Quelle tuile poser ? (indice 0 à 4) : ");
				int index = Integer.parseInt(choix);
				Tuile tuile = joueur.tuilesRack().get(index);

				int x = Integer.parseInt(entrée("Position X (0-8) : "));
				int y = Integer.parseInt(entrée("Position Y (0-8) : "));
				Position position = new Position(x, y);

				if (arbitre.premierCoup() && !(x == 4 && y == 4)) {
					message("Le premier coup doit être sur la case centrale");
					continue;
				}

				int ok = arbitre.jouerTuile(position, tuile);
				if (ok == 5) {
					message("Tuile posée");
				} else {
					message("Placement invalide");
				}
			}

			if (arbitre.getActions() == 0) {
				try {
					arbitre.changerTour();
					arbitre.remplireRack();
				} catch (PiocheVideException e) {
					message("Fin de la partie : la pioche est vide.");
					continuer = false;
				}
			}
		}
	}
}