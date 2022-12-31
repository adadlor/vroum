package fr.lomy.vroum.Component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.paint.Color;


public class StartPoint extends Component {


    private static int STARTPOINT_SIZEX = 50;
    private static int STARTPOINT_SIZEY = 25;

    public static int getSTARTPOINT_SIZEX() {return STARTPOINT_SIZEX;}
    public static int getSTARTPOINT_SIZEY() {return STARTPOINT_SIZEY;}

    private double x;
    private double y;

    private Texture upArrow;
    private Texture downArrow;



    public StartPoint(double x, double y) {
        Tools.debug_print("StartPoint | x: " + x + " | y: " + y);
        this.x = x;
        this.y = y;
    }

    /**
     * Ajoute les flèches pour ajouter des routes
     */
    private void addAction(){
        Tools.debug_print("StartPoint | addAction");
        upArrow = FXGL.texture("arrow.png").multiplyColor(Color.RED);
        downArrow = FXGL.texture("arrow.png").multiplyColor(Color.RED);

        downArrow.rotateProperty().setValue(180);


        FXGL.getGameScene().addUINodes(upArrow, downArrow);
    }

    /**
     * Met à jour la position du point de départ
     * @param x
     * @param y
     */
    public void update(double x, double y){
        Tools.debug_print("StartPoint | update");
        this.x = x;
        this.y = y;

        upArrow.setTranslateX(x + STARTPOINT_SIZEX/2 - upArrow.getWidth()/2);
        upArrow.setTranslateY(y - STARTPOINT_SIZEY - upArrow.getHeight()/2);

        downArrow.setTranslateX(x + STARTPOINT_SIZEX/2 - downArrow.getWidth()/2);
        downArrow.setTranslateY(y + STARTPOINT_SIZEY + upArrow.getHeight()/2);

    }

    /**
     * Associe les Textures des fleche à l'instance de StartPoint
     * TODO: A modifier pour que les flèches soient directement dans le StartPoint
     * @param upArrow
     * @param downArrow
     */
    public void asignAction(Texture upArrow, Texture downArrow){
        Tools.debug_print("StartPoint | asignAction");
        this.upArrow = upArrow;
        this.downArrow = downArrow;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
