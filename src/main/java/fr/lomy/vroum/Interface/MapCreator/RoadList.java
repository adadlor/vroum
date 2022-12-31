package fr.lomy.vroum.Interface.MapCreator;

import fr.lomy.vroum.Component.Road;
import javafx.scene.layout.HBox;

import java.util.List;

public class RoadList extends HBox {
    public RoadList(List<Road> roaddata) {

        setSpacing(2);

        roaddata.forEach(data -> {
            var icon = new RoadIcon(data);

            icon.setOnMouseClicked(e -> {
                if (icon != null) {
                    System.out.println("RoadList");
                }
            });

            getChildren().add(icon);
        });

    }
}
