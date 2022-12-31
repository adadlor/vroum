package fr.lomy.vroum.Interface;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.FontType;
import fr.lomy.vroum.Game;
import fr.lomy.vroum.Main;
import fr.lomy.vroum.MapCreator;
import fr.lomy.vroum.Rules;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MainMenu extends FXGLMenu {
    private static ObjectProperty<MenuButton> selectedButton; // Pour changer la description du bouton
    public MainMenu() {
        super(MenuType.MAIN_MENU); //récupération des construteurs et autre du FXGLMenu ( du main menu )

        // Mise en place du background
        getContentRoot().getChildren().addAll(FXGL.texture("Menu_Route.jpeg",getAppWidth(), getAppHeight()));

        // Mise en place des boutons et de leurs actions
        MenuButton btnPlay = new MenuButton("Play game","Start New game", ()-> {
            fireNewGame();
            if (Main.game == null) {
                Main.mapCreator = null;
                Main.game = new Game();
            } else {
                Main.game.reset();
            }
        });
        MenuButton btnCreate = new MenuButton("Map Creator","Create a new map", ()->{
            fireNewGame();
            if (Main.mapCreator == null) {
                Main.game = null;
                Main.mapCreator = new MapCreator();
            } else {
                Main.mapCreator.reset();
            }
        });
        MenuButton btnRules = new MenuButton("Rules","Rules",Rules::new);
        MenuButton btnExit = new MenuButton("Exit","Quit the game", this::fireExit);

        selectedButton = new SimpleObjectProperty<>(btnPlay); // crée le texte de la variable bouton play

        var textDescription = FXGL.getUIFactoryService().newText("", Color.LIGHTGREY, 25);
        textDescription.textProperty().bind(
                Bindings.createStringBinding(() -> selectedButton.get().description, selectedButton)//Met la description
        );

        //Les mettres dans une box
        var box = new VBox(15,//Taille entre les boxs
                FXGL.getUIFactoryService().newText("VROOM",Color.WHITE,FontType.GAME, 100.0),
                btnPlay,
                btnCreate,
                btnRules,
                btnExit,
                new Separator(Orientation.HORIZONTAL),
                textDescription);

        //Placer la box sur la fenêtre
        box.setTranslateX(120);
        box.setTranslateY(300);
        //Y rajouter tout les boutons
        getContentRoot().getChildren().addAll(box);
    }

    // Création d'une classe pour créer des boutons
    private static class MenuButton extends StackPane {
        private final String name;
        private final String description;
        private final Runnable action;
        private final Text text;
        private final Rectangle selector;
        private final Rectangle selector2;

        private static final Color Couleur_Choisi_Clavier = Color.WHITE;
        private static final Color Couleur_NON_Choisi_Clavier = Color.GREY;

        public MenuButton (String name, String description, Runnable action){
            this.name = name;
            this.description = description;
            this.action = action;

            //Mise en place du texte dans le bouton:
            text = FXGL.getUIFactoryService().newText(name, Color.WHITE, FontType.TEXT, 30.0); // création du texte

            // Faire le changement de couleur du focus (Clavier)
            text.fillProperty().bind(
                    Bindings.when(focusedProperty()).then(Couleur_Choisi_Clavier).otherwise(Couleur_NON_Choisi_Clavier)
            );

            //Mise en place du rectangle selecteur Clavier:
            selector = new Rectangle(7,17, Color.WHITE);
            selector.setTranslateX(-25);
            selector.setTranslateY(-2);
            selector.visibleProperty().bind(focusedProperty());//Change la visibilité en focus clavier

            focusedProperty().addListener((observable, oldvalue, isSelected)-> { //Quand le focus change
                if (isSelected){ //Si quelque chose est selectionné
                    selectedButton.setValue(this); //Changer la description
                }
            });

            //Mise en place du rectangle selecteur Souris:
            selector2 = new Rectangle(350,30, Color.WHITE);
            selector2.setOpacity(0.30);
            selector2.setTranslateX(-25);
            selector2.visibleProperty().bind(hoverProperty());//Change la visibilité avec la souris


            setAlignment(Pos.CENTER_LEFT); // Alignement à gauche
            setFocusTraversable(true);//Fait en sorte que le focus fonctionne

            setOnKeyPressed(e->{ //Lorsqu'une touche est pressé :
                if(e.getCode() == KeyCode.ENTER) { //Si c'est entrer :
                    this.action.run(); //Faire l'action associé
                }
            });
            //Lorsqu'on clique :
            setOnMouseClicked(e->{ this.action.run(); }); //Faire l'action associé


            getChildren().addAll(text, selector, selector2); // Ajouter les elements
        }
    }
}
