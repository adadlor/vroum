package fr.lomy.vroum.Factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.texture.Texture;
import fr.lomy.vroum.Componant.Road;
import fr.lomy.vroum.Componant.StartPoint;
import fr.lomy.vroum.Enums.EntityTypes;
import fr.lomy.vroum.Main;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MapCreatorFactory implements EntityFactory {
    public MapCreatorFactory() {
        Tools.debug_print("MapCreatorFactory");
    }

    @Spawns("Road")
    public Entity newRoad(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(EntityTypes.ROAD)
                .view(new Rectangle(25, 50, Color.DARKGRAY))
                .with(new Road(data.getX(), data.getY()))
                .build();
    }

    @Spawns("Floor")
    public Entity newFloor(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityTypes.FLOOR)
                .view(new Rectangle(Main.WIDTH, Main.HEIGHT, Color.GREEN))
                .build();
    }

    @Spawns("StartPoint")
    public Entity newStartPoint(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityTypes.STARTPOINT)
                .view(new Rectangle(StartPoint.getSTARTPOINT_SIZEX(),StartPoint.getSTARTPOINT_SIZEY(), Color.RED))
                .view(new Circle(StartPoint.getSTARTPOINT_SIZEX()/2,StartPoint.getSTARTPOINT_SIZEY()/2, 5, Color.BLACK))

                .with(new StartPoint(data.getX(), data.getY()))
                .build();
    }

}
