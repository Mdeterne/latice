package latice.model;

import java.util.ArrayList;

public class Rack {

	private ArrayList<Jeton> jetons = new ArrayList<Jeton>();
	
	
	public void vider() {
		jetons.clear();
	}

	public void ajouterJeton(Jeton jeton) {
		jetons.add(jeton);
	}
}
