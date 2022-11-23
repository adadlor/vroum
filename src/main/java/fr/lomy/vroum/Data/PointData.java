package fr.lomy.vroum.Data;

import com.almasb.fxgl.entity.component.Component;

import java.util.ArrayList;

public class PointData extends Component {

    private Double x;
    private Double y;

    private ArrayList<Integer> speed = new ArrayList<>() {{
        add(0); // offsetX
        add(0); // offsetY
    }};

    public PointData(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public ArrayList<Integer> getSpeed() {
        return speed;
    }

    public void setSpeed(Integer offsetX, Integer offsetY) {
        this.speed.set(0, offsetX);
        this.speed.set(1, offsetY);
    }

}
