package control;

import model.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import view.BottomView;
import view.SquareView;

public class ChessBoardController
{
    private final GridPane chessBoardGridPane = new GridPane();

    private Boolean whitesTurn = true;
    private final ChessBoard chessBoard = new ChessBoard();
    private final static ImageView highlightedSquare = new
            ImageView(SquareView.createImage("file:./src/main/java/Resources/Pics/selected.png"));

    private static Square selectedSquare = null;

    public void setWhitesTurn(boolean whitesTurn)
    {
        this.whitesTurn = whitesTurn;
    }

    public Square getSelectedSquare()
    {
        return selectedSquare;
    }

    public ChessBoard getChessBoard()
    {
        return this.chessBoard;
    }

    // clears Gridpane and fills it with chess board + chess pieces and sets it in the middle
    public void setChessBoardView()
    {
        chessBoardGridPane.getChildren().clear();
        chessBoardGridPane.setId("ChessBoard");
        setChessBoard();
        setChessPieces();
        chessBoardGridPane.setAlignment(Pos.CENTER);
        Main.getInstance().setCenterNode(chessBoardGridPane);
        chessBoardGridPane.setMaxHeight(600);
        chessBoardGridPane.setMaxWidth(600);

    }

    // checks every square of chess board and fills its background...
    public void setChessBoard()
    {
        for(int i = 0 ; i < ChessBoard.CHESS_BOARD_SIZE; i++)
        {
            for(int j = 0 ; j < ChessBoard.CHESS_BOARD_SIZE; j++)
            {
                Square square = chessBoard.getSquare(new Position(j, i));
                square.setWhite(!square.isBlackSquare());
                ImageView imageView = SquareView.createTile(square.getWhite());
                square.setBackgroundImage(imageView);
                chessBoardGridPane.add(imageView, j, i);
            }
        }
    }

    // adds chess piece image to gridpane
    public void setChessPieces()
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
                    ImageView imageView = SquareView.getChessPieceImage(chessBoard.getChessPieceSymbol(j, i), chessPiece);
                    chessPiece.setImage(imageView);

                    // set on matching square and add image to chess board
                    square.setChessPiece(chessPiece);
                    square.setHasChessPiece(true);
                    chessBoardGridPane.add(imageView, i, j);
                    chessPiece.setPosition(p);
                }
            }
        }
    }

    public void checkMouseClicks()
    {
        chessBoardGridPane.setOnMouseClicked(e -> {
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
                // clicked square has a piece and this piece belongs to actor
                if(clickedSquare.getHasChessPiece() && isOwnersPiece(clickedSquare.getChessPiece()))
                {
                    deselectSquare();
                    selectSquare(clickedPosition);
                }
                else
                {
                    MovementController.getInstance().move(getSelectedSquare(), clickedSquare);
                    deselectSquare();
                }
            }
        }
        catch (NullPointerException ex)
        {
            ex.getStackTrace();
            System.out.println("CLICK OUTSIDE CHESS BOARD!");
        }

        });

    }

//    protected boolean hasFriendlyPiece(Position position)
//    {
//        boolean whitePiece = getChessBoard().getSquare(position).getChessPiece().isWhite();
//        System.out.println(whitePiece + " white!!!   " + position + " asdasdasdasdsadd" );
//        return (whitesTurn && whitePiece) || (!whitesTurn && !whitePiece);
//    }

    protected void switchTurn()
    {
        whitesTurn ^= true;
        BottomView.editTurnLabel(whitesTurn);
    }

    public void highlightPaths(Position position)
    {
        ChessPiece actingChessPiece = getChessBoard().getSquare(position).getChessPiece();
        Square oldSquare = getChessBoard().getSquare(position);
        for(int i = 0; i < ChessBoard.CHESS_BOARD_SIZE; i++)
        {
            for(int j = 0; j < ChessBoard.CHESS_BOARD_SIZE; j++)
            {
                Square newSquare = getChessBoard().getSquare(new Position(j, i));
                MovementController.getInstance().setValues(oldSquare, newSquare);
                if(MovementController.getInstance().checkCanMove())
                {
                    System.out.println("can move: X: " + newSquare.getPosition().getY() + " Y: " + newSquare.getPosition().getX() +
                            " " + actingChessPiece);
                    System.out.println(newSquare.getChessPiece() + " (Chess piece on new square!)");
//                    chessBoardGridPane.add(highlightedSquare, position.getX(), position.getY());
                }
            }
        }
    }

    protected boolean isOwnersPiece(ChessPiece chessPiece)
    {
        return whitesTurn == chessPiece.isWhite();
    }


    protected void selectSquare(Position p)
    {
        Square clickedSquare = this.chessBoard.getSquare(p);
        if(clickedSquare.getHasChessPiece()) // square has a chess piece
        {
            chessBoardGridPane.getChildren().remove(clickedSquare.getChessPiece().getImage());
            chessBoardGridPane.add(highlightedSquare, p.getX(), p.getY());
            chessBoardGridPane.add(clickedSquare.getChessPiece().getImage(), p.getX(), p.getY());
            selectedSquare = chessBoard.getSquare(p);
            System.out.println("[SELECTED: " + clickedSquare.getChessPiece().getType() + " ON POSITION:" + p + " ]");

//            highlightPaths(p);
        }
        System.out.println("-------------------------------------------------");
    }

    protected void deselectSquare()
    {
        chessBoardGridPane.getChildren().remove(highlightedSquare);
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

    public GridPane getChessBoardGridPane()
    {
        return this.chessBoardGridPane;
    }

}