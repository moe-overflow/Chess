package control;

import model.*;
import javafx.scene.layout.GridPane;
import view.SquareView;

import java.util.LinkedList;

public class MovementController
{
    private final LinkedList<ChessPiece> deadWhitePieces = new LinkedList<>();
    private final LinkedList<ChessPiece> deadBlackPieces = new LinkedList<>();

    private int newX;
    private int oldX;
    private int newY;
    private int oldY;
    private int spaceX;
    private int spaceY;

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
//        System.out.println(actingChessPiece.getPosition() + " 451");



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

                System.out.println("[DEAD BLACK PIECES: " + deadBlackPieces.toArray().length + "]");
                System.out.println("[DEAD WHITE PIECES: " + deadWhitePieces.toArray().length + "]");

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

            System.out.println("[TYPE: " + actingChessPiece.getType() + " ]");
            System.out.println("[MOVING FROM: (" + oldX + ", " + oldY + ") ]");
            System.out.println("[MOVING TO  : (" + newX + ", " + newY + ") ]");

        }
        else
        {
            System.out.println("CAN'T MOVE THERE!");
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
            case PAWN   ->    {
                canMove = checkPawnsMovement();
                actingChessPiece.setFirstMove(false);
            }
            case ROOK   ->    canMove = checkRooksMovement();
            case KNIGHT ->    canMove = checkKnightsMovement();
            case BISHOP ->    canMove = checkBishopsMovement();
            case KING   ->    canMove = checkKingsMovement();
            case QUEEN  ->    canMove = checkQueensMovement();
        }
        return canMove;
    }

    public boolean checkPawnsMovement()
    {
        boolean firstMove = actingChessPiece.isFirstMove(),
                attacking = ChessBoardController.getInstance().getChessBoard().getSquare(newPosition).getHasChessPiece(),
                twoSteps = (spaceY == 1 || spaceY == 2) && spaceX == 0,
                oneStep  = spaceY == 1 && spaceX == 0,
                forward;

        if(actingChessPiece.isWhite()) forward = oldY > newY;
        else forward = oldY < newY;

        return attacking ? ( (spaceX == 1 && spaceY == 1) && forward ) : ( firstMove ? twoSteps && forward : oneStep && forward) ;
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
        return (spaceX == 1 || spaceY == 1);
    }

    public boolean checkBishopsMovement()
    {
        boolean northWest = (oldX > newX && oldY > newY),
                northEast = (oldX < newX && oldY > newY),
                southWest = (oldX > newX && oldY < newY),
                southEast = (oldX < newX && oldY < newY);

        Boolean isNoFreePath = null;

        Integer destination = null;
        if(northEast) destination = 45;
        if(southEast) destination = 135;
        if(southWest) destination = 225;
        if(northWest) destination = 315;

        assert destination != null;
        int  squaresToMove = spaceY - 1;
        switch (destination)
        {
            case 45  -> isNoFreePath = diagonalPathIsNotFree(squaresToMove,45 );
            case 135 -> isNoFreePath = diagonalPathIsNotFree(squaresToMove,135);
            case 225 -> isNoFreePath = diagonalPathIsNotFree(squaresToMove,225);
            case 315 -> isNoFreePath = diagonalPathIsNotFree(squaresToMove,315);
        }
        return !isNoFreePath && (spaceX == spaceY);
    }

    public boolean checkRooksMovement()
    {
        boolean EastAndWest   = (oldY != newY) && (oldX == newX),
                NorthAndSouth = (oldX != newX) && (oldY == newY);

        Boolean isNoFreePath = null;
        Integer destination = null;
        if(newY < oldY) destination = 0;
        if(newX > oldX) destination = 90;
        if(newY > oldY) destination = 180;
        if(newX < oldX) destination = 270;

        assert destination != null;
        int squaresToMove;
        switch (destination)
        {
            case 0 ->
            {
                squaresToMove = spaceY - 1;
                isNoFreePath = crossPathIsNotFree(squaresToMove,0);
            }
            case 90  ->
            {
                squaresToMove = spaceX - 1;
                isNoFreePath = crossPathIsNotFree(squaresToMove, 90);
            }
            case 180 ->
            {
                squaresToMove = spaceY - 1;
                isNoFreePath = crossPathIsNotFree(squaresToMove,180);
            }
            case 270  ->
            {
                squaresToMove = spaceX - 1;
                isNoFreePath = crossPathIsNotFree(squaresToMove, 270);
            }
        }
        return !isNoFreePath && (EastAndWest || NorthAndSouth);
    }

    private boolean crossPathIsNotFree(int squaresToMove, int direction)
    {
        for(int i = 0; i < squaresToMove ; i++)
        {
            Position iteratingPosition = null;
            switch (direction)
            {
                case 0   -> iteratingPosition = new Position(oldY - 1 - i, oldX);
                case 90  -> iteratingPosition = new Position(oldY, oldX + 1 + i);
                case 180 -> iteratingPosition = new Position(oldY + 1 + i, oldX);
                case 270 -> iteratingPosition = new Position(oldY, oldX - 1 - i);
            }
            assert iteratingPosition != null;
            Square square = ChessBoardController.getInstance().getChessBoard()
                    .getSquare(iteratingPosition);
            if(square.getHasChessPiece()) return true;
        }
        return false;
    }

    private boolean diagonalPathIsNotFree(int squaresToMove, int direction)
    {
        for(int i = 0; i < squaresToMove ; i++)
        {
            Position iteratingPosition = null;
            switch (direction)
            {
                case 45  -> iteratingPosition = new Position(oldY - 1 - i, oldX + 1 + i); // north-east
                case 135 -> iteratingPosition = new Position(oldY + 1 + i, oldX + 1 + i); // south-east
                case 225 -> iteratingPosition = new Position(oldY + 1 + i, oldX - 1 - i); // south-west
                case 315 -> iteratingPosition = new Position(oldY - 1 - i, oldX - 1 - i); // north-west
            }
            assert iteratingPosition != null;
            Square square = ChessBoardController.getInstance().getChessBoard()
                    .getSquare(iteratingPosition);
            if(square.getHasChessPiece()) return true;
        }
        return false;
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
