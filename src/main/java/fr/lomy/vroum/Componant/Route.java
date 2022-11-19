package fr.lomy.vroum.Componant;

import com.almasb.fxgl.texture.Texture;

public abstract class Route {
    protected String textureName;
    protected String name;

    public Route() {}


    public String getTextureName() {
        return textureName;
    }

    public String getName() {
        return name;
    }

}
