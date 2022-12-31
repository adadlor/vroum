package fr.lomy.vroum.Component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import fr.lomy.vroum.Data.PointData;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import javafx.geometry.Point2D;

public class Car extends Component {

    private Point2D carCenter;

    private int scale = 50;

    private ArrayList<Entity> actions = new ArrayList<>(); // Liste des points possibles

    private double angle = 0;
    private Point2D speedVector = new Point2D(0,0);
    private final Color color;

    public Car(Double x, Double y, Color color) {
        Tools.info_print("Creation d'une voiture");
        this.color = color;
        this.carCenter = new Point2D(x + (FXGL.texture("cars/pitstop_car_1.png").getWidth()*0.03)/2,
                y + (FXGL.texture("cars/pitstop_car_1.png").getHeight()*0.03)/2);  // X et Y du centre de l'image 0.07 = scale
        addAction(this.speedVector);
    }

    public void addAction(Point2D speedVector){
        // Draw a box around the car with black points
        // 3x3
        Tools.debug_print("Add action speed: offX: " + speedVector.getX() + " offY: " + speedVector.getY());
        for (int i = -1; i <= 1; i++) { // x
            for (int j = -1; j <= 1; j++) { // y
                var x = (this.carCenter.getX() + i * scale)+speedVector.getX() *scale; // coordonnée x du point
                var y = (this.carCenter.getY() + j * scale)+speedVector.getY() *scale; // coordonnée y du point
                Tools.debug_print("Add action x: " + x + " y: " + y);
                var point = FXGL.spawn("Choice", x, y); // spawn du point
                point.getComponent(PointData.class).setSpeed(i, j); // set de la vitesse du point
                actions.add(point); // ajout du point dans la liste des points
            }
        }
    }

    public void onAdded(){
        entity.getTransformComponent().setScaleOrigin(this.carCenter);
    }

    public void move(Point2D speedVector){
        for (Entity action : actions) {
            action.removeFromWorld(); // suppression des points
        }

        Tools.error_print("CP : 1");

        this.speedVector = speedVector; // set de la nouvelle vitesse
        this.carCenter = this.carCenter.add(speedVector.multiply(scale)); // set de la nouvelle position x et y
        this.angle = this.speedVector.angle(0,1) - this.angle;
        Tools.error_print("CP : 2");
        entity.translateX(speedVector.getX() * scale); // déplacement de la voiture
        entity.translateY(speedVector.getY() * scale); // déplacement de la voiture
        //entity.rotateToVector(speedVector);
        Tools.error_print("CP : 3");
        actions = new ArrayList<>();
        addAction(speedVector); // ajout des nouveaux points
    }


    public ArrayList<Entity> getActions() {
        return actions;
    }

    public Point2D getSpeed() {
        return speedVector;
    }
}
