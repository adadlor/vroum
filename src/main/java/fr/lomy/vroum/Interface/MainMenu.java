package fr.lomy.vroum.Interface;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.FontType;
import fr.lomy.vroum.Main;
import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MainMenu extends FXGLMenu {

    private static final int BUTTON_SIZE = 30;

    /**
     * Variable qui permet de connaitre le nombre de bouton
     * Va servir pour la position des boutons
     */
    private int BUTTON_NUM = 0;

    public MainMenu(MenuType type) {
        super(type);

        //Background
        var background = getAssetLoader().loadTexture("mainmenu.png"); // Charge la texture du background
        background.setTranslateX(0); // Position X
        background.setTranslateY(0); // Position Y
        // position 0 0 correspond au coin en haut à gauche de l'écran
        background.setFitWidth(FXGL.getAppWidth()); // Taille X
        background.setFitHeight(FXGL.getAppHeight()); // Taille Y
        getContentRoot().getChildren().add(background); // Ajoute le background à la fenêtre

        //Title
        var title = getUIFactoryService().newText("Vroum", Color.WHITE, FontType.GAME, 50.0); // Création du titre
        title.setTranslateX((Main.WIDTH / 8)); // Position X
        title.setTranslateY((Main.HEIGHT / 3)); // Position Y
        getContentRoot().getChildren().add(title); // Ajoute le titre à la fenêtre

        button("New Game", () -> {
            System.out.println("New Game");
            Main.setLevelType(1);
            getGameController().startNewGame();
        });

        button("Join Game", () -> {
            System.out.println("Join Game");
            Main.setLevelType(2);
            getGameController().startNewGame();
        });

        button("Map Creator", () -> {
            System.out.println("Map Creator");
            Main.setLevelType(3);
            getGameController().startNewGame();
        });

        button("Exit", () -> {
            System.out.println("Exit");
            FXGL.getGameController().exit(); // Ferme le jeu
        });
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onUpdate(double tpf) {
        super.onUpdate(tpf);
    }

    private void button(String name, Runnable action) {


        /*
        Creation du bouton
         */
        var button = getUIFactoryService().newButton(name);

        /*
        Creation du rectangle qui montre la selection
         */
        var boxSelect = new Rectangle(0, 0, 10, BUTTON_SIZE);

        button.setPrefSize(name.length()*20, BUTTON_SIZE); // Reglage de la taille du bouton

        boxSelect.setFill(Color.WHITE); // Couleur du rectangle


        boxSelect.setTranslateX((Main.WIDTH / 8) - 10); // Position X du rectangle
        boxSelect.setTranslateY((Main.HEIGHT / 2.7) + (BUTTON_NUM * 50)); // Position Y du rectangle
        button.setTranslateX((Main.WIDTH / 8)); // Position X du bouton
        button.setTranslateY((Main.HEIGHT / 2.7) + (BUTTON_NUM * 50)); // Position Y du bouton

        button.alignmentProperty().setValue(Pos.CENTER_LEFT); // Alignement du texte du bouton

        /*
         Style du bouton en CSS
         */
        var style = "-fx-text-fill: #ffffff; " + // Couleur du texte
                "-fx-font-size: 12px; " + // Taille du texte
                "-fx-font-family: \"Roboto\"; " + // Police du texte
                "-fx-background-color: rgba(0,0,0,0);" + // Couleur du bouton
                "-fx-padding: 0 0 0 10px; "; // Marge du texte
        button.setStyle(style); // Application du style

        button.setOnAction(e -> action.run()); // Action du bouton
        button.hoverProperty().addListener((observable, oldValue, newValue) -> { // Si la souris est sur le bouton
            if (newValue) {
                button.setStyle(style); // Application du style pour eviter d'avoir les propiétés du bouton par defaut
                getContentRoot().getChildren().add(boxSelect); // Ajout du rectangle de selection
            } else {
                button.setStyle(style); // Application du style pour eviter d'avoir les propiétés du bouton par defaut
                getContentRoot().getChildren().remove(boxSelect); // Suppression du rectangle de selection
            }
        });

        getContentRoot().getChildren().add(button); // Ajout du bouton
        BUTTON_NUM++; // Incrémentation du nombre de bouton
    }

}
