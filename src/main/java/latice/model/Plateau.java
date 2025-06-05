package latice.model;

import latice.util.exception.CaseInaccessibleException;

public class Plateau {
	private int taille = 9;
	private Case[][] plateau = new Case[taille][taille];

	public Plateau() {
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				Position position = new Position(i, j);
				boolean estSoleil = estPositionSoleil(position);
				boolean estLune = estPositionLune(position);
				plateau[i][j] = new Case(position, estSoleil, estLune);
			}
		}
	}

	private boolean estPositionSoleil(Position position) {
		int x = position.x();
		int y = position.y();

		// Diagonales autour des coins
		for (int i = 0; i < 3; i++) {
			if ((x == i && y == i) || (x == taille - 1 - i && y == i) || (x == i && y == taille - 1 - i)
					|| (x == taille - 1 - i && y == taille - 1 - i)) {
				return true;
			}
		}

		// Milieu des bords
		if ((x == 0 || y == 0 || x == taille - 1 || y == taille - 1)
				&& (x == (taille - 1) / 2 || y == (taille - 1) / 2)) {
			return true;
		}

		return false;
	}

	private boolean estPositionLune(Position position) {
		int centre = (taille - 1) / 2;
		return position.x() == centre && position.y() == centre;
	}

	public void afficherPlateau() {
		System.out.println("    0   1   2   3   4   5   6   7   8");
		System.out.println("  +---+---+---+---+---+---+---+---+---+");

		for (int y = 0; y < taille; y++) {
			System.out.print(y + " |");
			for (int x = 0; x < taille; x++) {
				Case c = plateau[x][y];
				Tuile t = c.getTuile();

				if (t != null) {
					char sym = t.symbole().name().charAt(0);
					char coul = t.couleur().name().charAt(0);
					System.out.print("" + coul + sym + "|");
				} else if (c.estLune()) {
					System.out.print(" L |");
				} else if (c.estSoleil()) {
					System.out.print(" S |");
				} else {
					System.out.print("   |");
				}
			}
			System.out.println();
			System.out.println("  +---+---+---+---+---+---+---+---+---+");
		}
	}

	public void posertuile(Tuile tuile, Position position) throws CaseInaccessibleException {
		plateau[position.x()][position.y()].posertuile(tuile);
	}

	public boolean estPositionValide(Position position) {
		return position.x() >= 0 && position.x() < taille && position.y() >= 0 && position.y() < taille;
	}

	public void donnerPoint(Joueur joueur, int nombres_points) {
		joueur.ajouterPoints(nombres_points);
	}

	public Case getCase(Position position) { // obliger de mettre getCase car case est un type
		if (estPositionValide(position)) {
			return plateau[position.x()][position.y()];
		}
		return null;
	}

	public int getTaille() {
		return taille;
	}

	// renvoie le nombre de tuiles compatibles si une tuiles n'est pas compatible
	// renvoie 0
	public int nombreTuilesCompatibles(Position pos, Tuile tuile) {
		int x = pos.x();
		int y = pos.y();
		int tuilesCompatibles = 0;

		// 4 directions(haut, bas, gauche, droite)
		int[][] directions = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

		for (int[] dir : directions) {
			int nx = x + dir[0];
			int ny = y + dir[1];
			Position voisinPosition = new Position(nx, ny);

			if (estPositionValide(voisinPosition)) {
				Tuile voisine = getCase(voisinPosition).getTuile();
				if (voisine != null) {
					tuilesCompatibles += 1;
					if (!tuile.estCompatible(voisine)) {
						return 0;
					}
				}
			}
		}

		return tuilesCompatibles;
	}
}