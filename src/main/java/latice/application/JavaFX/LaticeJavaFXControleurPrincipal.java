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

    @FXML private Label lblBienvenue;
    @FXML private Label lblJoueurActuel;
    

    // Déclaration des cases de case11 à case99
    @FXML private ImageView case11; @FXML private ImageView case12; @FXML private ImageView case13; @FXML private ImageView case14; @FXML private ImageView case15; @FXML private ImageView case16; @FXML private ImageView case17; @FXML private ImageView case18; @FXML private ImageView case19;
    @FXML private ImageView case21; @FXML private ImageView case22; @FXML private ImageView case23; @FXML private ImageView case24; @FXML private ImageView case25; @FXML private ImageView case26; @FXML private ImageView case27; @FXML private ImageView case28; @FXML private ImageView case29;
    @FXML private ImageView case31; @FXML private ImageView case32; @FXML private ImageView case33; @FXML private ImageView case34; @FXML private ImageView case35; @FXML private ImageView case36; @FXML private ImageView case37; @FXML private ImageView case38; @FXML private ImageView case39;
    @FXML private ImageView case41; @FXML private ImageView case42; @FXML private ImageView case43; @FXML private ImageView case44; @FXML private ImageView case45; @FXML private ImageView case46; @FXML private ImageView case47; @FXML private ImageView case48; @FXML private ImageView case49;
    @FXML private ImageView case51; @FXML private ImageView case52; @FXML private ImageView case53; @FXML private ImageView case54; @FXML private ImageView case55; @FXML private ImageView case56; @FXML private ImageView case57; @FXML private ImageView case58; @FXML private ImageView case59;
    @FXML private ImageView case61; @FXML private ImageView case62; @FXML private ImageView case63; @FXML private ImageView case64; @FXML private ImageView case65; @FXML private ImageView case66; @FXML private ImageView case67; @FXML private ImageView case68; @FXML private ImageView case69;
    @FXML private ImageView case71; @FXML private ImageView case72; @FXML private ImageView case73; @FXML private ImageView case74; @FXML private ImageView case75; @FXML private ImageView case76; @FXML private ImageView case77; @FXML private ImageView case78; @FXML private ImageView case79;
    @FXML private ImageView case81; @FXML private ImageView case82; @FXML private ImageView case83; @FXML private ImageView case84; @FXML private ImageView case85; @FXML private ImageView case86; @FXML private ImageView case87; @FXML private ImageView case88; @FXML private ImageView case89;
    @FXML private ImageView case91; @FXML private ImageView case92; @FXML private ImageView case93; @FXML private ImageView case94; @FXML private ImageView case95; @FXML private ImageView case96; @FXML private ImageView case97; @FXML private ImageView case98; @FXML private ImageView case99;

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
        this.musique = new LaticeGestionnaireDeMusique();
        this.arbitre = new Arbitre();
        arbitre.initialiser(joueur1, joueur2);
        changementTextDeJoueur(arbitre.tourJoueur());
        changementImageRack(arbitre.tourJoueur());
        lblBienvenue.setText("Bienvenue " + joueur1 + " et " + joueur2);
        lancerLaMusique();
    }
    
    
    public void verificationDuTour() {
    	if(arbitre.getActions() == 0) {
    		arbitre.changerTour();
    		try {
				arbitre.remplireRack();
			} catch (PiocheVideException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

    
    //gestion drag and drop
    private void makeDraggable(ImageView imageView) {
        imageView.setOnDragDetected(event -> {
            if (imageView.getImage() != null) {
                Dragboard db = imageView.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putImage(imageView.getImage());
                db.setContent(content);
            }
            event.consume();
        });
    }

    private void makeDroppable(ImageView imageView) {
        imageView.setOnDragOver(event -> {
            if (event.getGestureSource() != imageView && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        imageView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                imageView.setImage(db.getImage());
                // Supprimer l'image de la source (le rack)
                ImageView source = (ImageView) event.getGestureSource();
                source.setImage(null);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
            arbitre.retirerAction();
            verificationDuTour();
            
        });
    }

}