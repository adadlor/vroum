package fr.lomy.vroum;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import fr.lomy.vroum.Component.Car;
import fr.lomy.vroum.Data.PointData;
import fr.lomy.vroum.Factory.GameFactory;
import fr.lomy.vroum.Tools.Tools;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

import static com.almasb.fxgl.dsl.FXGL.*;

/*
Classe qui gère le jeu
 */

public class Game {

    private ArrayList<Entity> cars = new ArrayList<>(); // Liste des entités voiture
    private HashMap<Entity, Integer> carsMovesNumber = new HashMap<>(); // Liste des entités voiture et du nombre de mouvement qu'elle a effectué
    private Integer whoIsPlaying = 0; // Qui joue


    // Interface
    private Text whoIsPlayingText; // Texte qui indique qui joue

    public Game() {

        /*
        Creation des entités
         */

        getGameWorld().addEntityFactory(new GameFactory()); // Ajout de la factory (pour les entités du jeu) dans le monde du jeu
        spawn("Map", 0, 0); // Spawn de la map (carte en arrière plan)
        Entity car1 = spawn("Car", 820, 390);  // Spawn de la voiture 1
        Entity car2 = spawn("Car", 850, 390); // Spawn de la voiture 2
        cars.add(car1); // Ajout de la voiture 1 dans la liste des voitures
        cars.add(car2); // Ajout de la voiture 2 dans la liste des voitures
        carsMovesNumber.put(car1, 0); // Ajout de la voiture 1 dans la liste des voitures et du nombre de mouvement qu'elle a effectué
        carsMovesNumber.put(car2, 0); // Ajout de la voiture 2 dans la liste des voitures et du nombre de mouvement qu'elle a effectué

        /*
        Creation de l'inteface
         */

        whoIsPlayingText = new Text("Joueur " + (whoIsPlaying+1) + "• Nombre de déplacement : "+carsMovesNumber.get(cars.get(whoIsPlaying))); // Création du texte qui indique qui joue
        HBox hBox = new HBox(whoIsPlayingText); // Création de la box qui contient le texte
        hBox.setTranslateX(10); // Position de la box
        hBox.setTranslateY(10); // Position de la box

        getGameScene().addUINode(hBox); // Ajout du texte dans la scene du jeu

        FXGL.onBtnDown(MouseButton.PRIMARY, () -> { // Quand on clique sur la souris gauche
            Tools.info_print("Click on a point"); // Affichage d'un message dans la console
            var coords = getInput().getMousePositionWorld(); // Récupération des coordonnées de la souris
            var eX = coords.getX(); // Récupération de la coordonnée X de la souris
            var eY = coords.getY(); // Récupération de la coordonnée Y de la souris

            Entity car = cars.get(whoIsPlaying); // Récupération de la voiture qui joue

            Car carComponent = car.getComponent(Car.class); // Récupération du composant voiture (La classe dans Composant/Car.java) de l'entité
            ArrayList<Entity> currentAction = carComponent.getActions(); // Récupération de la liste des entités "Choice" (les points) de la voiture.
            Point2D speed = carComponent.getSpeed(); // Récupération du vecteur de vitesse de l'entité

            for (Entity action : currentAction) { // Pour chaque entité "Choice" (les points)
                var x = action.getX(); // Récupération de la coordonnée X de l'entité
                var y = action.getY(); // Récupération de la coordonnée Y de l'entité
                Tools.debug_print("x: " + x + " y: " + y + " eX: " + eX + " eY: " + eY);
                if (eX >= x - 10 && eX <= x + 10 && eY >= y - 10 && eY <= y + 10) { // Si on clique sur un point
                    Tools.info_print("Click on a point | x: " + x + " | y: " + y);
                    Point2D cpSpeed = carComponent.getSpeed(); // Création d'un nouveau vecteur de vitesse
                    Point2D entitySpeed = action.getComponent(PointData.class).getSpeed(); // Récupération du vecteur de vitesse de l'entité "Choice" (le point)
                    cpSpeed = cpSpeed.add(entitySpeed); // Ajout du vecteur de vitesse de l'entité "Choice" au vecteur de vitesse de la voiture
                    System.out.println(cpSpeed);// Déplacement de la voiture avec le nouveau vecteur de vitesse
                    car.getComponent(Car.class).move(cpSpeed);
                    whoIsPlaying = whoIsPlaying + 1 >= cars.size() ? 0 : whoIsPlaying + 1; // Changement de la voiture qui joue
                    var carMovesNumber = carsMovesNumber.get(car); // Récupération du nombre de mouvement de la voiture qui joue
                    carsMovesNumber.put(car, carMovesNumber + 1); // Ajout d'un déplacement à la voiture qui joue
                    whoIsPlayingText.setText("Joueur " + (whoIsPlaying+1) + "• Nombre de déplacement : "+carsMovesNumber.get(cars.get(whoIsPlaying))); // Changement du texte qui indique qui joue
                }
            }
        });
    }


    public void reset() {
        getGameWorld().getEntities().forEach(Entity::removeFromWorld);
        cars.clear();
        whoIsPlaying = 0;
        getGameWorld().addEntityFactory(new GameFactory());

        spawn("Map", 0, 0);
        cars.add(spawn("Car", 820, 390));
        cars.add(spawn("Car", 850, 390));

    }
}
