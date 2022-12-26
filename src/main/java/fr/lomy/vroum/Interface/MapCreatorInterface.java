package fr.lomy.vroum.Interface;

import fr.lomy.vroum.Component.StartPoint;
import fr.lomy.vroum.Main;
import fr.lomy.vroum.MapCreator;
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

    /**
     * Change startPlaced qui permet de savoir si le point de départ a été placé
     * @param startPlaced
     */
    public static void setStartPlaced(Boolean startPlaced) {
        MapCreatorInterface.startPlaced = startPlaced;
        roadButton.setDisable(!startPlaced); // Si le start est placé, on peut placer des routes

    }



    /*
    Instance side
     */
    private Rectangle bg;


    public MapCreatorInterface() {
        bg = new Rectangle(Main.WIDTH, Main.HEIGHT);
        bg.setFill(Color.rgb(66, 66, 66, 1)); // Change la couleur de fond

        startButton = new Button("Ligne de départ");
        startButton.setTranslateX(10);
        startButton.setTranslateY(10);

        roadButton = new Button("Ajouter une route");
        roadButton.setTranslateX(10);
        roadButton.setTranslateY(60);
        roadButton.disableProperty().set(!startPlaced); // Bouton désactivé si pas de départ

        startButton.setOnAction(e -> {
            MapCreator.setActionType(0); // Change le type d'action à "ajouter une ligne de départ"
        });

        roadButton.setOnAction(e -> {
            MapCreator.setActionType(1); // Change le type d'action à "ajouter une route"
        });


        getChildren().addAll(bg, startButton, roadButton);



    }

}
