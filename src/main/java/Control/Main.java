package Control;

import View.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import static Model.Interfaces.Gui.*;

public class Main extends Application
{

    private static final BorderPane borderPane = new BorderPane();
    private static final StackPane startScreen = new StackPane();

    private Scene scene ;


    @Override
    public void start(Stage stage) throws IOException
    {
//        setUpGui();
        StartScreenView.setUpStartScreen(startScreen, this);

//        stackPane.getChildren().addAll(button, WP_ROOT);
//        button.setTranslateX(150);
//        button.setTranslateY(60);


//        FXMLLoader fxmlLoader = new FXMLLoader(Chess.class.getResource("hello-view.fxml"));

        scene = new Scene(/*fxmlLoader.load()*/startScreen, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });


//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);

        stage.show();

        ChessBoardController.getInstance().checkMouseClicks();

    }

    public static void main(String[] args)
    {
        launch(args);

//        gridPane.setHgap(10);
//        gridPane.setVgap(10);

    }



    public void setUpWindow()
    {
        borderPane.setCenter(CenterView.createCenterView());
        ChessBoardController.getInstance().setChessBoardView(this);
        borderPane.setTop(TopView.createTopView(this));
        borderPane.setBottom(BottomView.createBottomView());
        borderPane.setRight(RightView.createRightView());
        borderPane.setLeft(LeftView.createLeftView());
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


}