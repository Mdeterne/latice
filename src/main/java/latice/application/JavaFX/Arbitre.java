package latice.application.JavaFX;


import java.util.ArrayList;
import java.util.Random;

import latice.model.Joueur;
import latice.model.PiochePrincipal;
import latice.model.Plateau;
import latice.model.Position;
import latice.model.Tuile;
import latice.util.exception.ActionsInsuffisanteException;
import latice.util.exception.CaseInaccessibleException;
import latice.util.exception.PiocheVideException;
import latice.util.exception.PointInsuffisantException;

public class Arbitre {
	
	Random random = new Random();
	
	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur joueurActuel;
	private int nombreTours = 20;
	
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

    public boolean jouerTuile(Position position, Tuile tuile) {
    	// On gère d'abord si le positionnement des tuiles est valide
        if (!plateau.estPositionValide(position)) {
            return false;
        }

        int nombreTuilesCompatibles = plateau.nombreTuilesCompatibles(position, tuile);
        if(!premierCoup && nombreTuilesCompatibles == 0) {
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
        }

        plateau.donnerPoint(getJoueurCourant(), pointsGagnes);


        joueurActuel.ajoutTuilePosé();
        retirertuile(tuile);
        try {
			retirerAction();
		} catch (ActionsInsuffisanteException e) {
			return false;
		}
        return true;
    }	
    
    public boolean estFinDuJeu() {
    	System.out.println(nombreTours);
    	if(nombreTours < 1) {
    		return true;
    	}
    	return false;
    }
    
    public int nombreTours() {
    	return nombreTours;
    }
    
    public Joueur getGagnant() {
    	if(joueur1.getTuilesPosées()>joueur2.getTuilesPosées()) {
    		return joueur1;
    	} else if (joueur1.getTuilesPosées()<joueur2.getTuilesPosées()) {
    		return joueur2;
    	}else {
    		return null;
    	}
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
	
	public void retirerAction() throws ActionsInsuffisanteException{
		if(joueurActuel == joueur1) {
			joueur1.enleverAction();
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
	
	public void acheterUnTour() throws PointInsuffisantException{
		
		if(joueurActuel == joueur1) {
			joueur1.enleverPoints(2);
			joueur1.ajouterUneAction();
		}
		else {
			joueur2.enleverPoints(2);
			joueur2.ajouterUneAction();
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
	    nombreTours = nombreTours - 1;
	}
	
	public Plateau getPlateau() {
	    return plateau;
	}
}
