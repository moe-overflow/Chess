package Model.Interfaces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public interface Gui
{
    // window (stage)
    int WINDOW_WIDTH = 1200;
    int WINDOW_HEIGHT = 800;

    // Start screen wallpaper
    String WP_URL = "file:./src/main/java/Resources/Pics/WP2.png";
    Image WP_IMAGE = new Image(WP_URL, WINDOW_WIDTH, WINDOW_HEIGHT, true, true);
    ImageView WP_IMAGE_VIEW = new ImageView(WP_IMAGE);
    // Group WP_ROOT = new Group(WP_IMAGE_VIEW);

    // Start screen buttons
    int START_SCREEN_BUTTON_SPACING = 22;
    int START_SCREEN_BUTTON_FONT = 22;
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


    String CHESS_PIECE_URL2 = "b-pawn.png" ;

    String BLACK_PAWN_URL = "file:./src/main/java/Resources/Pics/b-pawn.png";
    String BLACK_ROOK_URL = "file:./src/main/java/Resources/Pics/b-rook.png";
    String BLACK_KNIGHT_URL = "file:./src/main/java/Resources/Pics/b-knight.png";
    String BLACK_BISHOP_URL = "file:./src/main/java/Resources/Pics/b-bishop.png";
    String BLACK_KING_URL = "file:./src/main/java/Resources/Pics/b-king.png";
    String BLACK_QUEEN_URL = "file:./src/main/java/Resources/Pics/b-queen.png";

    String WHITE_PAWN_URL = "file:./src/main/java/Resources/Pics/w-pawn.png";
    String WHITE_ROOK_URL = "file:./src/main/java/Resources/Pics/w-rook.png";
    String WHITE_KNIGHT_URL = "file:./src/main/java/Resources/Pics/w-knight.png";
    String WHITE_KING_URL = "file:./src/main/java/Resources/Pics/w-king.png";
    String WHITE_QUEEN_URL = "file:./src/main/java/Resources/Pics/w-queen.png";
    String WHITE_BISHOP_URL = "file:./src/main/java/Resources/Pics/w-bishop.png";

    String WHITE_TILE_URL = "file:./src/main/java/Resources/Pics/white.png";
    String BLACK_TILE_URL = "file:./src/main/java/Resources/Pics/black.png";
    String SELECTED_TILE_URL = "file:./src/main/java/Resources/Pics/selected.png";


}
