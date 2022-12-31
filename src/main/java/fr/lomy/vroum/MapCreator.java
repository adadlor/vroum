package fr.lomy.vroum;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import fr.lomy.vroum.Component.Circuit;
import fr.lomy.vroum.Component.StartPoint;
import fr.lomy.vroum.Factory.GameFactory;
import fr.lomy.vroum.Factory.MapCreatorFactory;
import fr.lomy.vroum.Interface.MapCreatorInterface;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * Classe principale du MapCreator
 */
public class MapCreator {

    /*
    Static side
     */

    private static int actionType = 0; // 0 = add start , 1 = add road

    private Circuit circuit;

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

        onBtnDown(MouseButton.PRIMARY, () -> {
            var coords = getInput().getMousePositionWorld();
            var x = coords.getX();
            var y = coords.getY();
            switch (MapCreator.getActionType()) {
                case 0:
                    // Centrage du placement
                    x = x -((int)(StartPoint.getSTARTPOINT_SIZEY()/2));
                    y = y -((int)(StartPoint.getSTARTPOINT_SIZEY()/2));

                    if (circuit == null) {
                        circuit = new Circuit(x, y);
                    } else {
                        circuit.update(x, y);
                    }

                    MapCreatorInterface.setStartPlaced(true);
                    break;
                case 1:
                    //entities.add(spawn("Road", x, y));
                    break;
                default:
                    Tools.error_print("initInput : MapCreator.getActionType() = " + MapCreator.getActionType() + " is not a valid actionType");
                    break;
            }
        });


    }

    public void reset() {

        getGameWorld().getEntities().forEach(Entity::removeFromWorld);
        getGameWorld().addEntityFactory(new MapCreatorFactory());
        spawn("Floor", 0, 0);

        var interfaceMapCreator = new MapCreatorInterface();
        addUINode(interfaceMapCreator, 0,Main.HEIGHT - 100);
    }
}
