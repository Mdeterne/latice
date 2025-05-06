package latice.application;

import static latice.console.LaticeConsole.message;

import latice.model.Joueur;
import latice.model.PiochePrincipal;

public class LaticeJeuxEssais {

	public static void main(String[] args) {
		PiochePrincipal piochePrincipal = new PiochePrincipal();
        
        Joueur joueur1 = new Joueur("moi");
        Joueur joueur2 = new Joueur("toi");
       
        
        message(""+piochePrincipal.taille());
        message(""+piochePrincipal.pioche());
        message(""+joueur1.piochePersonelle().taille());
        message(""+joueur2.piochePersonelle().taille());
        joueur1.piochePersonelle().remplirPiochePerso(piochePrincipal);
        joueur2.piochePersonelle().remplirPiochePerso(piochePrincipal);
        message(""+joueur1.piochePersonelle().taille());
        message(""+joueur2.piochePersonelle().taille());
        
        message(""+joueur1.afficherPiochePersonelle());
        message(""+joueur2.afficherPiochePersonelle());
        
        message(joueur1.nom()+" : "+joueur1.rack().afficherJetons());
        message(joueur2.nom()+" : "+joueur2.rack().afficherJetons());
        
        joueur1.initialiserRack();
        joueur2.initialiserRack();
        
        message(joueur1.nom()+" : "+joueur1.rack().afficherJetons());
        message(joueur2.nom()+" : "+joueur2.rack().afficherJetons());
	}
}
