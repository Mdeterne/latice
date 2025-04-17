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
	    if (plateau.poserJeton(jeton, position)) {
	        return this.nom + " a joué le jeton " + jeton + " en position " + position.x() + "," + position.y();
	    } else {
	        return this.nom + " ne peut pas jouer ici";
	    }
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
