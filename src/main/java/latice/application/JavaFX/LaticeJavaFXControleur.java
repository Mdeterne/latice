package latice.application.JavaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LaticeJavaFXControleur {

	@FXML
    private Button connexionBouton;
    
    @FXML
    private void sauvegardeDesNoms() {
        System.out.println("Bouton cliqué !");
        connexionBouton.setText("Déjà cliqué");
    }
	
}
