package fr.lomy.vroum;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import fr.lomy.vroum.Componant.Circuit;
import fr.lomy.vroum.Componant.StartPoint;
import fr.lomy.vroum.Factory.MapCreatorFactory;
import fr.lomy.vroum.Interface.InterfaceImplement;
import fr.lomy.vroum.Interface.MapCreatorInterface;
import fr.lomy.vroum.Tools.Tools;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication {

    public static final int WIDTH = 800; // Largeur de la fenêtre
    public static final int HEIGHT = 600; // Hauteur de la fenêtre
    private static int LevelType = 0; // Type de niveau (1 = jeu, 2 = map creator)

    private Circuit circuit;


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

        /*
        Import CSS
         */
        gameSettings.getCSSList().add("MapCreator.css");

    }

    @Override
    protected void initGame() {
        Tools.debug_print("initGame : LevelType = " + LevelType);
        switch (LevelType) {
            case 1:
                new Game();
                break;
            case 2:
                new MapCreator();
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
            switch (LevelType){
                case 1:
                    // For the game
                    break;
                case 2:
                    // For MapCreator
                    var coords = getInput().getMousePositionWorld();
                    var x = coords.getX();
                    var y = coords.getY();
                    switch (MapCreator.getActionType()) {
                        case 0:
                            // Centrage du placement
                            x = x -((int)(StartPoint.getSTARTPOINT_SIZEY()/2));
                            y = y -((int)(StartPoint.getSTARTPOINT_SIZEY()/2));

                            if (circuit == null) {
                                circuit = new Circuit(x, y);
                            } else {
                                circuit.update(x, y);
                            }

                            MapCreatorInterface.setStartPlaced(true);
                            break;
                        case 1:
                            //entities.add(spawn("Road", x, y));
                            break;
                        default:
                            Tools.error_print("initInput : MapCreator.getActionType() = " + MapCreator.getActionType() + " is not a valid actionType");
                            break;
                    }
                    break;
                default:
                    Tools.error_print("initInput : LevelType = " + LevelType + " is not a valid LevelType");
                    break;
            }
        });


    }

    public static void setLevelType(int levelType) {
        LevelType = levelType;
    }



}
