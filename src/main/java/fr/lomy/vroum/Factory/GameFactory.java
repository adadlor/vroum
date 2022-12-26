package fr.lomy.vroum.Factory;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.multiplayer.NetworkComponent;
import fr.lomy.vroum.Component.Car;
import fr.lomy.vroum.Data.PointData;
import fr.lomy.vroum.Enums.EntityTypes;
import fr.lomy.vroum.Tools.Tools;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameFactory implements EntityFactory {

    public GameFactory() { Tools.debug_print("GameFactory"); }

    @Spawns("Car")
    public Entity newCar(SpawnData data) {
        Double size = 0.03; // Taille de la voiture
        return FXGL.entityBuilder(data) // Création de l'entité
                .type(EntityTypes.CAR) // Définition du type de l'entité
                .viewWithBBox("cars/pitstop_car_1.png") // Définition de l'image de l'entité
                .with(new Car(data.getX(),data.getY(),Color.RED)) // Définition des composants de l'entité
                .with(new NetworkComponent()) // Pour la synchronisation en réseau
                .scale(size , size) // Définition de la taille de l'entité
                .scaleOrigin(size , size) // Définition du point d'origine de la taille de l'entité
                .build();
    }

    @Spawns("Map")
    public Entity newMap(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityTypes.MAP) // Définition du type de l'entité
                .view("maps/Carte2.png") // Définition de l'image de l'entité
                .with(new NetworkComponent()) // Pour la synchronisation en réseau
                .build();
    }

    @Spawns("Choice")
    public Entity newChoice(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityTypes.CHOICE) // Définition du type de l'entité
                .view(new Circle(5, Color.RED)) // Définition de l'image de l'entité
                .with(new PointData()) // Définition des composants de l'entité
                .build();
    }
}
