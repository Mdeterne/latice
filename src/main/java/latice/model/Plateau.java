package latice.model;

public class Plateau {
    private Case plateau[][];
    
    public Plateau() {
        plateau = new Case[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
            }
        }
    }

    public boolean poserJeton(Jeton jeton, Position position) {
        if (position.x() < 0 || position.x() >= 9 || position.y() < 0 || position.y() >= 9) {
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
        return position.x() >= 0 && position.x() < 9 
            && position.y() >= 0 && position.y() < 9;
    }
    
    public void donnerPoint(Joueur joueur, int nombres_points) {
    	joueur.ajouterPoints(nombres_points);
    }
    
    public Case getCase(Position position) {
        if (estPositionValide(position)) {
            return plateau[position.x()][position.y()];
        }
        return null;
    }
    
}