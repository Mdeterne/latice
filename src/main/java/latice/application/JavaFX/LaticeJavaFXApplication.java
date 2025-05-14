package latice.application.JavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaticeJavaFXApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Scene.fxml"));
            
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