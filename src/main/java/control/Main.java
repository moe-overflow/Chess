package control;

import javafx.scene.image.Image;
import model.GuiConstants;
import view.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import static model.GuiConstants.*;

public class Main extends Application
{

    private static final String CSS_FILE_PATH = "file:./src/main/java/Resources/stylesheet.css";
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

    private static class MainHolder { private static final Main INSTANCE = new Main();}

    public static Main getInstance()
    {
        return Main.MainHolder.INSTANCE;
    }

}