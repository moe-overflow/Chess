package View;

import Control.Main;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

import static Model.Interfaces.Gui.*;
import static Model.Interfaces.Gui.CREDITS_MENUITEM;

public class TopView
{
    public static VBox createTopView(Main main)
    {
        VBox vBox = new VBox();
        MenuBar menuBar = createMenuBar(main);
        vBox.getChildren().addAll(menuBar);
        return vBox;
    }

    public static MenuBar createMenuBar(Main main)
    {
        MenuBar menuBar = new MenuBar();
        Menu game = new Menu(GAME_MENU);
        Menu options = new Menu(OPTIONS_MENU);
        Menu about = new Menu(ABOUT_MENU);

        // game
        MenuItem newGame = new MenuItem(NEW_GAME_MENUITEM);
        MenuItem saveGame = new MenuItem(SAVE_GAME_MENUITEM);
        MenuItem mainMenu = new MenuItem(MAIN_MENU_MENUITEM);
        MenuItem exit = new MenuItem(EXIT_MENUITEM);

        // options
        MenuItem sound = new MenuItem(SOUND_MENUITEM);

        // about
        MenuItem credits = new MenuItem(CREDITS_MENUITEM);

        game.getItems().addAll(newGame, saveGame, mainMenu, exit);
        options.getItems().addAll(sound);
        about.getItems().addAll(credits);

        menuBar.getMenus().addAll(game, options, about);

        mainMenu.setOnAction(e -> {
            main.getStartScreen().getChildren().clear();
            StartScreenView.setUpStartScreen(main.getStartScreen(), main);
            main.getScene().setRoot(main.getStartScreen());
        });

        return menuBar;
    }
}
