package latice.application;

import static latice.console.LaticeConsole.message;
import latice.model.*;

public class LaticeJeuxEssais {

	public static void main(String[] args) {
		PiochePrincipal piochePrincipal = new PiochePrincipal();
        
        Joueur joueur1 = new Joueur("moi");
        Joueur joueur2 = new Joueur("toi");
       
        
        message(""+piochePrincipal.taille());
        message(""+piochePrincipal.pioche());
        message(""+joueur1.taillePiochePersonelle());
        message(""+joueur2.taillePiochePersonelle());
        joueur1.remplirPiochePersonelle(piochePrincipal);
        joueur2.remplirPiochePersonelle(piochePrincipal);
        message(""+joueur1.taillePiochePersonelle());
        message(""+joueur2.taillePiochePersonelle());
        
        message(""+joueur1.afficherPiochePersonelle());
        message(""+joueur2.afficherPiochePersonelle());
        
        message(joueur1.nom()+" : "+joueur1.afficherJetonsRack());
        message(joueur2.nom()+" : "+joueur2.afficherJetonsRack());
        
        joueur1.initialiserRack();
        joueur2.initialiserRack();
        
        message(joueur1.nom()+" : "+joueur1.afficherJetonsRack());
        message(joueur2.nom()+" : "+joueur2.afficherJetonsRack());
        
		Plateau plateau = new Plateau();
		plateau.afficherPlateau();
	}
}
