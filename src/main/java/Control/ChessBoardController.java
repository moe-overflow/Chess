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
    private final GridPane gridPane = new GridPane();

    private static boolean whitesTurn = true;
    private final ChessBoard chessBoard = new ChessBoard();
    private final static ImageView highlightedSquare = new
            ImageView(Square.createImage("file:./src/main/java/Resources/Pics/selected.png"));



    public Square getSelectedSquare()
    {
        return selectedSquare;
    }

    public ImageView getHighlightedSquare()
    {
        return highlightedSquare;
    }

    private static Square selectedSquare = null;
    public ChessBoard getChessBoard()
    {
        return this.chessBoard;
    }

    // clears Gridpane and fills it with chess board + chess pieces and sets it in the middle
    public void setChessBoardView(Main main)
    {
        gridPane.getChildren().clear();
        setChessBoard(gridPane);
        //renderChessBoard(gridPane);
        setChessPieces(gridPane);
        gridPane.setAlignment(Pos.CENTER);
        main.getBorderPane().setCenter(gridPane);
    }

    // checks every square of chess board and fills its background...
    public void setChessBoard(GridPane gridPane)
    {
        for(int i = 0 ; i < ChessBoard.CHESS_BOARD_SIZE; i++)
        {
            for(int j = 0 ; j < ChessBoard.CHESS_BOARD_SIZE; j++)
            {
                Square square = chessBoard.getSquare(new Position(j, i));
                square.setWhite(!square.isBlackSquare());
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

                if(symbol != '-')
                {
                    Square square = chessBoard.getSquare(p);
                    ChessPiece chessPiece = new ChessPiece();

                    // set color
                    if(square.getPosition().getX() <= 1)
                        chessPiece.setWhite(false);
                    else if(square.getPosition().getX() >= 6)
                        chessPiece.setWhite(true);

                    // set image
                    ImageView imageView = chessPiece.getChessPieceImage(
                            chessBoard.getChessPieceSymbol(j, i));
                    chessPiece.setImage(imageView);

                    // set on matching square and add image to chess board
                    square.setChessPiece(chessPiece);
                    square.setHasChessPiece(true);
                    gridPane.add(imageView, i, j);
                    chessPiece.setPosition(p);
                }

            }
        }
    }

    public void checkMouseClicks()
    {
        gridPane.setOnMouseClicked(e -> {
        try
        {
            Node clickedNode = e.getPickResult().getIntersectedNode();
            int rowIndex = GridPane.getRowIndex(clickedNode);
            int columnIndex = GridPane.getColumnIndex(clickedNode);
            Position clickedPosition = new Position(rowIndex, columnIndex);
            Square clickedSquare = chessBoard.getSquare(clickedPosition);
            ChessPiece clickedPiece = clickedSquare.getChessPiece();
            boolean emptySquare = !clickedSquare.getHasChessPiece();

            if(!squareSelected() && !emptySquare && isOwnersPiece(clickedPiece))
            {
                selectSquare(clickedPosition);
            }
            else if(squareSelected())
            {
                // tp empty square
                boolean shouldMove = !clickedSquare.getHasChessPiece();

                //to enemy
                boolean shouldKill = clickedSquare.getHasChessPiece() &&
                        !isOwnersPiece(clickedSquare.getChessPiece());

                if(clickedSquare.getHasChessPiece() && isOwnersPiece(clickedSquare.getChessPiece()))
                {
                    deselectSquare();
                    selectSquare(clickedPosition);
                }
                else if(shouldMove || shouldKill)
                {
                    MovementController.getInstance().move(getSelectedSquare(), clickedSquare);
                    deselectSquare();
                    whitesTurn ^= true;
                    // TODO show turn in GUI
                }
            }
        }

        catch (NullPointerException ex)
        {
            ex.getStackTrace();
            System.out.println("NULLPOINTER!!!!");
        }


        });

    }

    private boolean isOwnersPiece(ChessPiece chessPiece)
    {
        return whitesTurn == chessPiece.isWhite();
    }


    protected void selectSquare(Position p)
    {
        Square square = this.chessBoard.getSquare(p);
        if(square.getHasChessPiece()) // square has a chess piece
        {
            gridPane.getChildren().remove(square.getChessPiece().getImage());
            gridPane.add(highlightedSquare, p.getX(), p.getY());
            gridPane.add(square.getChessPiece().getImage(), p.getX(), p.getY());
            selectedSquare = chessBoard.getSquare(p);
        }


    }

    protected void deselectSquare()
    {
//        gridPane.getChildren().remove(highlightedSquare);
        gridPane.getChildren().remove(highlightedSquare);
        selectedSquare = null;
    }

    protected boolean squareSelected()
    {
        return selectedSquare != null;
    }

    private static class ChessBoardHolder
    {
        private static final ChessBoardController INSTANCE = new ChessBoardController();
    }

    public static ChessBoardController getInstance()
    {
        return ChessBoardHolder.INSTANCE;
    }

    public GridPane getGridPane()
    {
        return this.gridPane;
    }
}

//          imageView.setOnMouseEntered(e->{
//              gridPane.setBackground(new Background(new BackgroundFill(GREY,
//                      CornerRadii.EMPTY, Insets.EMPTY)));
//          });
//
//          imageView.setOnMouseExited(e->{
//              gridPane.setBackground(new Background(new BackgroundFill(RED,
//                      CornerRadii.EMPTY, Insets.EMPTY)));
//          });