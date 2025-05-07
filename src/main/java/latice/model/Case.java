package latice.model;

public class Case {
    private boolean estSoleil;
    private Position position;
    private Jeton jeton;
    
    public Case(Position position, boolean estSoleil) {
    	this.estSoleil = estSoleil;
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
    
    public Position getPosition() {
        return position;
    }
}