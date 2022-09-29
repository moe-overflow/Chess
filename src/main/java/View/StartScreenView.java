package View;

import Control.Main;
import Model.Interfaces.Gui;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StartScreenView implements Gui
{

//    public static VBox createStartScreen()
//    {
//        VBox vBox = new VBox();
//        Button newGame = new Button("NEW GAME");
//        newGame.setOnAction(e -> {
//           print("newGame Button clicked!");
//        });
//        vBox.getChildren().addAll(newGame);
//        return vBox;
//    }


    public static void setUpStartScreen(StackPane stackPane, Main main)
    {
        Button newGame = StartScreenView.createButton(NEW_GAME);
        Button loadGame = StartScreenView.createButton(LOAD_GAME);
        Button settings = StartScreenView.createButton(SETTINGS);
        Button exit = StartScreenView.createButton(EXIT);
        //MenuBar menuBar = TopView.createMenu();
        //menuBar.setAligtment
        VBox vBox = new VBox();
        vBox.getChildren().addAll(newGame, loadGame, settings, exit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(START_SCREEN_BUTTON_SPACING);
        //stackPane.getChildren().add(WP_IMAGE_VIEW);
        MenuBar menuBar = TopView.createMenuBar(main);
        menuBar.setTranslateY(- (WINDOW_HEIGHT/2f) +10);
        stackPane.getChildren().addAll(WP_IMAGE_VIEW, vBox, menuBar);

        newGame.setOnAction(e -> {
            //main.newGame();

            setChooseWhiteOrBlackView(stackPane, main);

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

        for(int i =0 ; i < vBox.getChildren().size() ; i++) {
            int k = i;
            vBox.getChildren().get(i).setOnMouseEntered(e -> {
//                 vBox.getChildren().get(k).
            });
        }
    }

    public static void setChooseWhiteOrBlackView(StackPane stackPane, Main main){
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
        hBox.setSpacing(222);
        hBox.setPadding(new Insets(350));
        stackPane.getChildren().addAll(WP_IMAGE_VIEW, hBox);

        whiteButton.setOnAction(e ->{

            main.getScene().setRoot(main.getBorderPane());
            main.setUpWindow();
        });

        blackButton.setOnAction(e ->{

        });
    }

    public static Button createButton(String title)
    {
        Button button = new Button(title);
        button.setAlignment(Pos.CENTER);
        button.setFont(new Font(START_SCREEN_BUTTON_FONT));
        // button.setMinSize(140, 30);
        //button.setBackground(new Background(buttonBackground));
        return button;
    }
}
