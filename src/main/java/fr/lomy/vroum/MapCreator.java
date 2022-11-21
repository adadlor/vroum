package fr.lomy.vroum;

import fr.lomy.vroum.Factory.MapCreatorFactory;
import fr.lomy.vroum.Interface.MapCreatorInterface;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * Classe principale du MapCreator
 */
public class MapCreator {

    /*
    Static side
     */

    private static int actionType = 0; // 0 = add start , 1 = add road

    public static int getActionType() {
        return actionType;
    }

    public static void setActionType(int actionType) {
        MapCreator.actionType = actionType;
    }


    /*
    Instance side
     */

    public MapCreator() {

        getGameWorld().addEntityFactory(new MapCreatorFactory());
        spawn("Floor", 0, 0);

        var interfaceMapCreator = new MapCreatorInterface();
        addUINode(interfaceMapCreator, 0,Main.HEIGHT - 100);
    }
}
