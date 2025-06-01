package latice.application.JavaFX;


import java.util.ArrayList;
import java.util.Random;

import latice.model.Joueur;
import latice.model.PiochePrincipal;
import latice.model.Plateau;
import latice.model.Position;
import latice.model.Tuile;
import latice.util.exception.CaseInaccessibleException;
import latice.util.exception.PiocheVideException;

public class Arbitre {
	
	Random random = new Random();
	
	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur joueurActuel;
	
	private PiochePrincipal piochePrincipal = new PiochePrincipal();
	
	private final Plateau plateau = new Plateau();
	
	private boolean premierCoup = true;

    public Joueur getJoueurCourant() {
        return joueurActuel;
    }

    public Joueur getJoueur1() { return joueur1; }
    public Joueur getJoueur2() { return joueur2; }

    public boolean premierCoup() {
        return premierCoup;
    }

    public boolean jouerTuile(Position position, Tuile tuile){
    	// On gère d'abord si le positionnement des tuiles est valide
        if (!plateau.estPositionValide(position)) {
            return false;
        }

        int nombreTuilesCompatibles = plateau.nombreTuilesCompatibles(position, tuile);
        if(!premierCoup() && nombreTuilesCompatibles == 0) {
        	return false;
        }

        try {
            plateau.posertuile(tuile, position);
        } catch (CaseInaccessibleException e) {
            return false;
        }

        if (premierCoup) {
        	if (position.x() != 4 && position.y() != 4) {
        		return false;
        	}
            premierCoup = false;
        }

        int pointsGagnes = 0;
        
        if (nombreTuilesCompatibles == 2) {
        	pointsGagnes += 1;
        } else if (nombreTuilesCompatibles == 3) {
        	pointsGagnes += 2;
        } else if (nombreTuilesCompatibles == 4) {
        	pointsGagnes += 4;
        }

        if (plateau.getCase(position).estSoleil()) {
            pointsGagnes += 2;
        } else {
            pointsGagnes += 1;
        }

        plateau.donnerPoint(getJoueurCourant(), pointsGagnes);


        retirertuile(tuile);
        retirerAction();
        return true;
    }	
	
	public void initialiser(String nomJoueur1, String nomJoueur2) {
		joueur1 = new Joueur(nomJoueur1);
        joueur2 = new Joueur(nomJoueur2);
        
        try {
			joueur1.remplirPiochePersonelle(piochePrincipal);
			joueur2.remplirPiochePersonelle(piochePrincipal);
			joueur1.initialiserRack();
			joueur2.initialiserRack();
		} catch (PiocheVideException e) {
			System.out.println(e.getMessage());
		}
		
        if(random.nextBoolean()) {
        	joueurActuel = joueur1;
        } else {
        	joueurActuel = joueur2;
        }
	}
	
	public String nomJoueur1() {
		return joueur1.nom();
	}
	
	public String nomJoueur2() {
		return joueur2.nom();
	}
	
	public ArrayList<Tuile> RackJoueur1() {
	    return joueur1.tuilesRack();
	}

	public ArrayList<Tuile> RackJoueur2() {
	    return joueur2.tuilesRack();
	}
	
	public void changerRack() throws PiocheVideException {
		if(joueurActuel == joueur1) {
			joueur1.echangerRack();
		}
		else {
			joueur2.echangerRack();
		}
	}
	
	public int getActions() {
		if(joueurActuel == joueur1) {
			return joueur1.actions();
		}
		else {
			return joueur2.actions();
		}
	}
	
	public void retirerAction() {
		if(joueurActuel == joueur1) {
			joueur1.enleverAction();;
		}
		else {
			joueur2.enleverAction();
		}
	}
	
	public void remplireRack() throws PiocheVideException {
		if(joueurActuel == joueur1) {
			joueur1.remplirRack();
		}
		else {
			joueur2.remplirRack();
		}
	}
	
	public void retirertuile(Tuile tuile) {
	    if (joueurActuel == joueur1) {
	        joueur1.getRack().retirerTuile(tuile);
	    } else {
	        joueur2.getRack().retirerTuile(tuile);
	    }
	}

	
	public int taillePioche() {
		if(joueurActuel == joueur1) {
			return joueur1.taillePiochePersonelle();
		}
		else {
			return joueur2.taillePiochePersonelle();
		}
	}
	
	public void changerTour() {
	    if (getJoueurCourant() == joueur1) {
	        joueurActuel = joueur2;
	        joueur2.réinitialiserActions();
	    } else {
	        joueurActuel = joueur1;
	        joueur1.réinitialiserActions();
	    }
	}
	public Plateau getPlateau() {
	    return plateau;
	}
}
