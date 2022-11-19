package fr.lomy.vroum.Interface;

import com.almasb.fxgl.dsl.FXGL;
import fr.lomy.vroum.Main;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapCreatorInterface extends Parent {



    public MapCreatorInterface() {
        var rct = new Rectangle(Main.WIDTH, Main.HEIGHT);
        rct.setStroke(Color.BLACK);
        rct.setStrokeWidth(2.5);

        getChildren().addAll(rct);


    }

}
