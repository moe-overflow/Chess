package Control;

import Model.*;
import javafx.scene.layout.GridPane;

public class MovementController
{

    public void move(Square oldSquare, Square newSquare)
    {
        GridPane gridPane = ChessBoardController.getInstance().getGridPane();
        ChessPiece actingChessPiece = oldSquare.getChessPiece();
        Position newSquarePosition = newSquare.getPosition();

        boolean ableToMove = checkCanMove(actingChessPiece, newSquarePosition);

        if(ableToMove)
        {
            gridPane.getChildren().remove(actingChessPiece.getImage());

            // kill
            if(newSquare.getHasChessPiece())
            {
                ChessPiece pieceToKill = newSquare.getChessPiece();
                gridPane.getChildren().remove(pieceToKill.getImage());
                pieceToKill.setPosition(null);
            }
//            actingChessPiece.setPosition(newSquare.getPosition());
            // TODO could be better resolved
            actingChessPiece.getPosition().setX(newSquarePosition.getY());
            actingChessPiece.getPosition().setY(newSquarePosition.getX());

            gridPane.add(actingChessPiece.getImage(), newSquarePosition.getY(), newSquarePosition.getX());

            // empty previous square
            oldSquare.setHasChessPiece(false);
            oldSquare.setChessPiece(null);

            // fill new square
            newSquare.setHasChessPiece(true);
            newSquare.setChessPiece(actingChessPiece);

        }
        else
        {
            System.out.println("CAN'T MOVE THERE!");
        }
    }

    private boolean checkCanMove(ChessPiece actingChessPiece, Position newPosition)
    {
        boolean canMoveThere = false;


        // TODO NOT THE BEST SOLUTION (SWITCHED COORDINATES)
        newPosition = new Position(newPosition.getX(), newPosition.getY());


        switch (actingChessPiece.getType())
        {
            case PAWN -> canMoveThere = checkPawnsMovement(actingChessPiece, newPosition);
            case ROOK ->    canMoveThere = checkRooksMovement(actingChessPiece, newPosition);
            case KNIGHT ->  canMoveThere = checkKnightsMovement(actingChessPiece, newPosition);
            case BISHOP ->  canMoveThere = checkBishopsMovement(actingChessPiece, newPosition);
            case KING ->    canMoveThere = checkKingsMovement(actingChessPiece, newPosition);
            case QUEEN ->   canMoveThere = checkQueensMovement(actingChessPiece, newPosition);
        }
        actingChessPiece.setFirstMove(false);

        System.out.println(actingChessPiece.getType());
        System.out.println("MOVING FROM: " + actingChessPiece.getPosition() ); // converted
        System.out.println("TO: " + newPosition);

        return canMoveThere;
    }

    public boolean checkKnightsMovement(ChessPiece actingChessPiece, Position positionOfNewSquare)
    {
        int spaceX = Math.abs(actingChessPiece.getPosition().getX() - positionOfNewSquare.getX());
        int spaceY = Math.abs(actingChessPiece.getPosition().getY() - positionOfNewSquare.getY());

        boolean case1 = spaceX == 1 && spaceY == 2 ;
        boolean case2 = spaceY == 1 && spaceX == 2 ;

        return case1 || case2;
    }

    public boolean checkQueensMovement(ChessPiece actingChessPiece, Position positionOfNewSquare)
    {
        int spaceX = Math.abs(actingChessPiece.getPosition().getX() - positionOfNewSquare.getX());
        int spaceY = Math.abs(actingChessPiece.getPosition().getY() - positionOfNewSquare.getY());

        boolean case1 = (actingChessPiece.getPosition().getY() != positionOfNewSquare.getY()) &&
                (actingChessPiece.getPosition().getX() == positionOfNewSquare.getX()) ;

        boolean case2 = (actingChessPiece.getPosition().getX() != positionOfNewSquare.getX()) &&
                (actingChessPiece.getPosition().getY() == positionOfNewSquare.getY()) ;

        boolean diagonals = spaceX == spaceY;
        boolean cross = case1 || case2;

        return diagonals ^ cross;
    }

    public boolean checkKingsMovement(ChessPiece actingChessPiece, Position positionOfNewSquare)
    {
        int spaceX = Math.abs(actingChessPiece.getPosition().getX() - positionOfNewSquare.getX());
        int spaceY = Math.abs(actingChessPiece.getPosition().getY() - positionOfNewSquare.getY());

        return (spaceX == 1 || spaceY == 1);

    }

    public boolean checkBishopsMovement(ChessPiece actingChessPiece, Position positionOfNewSquare)
    {
        int spaceX = Math.abs(actingChessPiece.getPosition().getX() - positionOfNewSquare.getX());
        int spaceY = Math.abs(actingChessPiece.getPosition().getY() - positionOfNewSquare.getY());

        return spaceX == spaceY;
    }

    public boolean checkRooksMovement(ChessPiece actingChessPiece, Position positionOfNewSquare)
    {
        boolean case1 = (actingChessPiece.getPosition().getY() != positionOfNewSquare.getY()) &&
                (actingChessPiece.getPosition().getX() == positionOfNewSquare.getX()) ;

        boolean case2 = (actingChessPiece.getPosition().getX() != positionOfNewSquare.getX()) &&
                (actingChessPiece.getPosition().getY() == positionOfNewSquare.getY()) ;

        return (case1 || case2);
    }

    public boolean checkPawnsMovement(ChessPiece actingChessPiece, Position positionOfNewSquare)
    {
        // TODO fix: can move backwards
        // TODO attacking movement
        // TODO convert coordinates if enemy
        // TODO change type if reached final row

        int spaceY = Math.abs(actingChessPiece.getPosition().getY() - positionOfNewSquare.getY());

        boolean noSpaceX = Math.abs(actingChessPiece.getPosition().getX() - positionOfNewSquare.getX()) == 0;

        boolean firstMove = actingChessPiece.isFirstMove();
        System.out.println("First move: " + firstMove);

        boolean twoSteps = (spaceY == 1 || spaceY == 2) && noSpaceX ;
        boolean oneStep  = spaceY == 1 && noSpaceX;

        return firstMove ? twoSteps : oneStep;
    }

    public boolean areEnemies(ChessPiece p1, ChessPiece p2)
    {
        return (p1.isWhite() && !p2.isWhite() || !p1.isWhite() && p2.isWhite());
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
