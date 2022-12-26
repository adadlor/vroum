package fr.lomy.vroum;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.core.serialization.Bundle;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.multiplayer.MultiplayerService;
import com.almasb.fxgl.net.Connection;
import fr.lomy.vroum.Componant.Circuit;
import fr.lomy.vroum.Componant.StartPoint;
import fr.lomy.vroum.Factory.GameFactory;
import fr.lomy.vroum.Factory.MapCreatorFactory;
import fr.lomy.vroum.Interface.InterfaceImplement;
import fr.lomy.vroum.Interface.MapCreatorInterface;
import fr.lomy.vroum.Tools.Tools;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.function.Consumer;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication {

    public static final int WIDTH = 1000; // Largeur de la fenêtre
    public static final int HEIGHT = 800; // Hauteur de la fenêtre
    private static int LevelType = 0; // Type de niveau (1 = jeu, 2 = map creator)

    private Circuit circuit; // Circuit

    private static Connection<Bundle> connection; // Connexion au serveur


    public static void main(String[] args) {
        Tools.debug_print("Vroum Vroum");
        launch(args); // Lancement du jeu dans l'environnement FXGL
    }

    @Override
    /**
     * Initialisation des paramètres du jeu
     */
    protected void initSettings(GameSettings gameSettings) {
        Tools.debug_print("initSettings");
        gameSettings.setWidth(WIDTH); // Largeur de la fenêtre
        gameSettings.setHeight(HEIGHT); // Hauteur de la fenêtre
        gameSettings.setTitle("Vroum Vroum"); // Titre de la fenêtre
        gameSettings.setVersion("SC 0.1"); // Version du jeu
        gameSettings.setMainMenuEnabled(true); // Affichage du menu principal
        gameSettings.setSceneFactory(new InterfaceImplement()); // Ajout de la classe de gestion des interfaces
        gameSettings.addEngineService(MultiplayerService.class); // Ajout du service de multi-joueur
        /*
        Import CSS
         */
        gameSettings.getCSSList().add("MapCreator.css"); // Ajout du CSS du MapCreator

    }

    @Override
    protected void initGame() {
        Tools.debug_print("initGame : LevelType = " + LevelType); // Affichage du type de niveau
        switch (LevelType) { // Switch sur le type de niveau
            case 1: // Si le niveau est un jeu

                var server = getNetService().newTCPServer(55555); // Création d'un serveur TCP sur le port 55555
                System.out.println("Server started on port 55555");
                server.setOnConnected((conn) -> {
                    System.out.println("Server connected");
                    connection = conn; // Connexion au serveur
                    getExecutor().startAsyncFX(this::onServer);
                });
                server.startAsync(); // Lancement du serveur
                break;
            case 2: // Rejoindre une partie

                runOnce(() ->{
                    getDialogService().showInputBox("Rejoindre une partie", (anwser) -> {
                        if(anwser.equals("") || !Tools.ipIsValid(anwser)){
                            getDialogService().showMessageBox("Veuillez entrer une IP valide");
                        }else{
                            var client = getNetService().newTCPClient(anwser, 55555); // Création d'un client TCP sur le port 55555
                            Tools.info_print("Client connecté à l'adresse " + anwser + " sur le port 55555");
                            client.setOnConnected((conn) -> {
                                System.out.println("Client connected");
                                connection = conn; // Connexion au serveur

                                onClient();
                            });
                            client.connectAsync();
                        }
                    });
                }, Duration.seconds(1)); // Délai de 1 seconde

                break;
            case 3: // Si le niveau est un MapCreator
                new MapCreator(); // Instanciation de la classe MapCreator
                break;
            default:
                Tools.debug_print("initGame : LevelType non reconnu"); // Affichage d'un message d'erreur
                Tools.error_print("initGame : LevelType = " + LevelType + " is not a valid LevelType");
                break;
        }

    }

    private void onServer() {
        Tools.debug_print("onServer");
        new Server(connection); // Instanciation de la classe Game
        //getGameWorld().addEntityFactory(new GameFactory()); // Ajout de la factory (pour les entités du jeu) dans le monde du jeu

        //var map = spawn("Map", 0, 0); // Spawn de la map (carte en arrière plan)
        //getService(MultiplayerService.class).spawn(connection,map,"Map"); // Ajout de la map dans le monde du jeu (Coté serveur)

    }

    private void onClient() {

        //new Game(); // Instanciation de la classe Game
        getGameWorld().addEntityFactory(new GameFactory()); // Ajout de la factory (pour les entités du jeu) dans le monde du jeu
        getService(MultiplayerService.class).addEntityReplicationReceiver(connection, getGameWorld());
    }




    @Override
    /**
     * Initialisation des touches du jeu
     */
    protected void initInput() {
        Tools.debug_print("initInput");
        onKeyDown(KeyCode.F, () -> getNotificationService().pushNotification("Hello World!")); // Affiche une notification

        onKeyDown(KeyCode.F1, "Show Rules", () -> { // Affiche une boite de dialogue avec les règles du jeu
            getDialogService().showMessageBox("Rules");
        });

        onKeyDown(KeyCode.G, () -> {
            if (LevelType == 2) {
                getNotificationService().pushNotification("Map Creator");
            }
        });

        //Mouse input
        /*
        onBtnDown(MouseButton.PRIMARY, () -> {
            switch (LevelType){
                case 1:
                    // For the game
                    break;
                case 2:
                    // For MapCreator
                    var coords = getInput().getMousePositionWorld();
                    var x = coords.getX();
                    var y = coords.getY();
                    switch (MapCreator.getActionType()) {
                        case 0:
                            // Centrage du placement
                            x = x -((int)(StartPoint.getSTARTPOINT_SIZEY()/2));
                            y = y -((int)(StartPoint.getSTARTPOINT_SIZEY()/2));

                            if (circuit == null) {
                                circuit = new Circuit(x, y);
                            } else {
                                circuit.update(x, y);
                            }

                            MapCreatorInterface.setStartPlaced(true);
                            break;
                        case 1:
                            //entities.add(spawn("Road", x, y));
                            break;
                        default:
                            Tools.error_print("initInput : MapCreator.getActionType() = " + MapCreator.getActionType() + " is not a valid actionType");
                            break;
                    }
                    break;
                default:
                    Tools.error_print("initInput : LevelType = " + LevelType + " is not a valid LevelType");
                    break;
            }
        });


         */

    }

    public static void setLevelType(int levelType) {
        LevelType = levelType; // Définition du type de niveau
    }

    public static Connection<Bundle> getConnection() {
        return connection; // Retourne la connexion au serveur
    }



}
