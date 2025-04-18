package latice.model;


import java.util.List;

public class Joueur {

	private final String nom;
	private int point;
	private Rack rack;
	public PiochePerso pioche;
	
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
	
	public Boolean acheter() {
		if (point >= 2) {
			point-=2;
			return true;
		}
		return false;
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
		return (this.nom+" à passer sont tour");
	}

	public int ajouterPoints(int nombres_points) {
		return point+nombres_points;	
	}
	
	 public String nom() { 
		 return nom;
	}
	 public int point() { 
		 return point; 
	}
	 
	 public Rack rack() { 
		 return rack; 
	}
	 
	 public void initialiserRack() {
	     rack.vider();
	     
	     for (int i = 0; i < Rack.TAILLE_MAX && !pioche.estVide(); i++) {
	         Jeton jeton = pioche.piocher();
	         rack.ajouterJeton(jeton);
	     }
	 }
	
	
}
