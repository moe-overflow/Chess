package view;

import control.MovementController;
import model.ChessPiece;
import model.ChessPieceType;
import model.Position;
import model.Square;

public class Logger
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static void logDeadPieces()
    {
        System.out.println(ANSI_CYAN + "[DEAD BLACK PIECES: " + MovementController.getInstance().getDeadBlackPieces().toArray().length +
                "]" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "[DEAD WHITE PIECES: " + MovementController.getInstance().getDeadWhitePieces().toArray().length +
                 "]" + ANSI_RESET);
    }

    public static void logMove(ChessPieceType actingChessPieceType)
    {
        MovementController instance = MovementController.getInstance();
        System.out.println(ANSI_GREEN + "[TYPE: " + actingChessPieceType + " ]" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "[MOVING FROM: (" + instance.getOldX() + ", " + instance.getOldY() + ") ]" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "[MOVING TO  : (" + instance.getNewX() + ", " + instance.getNewY() + ") ]" + ANSI_RESET);
    }

    public static void logError(String message)
    {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    public static void logLine()
    {
        System.out.println("------------------------------------------------------------------------------");
    }

    public static void logSelectedSquare(Square clickedSquare, Position position)
    {
        System.out.println(ANSI_YELLOW + "[SELECTED: " + clickedSquare.getChessPiece().getType() + " ON POSITION:" +
                position + " ]" + ANSI_RESET);
    }

}
