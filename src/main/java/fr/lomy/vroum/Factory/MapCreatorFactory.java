package fr.lomy.vroum.Factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import fr.lomy.vroum.Enums.EntityTypes;
import fr.lomy.vroum.Main;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapCreatorFactory implements EntityFactory {
    public MapCreatorFactory() {
        Tools.debug_print("MapCreatorFactory");
    }

    @Spawns("Route")
    public Entity newRoute(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(EntityTypes.ROUTE)
                .view(new Rectangle(25, 50, Color.BLUE))
                .build();
    }

    @Spawns("Floor")
    public Entity newFloor(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityTypes.FLOOR)
                .view(new Rectangle(Main.WIDTH, Main.HEIGHT, Color.GREEN))
                .build();
    }

}
