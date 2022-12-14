package view;

import control.Main;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import static model.GuiConstants.*;
import static model.GuiConstants.CREDITS_MENUITEM;

public class TopView
{
    public static VBox createTopView()
    {
        VBox vBox = new VBox();
        MenuBar menuBar = createMenuBar();
        vBox.getChildren().addAll(menuBar);
        return vBox;
    }

    public static MenuBar createMenuBar()
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


        Slider volumeSlider = new Slider(0, 100, 50);
        CustomMenuItem customMenuItem = new CustomMenuItem();
        customMenuItem.setContent(volumeSlider);
        options.getItems().add(customMenuItem);

        Label musicLabel = new Label("Set music volume:"); // TODO add label to volume slider


        // about
        MenuItem credits = new MenuItem(CREDITS_MENUITEM);

        game.getItems().addAll(newGame, saveGame, mainMenu, exit);
//        options.getItems().addAll(sound);
        about.getItems().addAll(credits);

        menuBar.getMenus().addAll(game, options, about);

        mainMenu.setOnAction(e -> {
            Main.getInstance().getStartScreen().getChildren().clear();
            StartScreenView.setUpStartScreen(Main.getInstance().getStartScreen());
            Main.getInstance().getScene().setRoot(Main.getInstance().getStartScreen());
        });

        exit.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

        return menuBar;
    }
}
