package fr.lomy.vroum;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import fr.lomy.vroum.Interface.InterfaceImplement;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;


    public static void main(String[] args) {
        System.out.println("Vroum Vroum");
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(WIDTH);
        gameSettings.setHeight(HEIGHT);
        gameSettings.setTitle("Vroum Vroum");
        gameSettings.setVersion("SC 0.1");
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setSceneFactory(new InterfaceImplement());
    }

    @Override
    protected void initGame() {
        System.out.println("initGame");
        getGameWorld().addEntityFactory(new SimpleFactory());

    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.F, () -> getNotificationService().pushNotification("Hello World!"));

        onKeyDown(KeyCode.F1, "Show Rules", () -> {
            getDialogService().showMessageBox("Rules");
        });
    }


}
