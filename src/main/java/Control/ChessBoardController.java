package Control;

import Model.ChessBoard;
import Model.ChessPiece;
import Model.Position;
import Model.Square;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ChessBoardController
{
    private static boolean whitesTurn = true;

    private static final int CHESS_BOARD_SIZE = 8;
    private final ChessBoard chessBoard = new ChessBoard();
    private final static ImageView highlightedSquare = new
            ImageView(Square.createImage("file:./src/main/java/Resources/Pics/selected.png"));

    public Square getSelectedSquare() {
        return selectedSquare;
    }

    private static Square selectedSquare = null;
    public ChessBoard getChessBoard()
    {
        return this.chessBoard;
    }

    // clears Gridpane and fills it with chess board + chess pieces and sets it in the middle
    public void setChessBoardView(Main main, GridPane gridPane)
    {
        gridPane.getChildren().clear();
        setChessBoard(gridPane);
        //renderChessBoard(gridPane);
        setChessPieces(gridPane);
        gridPane.setAlignment(Pos.CENTER);
        main.getBorderPane().setCenter(gridPane);
    }

    // checks every square of chess board and fills its background...
    public void setChessBoard(GridPane gridPane) {
        for(int i = 0 ; i < ChessBoard.CHESS_BOARD_SIZE ; i++)
        {
            for(int j = 0 ; j < ChessBoard.CHESS_BOARD_SIZE ; j++)
            {
                Square square = chessBoard.getSquare(new Position(j, i));
                if(square.isBlackSquare())
                    square.setWhite(false);
                else if(square.isWhiteSquare())
                    square.setWhite(true);

                ImageView imageView = Square.createTile(square.getWhite());
                square.setBackgroundImage(imageView);
                gridPane.add(imageView /*square.getBackgroundImage()*/, j, i);
            }
        }
    }

    // adds chess piece image to gridpane
    public void setChessPieces(GridPane gridPane)
    {
        for(int i = 0; i < ChessBoard.CHESS_BOARD_SIZE ;i++)
        {
            for(int j = 0; j < ChessBoard.CHESS_BOARD_SIZE; j++)
            {
                Position p = new Position(j, i);
                char symbol = chessBoard.getChessPieceSymbol(p.getY(), p.getX()); // type
                Square square = chessBoard.getSquare(p);

                if(symbol != '-')
                {
                    ChessPiece chessPiece = new ChessPiece();
                    if(square.getPosition().getX() <= 1)
                        chessPiece.setWhite(false);
                    else if(square.getPosition().getX() >= 6)
                        chessPiece.setWhite(true);
                    ImageView imageView = chessPiece.getChessPieceImage(chessBoard.getChessPieceSymbol(j, i));
                    chessPiece.setImage(imageView);
                    square.setChessPiece(chessPiece);
                    square.setHasChessPiece(true);
                    gridPane.add(imageView, i, j);
                }

            }
        }
    }

    public void checkMouseClicks(GridPane gridPane)
    {
        gridPane.setOnMouseClicked(e -> {
            Node clickedNode = e.getPickResult().getIntersectedNode();
            int rowIndex = GridPane.getRowIndex(clickedNode);
            int columnIndex = GridPane.getColumnIndex(clickedNode);
            Position clickedPosition = new Position(rowIndex, columnIndex);
            selectSquare(gridPane, clickedPosition);

            MovementController.getInstance().checkMovement(gridPane);
        });
    }

    public void selectSquare(GridPane gridPane, Position p)
    {
        Square square = this.chessBoard.getSquare(p);
        if(square.getHasChessPiece() && selectedSquare == null) // square has a chess piece
        {
            gridPane.getChildren().remove(square.getChessPiece().getImage());
            gridPane.add(highlightedSquare, p.getX(), p.getY());
            gridPane.add(square.getChessPiece().getImage(), p.getX(), p.getY());
            selectedSquare = chessBoard.getSquare(p);
        }
        else if (selectedSquare != null)
        {
            deselectSquare(gridPane);
            selectSquare(gridPane, p);
        }
    }

    public void deselectSquare(GridPane gridPane)
    {
        gridPane.getChildren().remove(highlightedSquare);
        selectedSquare = null;
    }

    public boolean squareSelected()
    {
        return (selectedSquare != null);
    }

    private static class ChessBoardHolder
    {
        private static final ChessBoardController INSTANCE = new ChessBoardController();
    }

    public static ChessBoardController getInstance()
    {
        return ChessBoardHolder.INSTANCE;
    }


}



//    public void setChessPieces(GridPane gridPane){
//        for(int i = 0; i < CHESS_BOARD_SIZE ; i++){
//            for(int j = 0; j < CHESS_BOARD_SIZE ; j++){
//                // switch piece
//                String pieceSymbol = chessBoard.getSymbol(i, j);
//                ImageView pieceImageView = Square.getChessPieceImage(pieceSymbol);
//                gridPane.add(pieceImageView, j, i);
//            }
//        }
//
//    }




//          imageView.setOnMouseEntered(e->{
//              gridPane.setBackground(new Background(new BackgroundFill(GREY,
//                      CornerRadii.EMPTY, Insets.EMPTY)));
//          });
//
//          imageView.setOnMouseExited(e->{
//              gridPane.setBackground(new Background(new BackgroundFill(RED,
//                      CornerRadii.EMPTY, Insets.EMPTY)));
//          });




