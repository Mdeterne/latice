package latice.model;


import java.util.List;

public class Joueur {

	private final String nom;
	private int point;
	private Rack rack;
	private PiochePerso pioche;
	
	public Joueur(String nom) {
		this.nom = nom;
		this.point = 0;
		this.rack = new Rack();
		this.pioche = new PiochePerso();
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
		 List<Jeton> anciensJetons = rack.vider();
		anciensJetons.forEach(jeton -> pioche.ajouterJeton(jeton));
		while (rack.Jetons().size() < Rack.TAILLE_MAX && !pioche.estVide()) {
            rack.ajouterJeton(pioche.piocher());
        }
		return (this.nom+" à échanger son rack");
	}
	
	public String passer() {
		//TODO le joueur doit passer sont tour
		return (this.nom+" à passer sont tour");
	}

	public int ajouterPoints(int nombres_points) {
		return point+nombres_points;
		
	}
	
}
