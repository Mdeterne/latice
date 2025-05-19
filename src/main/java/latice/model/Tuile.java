package latice.model;

public class Tuile {
	private Couleur couleur;
	private Symbole symbole;
	
	public Tuile(Couleur couleur, Symbole symbole) {
		this.couleur = couleur;
		this.symbole = symbole;
	}
	
	public boolean estCompatible(Tuile autre) {
        return couleur == autre.couleur || symbole == autre.symbole; 
    }
	
	public Couleur couleur() {
		return couleur;
	}
	
	public Symbole symbole() {
		return symbole;
	}
	
	@Override
	public String toString() {
		return "Tuile [" + couleur + ", " + symbole + "]";
	}
	
}
