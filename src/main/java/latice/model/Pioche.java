package latice.model;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Pioche {

protected ArrayList<Jeton> pioche;
	
	protected Pioche() {
		this.pioche = new ArrayList<Jeton>();
		
	}
	
	public boolean estVide() {
        return this.pioche.isEmpty();
    }
	
	public void ajouterJeton(Jeton jeton) {
		pioche.add(jeton); // Ajoute à la fin
    }
	
	public Jeton piocher() {
		if (pioche.isEmpty()) return null;
        return pioche.remove(pioche.size() - 1); // Retire le dernier
    }
	
	
	public int taille() {
		return pioche.size();
	}
	
	public ArrayList pioche() {
		return this.pioche;
	}
}
