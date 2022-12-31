package fr.lomy.vroum.Data;

import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class PointData extends Component {

    /*
     La vitesse (ou X = [-1,0,1] & Y = [-1,0,1] ) qui sera plus tard ajouté à la vitesse de la voiture.
     */
    private Point2D speed = new Point2D(0,0);

    public PointData() {}

    public Point2D getSpeed() {
        return speed;
    }

    public void setSpeed(Integer offsetX, Integer offsetY) {
        this.speed = new Point2D(offsetX,offsetY); // Assignation des valeurs de vitesse (toujours comprise entre [-1,0,1])
    }

}
