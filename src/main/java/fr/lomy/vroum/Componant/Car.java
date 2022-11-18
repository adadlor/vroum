package fr.lomy.vroum.Componant;

import javafx.scene.paint.Color;

public class Car {
    private int speed = 0;
    private final Color color;

    public Car(Color color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void increaseSpeed() {
        speed++;
    }

    public void decreaseSpeed() {
        speed--;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }




}
