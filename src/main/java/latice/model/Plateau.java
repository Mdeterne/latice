package latice.model;

public class Plateau {
    private int taille = 9;
	private Case[][] plateau = new Case[taille][taille];

    
    public Plateau() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {

            	Position position = new Position(i, j);
            	boolean estSoleil = false;
            	boolean estLune = false;

				for (int y = 0; y<3; y++) {
					if( position.x() == y && position.y() == y) {
						estSoleil = true;
					} else if (position.x() == taille - 1 - y && position.y() == y) {
						estSoleil = true;
					} else if (position.x() == y && position.y() == taille - 1 - y) {
						estSoleil = true;
					} else if (position.x() == taille - 1 - y && position.y() == taille - 1 - y) {
						estSoleil = true;
					}
				}
				if (position.x() == 0 || position.y() == 0 || position.x() == taille - 1 || position.y() == taille - 1) {
					if (position.x() == (taille - 1) / 2 || position.y() == (taille - 1) / 2 ) {
						estSoleil = true;
					}
				}
				if (position.x() == (taille-1)/2 && position.y() == (taille-1)/2) {
					estLune = true;
				}
            	Case nouvelleCase = new Case(position, estSoleil, estLune);
            	plateau[i][j] = nouvelleCase;
            }
        }
    }

    public void afficherPlateau() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                Case c = plateau[i][j];
                if (c.estSoleil()) {
                    System.out.print("[X]");
                } else if (c.estLune()) {
					System.out.print("[L]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }

    public boolean poserJeton(Jeton jeton, Position position) {
        if (position.x() < 0 || position.x() >= taille || position.y() < 0 || position.y() >= taille) {
            return false; // Position hors du plateau
        }
        
        Case caseCible = plateau[position.x()][position.y()];
        
        if (!caseCible.estVide()) {
            return false; // Case déjà occupée
        }
        
        caseCible.poserJeton(jeton);
        return true;
    }
    
    public boolean estPositionValide(Position position) {
        return position.x() >= 0 && position.x() < taille 
            && position.y() >= 0 && position.y() < taille;
    }
    
    public void donnerPoint(Joueur joueur, int nombres_points) {
    	joueur.ajouterPoints(nombres_points);
    }
    
    public Case getCase(Position position) { //obliger de mettre getCase car case est un type
        if (estPositionValide(position)) {
            return plateau[position.x()][position.y()];
        }
        return null;
    }
    public int getTaille() {
    	return taille;
    }
    
}