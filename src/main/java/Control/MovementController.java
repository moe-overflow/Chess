package Control;

import Model.Position;
import Model.Square;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MovementController
{
    public void checkMovement(GridPane gridPane)
    {
        // a square should be selected and
        if(ChessBoardController.getInstance().squareSelected() )
        {

            gridPane.setOnMouseClicked(e -> {
                Node clickedSquare = e.getPickResult().getIntersectedNode();
                int rowIndex = GridPane.getRowIndex(clickedSquare);
                int columnIndex = GridPane.getColumnIndex(clickedSquare);
                Position newPosition = new Position(rowIndex, columnIndex);

                Square oldSquare = ChessBoardController.getInstance().getSelectedSquare();
                Square newSquare = ChessBoardController.getInstance().getChessBoard().getSquare(newPosition);

                ImageView chessPieceImage = oldSquare.getChessPiece().getImage();

                gridPane.getChildren().remove(chessPieceImage); // removing chess piece from old square
                oldSquare.setChessPiece(null);

                if(newSquare.getHasChessPiece())
                {
                    gridPane.getChildren().remove(newSquare.getChessPiece().getImage());
                    newSquare.setChessPiece(null);
                }
                gridPane.add(chessPieceImage, newSquare.getPosition().getY(), newSquare.getPosition().getX());
                ChessBoardController.getInstance().deselectSquare(gridPane);
            });

        }


        /*
            boolean squareSelected = ChessBoardController.getInstance().isSquareSelected();
            Square square = ChessBoardController.getInstance().getSelectedSquare();
            //if(squareSelected && isAbleToMove())
        */
    }



    private void move(Square oldSquare, Square newSquare)
    {
        //if(isAbleToMove())

        GridPane g = Main.gridPane;
        g.getChildren().remove(oldSquare.getChessPiece().getImage());
        g.add(oldSquare.getChessPiece().getImage(),
                newSquare.getPosition().getY(), newSquare.getPosition().getX());


    }

    private boolean isAbleToMove()
    {
        return true;
    }

    private static class MovementHolder
    {
        private static final MovementController INSTANCE = new MovementController();
    }

    public static MovementController getInstance()
    {
        return MovementController.MovementHolder.INSTANCE;
    }
}
