package latice.model;

import java.util.ArrayList;
import java.util.List;




public class Rack {

	private ArrayList<Jeton> jetons = new ArrayList<Jeton>();
	public static final int TAILLE_MAX = 5;
	
	public Rack() {
        jetons = new ArrayList<>();
    }
	
	public List<Jeton> vider() {
        List<Jeton> jetonsVides = new ArrayList<>(jetons);
        jetons.clear();
        return jetonsVides;
    }

	public void ajouterJeton(Jeton jeton) {
		if (jetons.size() < TAILLE_MAX) {
            jetons.add(jeton);
        }
	}
	
	public void retirerJeton(Jeton jeton) {
        if (jetons.contains(jeton)) {
            jetons.remove(jeton);   
        }
    }
	
	public ArrayList<Jeton> afficherJetons() {
        return new ArrayList<>(jetons);
    }
}
