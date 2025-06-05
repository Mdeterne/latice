package latice.model;

import java.util.ArrayList;
import java.util.Random;

import latice.util.exception.ActionsInsuffisanteException;
import latice.util.exception.CaseInaccessibleException;
import latice.util.exception.PiocheVideException;
import latice.util.exception.PointInsuffisantException;

public class Arbitre {

	Random random = new Random();

	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur joueur3;
	private Joueur joueur4;

	private Joueur joueurActuel;
	private int nombreTours = 20;
	private int nombreJoueur = 2;

	private PiochePrincipal piochePrincipal = new PiochePrincipal();

	private final Plateau plateau = new Plateau();

	private boolean premierCoup = true;

	public Joueur getJoueurCourant() {
		return joueurActuel;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public Joueur getJoueur3() {
		return joueur3;
	}

	public Joueur getJoueur4() {
		return joueur4;
	}

	public boolean premierCoup() {
		return premierCoup;
	}

	public int jouerTuile(Position position, Tuile tuile) {
		int error = 5;

		if (!plateau.estPositionValide(position)) {
			error = 0;
		}

		int nombreTuilesCompatibles = plateau.nombreTuilesCompatibles(position, tuile);
		if (!premierCoup && nombreTuilesCompatibles == 0) {
			error = 1;
		}

		if (premierCoup) {
			if (position.x() != 4 && position.y() != 4) {
				error = 2;
			}
			premierCoup = false;
		}

		if (getJoueurCourant().actions() == 0) {
			error = 4;
		}

		int pointsGagnes = 0;

		if (nombreTuilesCompatibles == 2) {
			pointsGagnes += 1;
		} else if (nombreTuilesCompatibles == 3) {
			pointsGagnes += 2;
		} else if (nombreTuilesCompatibles == 4) {
			pointsGagnes += 4;
		}

		if (plateau.getCase(position).estSoleil()) {
			pointsGagnes += 2;
		}

		if (error == 5) {

			try {
				plateau.posertuile(tuile, position);
				plateau.donnerPoint(getJoueurCourant(), pointsGagnes);
				joueurActuel.ajoutTuilePosé();
				try {
					retirerAction();
				} catch (ActionsInsuffisanteException e) {
					System.out.println(e.getMessage());
				}
				retirertuile(tuile);
				return error;
			} catch (CaseInaccessibleException e) {
				System.out.println(e.getMessage());
				error = 3;
				return error;
			}

		} else {
			return error;
		}
	}

	public boolean estFinDuJeu() {
		System.out.println(nombreTours);
		if (nombreTours < 1) {
			return true;
		}
		return false;
	}

	public int nombreTours() {
		return nombreTours;
	}

	public Joueur getGagnant() {
		if (joueur1.getTuilesPosées() > joueur2.getTuilesPosées()) {
			return joueur1;
		} else if (joueur1.getTuilesPosées() < joueur2.getTuilesPosées()) {
			return joueur2;
		} else if (joueur2.getTuilesPosées() < joueur3.getTuilesPosées()) {
			return joueur3;
		} else if (joueur3.getTuilesPosées() < joueur4.getTuilesPosées()) {
			return joueur4;
		} else {
			return null;
		}
	}

	public void initialiser(String nomJoueur1, String nomJoueur2, String nomJoueur3, String nomJoueur4) {
		joueur1 = new Joueur(nomJoueur1);
		joueur2 = new Joueur(nomJoueur2);
		joueur3 = new Joueur(nomJoueur3);
		joueur4 = new Joueur(nomJoueur4);

		if (!nomJoueur3.equals("")) {
			nombreJoueur = 3;
			joueur1.definir3Joueur();
			joueur2.definir3Joueur();
			joueur3.definir3Joueur();
			nombreTours = 30;
		}
		if (!nomJoueur4.equals("")) {
			nombreJoueur = 4;
			joueur1.definir4Joueur();
			joueur2.definir4Joueur();
			joueur3.definir4Joueur();
			joueur4.definir4Joueur();
			nombreTours = 40;
		}

		try {
			joueur1.remplirPiochePersonelle(piochePrincipal);
			joueur2.remplirPiochePersonelle(piochePrincipal);
			joueur1.initialiserRack();
			joueur2.initialiserRack();
			if (nombreJoueur <= 3) {
				joueur3.remplirPiochePersonelle(piochePrincipal);
				joueur3.initialiserRack();
			}
			if (nombreJoueur == 4) {
				joueur3.remplirPiochePersonelle(piochePrincipal);
				joueur3.initialiserRack();
				joueur4.remplirPiochePersonelle(piochePrincipal);
				joueur4.initialiserRack();
			}
		} catch (PiocheVideException e) {
			System.out.println(e.getMessage());
		}

		if (random.nextBoolean()) {
			joueurActuel = joueur1;
		} else {
			joueurActuel = joueur2;
		}
	}

	public String nomJoueur() {
		return joueurActuel.nom();
	}

	public ArrayList<Tuile> RackJoueur() {
		return joueurActuel.tuilesRack();
	}

	public int pointJoueur() {
		return joueurActuel.point();
	}

	public void changerRack() throws PiocheVideException {
		joueurActuel.echangerRack();
	}

	public int getActions() {
		return joueurActuel.actions();
	}

	public void retirerAction() throws ActionsInsuffisanteException {
		joueurActuel.enleverAction();
	}

	public void remplireRack() throws PiocheVideException {
		joueurActuel.remplirRack();
	}

	public void retirertuile(Tuile tuile) {
		joueurActuel.retirerTuile(tuile);
	}

	public int taillePioche() {
		return joueurActuel.taillePiochePersonelle();
	}

	public void acheterUnTour() throws PointInsuffisantException {
		joueurActuel.enleverPoints(2);
		joueurActuel.ajouterUneAction();
	}

	public void changerTour() {
		if (nombreJoueur == 2) {
			if (getJoueurCourant() == joueur1) {
				joueurActuel = joueur2;
				joueur2.réinitialiserActions();
			} else {
				joueurActuel = joueur1;
				joueur1.réinitialiserActions();
			}
		}

		if (nombreJoueur == 3) {
			if (getJoueurCourant() == joueur1) {
				joueurActuel = joueur2;
				joueur2.réinitialiserActions();
			} else if (getJoueurCourant() == joueur2) {
				joueurActuel = joueur3;
				joueur3.réinitialiserActions();
			} else {
				joueurActuel = joueur1;
				joueur1.réinitialiserActions();
			}
		}

		if (nombreJoueur == 4) {
			if (getJoueurCourant() == joueur1) {
				joueurActuel = joueur2;
				joueur2.réinitialiserActions();
			} else if (getJoueurCourant() == joueur2) {
				joueurActuel = joueur3;
				joueur3.réinitialiserActions();
			} else if (getJoueurCourant() == joueur3) {
				joueurActuel = joueur4;
				joueur4.réinitialiserActions();
			} else {
				joueurActuel = joueur1;
				joueur1.réinitialiserActions();
			}
		}
		nombreTours = nombreTours - 1;
	}

	public Plateau getPlateau() {
		return plateau;
	}
}
