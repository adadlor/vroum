package fr.lomy.vroum.Componant;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Circuit extends Component {


    private Entity spEntity;
    private StartPoint startPoint;
    /*
    Start Point options
     */
    private Texture upArrow;
    private Texture downArrow;

    private int STARTPOINT_SIZEX = startPoint.getSTARTPOINT_SIZEX();
    private int STARTPOINT_SIZEY = startPoint.getSTARTPOINT_SIZEY();

    private HashMap<Integer, Entity> routes;

    public Circuit(double start_x, double start_y) {
        routes = new HashMap<>();
        spEntity=FXGL.spawn("StartPoint", start_x, start_y); // Creation de l'entité StartPoint (Correspond à la position de départ du circuit)
        startPoint = spEntity.getComponent(StartPoint.class); // Récupération du composant StartPoint de l'entité
        /*
         TODO: A modifier: Mettre les actions dans le composant StartPoint
         */
        addAction(); // Ajout des flèches
        startPoint.asignAction(upArrow, downArrow); // Affectation des flèches au composant StartPoint
    }

    /**
     * Actualisation de la position du circuit et des routes qui le compose.
     * @param x
     * @param y
     */
    public void update(double x, double y) {
        startPoint.update(x, y);
        spEntity.setPosition(x, y);
        for (int i = 0; i < routes.size(); i++) {
            routes.get(i).setPosition(x, y);
        }
    }

    /**
     * Ajout des flèches action.
     */
    private void addAction() {
        if (routes.size() == 0) {
            // Le circuit n'a pas encore de route donc c'est le point de départ
            var x = startPoint.getX();
            var y = startPoint.getY();

            upArrow = FXGL.texture("arrow.png").multiplyColor(Color.RED);
            downArrow = FXGL.texture("arrow.png").multiplyColor(Color.RED);

            downArrow.rotateProperty().setValue(180);


            upArrow.setTranslateX(x + STARTPOINT_SIZEX/2 - upArrow.getWidth()/2);
            upArrow.setTranslateY(y - STARTPOINT_SIZEY - upArrow.getHeight()/2);

            downArrow.setTranslateX(x + STARTPOINT_SIZEX/2 - downArrow.getWidth()/2);
            downArrow.setTranslateY(y + STARTPOINT_SIZEY + upArrow.getHeight()/2);

            upArrow.onMouseClickedProperty().setValue(e -> {
                Tools.debug_print("StartPoint | upArrow | mouseClicked");
                addRoad(startPoint.getX() + STARTPOINT_SIZEX/4, startPoint.getY()-STARTPOINT_SIZEY*2, "up");
                upArrow.visibleProperty().setValue(false);
            });

            downArrow.onMouseClickedProperty().setValue(e -> {
                Tools.debug_print("StartPoint | downArrow | mouseClicked");
                addRoad(startPoint.getX() + STARTPOINT_SIZEX/4, startPoint.getY() + STARTPOINT_SIZEY, "down");
                downArrow.visibleProperty().setValue(false);
            });

            FXGL.getGameScene().addUINodes(upArrow, downArrow);
        }
    }

    /**
     * Ajout d'une route au circuit.
     * @param x
     * @param y
     * @param direction
     */
    private void addRoad(double x, double y, String direction) {
        Tools.debug_print("Circuit | addRoad");
        routes.put(routes.size(), FXGL.spawn("Road", x, y));
        var road = routes.get(routes.size()-1).getComponent(Road.class);
        road.addAction(direction);
    }
}
