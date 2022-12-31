package fr.lomy.vroum.Interface;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.FontType;
import fr.lomy.vroum.Main;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class GameMenu extends FXGLMenu {
    public GameMenu() {
        super(MenuType.GAME_MENU); //Game Menu (différent du main menu celui là s'affiche quand on est dans le jeu)

        //Rectangle de fond noir
        Rectangle fondNoir = new Rectangle(getAppWidth(),getAppHeight(), Color.BLACK);
        fondNoir.setOpacity(0.40);
        //Rectangle de boite
        Rectangle fondBox = new Rectangle(getAppWidth()/1.3,getAppHeight()/2, Color.BLACK);
        fondBox.setX(getAppWidth()/2 - getAppWidth()/1.3/2);
        fondBox.setY(getAppHeight()/2 - getAppHeight()/1.5/2);

        //Text en haut de la box
        Text text = FXGL.getUIFactoryService().newText("What do you want to do",Color.WHITE,FontType.GAME, 70.0);

        //Game Button
        GameButton resume = new GameButton("Resume", ()->this.fireResume());
        GameButton exitToMenu = new GameButton("Exit to main menu", ()->{
            this.fireExitToMainMenu();
        });
        GameButton new_game = new GameButton("Restart Game", ()-> {
            this.fireNewGame();
            Main.game.reset();
        });
        GameButton new_mapcreator = new GameButton("Restart MapCreator", ()-> {
            this.fireNewGame();
            Main.mapCreator.reset();
        });

        //Tout mettre dans la box
        var box = new VBox(15, text, resume, new_game, new_mapcreator, exitToMenu);

        //Aligner les éléments dedant
        box.setAlignment(Pos.CENTER)
        ;
        //Placer la box sur la fenêtre
        box.setTranslateX(getAppWidth()/2 - getAppWidth()/1.7/2);
        box.setTranslateY(getAppHeight()/2 - getAppHeight()/4);

        //Application de tout les éléments au menu
        getContentRoot().getChildren().addAll(fondNoir, fondBox, box);
    }

    private static class GameButton extends StackPane{
        private final String name;
        private final Runnable action;
        private final Text text;
        private Rectangle selector;
        public GameButton (String name, Runnable action){
            this.name = name;
            this.action = action;

            //Mise en place du texte dans le bouton:
            text = FXGL.getUIFactoryService().newText(name, Color.WHITE, FontType.TEXT, 30.0);

            //Mise en place du rectangle selecteur Souris:
            selector = new Rectangle(350,30, Color.WHITE);
            selector.setOpacity(0.30);
            selector.visibleProperty().bind(hoverProperty());//Change la visibilité avec la souris

            setOnMouseClicked(e->{ this.action.run(); }); //Faire l'action associé

            getChildren().addAll(text, selector); // Ajouter les elements
        }
    }
}
