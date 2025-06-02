package latice.application.JavaFX;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class LaticeGestionnaireDeMusique {
	private MediaPlayer mediaPlayer;
    
    public void chargerMusique(String filePath) {
    	 try {
             
             String uri = getClass().getResource(filePath).toURI().toString();
             Media media = new Media(uri);
             
             mediaPlayer = new MediaPlayer(media);
             
             
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
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setOnEndOfMedia(() -> {
        
        });
    }
    
    public void jouerUneFois() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
            mediaPlayer.setCycleCount(1);
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
    
    public void changerVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }
}
