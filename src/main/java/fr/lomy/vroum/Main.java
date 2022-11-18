package fr.lomy.vroum;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication {
    public static void main(String[] args) {
        System.out.println("Vroum Vroum");
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Vroum Vroum");
        gameSettings.setVersion("SC 0.1");
    }

    @Override
    protected void initGame() {
        System.out.println("initGame");
        getGameWorld().addEntityFactory(new SimpleFactory());

    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.F, () -> getNotificationService().pushNotification("Hello World!"));
    }
}
