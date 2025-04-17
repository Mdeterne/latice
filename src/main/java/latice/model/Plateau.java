package latice.model;

public class Plateau {
    private Case plateau[][];
    
    public Plateau() {
        plateau = new Case[9][9];
        // Initialisation du plateau avec des cases
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
    
    public void donnerPoint(Joueur joueur, int nombres_points) {
        //TODO Implémenter la méthode donner_point
    }
    
    public void calculerPoint() {
        //TODO Implémenter la méthode calculerPoint
    }
}