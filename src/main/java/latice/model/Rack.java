package latice.model;

import java.util.ArrayList;




public class Rack {

	private ArrayList<Jeton> jetons = new ArrayList<Jeton>();
	public static final int TAILLE_MAX = 5;
	
	public Rack() {
        jetons = new ArrayList<>();
    }
	
	public void vider() {
		jetons.clear();
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
	
	public ArrayList<Jeton> Jetons() {
        return new ArrayList<>(jetons);
    }
}
