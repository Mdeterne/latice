package latice.application.JavaFX;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LaticeJavaFXControleurPrincipal {
    @FXML
    private Label lblBienvenue;
    
    Arbitre arbitre = new Arbitre();
    
    public void setNomsJoueurs(String joueur1, String joueur2) {
    	arbitre.setNomJoueur1(joueur1);
    	arbitre.setNomJoueur2(joueur2);
        lblBienvenue.setText("Bienvenue " + joueur1 + " et " + joueur2);
    }
}