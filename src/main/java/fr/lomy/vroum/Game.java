package fr.lomy.vroum;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import fr.lomy.vroum.Componant.Car;
import fr.lomy.vroum.Data.PointData;
import fr.lomy.vroum.Factory.GameFactory;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.*;

/*
Classe qui gère le jeu
 */

public class Game {

    ArrayList<Entity> cars = new ArrayList<>(); // Liste des entités voiture

    public Game() {
        getGameWorld().addEntityFactory(new GameFactory()); // Ajout de la factory (pour les entités du jeu) dans le monde du jeu
        spawn("Map", 0, 0); // Spawn de la map (carte en arrière plan)
        Entity car1 = spawn("Car", 100, 100);  // Spawn de la voiture 1
        Entity car2 = spawn("Car", 200, 200); // Spawn de la voiture 2
        cars.add(car1); // Ajout de la voiture 1 dans la liste des voitures
        cars.add(car2); // Ajout de la voiture 2 dans la liste des voitures

        FXGL.onBtnDown(MouseButton.PRIMARY, () -> { // Quand on clique sur la souris gauche
            Tools.info_print("Click on a point"); // Affichage d'un message dans la console
            var coords = getInput().getMousePositionWorld(); // Récupération des coordonnées de la souris
            var eX = coords.getX(); // Récupération de la coordonnée X de la souris
            var eY = coords.getY(); // Récupération de la coordonnée Y de la souris

            for (Entity car : cars) { // Pour chaque entité voiture
                /*
                Optimisation possible ici.
                 */

                Car carComponant = car.getComponent(Car.class); // Récupération du composant voiture (La classe dans Composant/Car.java) de l'entité
                ArrayList<Entity> currentaction = carComponant.getActions(); // Récupération de la liste des entités "Choice" (les points) de la voiture.
                ArrayList<Integer> speed = carComponant.getSpeed(); // Récupération du vecteur de vitesse de l'entité

                for (Entity action : currentaction) { // Pour chaque entité "Choice" (les points)
                    var x = action.getX(); // Récupération de la coordonnée X de l'entité
                    var y = action.getY(); // Récupération de la coordonnée Y de l'entité
                    Tools.debug_print("x: " + x + " y: " + y + " eX: " + eX + " eY: " + eY);
                    if (eX >= x-10 && eX <= x + 10 && eY >= y-10 && eY <= y + 10) { // Si on clique sur un point
                        Tools.info_print("Click on a point | x: " + x + " | y: " + y);
                        var cpSpeed = new ArrayList<>(speed); // Création d'un nouveau vecteur de vitesse
                        var entitySpeed = action.getComponent(PointData.class).getSpeed(); // Récupération du vecteur de vitesse de l'entité "Choice" (le point)
                        cpSpeed.set(0, cpSpeed.get(0) + entitySpeed.get(0)); // Ajout du vecteur de vitesse de l'entité "Choice" au vecteur de vitesse de la voiture [x]
                        cpSpeed.set(1, cpSpeed.get(1) + entitySpeed.get(1)); // Ajout du vecteur de vitesse de l'entité "Choice" au vecteur de vitesse de la voiture [y]
                        car.getComponent(Car.class).move(cpSpeed); // Déplacement de la voiture avec le nouveau vecteur de vitesse
                    }
                }
            }


            Tools.error_print("CP : OSCOUR");

        });

    }
}
