package control;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import view.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


import static control.GuiConstants.*;

public class Main extends Application
{

    private static final String CSS_FILE_PATH = "file:./src/main/java/utilities/stylesheet.css";
    private static final BorderPane borderPane = new BorderPane();
    private static final StackPane startScreen = new StackPane();

    private static Scene scene ;


    @Override
    public void start(Stage stage) {
        StartScreenView.setUpStartScreen(startScreen);

        scene = new Scene(startScreen, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(CSS_FILE_PATH);

        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        stage.getIcons().add(new Image(GuiConstants.BLACK_KNIGHT_URL));
        stage.show();
        ChessBoardController.getInstance().checkMouseClicks();
    }

    public static void main(String[] args)
    {
//        MusicPlayer.getInstance().start();
        launch(args);
    }

    public void setUpWindow()
    {
        ChessBoardController.getInstance().setChessBoardView();
        borderPane.setTop(TopView.createTopView());
        borderPane.setBottom(BottomView.createBottomView(true));
        borderPane.setRight(SidesView.createDeadPiecesView(true));
        borderPane.setLeft(SidesView.createDeadPiecesView(false));
        borderPane.setCenter(renderCompleteChessBoard());
    }

    public static void updateDeadChessPieces()
    {
        borderPane.setRight(SidesView.createDeadPiecesView(true));
        borderPane.setLeft(SidesView.createDeadPiecesView(false));
    }

    public BorderPane getBorderPane()
    {
        return borderPane;
    }

    public Scene getScene()
    {
        return scene;
    }

    public StackPane getStartScreen()
    {
        return startScreen;
    }

    protected void setCenterNode(Node node)
    {
        borderPane.setCenter(node);
    }

    private static class MainHolder
    {
        private static final Main INSTANCE = new Main();
    }

    public static Main getInstance()
    {
        return Main.MainHolder.INSTANCE;
    }

    public GridPane renderCompleteChessBoard()
    {
        // chess board
        GridPane chessBoard = ChessBoardController.getInstance().getChessBoardGridpane();

        // letters box
        HBox lettersBox = SquareView.createLettersView();

        // numbers box
        VBox numbersBox = SquareView.createNumbersView();

        GridPane completeChessBoard = new GridPane();

        completeChessBoard.add(chessBoard, 1 , 0);
        completeChessBoard.add(lettersBox, 1 , 1);
        completeChessBoard.add(numbersBox, 0 , 0);
        completeChessBoard.setAlignment(Pos.CENTER);

        return completeChessBoard;
    }
}