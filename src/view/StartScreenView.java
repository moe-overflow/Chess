package view;

import control.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import control.GuiConstants;

import static control.GuiConstants.*;

public class StartScreenView
{
    public static void setUpStartScreen(StackPane stackPane)
    {
        Button newGame = new Button(NEW_GAME);
        Button loadGame = new Button(LOAD_GAME);
        Button settings = new Button(SETTINGS);
        Button exit = new Button(EXIT);
        //MenuBar menuBar = TopView.createMenu();
        //menuBar.setAligtment
        VBox vBox = new VBox();
        vBox.getChildren().addAll(newGame, loadGame, settings, exit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(START_SCREEN_BUTTON_SPACING);
        //stackPane.getChildren().add(WP_IMAGE_VIEW);
        MenuBar menuBar = TopView.createMenuBar();
        menuBar.setTranslateY(- (WINDOW_HEIGHT/2f) + 10);
        stackPane.getChildren().addAll(GuiConstants.WP_IMAGE_VIEW, vBox, menuBar);

        newGame.setOnAction(e -> {
            //main.newGame();

            setChooseWhiteOrBlackView(stackPane);

            // choosing white or black
            // another methode startNewGame (Wird aufgerufen nach Auswahl der Farbe bzw.
            // beim Klicken auf das new Game MenuItem

        });

        loadGame.setOnAction(e -> {
            stackPane.getChildren().clear();
            stackPane.getChildren().addAll(WP_IMAGE_VIEW);
        });



        settings.setOnAction(e -> {

        });

        exit.setOnAction(e -> {
            System.exit(0);
        });

    }

    public static void setChooseWhiteOrBlackView(StackPane stackPane)
    {
        stackPane.getChildren().clear();
        HBox hBox = new HBox(); // White or black view
        VBox white = new VBox();
        VBox black = new VBox();
        Button whiteButton = new Button("WHITE");
        Button blackButton = new Button("BLACK");
        white.getChildren().add(whiteButton);
        black.getChildren().add(blackButton);
        hBox.getChildren().addAll(white, black);
        hBox.setAlignment(Pos.CENTER);
        final int HBOX_SPACING = 110; //220;
        final int HBOX_PADDING = 250; //350;
        hBox.setSpacing(HBOX_SPACING);
        hBox.setPadding(new Insets(HBOX_PADDING));

        MenuBar menuBar = TopView.createMenuBar();
        menuBar.setTranslateY(- (WINDOW_HEIGHT/2f) + 10);

        stackPane.getChildren().addAll(WP_IMAGE_VIEW, hBox, menuBar);

        whiteButton.setOnAction(e -> {

            Main.getInstance().getScene().setRoot(Main.getInstance().getBorderPane());
//            Main.setWindowSize();
            Main.getInstance().setUpWindow();


        });

        blackButton.setOnAction(e -> {
            // ...
        });
    }

}
