package fr.lomy.vroum.Interface;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;
import org.jetbrains.annotations.NotNull;

public class InterfaceImplement extends SceneFactory {

    @NotNull
    @Override
    public FXGLMenu newMainMenu() {
        return new MainMenu(MenuType.MAIN_MENU);
    }
}
