package latice.model;

import java.util.ArrayList;
import java.util.List;

public class Rack {

	private ArrayList<Tuile> tuiles = new ArrayList<Tuile>();
	public static final int TAILLE_MAX = 5;

	public Rack() {
		tuiles = new ArrayList<>();
	}

	public List<Tuile> vider() {
		List<Tuile> tuilesVides = new ArrayList<>(tuiles);
		tuiles.clear();
		return tuilesVides;
	}

	public void ajouterTuile(Tuile tuile) {
		if (tuiles.size() < TAILLE_MAX) {
			tuiles.add(tuile);
		}
	}

	public void retirerTuile(Tuile tuile) {
		tuiles.remove(tuile);
	}

	public ArrayList<Tuile> afficherTuiles() {
		return new ArrayList<>(tuiles);
	}

	public int taille() {
		return tuiles.size();
	}
}
