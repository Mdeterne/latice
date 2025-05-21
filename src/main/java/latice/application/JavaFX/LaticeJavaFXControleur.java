package latice.application.JavaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LaticeJavaFXControleur {

	Arbitre arbitre = new Arbitre();
	
	@FXML
    private Button connexionBouton;
	@FXML
	private TextField nomJoueur1;
	@FXML
	private TextField nomJoueur2;
    
    @FXML
    private void sauvegardeDesNoms() {
        System.out.println("Bouton cliqué !");
        try {
        	arbitre.setNomJoueur1(nomJoueur1.getText());
        	arbitre.setNomJoueur2(nomJoueur2.getText());
        	System.out.println(arbitre.nomJoueur1()+" "+arbitre.nomJoueur2());
        }
        catch(java.lang.NullPointerException e) {
        	System.out.println("veuillez rentrer des noms");
        	
        }
        
        
    }
	
}
