package fr.lomy.vroum.Interface;

import com.almasb.fxgl.dsl.FXGL;
import fr.lomy.vroum.Componant.StartPoint;
import fr.lomy.vroum.Main;
import fr.lomy.vroum.MapCreator;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapCreatorInterface extends Parent {



    /*
    Static side
     */

    private static Boolean startPlaced = false;
    private static StartPoint startPoint;

    private static Button startButton;
    private static Button roadButton;

    public static Boolean getStartPlaced() {
        return startPlaced;
    }

    public static void setStartPlaced(Boolean startPlaced) {
        MapCreatorInterface.startPlaced = startPlaced;
        roadButton.setDisable(!startPlaced);

    }



    /*
    Instance side
     */
    private Rectangle bg;


    public MapCreatorInterface() {
        bg = new Rectangle(Main.WIDTH, Main.HEIGHT);
        bg.setFill(Color.rgb(66, 66, 66, 1));

        startButton = new Button("Ligne de départ");
        startButton.setTranslateX(10);
        startButton.setTranslateY(10);

        roadButton = new Button("Ajouter une route");
        roadButton.setTranslateX(10);
        roadButton.setTranslateY(60);
        roadButton.disableProperty().set(!startPlaced);

        startButton.setOnAction(e -> {
            MapCreator.setActionType(0);
        });

        roadButton.setOnAction(e -> {
            MapCreator.setActionType(1);
        });


        getChildren().addAll(bg, startButton, roadButton);



    }

}
