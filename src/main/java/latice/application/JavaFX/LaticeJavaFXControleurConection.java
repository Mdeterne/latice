package latice.application.JavaFX;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LaticeJavaFXControleurConection {
	
	@FXML
    private Button connexionBouton;
	@FXML
	private TextField nomJoueur1;
	@FXML
	private TextField nomJoueur2;
	@FXML
    private Label erreurNoms;
	
	private Scene scene;
    
	private Stage stage;
	
    @FXML
    private void sauvegardeDesNoms(ActionEvent event) throws IOException {
        String nom1 = nomJoueur1.getText().trim();
        String nom2 = nomJoueur2.getText().trim();
        if (nom1.isEmpty() || nom2.isEmpty()) {
            erreurNoms.setText("Les deux noms doivent être remplis.");
            return;
        }
        if (nom1.equalsIgnoreCase(nom2)) {
            erreurNoms.setText("Les joueurs doivent avoir des noms différents.");
            return;
        }
        erreurNoms.setText("");
        System.out.println("Bouton cliqué !");
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        changerDeScene(stage);
    }
	
    
    public void changerDeScene(Stage stage) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene.fxml"));
        Parent root = loader.load();
    	LaticeJavaFXControleurPrincipal ControleurPrincipal = loader.getController();
        ControleurPrincipal.initialisation(nomJoueur1.getText(), nomJoueur2.getText());
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    
}