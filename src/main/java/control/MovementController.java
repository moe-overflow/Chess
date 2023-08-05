package control;

import model.*;
import javafx.scene.layout.GridPane;
import view.SquareView;
import java.util.LinkedList;

import static view.Logger.*;

public class MovementController
{
    private final LinkedList<ChessPiece> deadWhitePieces = new LinkedList<>();
    private final LinkedList<ChessPiece> deadBlackPieces = new LinkedList<>();

   private int newX, oldX, newY, oldY, spaceX, spaceY;

    private ChessPiece actingChessPiece = null;
    private Position newPosition = null;

    private void addDeadBlackPiece(ChessPiece deadPiece)
    {
        this.deadBlackPieces.add(deadPiece);
    }

    private void addDeadWhitePiece(ChessPiece deadPiece)
    {
        this.deadWhitePieces.add(deadPiece);
    }

    public LinkedList<ChessPiece> getDeadWhitePieces()
    {
        return deadWhitePieces;
    }

    public LinkedList<ChessPiece> getDeadBlackPieces()
    {
        return deadBlackPieces;
    }

    public void move(Square oldSquare, Square newSquare)
    {
        GridPane gridPane = ChessBoardController.getInstance().getChessBoardGridPane();
        setValues(oldSquare, newSquare);
        if(checkCanMove())
        {
            gridPane.getChildren().remove(actingChessPiece.getImage());

            ChessPiece pieceToKill = newSquare.getChessPiece();
            // kill
            if(newSquare.getHasChessPiece())
            {
                gridPane.getChildren().remove(pieceToKill.getImage());
                pieceToKill.setPosition(null);

                if (pieceToKill.isWhite())
                    addDeadWhitePiece(pieceToKill);
                else
                    addDeadBlackPiece(pieceToKill);

                logDeadPieces();

                Main.updateDeadChessPieces(); // TODO move to ChessBoardController
            }
            actingChessPiece.getPosition().setX(newPosition.getX());
            actingChessPiece.getPosition().setY(newPosition.getY());

            if(actingChessPiece.getType() == ChessPieceType.PAWN) checkPawnAtEnd();

            gridPane.add(actingChessPiece.getImage(), newPosition.getX(), newPosition.getY());

            // empty previous square
            oldSquare.setHasChessPiece(false);
            oldSquare.setChessPiece(null);

            // fill new square
            newSquare.setHasChessPiece(true);
            newSquare.setChessPiece(actingChessPiece);

            ChessBoardController.getInstance().switchTurn();
            logMove(actingChessPiece.getType());
        }
        else
        {
            logError("CAN'T MOVE THERE!");
        }
    }

    protected void setValues(Square oldSquare, Square newSquare)
    {
        this.actingChessPiece = oldSquare.getChessPiece();
        this.newPosition = newSquare.getPosition();
        newX = newPosition.getY();
        oldX = this.actingChessPiece.getPosition().getX();
        newY = newPosition.getX();
        oldY = this.actingChessPiece.getPosition().getY();
        spaceX = Math.abs(oldX - newX);
        spaceY = Math.abs(oldY - newY);
    }

    private void checkPawnAtEnd()
    {
        if(actingChessPiece.getPosition().getY() == 0 || actingChessPiece.getPosition().getY() == 7)
        {
            actingChessPiece.setType(ChessPieceType.QUEEN);
            actingChessPiece.setImage(SquareView.getChessPieceImage('Q', actingChessPiece));
        }
    }


    protected boolean checkCanMove()
    {
        boolean canMove = false;
        newPosition = new Position(newPosition.getX(), newPosition.getY());

        switch (actingChessPiece.getType())
        {
            case PAWN   ->    canMove = checkPawnsMovement();
            case ROOK   ->    canMove = checkRooksMovement();
            case KNIGHT ->    canMove = checkKnightsMovement();
            case BISHOP ->    canMove = checkBishopsMovement();
            case KING   ->    canMove = checkKingsMovement();
            case QUEEN  ->    canMove = checkQueensMovement();
        }
        return canMove; // && !ChessBoardController.getInstance().isOwnersPiece(actingChessPiece);
    }

    public boolean checkPawnsMovement()
    {
        boolean firstMove = (actingChessPiece.getPosition().getY() == 1 && !actingChessPiece.isWhite())
                || (actingChessPiece.getPosition().getY() == 6 && actingChessPiece.isWhite()) ,
                attacking = ChessBoardController.getInstance().getChessBoard().getSquare(newPosition).getHasChessPiece(),
                twoSteps = (spaceY == 1 || spaceY == 2) && spaceX == 0,
                oneStep  = spaceY == 1 && spaceX == 0,
                forward;

        if(actingChessPiece.isWhite()) forward = oldY > newY;
        else forward = oldY < newY;

        return attacking ? ( (spaceX == 1 && spaceY == 1) && forward ) :
                ( firstMove ? twoSteps && forward : oneStep && forward) ;
    }

    public boolean checkKnightsMovement()
    {
        return spaceX * spaceY == 2;
    }

    public boolean checkQueensMovement()
    {
        boolean sameXDifY = (oldY != newY) && (oldX == newX),
                sameYDifX = (oldX != newX) && (oldY == newY),
                diagonals = spaceX == spaceY,
                cross = sameXDifY || sameYDifX;

        if(diagonals) return checkBishopsMovement();
        else if (cross) return checkRooksMovement();
        return false;

    }

    public boolean checkKingsMovement()
    {
        return  spaceX * spaceY == 1;
    }

    public boolean checkBishopsMovement()
    {
        boolean northWest = (oldX > newX && oldY > newY),
                northEast = (oldX < newX && oldY > newY),
                southWest = (oldX > newX && oldY < newY),
                southEast = (oldX < newX && oldY < newY);

        Boolean isNoFreePath = null;
        Directions destination = null;

        if(northEast) destination = Directions.NORTH_EAST;
        if(southEast) destination = Directions.SOUTH_EAST;
        if(southWest) destination = Directions.SOUTH_WEST;
        if(northWest) destination = Directions.NORTH_WEST;

        assert destination != null;
        int  squaresToMove = spaceY - 1;
        switch (destination)
        {
            case NORTH_EAST -> isNoFreePath = diagonalPathIsNotFree(squaresToMove, Directions.NORTH_EAST);
            case SOUTH_EAST -> isNoFreePath = diagonalPathIsNotFree(squaresToMove, Directions.SOUTH_EAST);
            case SOUTH_WEST -> isNoFreePath = diagonalPathIsNotFree(squaresToMove, Directions.SOUTH_WEST);
            case NORTH_WEST -> isNoFreePath = diagonalPathIsNotFree(squaresToMove, Directions.NORTH_WEST);
        }
        return !isNoFreePath && (spaceX == spaceY);
    }

    public boolean checkRooksMovement()
    {
        boolean EastAndWest   = (oldY != newY) && (oldX == newX),
                NorthAndSouth = (oldX != newX) && (oldY == newY);

        Boolean isNoFreePath = null;
        Directions destination = null;
        if(newY < oldY) destination = Directions.NORTH;
        if(newX > oldX) destination = Directions.EAST;
        if(newY > oldY) destination = Directions.SOUTH;
        if(newX < oldX) destination = Directions.WEST;

        assert destination != null;
        int squaresToMove;
        switch (destination)
        {
            case NORTH ->
            {
                squaresToMove = spaceY - 1;
                isNoFreePath = crossPathIsNotFree(squaresToMove, Directions.NORTH);
            }
            case EAST ->
            {
                squaresToMove = spaceX - 1;
                isNoFreePath = crossPathIsNotFree(squaresToMove, Directions.EAST);
            }
            case SOUTH ->
            {
                squaresToMove = spaceY - 1;
                isNoFreePath = crossPathIsNotFree(squaresToMove,Directions.SOUTH);
            }
            case WEST  ->
            {
                squaresToMove = spaceX - 1;
                isNoFreePath = crossPathIsNotFree(squaresToMove, Directions.WEST);
            }
        }
        return !isNoFreePath && (EastAndWest || NorthAndSouth);
    }

    private boolean crossPathIsNotFree(int squaresToMove, Directions direction)
    {
        for(int i = 0; i < squaresToMove ; i++)
        {
            Position iteratingPosition = null;
            switch (direction)
            {
                case NORTH  -> iteratingPosition = new Position(oldY - 1 - i, oldX);
                case EAST   -> iteratingPosition = new Position(oldY, oldX + 1 + i);
                case SOUTH  -> iteratingPosition = new Position(oldY + 1 + i, oldX);
                case WEST   -> iteratingPosition = new Position(oldY, oldX - 1 - i);
            }
            assert iteratingPosition != null;
            Square square = ChessBoardController.getInstance().getChessBoard()
                    .getSquare(iteratingPosition);
            if(square.getHasChessPiece()) return true;
        }
        return false;
    }

    private boolean diagonalPathIsNotFree(int squaresToMove, Directions direction)
    {
        for(int i = 0; i < squaresToMove ; i++)
        {
            Position iteratingPosition = null;
            switch (direction)
            {
                case NORTH_EAST -> iteratingPosition = new Position(oldY - 1 - i, oldX + 1 + i);
                case SOUTH_EAST -> iteratingPosition = new Position(oldY + 1 + i, oldX + 1 + i);
                case SOUTH_WEST -> iteratingPosition = new Position(oldY + 1 + i, oldX - 1 - i);
                case NORTH_WEST -> iteratingPosition = new Position(oldY - 1 - i, oldX - 1 - i);
            }
            assert iteratingPosition != null;
            Square square = ChessBoardController.getInstance().getChessBoard()
                    .getSquare(iteratingPosition);
            if(square.getHasChessPiece()) return true;
        }
        return false;
    }

    public enum Directions {
        NORTH, EAST, SOUTH, WEST,
        NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
    }

    public int getNewX()
    {
        return newX;
    }

    public int getOldX()
    {
        return oldX;
    }

    public int getNewY()
    {
        return newY;
    }

    public int getOldY()
    {
        return oldY;
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
