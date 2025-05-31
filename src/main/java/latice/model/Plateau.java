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
    
    
    //renvoie le nombre de tuiles compatibles si une tuiles n'est pas compatible renvoie 0
    public int nombreTuilesCompatibles(Position pos, Tuile tuile) {
        int x = pos.x();
        int y = pos.y();
        int tuilesCompatibles = 0;

        // 4 directions(haut, bas, gauche, droite)
        int[][] directions = { {0, -1}, {0, 1}, {-1, 0}, {1, 0} };

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

    
    
    
    

    // Compte le nombre de tuiles compatibles autour d'une case à partir de son id
    public int compterCompatibilitesAutourCaseId(String caseId, Tuile tuile) {
        int count = 0;
        if (caseId == null || caseId.length() != 6) return 0;
        // Récupère la colonne et la ligne à partir de l'id
        int col = Character.getNumericValue(caseId.charAt(4));
        int lig = Character.getNumericValue(caseId.charAt(5));
        // Pour chaque direction (haut, bas, gauche, droite)
        // Haut : case col, lig-1
        if (lig > 1) {
            String idHaut = "case" + col + (lig-1);
            if (caseCompatible(idHaut, tuile)) count++;
        }
        // Bas : case col, lig+1
        if (lig < 9) {
            String idBas = "case" + col + (lig+1);
            if (caseCompatible(idBas, tuile)) count++;
        }
        // Gauche : case col-1, lig
        if (col > 1) {
            String idGauche = "case" + (col-1) + lig;
            if (caseCompatible(idGauche, tuile)) count++;
        }
        // Droite : case col+1, lig
        if (col < 9) {
            String idDroite = "case" + (col+1) + lig;
            if (caseCompatible(idDroite, tuile)) count++;
        }
        return count;
    }

    private boolean caseCompatible(String caseId, Tuile tuile) {
        if (caseId == null || caseId.length() != 6) return false;
        int col = Character.getNumericValue(caseId.charAt(4));
        int lig = Character.getNumericValue(caseId.charAt(5));
        if (col < 1 || col > 9 || lig < 1 || lig > 9) return false;
        Position pos = new Position(col-1, lig-1);
        Case c = getCase(pos);
        if (c == null) return false;
        Tuile t = c.getTuile();
        if (t == null) return false;
        return t.couleur() == tuile.couleur() || t.symbole() == tuile.symbole();
    }
    
    private Tuile getTuileCaseId(String caseId) {
        if (caseId == null || caseId.length() != 6) return null;
        int col = Character.getNumericValue(caseId.charAt(4));
        int lig = Character.getNumericValue(caseId.charAt(5));
        if (col < 1 || col > 9 || lig < 1 || lig > 9) return null;
        Position pos = new Position(col-1, lig-1);
        Case c = getCase(pos);
        if (c == null) return null;
        return c.getTuile();
    }
    
    public boolean detecterAngleAmateur(String caseId) {
        if (caseId == null || caseId.length() != 6) return false;
        int col = Character.getNumericValue(caseId.charAt(4));
        int lig = Character.getNumericValue(caseId.charAt(5));
        Tuile centre = getTuileCaseId(caseId);
        if (centre == null) return false;
        // Haut + Gauche
        if (col > 1 && lig > 1) {
            Tuile tHaut = getTuileCaseId("case" + col + (lig-1));
            Tuile tGauche = getTuileCaseId("case" + (col-1) + lig);
            if (tHaut != null && tGauche != null) {
                boolean centreOk = centre.estCompatible(tHaut) || centre.estCompatible(tGauche);
                boolean hautOk = tHaut.estCompatible(centre) || tHaut.estCompatible(tGauche);
                boolean gaucheOk = tGauche.estCompatible(centre) || tGauche.estCompatible(tHaut);
                if (centreOk && hautOk && gaucheOk) return true;
            }
        }
        // Haut + Droite
        if (col < 9 && lig > 1) {
            Tuile tHaut = getTuileCaseId("case" + col + (lig-1));
            Tuile tDroite = getTuileCaseId("case" + (col+1) + lig);
            if (tHaut != null && tDroite != null) {
                boolean centreOk = centre.estCompatible(tHaut) || centre.estCompatible(tDroite);
                boolean hautOk = tHaut.estCompatible(centre) || tHaut.estCompatible(tDroite);
                boolean droiteOk = tDroite.estCompatible(centre) || tDroite.estCompatible(tHaut);
                if (centreOk && hautOk && droiteOk) return true;
            }
        }
        // Bas + Gauche
        if (col > 1 && lig < 9) {
            Tuile tBas = getTuileCaseId("case" + col + (lig+1));
            Tuile tGauche = getTuileCaseId("case" + (col-1) + lig);
            if (tBas != null && tGauche != null) {
                boolean centreOk = centre.estCompatible(tBas) || centre.estCompatible(tGauche);
                boolean basOk = tBas.estCompatible(centre) || tBas.estCompatible(tGauche);
                boolean gaucheOk = tGauche.estCompatible(centre) || tGauche.estCompatible(tBas);
                if (centreOk && basOk && gaucheOk) return true;
            }
        }
        // Bas + Droite
        if (col < 9 && lig < 9) {
            Tuile tBas = getTuileCaseId("case" + col + (lig+1));
            Tuile tDroite = getTuileCaseId("case" + (col+1) + lig);
            if (tBas != null && tDroite != null) {
                boolean centreOk = centre.estCompatible(tBas) || centre.estCompatible(tDroite);
                boolean basOk = tBas.estCompatible(centre) || tBas.estCompatible(tDroite);
                boolean droiteOk = tDroite.estCompatible(centre) || tDroite.estCompatible(tBas);
                if (centreOk && basOk && droiteOk) return true;
            }
        }
        return false;
    }
}
