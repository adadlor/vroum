package fr.lomy.vroum.Factory;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import fr.lomy.vroum.Componant.Car;
import fr.lomy.vroum.Data.PointData;
import fr.lomy.vroum.Enums.EntityTypes;
import fr.lomy.vroum.Main;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameFactory implements EntityFactory {

    public GameFactory() { Tools.debug_print("GameFactory"); }

    @Spawns("Car")
    public Entity newCar(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(EntityTypes.CAR)
                .viewWithBBox("cars/pitstop_car_1.png")
                .with(new Car(data.getX(),data.getY(),Color.RED))
                .scale(0.07 , 0.07)
                .scaleOrigin(0.07 , 0.07)
                .build();
    }

    @Spawns("Map")
    public Entity newMap(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityTypes.MAP)
                .view(new Rectangle(Main.WIDTH, Main.HEIGHT, Color.GREEN))
                .build();
    }

    @Spawns("Choice")
    public Entity newChoice(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityTypes.CHOICE)
                .view(new Circle(5, Color.RED))
                .with(new PointData(data.getX(), data.getY()))
                .build();
    }
}
