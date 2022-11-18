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

    private Animation<?> animation;
    private static final int SIZE = 150;
    private static final int BUTTON_SIZE = 30;
    private static final int BUTTON_SPACING = 50;
    private int BUTTON_NUM = 0;

    public MainMenu(MenuType type) {
        super(type);


        /*

        getContentRoot().setTranslateX(FXGL.getAppWidth() / 2.0 - SIZE);
        getContentRoot().setTranslateY(FXGL.getAppHeight() / 2.0 - SIZE);

        var shape = Shape.subtract(new Circle(SIZE, SIZE, SIZE), new Rectangle(0, SIZE, SIZE*2, SIZE));

        var shape2 = Shape.subtract(shape, new Rectangle(0, 0, SIZE, SIZE));

        shape = Shape.subtract(shape, new Rectangle(SIZE, 0, SIZE, SIZE));

        shape.setStrokeWidth(2.5);
        shape.strokeProperty().bind(
                Bindings.when(shape.hoverProperty()).then(Color.YELLOW).otherwise(Color.BLACK)
        );

        shape.fillProperty().bind(
                Bindings.when(shape.pressedProperty()).then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
        );

        shape.setOnMouseClicked(e -> fireResume());

        shape2.setStrokeWidth(2.5);
        shape2.strokeProperty().bind(
                Bindings.when(shape2.hoverProperty()).then(Color.YELLOW).otherwise(Color.BLACK)
        );

        shape2.fillProperty().bind(
                Bindings.when(shape2.pressedProperty()).then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
        );
        shape2.setOnMouseClicked(e -> FXGL.getGameController().exit());

        var shape3 = new Rectangle(SIZE*2, SIZE / 2);
        shape3.setStrokeWidth(2.5);
        shape3.strokeProperty().bind(
                Bindings.when(shape3.hoverProperty()).then(Color.YELLOW).otherwise(Color.BLACK)
        );

        shape3.fillProperty().bind(
                Bindings.when(shape3.pressedProperty()).then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
        );

        shape3.setTranslateY(SIZE);

        Text textResume = FXGL.getUIFactoryService().newText("RESUME", Color.WHITE, FontType.GAME, 24.0);
        textResume.setTranslateX(50);
        textResume.setTranslateY(100);
        textResume.setMouseTransparent(true);

        Text textExit = FXGL.getUIFactoryService().newText("EXIT", Color.WHITE, FontType.GAME, 24.0);
        textExit.setTranslateX(200);
        textExit.setTranslateY(100);
        textExit.setMouseTransparent(true);

        Text textOptions = FXGL.getUIFactoryService().newText("OPTIONS", Color.WHITE, FontType.GAME, 24.0);
        textOptions.setTranslateX(110);
        textOptions.setTranslateY(195);
        textOptions.setMouseTransparent(true);

        getContentRoot().getChildren().addAll(shape, shape2, shape3, textResume, textExit, textOptions);

        getContentRoot().setScaleX(0);
        getContentRoot().setScaleY(0);

        animation = FXGL.animationBuilder()
                .duration(Duration.seconds(0.66))
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .scale(getContentRoot())
                .from(new Point2D(0, 0))
                .to(new Point2D(1, 1))
                .build();

        */

        //Background
        var background = getAssetLoader().loadTexture("mainmenu.png");
        background.setTranslateX(0);
        background.setTranslateY(0);
        background.setFitWidth(FXGL.getAppWidth());
        background.setFitHeight(FXGL.getAppHeight());
        getContentRoot().getChildren().add(background);

        //Title
        var title = getUIFactoryService().newText("Vroum", Color.WHITE, FontType.GAME, 50.0);
        title.setTranslateX((Main.WIDTH / 8));
        title.setTranslateY((Main.HEIGHT / 3));
        getContentRoot().getChildren().add(title);

        button("New Game", () -> {
            System.out.println("New Game");
        });

        button("Map Creator", () -> {
            System.out.println("Map Creator");
        });

        button("Exit", () -> {
            System.out.println("Exit");
            FXGL.getGameController().exit();
        });
    }


    @Override
    public void onCreate() {
        super.onCreate();
        /*
        animation.setOnFinished(EmptyRunnable.INSTANCE);
        animation.stop();
        animation.start();
         */
    }

    @Override
    protected void onUpdate(double tpf) {
        super.onUpdate(tpf);
        /*
        animation.onUpdate(tpf);

         */
    }

    private void button(String name, Runnable action) {



        var button = getUIFactoryService().newButton(name);
        var boxSelect = new Rectangle(0, 0, 10, BUTTON_SIZE);

        button.setPrefSize(name.length()*20, BUTTON_SIZE);

        boxSelect.setFill(Color.WHITE);


        boxSelect.setTranslateX((Main.WIDTH / 8) - 10);
        boxSelect.setTranslateY((Main.HEIGHT / 2.7) + (BUTTON_NUM * 50));
        button.setTranslateX((Main.WIDTH / 8));
        button.setTranslateY((Main.HEIGHT / 2.7) + (BUTTON_NUM * 50));

        button.alignmentProperty().setValue(Pos.CENTER_LEFT);

        var style = "-fx-text-fill: #ffffff; " +
                "-fx-font-size: 12px; " +
                "-fx-font-family: \"Roboto\"; " +
                "-fx-background-color: rgba(0,0,0,0);" +
                "-fx-padding: 0 0 0 10px; ";
        button.setStyle(style);

        button.setOnAction(e -> action.run());
        button.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                button.setStyle(style);
                getContentRoot().getChildren().add(boxSelect);
            } else {
                button.setStyle(style);
                getContentRoot().getChildren().remove(boxSelect);
            }
        });

        getContentRoot().getChildren().add(button);
        BUTTON_NUM++;
    }

}
