package view;

import control.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class BottomView
{
    private static final String WHITE_KING_URL = "file:./src/main/java/Resources/Pics/w-king.png";
    private static final String BLACK_KING_URL = "file:./src/main/java/Resources/Pics/b-king.png";
    private static final int FIT = 50;


    public static HBox createBottomView(boolean forWhite)
    {
       HBox bottomBar = new HBox();

       Label label = new Label("TO PLAY: ");

       ImageView imageView;
       if(forWhite)  imageView = new ImageView(new Image(WHITE_KING_URL));
       else          imageView = new ImageView(new Image(BLACK_KING_URL));

       imageView.setFitHeight(FIT);
       imageView.setFitWidth(FIT);

       bottomBar.setAlignment(Pos.CENTER);
       bottomBar.getChildren().addAll(label, imageView);

       return bottomBar;
    }


    public static void editTurnLabel(boolean whitesTurn)
    {
        Main.getInstance().getBorderPane().setBottom(createBottomView(whitesTurn));
    }
}
