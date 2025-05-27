package latice.application.JavaFX;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import latice.model.Tuile;
import latice.util.exception.PiocheVideException;

public class LaticeJavaFXControleurPrincipal {
    
	private Arbitre arbitre;
    private LaticeGestionnaireDeMusique musique;
	
	@FXML
    private Label lblBienvenue;
	
	@FXML
	private Label lblJoueurActuel;
    
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
    
    @FXML
    private Button changerRack;
    
    @FXML
    private Label erreurChangerRack;
    
    
    public void initialisation(String joueur1, String joueur2) {
    	this.musique = new LaticeGestionnaireDeMusique();
		this.arbitre = new Arbitre();
    	arbitre.initialiser(joueur1,joueur2);
    	changementTextDeJoueur(arbitre.tourJoueur());
    	changementImageRack(arbitre.tourJoueur());
		lancerLaMusique();
    }
    
    
    public void verificationDuTour() {
    	if(arbitre.getActions() == 0) {
    		arbitre.changerTour();
    		changementImageRack(arbitre.tourJoueur());
    		changementTextDeJoueur(arbitre.tourJoueur());
    	}
    }
    
    public void changementTextDeJoueur(Boolean tourJoueur) {
		
    	if (tourJoueur) {
			lblJoueurActuel.setText(arbitre.nomJoueur1() + " à vous de jouer !");
		}
    	else {
    		lblJoueurActuel.setText(arbitre.nomJoueur2() + " à vous de jouer !");
    	}
		
	}
    
    @FXML
    private void changerRack(ActionEvent event) {
    	try {
			arbitre.changerRack();
			arbitre.retirerAction();
			verificationDuTour();
		} catch (PiocheVideException e) {
			erreurChangerRack.setText("Impossible votre pioche est vide");
		}
    }
    
    
    //gestion des images
    public void changementImageRack(boolean estPremierJoueur) {
    	ArrayList<Tuile> tuiles = new ArrayList<Tuile>();
    	if (estPremierJoueur) {
    		tuiles = arbitre.RackJoueur1();
    	}
    	else {
    		tuiles = arbitre.RackJoueur2();
    	}
    	System.out.println("img/"+tuiles.get(0).symbole()+"_"+tuiles.get(0).couleur()+".png");
    	tuile1.setImage(loadImage("/img/"+tuiles.get(0).symbole()+"_"+tuiles.get(0).couleur()+".png"));
    	tuile2.setImage(loadImage("/img/"+tuiles.get(1).symbole()+"_"+tuiles.get(1).couleur()+".png"));
    	tuile3.setImage(loadImage("/img/"+tuiles.get(2).symbole()+"_"+tuiles.get(2).couleur()+".png"));
    	tuile4.setImage(loadImage("/img/"+tuiles.get(3).symbole()+"_"+tuiles.get(3).couleur()+".png"));
    	tuile5.setImage(loadImage("/img/"+tuiles.get(4).symbole()+"_"+tuiles.get(4).couleur()+".png"));
    }
    
    
    private Image loadImage(String chemin) {
    	return new Image(getClass().getResourceAsStream(chemin));
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
    	musique.changerVolume(barVolume.getValue()/100);
    	textVolume.setText(((int)barVolume.getValue())+"");
    }
}