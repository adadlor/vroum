package fr.lomy.vroum.Data;

import com.almasb.fxgl.entity.component.Component;

import java.util.ArrayList;

public class PointData extends Component {

    private Double x;
    private Double y;

    /*
    La vitesse (ou X = [-1,0,1] & Y = [-1,0,1] ) qui sera plus tard ajouté à la vitesse de la voiture.
     */
    private ArrayList<Integer> speed = new ArrayList<>() {{
        add(0); // offsetX (x) [-1, 0, 1]
        add(0); // offsetY (y) [-1, 0, 1]
    }};

    public PointData(Double x, Double y) {
        this.x = x; // Assignation des coordonnées du point [x]
        this.y = y; // Assignation des coordonnées du point [y]
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
        this.speed.set(0, offsetX); // Assignation des valeurs de vitesse (toujours comprise entre [-1,0,1] [x]
        this.speed.set(1, offsetY);// Assignation des valeurs de vitesse (toujours comprise entre [-1,0,1] [y]
    }

}
