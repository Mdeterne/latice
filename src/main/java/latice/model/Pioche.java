package latice.model;

import java.util.ArrayList;


public class Pioche {

	private ArrayList<Jeton> piochePrincipal;
	
	
	public Pioche() {
		piochePrincipal = new ArrayList<Jeton>();
		for (Couleur couleur : Couleur.values()) {
            for (Symbole symbole : Symbole.values()) {
            	piochePrincipal.add(new Jeton(couleur, symbole));
            	piochePrincipal.add(new Jeton(couleur, symbole)); 
            }
        }
	}
	public Jeton piocher() {
		if (piochePrincipal.isEmpty()) return null;
        return piochePrincipal.remove(piochePrincipal.size() - 1); // Retire le dernier
    }
	
	public boolean estVide() {
        return piochePrincipal.isEmpty();
    }
	
	public void ajouterJeton(Jeton jeton) {
		piochePrincipal.add(jeton); // Ajoute à la fin
    }
}
