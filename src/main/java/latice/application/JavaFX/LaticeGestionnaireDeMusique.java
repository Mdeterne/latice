package latice.application.JavaFX;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class LaticeGestionnaireDeMusique {
	private MediaPlayer mediaPlayer;
    
    public void chargerMusique(String filePath) {
    	 try {
             // Conversion du chemin pour les URI
             String uri = Paths.get(filePath).toUri().toString();
             Media media = new Media(uri);
             
             mediaPlayer = new MediaPlayer(media);
             
             // Gestion des erreurs
             mediaPlayer.setOnError(() -> {
                 System.err.println("Media error: " + mediaPlayer.getError());
             });
             
         } catch (Exception e) {
             System.err.println("Error loading media: " + e.getMessage());
         }
     }
    
    public void jouer() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }
    
    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
    
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
    
    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }
}
