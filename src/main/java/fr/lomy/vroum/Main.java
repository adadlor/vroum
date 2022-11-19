package fr.lomy.vroum;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import fr.lomy.vroum.Factory.MapCreatorFactory;
import fr.lomy.vroum.Interface.InterfaceImplement;
import fr.lomy.vroum.Interface.MapCreatorInterface;
import fr.lomy.vroum.Tools.Tools;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication {

    public static final int WIDTH = 800; // Largeur de la fenêtre
    public static final int HEIGHT = 600; // Hauteur de la fenêtre
    private static int LevelType = 0; // Type de niveau (1 = jeu, 2 = map creator)


    public static void main(String[] args) {
        Tools.debug_print("Vroum Vroum");
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        Tools.debug_print("initSettings");
        gameSettings.setWidth(WIDTH);
        gameSettings.setHeight(HEIGHT);
        gameSettings.setTitle("Vroum Vroum");
        gameSettings.setVersion("SC 0.1");
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setSceneFactory(new InterfaceImplement());
    }

    @Override
    protected void initGame() {
        Tools.debug_print("initGame : LevelType = " + LevelType);
        switch (LevelType) {
            case 1:
                gamestart();
                break;
            case 2:
                mapcreator();
                break;
            default:
                Tools.error_print("initGame : LevelType = " + LevelType + " is not a valid LevelType");
                break;
        }

    }

    @Override
    protected void initInput() {
        Tools.debug_print("initInput");
        onKeyDown(KeyCode.F, () -> getNotificationService().pushNotification("Hello World!")); // Affiche une notification

        onKeyDown(KeyCode.F1, "Show Rules", () -> { // Affiche une boite de dialogue avec les règles du jeu
            getDialogService().showMessageBox("Rules");
        });

        onKeyDown(KeyCode.G, () -> {
            if (LevelType == 2) {
                getNotificationService().pushNotification("Map Creator");
            }
        });

        //Mouse input
        onBtnDown(MouseButton.PRIMARY, () -> {
            if (LevelType == 2) {
                getGameWorld().spawn("Route", getInput().getMousePositionWorld());
            }
        });

    }

    public static void setLevelType(int levelType) {
        LevelType = levelType;
    }

    /**
     * Fonction qui permet de lancer le jeu
     */
    private void gamestart(){
        getGameWorld().addEntityFactory(new SimpleFactory());
    }

    /**
     * Fonction qui permet de lancer le map creator
     */
    private void mapcreator(){

        getGameWorld().addEntityFactory(new MapCreatorFactory());
        spawn("Floor", 0, 0);

        var interfaceMapCreator = new MapCreatorInterface();
        addUINode(interfaceMapCreator, 0,Main.HEIGHT - 100);
    }

}
