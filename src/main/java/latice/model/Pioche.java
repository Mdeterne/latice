package latice.model;

import java.util.ArrayList;
import java.util.Collections;

import latice.test.exception.PiocheVideException;

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
	
	public Jeton piocher() throws PiocheVideException {
		if (pioche.isEmpty()) {
			throw new PiocheVideException("la pioche est vide vous ne pouvez pas prendre de jeton");
		}
        return pioche.remove(pioche.size() - 1); // Retire le dernier
    }
	
	
	public int taille() {
		return pioche.size();
	}
	
	public ArrayList<Jeton> pioche() {
		return this.pioche;
	}
	
	public void mélanger() {
		Collections.shuffle(pioche);
	}
}
