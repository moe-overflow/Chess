package Model;

public class ChessBoard
{
    public static final int CHESS_BOARD_SIZE = 8;

    private final Square[][] chessBoard = new Square[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE];

    public char[][] getChessPieceSymbol() {
        return chessPieceSymbol;
    }

    private final char[][] chessPieceSymbol = {
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'K', 'Q', 'B', 'N', 'R'}
    };

    // Creates a square for every position of chess board
    public ChessBoard()
    {
        for(int i = 0; i < CHESS_BOARD_SIZE; i++)
        {
            for(int j = 0; j < CHESS_BOARD_SIZE; j++)
            {
                Position position = new Position(i, j);
                chessBoard[j][i] = new Square(position);
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < chessBoard.length ; i++)
        {
            for (int k = 0; k < chessBoard.length; k++)
            {
                stringBuilder.append(chessBoard[i][k]); // toString for Square
            }
            stringBuilder.append("\n\r");
        }
        return stringBuilder.toString();
    }

    public Square getSquare(Position p)
    {
        return chessBoard[p.getY()][p.getX()];
    }

    public char getChessPieceSymbol(int j, int i)
    {
        return chessPieceSymbol[j][i];
    }

}
