package latice.application.JavaFX;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import latice.model.Tuile;

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
    
    @FXML
    private ImageView tuile1;
    
    @FXML
    private ImageView tuile2;
    
    @FXML
    private ImageView tuile3;
    
    @FXML
    private ImageView tuile4;
    
    @FXML
    private ImageView tuile5;
    
    
    public void initialisation(String joueur1, String joueur2) {
    	this.musique = new LaticeGestionnaireDeMusique();
		this.arbitre = new Arbitre();
    	arbitre.initialiser(joueur1,joueur2);
    	changerImageRack(1);
        lblBienvenue.setText("Bienvenue " + joueur1 + " et " + joueur2);
		lancerLaMusique();
    }
    
    public void changerImageRack(int joueur) {
    	ArrayList<Tuile> tuiles = new ArrayList<Tuile>();
    	if (joueur == 1) {
    		tuiles = arbitre.getRackJoueur1();
    	}
    	if (joueur == 2) {
    		tuiles = arbitre.getRackJoueur2();
    	}
    	
    	for (int i=0; i>=4 ;i++) {
    		tuile1.setImage(tuiles[i].symbole()+"_"+tuiles[i].couleur()+".png"); 
    	}
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