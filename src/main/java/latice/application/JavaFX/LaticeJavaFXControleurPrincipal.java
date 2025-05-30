package latice.application.JavaFX;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import latice.model.Position;
import latice.model.Tuile;
import latice.model.Plateau;
import latice.util.exception.PiocheVideException;

public class LaticeJavaFXControleurPrincipal {
    private Arbitre arbitre;
    private LaticeGestionnaireDeMusique musique;
    private Plateau plateau;

    @FXML private Label lblJoueurActuel;

    // Déclaration des cases de case11 à case99
    @FXML private ImageView case11; @FXML private ImageView case12; @FXML private ImageView case13;
    @FXML private ImageView case14; @FXML private ImageView case15; @FXML private ImageView case16;
    @FXML private ImageView case17; @FXML private ImageView case18; @FXML private ImageView case19;
    @FXML private ImageView case21; @FXML private ImageView case22; @FXML private ImageView case23;
    @FXML private ImageView case24; @FXML private ImageView case25; @FXML private ImageView case26;
    @FXML private ImageView case27; @FXML private ImageView case28; @FXML private ImageView case29;
    @FXML private ImageView case31; @FXML private ImageView case32; @FXML private ImageView case33;
    @FXML private ImageView case34; @FXML private ImageView case35; @FXML private ImageView case36;
    @FXML private ImageView case37; @FXML private ImageView case38; @FXML private ImageView case39;
    @FXML private ImageView case41; @FXML private ImageView case42; @FXML private ImageView case43;
    @FXML private ImageView case44; @FXML private ImageView case45; @FXML private ImageView case46;
    @FXML private ImageView case47; @FXML private ImageView case48; @FXML private ImageView case49;
    @FXML private ImageView case51; @FXML private ImageView case52; @FXML private ImageView case53;
    @FXML private ImageView case54; @FXML private ImageView case55; @FXML private ImageView case56;
    @FXML private ImageView case57; @FXML private ImageView case58; @FXML private ImageView case59;
    @FXML private ImageView case61; @FXML private ImageView case62; @FXML private ImageView case63;
    @FXML private ImageView case64; @FXML private ImageView case65; @FXML private ImageView case66;
    @FXML private ImageView case67; @FXML private ImageView case68; @FXML private ImageView case69;
    @FXML private ImageView case71; @FXML private ImageView case72; @FXML private ImageView case73;
    @FXML private ImageView case74; @FXML private ImageView case75; @FXML private ImageView case76;
    @FXML private ImageView case77; @FXML private ImageView case78; @FXML private ImageView case79;
    @FXML private ImageView case81; @FXML private ImageView case82; @FXML private ImageView case83;
    @FXML private ImageView case84; @FXML private ImageView case85; @FXML private ImageView case86;
    @FXML private ImageView case87; @FXML private ImageView case88; @FXML private ImageView case89;
    @FXML private ImageView case91; @FXML private ImageView case92; @FXML private ImageView case93;
    @FXML private ImageView case94; @FXML private ImageView case95; @FXML private ImageView case96;
    @FXML private ImageView case97; @FXML private ImageView case98; @FXML private ImageView case99;

    @FXML private ImageView tuile1;
    @FXML private ImageView tuile2;
    @FXML private ImageView tuile3;
    @FXML private ImageView tuile4;
    @FXML private ImageView tuile5;
    @FXML private Slider barVolume;
    @FXML private Label textVolume;
    @FXML private Button changerRack;
    @FXML private Label erreurChangerRack;

    //Initialisation du contrôleur : joueurs, rack, cases et musique.
    public void initialisation(String nomJoueur1, String nomJoueur2) {
        musique = new LaticeGestionnaireDeMusique();
        arbitre = new Arbitre();
        arbitre.initialiser(nomJoueur1, nomJoueur2);
        plateau = arbitre.plateau;

        // Affiche le joueur courant et son rack
        changementTextDeJoueur();
        changementImageRack();
        lancerLaMusique();

        // Configure drag sur les tuiles du rack
        ImageView[] rackViews = { tuile1, tuile2, tuile3, tuile4, tuile5 };
        List<Tuile> rack = arbitre.getJoueurCourant().getRack().affichertuiles();
        for (int i = 0; i < rack.size(); i++) {
            Tuile t = rack.get(i);
            ImageView iv = rackViews[i];
            iv.setImage(loadImage("/img/" + t.symbole() + "_" + t.couleur() + ".png"));
            iv.setUserData(t);
            makeDraggable(iv);
        }

        // Active le drop sur toutes les cases du plateau
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                try {
                    ImageView caseView = (ImageView) getClass()
                        .getDeclaredField("case" + (i * 10 + j))
                        .get(this);
                    makeDroppable(caseView);
                } catch (Exception e) {
                    // Ignore
                }
            }
        }
    }

    //Change le rack du joueur courant en échange de toutes les tuiles.
    @FXML
    private void changerRack(ActionEvent event) {
        try {
            // Échange le rack du joueur + retire action
            arbitre.changerRack();
            arbitre.retirerAction();
            changementTextDeJoueur();
            changementImageRack();
        } catch (PiocheVideException e) {
            erreurChangerRack.setText("Impossible : votre pioche est vide");
        }
        verificationDuTour();
    }

    //Vérifie si le tour doit changer, remplit le rack et met à jour l'interface.
    private void verificationDuTour() {
        if (arbitre.getActions() == 0) {
            arbitre.changerTour();
            try {
                arbitre.remplireRack();
            } catch (PiocheVideException e) {
                // terminer la partie si rack vide
                if (arbitre.getJoueurCourant().getRack().affichertuiles().isEmpty()) {
                    finDePartie();
                    return;
                }
                if (arbitre.getActions() == 0) {
                    arbitre.changerTour();
                }
                // maj de l'interface
                changementTextDeJoueur();
                changementImageRack();
            }
        }
        // maj de l'interface
        changementTextDeJoueur();
        changementImageRack();
    }
    private void finDePartie() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fin de partie");
        alert.setHeaderText(null);
        alert.setContentText("La pioche est vide, partie terminée !");
        alert.showAndWait();

        // Désactivation tuiles et bouton
        tuile1.setDisable(true);
        tuile2.setDisable(true);
        tuile3.setDisable(true);
        tuile4.setDisable(true);
        tuile5.setDisable(true);
        changerRack.setDisable(true);
    }


    //Met à jour les images du rack avec les tuiles du joueur courant
    private void changementImageRack() {
        List<Tuile> tuiles = arbitre.getJoueurCourant().getRack().affichertuiles();
        ImageView[] rackViews = { tuile1, tuile2, tuile3, tuile4, tuile5 };
        for (int i = 0; i < tuiles.size(); i++) {
            Tuile t = tuiles.get(i);
            ImageView iv = rackViews[i];
            iv.setImage(loadImage("/img/" + t.symbole() + "_" + t.couleur() + ".png"));
            iv.setUserData(t);
        }
    }

    //Met à jour le texte indiquant le joueur courant.
    private void changementTextDeJoueur() {
        lblJoueurActuel.setText(arbitre.getJoueurCourant().nom() + " à vous de jouer !");
    }

    private Image loadImage(String chemin) {
        return new Image(getClass().getResourceAsStream(chemin));
    }

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
        musique.changerVolume(barVolume.getValue() / 100);
        textVolume.setText(String.valueOf((int) barVolume.getValue()));
    }

    @FXML
    private void QuitterLatice() {
        System.exit(0);
    }

    // gestion du drag & drop
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
                ImageView source = (ImageView) event.getGestureSource();
                Object ud = source.getUserData();
                if (!(ud instanceof Tuile)) return;
                Tuile tuile = (Tuile) ud;

                String id = imageView.getId();
                int x = Character.getNumericValue(id.charAt(4)) - 1;
                int y = Character.getNumericValue(id.charAt(5)) - 1;
                Position position = new Position(x, y);

                // Ajout de la vérification du premier coup
                if (arbitre.premier_coup() && !"case55".equals(id)) {
                    // Affiche un message d'erreur et refuse le drop
                    erreurChangerRack.setText("Le premier coup doit être joué sur la case Lune (case centrale)");
                    event.setDropCompleted(false);
                    event.consume();
                    return;
                } else {
                    erreurChangerRack.setText(""); // Efface l'erreur si tout va bien
                }

                boolean ok = arbitre.jouerTuile(position, tuile);
                System.out.println("Placement ok? " + ok);

                if (ok) {
                    imageView.setImage(db.getImage());
                    source.setImage(null);
                    success = true;
                    arbitre.retirerAction();

                    // Vérification des tuiles adjacentes après placement
                    int nbCompatibles = arbitre.plateau.compterCompatibilitesAutourCaseId(id, tuile);
                    if (nbCompatibles > 0) {
                        System.out.println("Tuile correspond");
                    } else {
                        System.out.println("rien de trouvé");
                    }
                    verifierPatternAngleAutour(id);
                }
            }

            event.setDropCompleted(success);
            event.consume();
            verificationDuTour();
        });
    }

    private void verifierPatternAngleAutour(String caseId) {
        int col = Character.getNumericValue(caseId.charAt(4));
        int lig = Character.getNumericValue(caseId.charAt(5));
        String[] ids = new String[] {
            caseId,
            (col > 1 ? "case" + (col-1) + lig : null),
            (col < 9 ? "case" + (col+1) + lig : null),
            (lig > 1 ? "case" + col + (lig-1) : null),
            (lig < 9 ? "case" + col + (lig+1) : null)
        };
        boolean found = false;
        for (String idTest : ids) {
            if (idTest != null && arbitre.plateau.detecterAngleAmateur(idTest)) {
                System.out.println("Patern de 3 trouvé sur " + idTest);
                afficherTuilesAngle(idTest);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Pas de pattern trouvé");
        }
    }

    // Affiche les tuiles impliquées dans le pattern angle amateur pour debug
    private void afficherTuilesAngle(String caseId) {
        int col = Character.getNumericValue(caseId.charAt(4));
        int lig = Character.getNumericValue(caseId.charAt(5));
        String[] angles = {
            "Haut+Gauche", "Haut+Droite", "Bas+Gauche", "Bas+Droite"
        };
        int[][] deltas = {
            {-1, 0, 0, -1}, // Haut+Gauche
            {-1, 0, 0, 1},  // Haut+Droite
            {1, 0, 0, -1},  // Bas+Gauche
            {1, 0, 0, 1}    // Bas+Droite
        };
        for (int i = 0; i < 4; i++) {
            int dLig1 = deltas[i][0]; int dCol1 = deltas[i][1];
            int dLig2 = deltas[i][2]; int dCol2 = deltas[i][3];
            int lig1 = lig + dLig1; int col1 = col + dCol1;
            int lig2 = lig + dLig2; int col2 = col + dCol2;
            if (col1 >= 1 && col1 <= 9 && lig1 >= 1 && lig1 <= 9 && col2 >= 1 && col2 <= 9 && lig2 >= 1 && lig2 <= 9) {
                String id1 = "case" + col1 + lig1;
                String id2 = "case" + col2 + lig2;
                Tuile tCentre = arbitre.plateau.detecterAngleAmateur(caseId) ? arbitre.plateau.getCase(new Position(col-1, lig-1)).gettuile() : null;
                Tuile t1 = arbitre.plateau.getCase(new Position(col1-1, lig1-1)).gettuile();
                Tuile t2 = arbitre.plateau.getCase(new Position(col2-1, lig2-1)).gettuile();
                if (tCentre != null && t1 != null && t2 != null) {
                    System.out.println("Angle " + angles[i] + ": centre=" + tCentre + ", t1=" + t1 + ", t2=" + t2);
                }
            }
        }
    }
}
