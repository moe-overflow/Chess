package View;

import Model.Interfaces.Gui;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RightView {

    public static VBox createRightView() {
        VBox vBox = new VBox();
        ImageView iv = new ImageView(Gui.BLACK_QUEEN_URL);
        iv.setFitHeight(60);
        iv.setFitWidth(80);
        vBox.getChildren().add(iv);
        return vBox;
    }
}
