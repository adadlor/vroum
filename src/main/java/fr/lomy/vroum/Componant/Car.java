package fr.lomy.vroum.Componant;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import fr.lomy.vroum.Data.PointData;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.getInput;

public class Car extends Component {
    private Double x ;
    private Double y ;

    private Double xCenter;
    private Double yCenter;

    private ArrayList<Entity> actions = new ArrayList<>();

    private int speed = 0;
    private ArrayList<Integer> speedVector = new ArrayList<>() {{
        add(0); // offsetX
        add(0); // offsetY
    }};
    private final Color color;

    public Car(Double x, Double y, Color color) {
        Tools.info_print("Creation d'une voiture");
        this.color = color;
        this.x = x; // X en 0 0 de l'image
        this.y = y; // Y en 0 0 de l'image
        this.xCenter = x + (FXGL.texture("cars/pitstop_car_1.png").getWidth()*0.07)/2; // X du centre de l'image 0.07 = scale
        this.yCenter = y + (FXGL.texture("cars/pitstop_car_1.png").getHeight()*0.07)/2; // Y du centre de l'image 0.07 = scale
        addAction(this.speedVector);
    }

    public void addAction(ArrayList<Integer> speedVector){
        // Draw a box around the car with black points
        // 3x3
        Tools.debug_print("Add action speed: offX: " + speedVector.get(0) + " offY: " + speedVector.get(1));
        for (int i = -1; i <= 1; i++) { // x
            for (int j = -1; j <= 1; j++) { // y

                var x = (this.xCenter + i * 50)+speedVector.get(0) *50; // coordonnée x du point
                var y = (this.yCenter + j * 50)+speedVector.get(1) *50; // coordonnée y du point
                Tools.debug_print("Add action x: " + x + " y: " + y);
                var point = FXGL.spawn("Choice", x, y); // spawn du point
                point.getComponent(PointData.class).setSpeed(i, j); // set de la vitesse du point
                actions.add(point); // ajout du point dans la liste des points

            }
        }
    }

    public void move(ArrayList<Integer> speedVector){
        for (Entity action : actions) {
            action.removeFromWorld(); // suppression des points
        }

        Tools.error_print("CP : 1");

        this.speedVector = speedVector; // set de la nouvelle vitesse
        this.xCenter += speedVector.get(0) * 50; // set de la nouvelle position x
        this.yCenter += speedVector.get(1) * 50; // set de la nouvelle position y
        Tools.error_print("CP : 2");
        entity.translateX(speedVector.get(0) * 50); // déplacement de la voiture
        entity.translateY(speedVector.get(1) * 50); // déplacement de la voiture
        Tools.error_print("CP : 3");

        actions = new ArrayList<>();
        addAction(speedVector); // ajout des nouveaux points
    }


    public ArrayList<Entity> getActions() {
        return actions;
    }

    public ArrayList<Integer> getSpeed() {
        return speedVector;
    }
}
