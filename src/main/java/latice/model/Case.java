package latice.model;

public class Case {
    private Position position;
    private Jeton jeton;
    private boolean estSoleil;
    private boolean estLune;
    
    public Case(Position position, boolean estSoleil, boolean estLune) {
    	this.estSoleil = estSoleil;
    	this.estLune = estLune;
        this.position = position;
        this.jeton = null;
    }
    
    public boolean estVide() {
        return jeton == null;
    }
    
    public void poserJeton(Jeton jeton) {
        this.jeton = jeton;
    }
    
    public Jeton getJeton() {
        return jeton;
    }
    
    public boolean estSoleil() {
        return estSoleil;
    }
    
    public boolean estLune() {
    	return estLune;
    }
    
    public Position getPosition() {
        return position;
    }
}