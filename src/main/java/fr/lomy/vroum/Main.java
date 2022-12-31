package fr.lomy.vroum;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.*;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import fr.lomy.vroum.Component.Circuit;
import fr.lomy.vroum.Interface.GameMenu;
import fr.lomy.vroum.Interface.MainMenu;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication {
    public static final int WIDTH = 1000; // Largeur de la fenêtre
    public static final int HEIGHT = 800; // Hauteur de la fenêtre

    public static Game game;
    public static MapCreator mapCreator;


    @Override
    /**
     * Initialisation des paramètres du jeu
     */
    protected void initSettings(GameSettings gameSettings) {
        Tools.debug_print("initSettings");
        gameSettings.setWidth(WIDTH);
        gameSettings.setHeight(HEIGHT);
        gameSettings.setTitle("Vroum Vroum"); //Titre du jeu
        gameSettings.setVersion("SC 0.1"); //Version du jeu
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setFontGame("SpeedRacing.ttf"); //Change la police setFontGame avec le speedRacing
        gameSettings.setFontText("Fastup-Bold.ttf"); //Change la police setFontText avec le speedRacing
        gameSettings.setSceneFactory(new SceneFactory(){
            @Override
            public FXGLMenu newMainMenu() { //Créé un Menu Main
                return new MainMenu();
            }
            @Override
            public FXGLMenu newGameMenu() { //
                return new GameMenu();
            }
        });

        /*
        Import CSS
         */
        gameSettings.getCSSList().add("MapCreator.css");

    }

    @Override
    protected void initGame() {
        super.initGame();
    }

    @Override
    /**
     * Initialisation des touches du jeu
     */
    protected void initInput() {
        Tools.debug_print("initInput");

    };


    public static void main(String[] args) {
        Tools.debug_print("Vroum Vroum");
        launch(args); // Lancement du jeu
    }
}
