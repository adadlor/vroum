package fr.lomy.vroum.Interface.MapCreator;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import fr.lomy.vroum.Componant.Route;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RoadIcon extends VBox {
    private Texture texture;
    private Route route;

    public RoadIcon(Route route) {
        this.route = route;
        this.texture = FXGL.texture(route.getTextureName());

        var bg = new Rectangle(32, 32);
        bg.setFill(Color.TRANSPARENT);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(2.5);

        var text = FXGL.getUIFactoryService().newText(route.getName());
        text.setStroke(Color.BLACK);

        var stackPane = new StackPane(bg, texture);
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);

        getChildren().addAll(stackPane, text);
    }
}
