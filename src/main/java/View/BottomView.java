package View;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Timer;

public class BottomView {

    public static HBox createBottomView() {
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        Timer timer = new Timer();
        Label test = new Label("Test ");
        Button sa = new Button("asdasdsa");
        sa.setFont(new Font(222));
        vBox.getChildren().addAll(test, sa);
        hBox.getChildren().add(vBox);
        return null;
    }
}
