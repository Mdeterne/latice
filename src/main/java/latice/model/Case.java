package latice.model;

public class Case {
    private boolean bonus;
    private Position position;
    private Jeton jeton;
    
    public Case(boolean bonus, Position position) {
        this.bonus = bonus;
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
    
    public boolean Bonus() {
        return bonus;
    }
    
    public Position getPosition() {
        return position;
    }
}