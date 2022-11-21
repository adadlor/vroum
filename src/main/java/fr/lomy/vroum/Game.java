package fr.lomy.vroum;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

public class Game {

    public Game() {
        getGameWorld().addEntityFactory(new SimpleFactory());
    }
}
