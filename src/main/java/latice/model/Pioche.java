package latice.model;

import java.util.ArrayList;
import java.util.Collections;

import latice.test.exception.PiocheVideException;

public abstract class Pioche {

protected ArrayList<Tuile> pioche;
	
	protected Pioche() {
		this.pioche = new ArrayList<Tuile>();
		
	}
	
	public boolean estVide() {
        return this.pioche.isEmpty();
    }
	
	public void ajoutertuile(Tuile tuile) {
		pioche.add(tuile); // Ajoute à la fin
    }
	
	public Tuile piocher() throws PiocheVideException {
		if (pioche.isEmpty()) {
			throw new PiocheVideException("la pioche est vide vous ne pouvez pas prendre de tuile");
		}
        return pioche.remove(pioche.size() - 1); // Retire le dernier
    }
	
	
	public int taille() {
		return pioche.size();
	}
	
	public ArrayList<Tuile> pioche() {
		return this.pioche;
	}
	
	public void mélanger() {
		Collections.shuffle(pioche);
	}
}
