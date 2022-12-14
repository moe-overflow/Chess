package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public interface GuiConstants
{
    // window (stage)
    int WINDOW_WIDTH = 1200;
    int WINDOW_HEIGHT = 800;


    // Start screen wallpaper
    String WP_URL = "file:./src/main/java/Resources/Pics/WP2.png";
    Image WP_IMAGE = new Image(WP_URL, WINDOW_WIDTH, WINDOW_HEIGHT, true, true);
    ImageView WP_IMAGE_VIEW = new ImageView(WP_IMAGE);

    // Start screen buttons
    int START_SCREEN_BUTTON_SPACING = 22;
    String NEW_GAME = "NEW GAME" ;
    String LOAD_GAME = "LOAD GAME" ;
    String SETTINGS = "SETTINGS" ;
    String EXIT = "EXIT" ;

    // Menu and menubar
    String GAME_MENU = "Game";
    String OPTIONS_MENU = "Options";
    String ABOUT_MENU = "About";

    String NEW_GAME_MENUITEM = "New Game" ;
    String SAVE_GAME_MENUITEM = "Save Game";
    String MAIN_MENU_MENUITEM = "Back to Main Menu";
    String EXIT_MENUITEM = "Exit";
    String SOUND_MENUITEM = "Sound";
    String CREDITS_MENUITEM = "Credits" ;


    // images of pieces
    String CHESS_PIECE_URL = "file:./src/main/java/Resources/Pics/" ;
    String BLACK_KNIGHT_URL = "file:./src/main/java/Resources/Pics/b-knight.png";
    String WHITE_TILE_URL = "file:./src/main/java/Resources/Pics/white.png";
    String BLACK_TILE_URL = "file:./src/main/java/Resources/Pics/black.png";


}
