package latice.application.JavaFX;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class ControlleurVideo {

	@FXML
	private MediaView mediaView;

	private MediaPlayer mediaPlayer;

	public void initialize() {
		String cheminVideo = new File("src/main/resources/video/fond-y2k.mp4").toURI().toString();

		Media media = new Media(cheminVideo);
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // boucle la vidéo
		mediaPlayer.setAutoPlay(true);

		mediaView.setMediaPlayer(mediaPlayer);
	}

	public void stopVideo() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
	}

	public void playVideo() {
		if (mediaPlayer != null) {
			mediaPlayer.play();
		}
	}

	public void pauseVideo() {
		if (mediaPlayer != null) {
			mediaPlayer.pause();
		}
	}
}
