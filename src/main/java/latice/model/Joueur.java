package latice.model;

public class Joueur {

	private final String nom;
	private int point;
	private Rack rack;
	private Pioche pioche;
	
	public Joueur(String nom, int point) {
		this.nom = nom;
		this.point = point;
	}
	
	public String jouer(Plateau plateau, Jeton jeton, Position position) {
		//TODO le joueur doit placer un jeton sur un plateau
		return (this.nom + " vien de jouer");
	}
	
	public String acheter() {
		//TODO le joueur doit pouvoir acheter un tour avec 2points
		return (this.nom+" à acheter un tour");
	}
	
	public String echangerRack() {
		//TODO le joueur doit pouvoir echanger toute les tuilles de son rack
		return (this.nom+" à échanger son rack");
	}
	
	public String passer() {
		//TODO le joueur doit passer sont tour
		return (this.nom+" à passer sont tour");
	}
	
}
