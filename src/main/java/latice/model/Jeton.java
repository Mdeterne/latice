package latice.model;

public class Jeton {
	private Couleur couleur;
	private Symbole symbole;
	
	public Jeton(Couleur couleur, Symbole symbole) {
		this.couleur = couleur;
		this.symbole = symbole;
	}
	
	public boolean estCompatible(Jeton autre) {
        return this.couleur == autre.couleur || this.symbole == autre.symbole; 
    }
	
	public Couleur couleur() {
		return couleur;
	}
	
	public Symbole symbole() {
		return symbole;
	}
	
	@Override
	public String toString() {
		return "Jeton [" + couleur + ", " + symbole + "]";
		
	}
	
}
