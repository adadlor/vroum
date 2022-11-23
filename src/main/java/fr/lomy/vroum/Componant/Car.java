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


    private ArrayList<Integer> speed = new ArrayList<>() {{
        add(0); // offsetX
        add(0); // offsetY
    }};
    private final Color color;

    public Car(Double x, Double y, Color color) {
        Tools.info_print("Creation d'une voiture");
        this.color = color;
        this.x = x;
        this.y = y;
        this.xCenter = x + (FXGL.texture("cars/pitstop_car_1.png").getWidth()*0.07)/2;
        this.yCenter = y + (FXGL.texture("cars/pitstop_car_1.png").getHeight()*0.07)/2;
        addAction(this.speed);
    }

    public void addAction(ArrayList<Integer> speed){
        // Draw a box around the car with black points
        // 3x3
        Tools.debug_print("Add action speed: offX: " + speed.get(0) + " offY: " + speed.get(1));
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                var x = (this.xCenter + i * 50)+1*speed.get(0);
                var y = (this.yCenter + j * 50)+1*speed.get(1);
                Tools.debug_print("Add action x: " + x + " y: " + y);
                var point = FXGL.spawn("Choice", x, y);
                point.getComponent(PointData.class).setSpeed(speed.get(0)+i, speed.get(1)+j);
                actions.add(point);

            }
        }
    }

    public void move(ArrayList<Integer> speed){
        for (Entity action : actions) {
            action.removeFromWorld();
        }

        Tools.error_print("CP : 1");

        this.speed = speed;
        this.xCenter += speed.get(0) * 50;
        this.yCenter += speed.get(1) * 50;
        Tools.error_print("CP : 2");
        entity.translateX(speed.get(0) * 50);
        entity.translateY(speed.get(1) * 50);
        Tools.error_print("CP : 3");

        actions = new ArrayList<>();
        addAction(speed);
    }


    public ArrayList<Entity> getActions() {
        return actions;
    }

    public ArrayList<Integer> getSpeed() {
        return speed;
    }
}
