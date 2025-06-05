package latice.application.JavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LaticeJavaFXApplication extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/Scene1.fxml"));
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/LogoLatice.jpg")));
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
