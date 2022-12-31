package fr.lomy.vroum.Component;

public class Player {
    private String name;
    private int hit;
    private Car car;

    public Player(String name, int hit, Car car) {
        this.name = name;
        this.hit = hit;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

}
