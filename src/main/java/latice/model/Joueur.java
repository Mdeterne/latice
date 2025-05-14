package latice.model;


import java.util.ArrayList;
import java.util.List;

public class Joueur {

	private final String nom;
	private int point;
	private Rack rack;
	private PiochePersonelle piochePersonelle;
	
	public Joueur(String nom) {
		this.nom = nom;
		this.point = 0;
		this.rack = new Rack();
		this.piochePersonelle = new PiochePersonelle();
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
		anciensJetons.forEach(jeton -> piochePersonelle.ajouterJeton(jeton));
		piochePersonelle.mélanger();
		while (rack.afficherJetons().size() < Rack.TAILLE_MAX && !piochePersonelle.estVide()) {
            rack.ajouterJeton(piochePersonelle.piocher());
        }
		return (this.nom+" à échanger son rack");
	}
	
	public String passer() {
		return (this.nom+" à passer sont tour");
	}

	public void ajouterPoints(int nombresPoints) {
		point = point + nombresPoints;
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
	 
	public String afficherJetonsRack() {
		return ""+rack().afficherJetons();
	}
	
	public void initialiserRack() {
	    rack.vider();
	     
	    for (int i = 0; i < Rack.TAILLE_MAX && !piochePersonelle.estVide(); i++) {
	        Jeton jeton = piochePersonelle.piocher();
	        rack.ajouterJeton(jeton);
	    }
	}
	
	public int taillePiochePersonelle() {
		return piochePersonelle().taille();
	}
	
	public void remplirPiochePersonelle(Pioche piochePrincipal) {
		piochePersonelle().remplirPiochePerso(piochePrincipal);
	}
	 
	public PiochePersonelle piochePersonelle() {
		return piochePersonelle;
	}
	
	public ArrayList<Jeton> afficherPiochePersonelle() {
		return piochePersonelle.pioche();
	}
	
}
