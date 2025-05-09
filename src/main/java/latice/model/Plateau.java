package latice.model;

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
	        if ((x == i && y == i) || 
	            (x == taille - 1 - i && y == i) || 
	            (x == i && y == taille - 1 - i) || 
	            (x == taille - 1 - i && y == taille - 1 - i)) {
	            return true;
	        }
	    }

	    // Milieu des bords
	    if ((x == 0 || y == 0 || x == taille - 1 || y == taille - 1) &&
	        (x == (taille - 1) / 2 || y == (taille - 1) / 2)) {
	        return true;
	    }

	    return false;
	}
	
	private boolean estPositionLune(Position position) {
	    int centre = (taille - 1) / 2;
	    return position.x() == centre && position.y() == centre;
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