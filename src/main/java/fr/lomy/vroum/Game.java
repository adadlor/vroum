package fr.lomy.vroum;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import fr.lomy.vroum.Componant.Car;
import fr.lomy.vroum.Data.PointData;
import fr.lomy.vroum.Factory.GameFactory;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Game {

    public Game() {
        getGameWorld().addEntityFactory(new GameFactory());
        spawn("Map", 0, 0);
        var car = spawn("Car", 100, 100);



        FXGL.onBtnDown(MouseButton.PRIMARY, () -> {
            Tools.info_print("Click on a point");
            var coords = getInput().getMousePositionWorld();
            var eX = coords.getX();
            var eY = coords.getY();

            var actions = car.getComponent(Car.class).getActions();
            var speed = car.getComponent(Car.class).getSpeed();

            Tools.error_print("CP : OSCOUR");
            for (Entity action : actions) {
                var x = action.getX();
                var y = action.getY();
                Tools.debug_print("x: " + x + " y: " + y + " eX: " + eX + " eY: " + eY);
                if (eX >= x-10 && eX <= x + 10 && eY >= y-10 && eY <= y + 10) {
                    Tools.info_print("Click on a point | x: " + x + " | y: " + y);
                    var cpSpeed = new ArrayList<>(speed);
                    var entitySpeed = action.getComponent(PointData.class).getSpeed();
                    cpSpeed.set(0, cpSpeed.get(0) + entitySpeed.get(0));
                    cpSpeed.set(1, cpSpeed.get(1) + entitySpeed.get(1));
                    car.getComponent(Car.class).move(cpSpeed);
                }
            }
        });

    }
}
