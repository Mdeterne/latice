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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class LaticeJavaFXControleurConection {

	@FXML
	private Button connexionBouton;

	@FXML
	private TextField nomJoueur1;

	@FXML
	private TextField nomJoueur2;

	@FXML
	private TextField nomJoueur3;

	@FXML
	private TextField nomJoueur4;

	@FXML
	private Label erreurNoms;

	private Scene scene;

	private Stage stage;

	private LaticeGestionnaireDeMusique musique = new LaticeGestionnaireDeMusique();
	private LaticeGestionnaireDeMusique Boutton = new LaticeGestionnaireDeMusique();

	@FXML
	public void initialize() {
		musique.chargerMusique("/connectionMainTheme.mp3");
		Boutton.chargerMusique("/SonBoutton.wav");
		musique.jouer();

		javafx.event.EventHandler<KeyEvent> handler = event -> {
			if (event.getCode() == KeyCode.ENTER) {
				try {
					Boutton.jouerUneFois();
					sauvegardeDesNoms(null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		nomJoueur1.setOnKeyPressed(handler);
		nomJoueur2.setOnKeyPressed(handler);
	}

	@FXML
	private void sauvegardeDesNoms(ActionEvent event) throws IOException {
		String nom1 = nomJoueur1.getText().trim();
		String nom2 = nomJoueur2.getText().trim();
		if (nom1.isEmpty() || nom2.isEmpty()) {
			erreurNoms.setText("Les deux noms doivent être remplis.");
			return;
		}
		if (nom1.length() > 10 || nom2.length() > 10) {
			erreurNoms.setText("Les noms ne doivent pas dépasser 10 caractères.");
			return;
		}
		if (nom1.equalsIgnoreCase(nom2)) {
			erreurNoms.setText("Les joueurs doivent avoir des noms différents.");
			return;
		}
		erreurNoms.setText("");
		System.out.println("Bouton cliqué !");
		Boutton.jouerUneFois();
		if (event != null) {
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		} else {
			stage = (Stage) nomJoueur1.getScene().getWindow();
		}
		changerDeScene(stage);
	}

	@FXML
	private void afficherRegles() {
		try {
			Boutton.jouerUneFois();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Regles.fxml"));
			Parent root = loader.load();
			Stage reglesStage = new Stage();
			reglesStage.setTitle("Règles du jeu Latice");
			reglesStage.setScene(new Scene(root));
			reglesStage.show();
		} catch (Exception e) {
			erreurNoms.setText("Impossible d'ouvrir la fenêtre des règles.");
		}
	}

	public void changerDeScene(Stage stage) throws IOException {
		musique.stop();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene.fxml"));
		Parent root = loader.load();
		LaticeJavaFXControleurPrincipal ControleurPrincipal = loader.getController();
		ControleurPrincipal.initialisation(nomJoueur1.getText(), nomJoueur2.getText(), nomJoueur3.getText(),
				nomJoueur4.getText());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Jeux Latice");
		stage.centerOnScreen();
		stage.show();
	}

}