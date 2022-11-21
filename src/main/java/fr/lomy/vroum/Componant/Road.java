package fr.lomy.vroum.Componant;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.paint.Color;

public class Road extends Component {
    private static int ROAD_SIZEX = 25;
    private static int ROAD_SIZEY = 50;

    public static int getROAD_SIZEX() {return ROAD_SIZEX;}
    public static int getROAD_SIZEY() {return ROAD_SIZEY;}

    private String textureName;
    private String name;

    private double x;
    private double y;


    private Texture upArrow;
    private Texture downArrow;

    public Road(double x, double y) {
        Tools.debug_print("Road | x: " + x + " | y: " + y);
        this.x = x;
        this.y = y;
    }

    public void update(double x, double y){
        Tools.debug_print("Road | update");
        this.x = x;
        this.y = y;
    }

    public void addAction(String side){
        Tools.debug_print("Road | addAction");
        switch (side){
            case "up":
                upArrow = FXGL.texture("arrow.png").multiplyColor(Color.RED);
                upArrow.setTranslateX(x + ROAD_SIZEX/2 - upArrow.getWidth()/2);
                upArrow.setTranslateY(y - ROAD_SIZEY - upArrow.getHeight()/2);
                upArrow.onMouseClickedProperty().setValue(e -> {
                    Tools.debug_print("Road | upArrow | onClick");
                    var road = FXGL.spawn("Road", x, y - ROAD_SIZEY);
                    road.getComponent(Road.class).addAction("up");
                    upArrow.visibleProperty().setValue(false);
                });
                FXGL.getGameScene().addUINodes(upArrow);
                break;
            case "down":
                downArrow = FXGL.texture("arrow.png").multiplyColor(Color.RED);
                downArrow.rotateProperty().setValue(180);
                downArrow.setTranslateX(x + ROAD_SIZEX/2 - downArrow.getWidth()/2);
                downArrow.setTranslateY(y + ROAD_SIZEY + downArrow.getHeight()/2);
                downArrow.onMouseClickedProperty().setValue(e -> {
                    Tools.debug_print("Road | downArrow | onClick");
                    var road = FXGL.spawn("Road", x, y + ROAD_SIZEY);
                    road.getComponent(Road.class).addAction("down");
                    downArrow.visibleProperty().setValue(false);
                });
                FXGL.getGameScene().addUINodes(downArrow);
                break;
        }
    }




    public String getTextureName() {
        return textureName;
    }

    public String getName() {
        return name;
    }

}
