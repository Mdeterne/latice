package latice.application.JavaFX;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class LaticeJavaFXControleurRegles {
private LaticeGestionnaireDeMusique Boutton = new LaticeGestionnaireDeMusique();


    @FXML
    private VBox vboxGauche;
    @FXML
    private VBox vboxDroite;

    @FXML
    private void fermerRegles() {
    	Boutton.jouerUneFois();
        vboxGauche.getScene().getWindow().hide();
    }

    @FXML
    public void initialize() {
        Boutton.chargerMusique("/SonBoutton.wav");
    }
}
