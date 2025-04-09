package latice.model;

import java.util.ArrayList;
import java.util.List;

public class Rack {

	private List jetons = new ArrayList<Jeton>();
	
	public Rack() {}
	
	public void vider() {
		jetons.clear();
	}
	
	public void ajouterJeton(Jeton jeton) {
		jetons.add(jeton);
	}
}
