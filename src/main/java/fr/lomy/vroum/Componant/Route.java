package fr.lomy.vroum.Componant;

import com.almasb.fxgl.texture.Texture;

public abstract class Route {
    protected String textureName;
    protected String name;

    private double x;
    private double y;

    public Route() {}


    public String getTextureName() {
        return textureName;
    }

    public String getName() {
        return name;
    }

    public void update(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
