package latice.application.JavaFX;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class LaticeJavaFXControleurPrincipal {
    
	private Arbitre arbitre;
    private LaticeGestionnaireDeMusique musique;
	
	@FXML
    private Label lblBienvenue;
    
    @FXML
    private Button boutonLancer;
    
    @FXML
    private Button boutonArrêter;
    
    @FXML
    private Slider barVolume;
    
    @FXML
    private Label textVolume;
    
    
    public void initialisation(String joueur1, String joueur2) {
    	this.musique = new LaticeGestionnaireDeMusique();
		this.arbitre = new Arbitre();
    	arbitre.setNomJoueur1(joueur1);
    	arbitre.setNomJoueur2(joueur2);
        lblBienvenue.setText("Bienvenue " + joueur1 + " et " + joueur2);
		lancerLaMusique();
    }
    
    
    
    //Gestion de la musique
    @FXML
    public void lancerLaMusique() {
    	musique.chargerMusique("/laticeMainTheme.mp3");
    	musique.jouer();
    }
    
    @FXML
    public void arreterLaMusique() {
    	musique.stop();
    }
    
    @FXML
    public void changerLeVolume() {
    	musique.changerVolume(barVolume.getValue());
    	textVolume.setText(((int)barVolume.getValue())+"");
    }
}