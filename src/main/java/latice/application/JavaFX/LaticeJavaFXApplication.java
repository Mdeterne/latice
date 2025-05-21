package latice.application.JavaFX;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LaticeJavaFXApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource("/Scene1.fxml"));
        	Parent root = loader.load();
        	
        	LaticeJavaFXControleur controleur = loader.getController();
        	
            Scene scene = new Scene(root);

            LaticeGestionnaireDeMusique musique = new LaticeGestionnaireDeMusique();
            musique.chargerMusique("/laticeMainTheme.mp3");
            musique.jouer();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Latice Game");
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
