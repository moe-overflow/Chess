package view;


import control.MovementController;
import javafx.scene.Node;
import model.ChessPiece;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class SidesView
{
    private static final double FIT_HEIGHT = 40;
    private static final double FIT_WIDTH = 40;


    public static FlowPane createDeadPiecesView(boolean forWhite)
    {
        FlowPane flowPane = new FlowPane();
        flowPane.setId("Dead");
        flowPane.setBackground(new Background(new BackgroundFill(Color.BISQUE,
                CornerRadii.EMPTY, Insets.EMPTY)));

        flowPane.setTranslateY(100);

        if (forWhite)
        {
            flowPane.setAlignment(Pos.CENTER_RIGHT);

            LinkedList<ChessPiece> list = MovementController.getInstance().getDeadWhitePieces();

            for (ChessPiece chessPiece : list)
            {
                // TODO modularize (duplicated code)
                ImageView imageView = chessPiece.getImage();
                imageView.setFitHeight(FIT_HEIGHT);
                imageView.setFitWidth(FIT_WIDTH);
                flowPane.getChildren().add(imageView);
            }
        }
        else
        {
            flowPane.setAlignment(Pos.CENTER_LEFT);

            LinkedList<ChessPiece> list = MovementController.getInstance().getDeadBlackPieces();

            for (ChessPiece chessPiece : list)
            {
                ImageView imageView = chessPiece.getImage();
                imageView.setFitHeight(FIT_HEIGHT);
                imageView.setFitWidth(FIT_WIDTH);
                flowPane.getChildren().add(imageView);
            }
        }

        return flowPane;
    }

    public static Node[] createChessboardEdges()
    {
        Node[] nodes;

        VBox leftEdge = new VBox();
        VBox rightEdge = new VBox();
        HBox topEdge = new HBox();
        HBox bottomEdge = new HBox();

        nodes = new Node[]{leftEdge, rightEdge, topEdge, bottomEdge};
        for (Node node : nodes)
            node.setId("edge");

        return nodes;
    }


}
