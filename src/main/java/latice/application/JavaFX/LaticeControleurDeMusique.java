package latice.application.JavaFX;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class LaticeControleurDeMusique {
    private final LaticeGestionnaireDeMusique musicPlayer = new LaticeGestionnaireDeMusique();
    
    public HBox createMusicControls() {
        
        Button playBtn = new Button("▶");
        Button pauseBtn = new Button("⏸");
        Button stopBtn = new Button("⏹");
        
      
        Slider volumeSlider = new Slider(0, 1, 0.7);
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            musicPlayer.setVolume(newVal.doubleValue());
        });
        
        
        Slider progressSlider = new Slider();
        progressSlider.setMax(1);
        
        
        playBtn.setOnAction(e -> musicPlayer.jouer());
        pauseBtn.setOnAction(e -> musicPlayer.pause());
        stopBtn.setOnAction(e -> musicPlayer.stop());
        
        return new HBox(10, playBtn, pauseBtn, stopBtn, volumeSlider, progressSlider);
    }
    
    
    /*public void bindProgress(LaticeGestionnaireDeMusique player, Slider progressSlider) {
        player.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!progressSlider.isValueChanging()) {
                double progress = newTime.toMillis() / player.getTotalDuration().toMillis();
                progressSlider.setValue(progress);
            }
        });
        
        progressSlider.setOnMousePressed(e -> {
            double newTime = progressSlider.getValue() * player.getTotalDuration().toMillis();
            player.seek(Duration.millis(newTime));
        });
    }*/
}