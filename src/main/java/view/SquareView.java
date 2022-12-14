package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ChessPiece;
import model.ChessPieceType;
import model.GuiConstants;
import model.Square;

import static model.ChessPieceType.*;
import static model.ChessPieceType.KING;

public class SquareView
{
    // creating an empty square
    public static ImageView createTile(boolean white)
    {
        Image image;
        if(white) image = createImage(GuiConstants.WHITE_TILE_URL);
        else image = createImage(GuiConstants.BLACK_TILE_URL);
        return new ImageView(image);
    }

    public static Image createImage(String url)
    {
        return new Image(url, Square.squareSize, Square.squareSize, true, true);
    }

    // returns Image of Chess piece
    public static ImageView getChessPieceImage(char pieceSymbol, ChessPiece piece)
    {
        Image image;
        StringBuilder imageUrl = new StringBuilder();
        imageUrl.append(GuiConstants.CHESS_PIECE_URL);
        if(piece.isWhite()) imageUrl.append("w"); else imageUrl.append("b");
        imageUrl.append("-");
        switch (pieceSymbol) {
            case 'P' -> {
                imageUrl.append("pawn");
                piece.setType(PAWN);
            }
            case 'R' -> {
                imageUrl.append("rook");
                piece.setType(ROOK);
            }
            case 'N' -> {
                imageUrl.append("knight");
                piece.setType(KNIGHT);
            }
            case 'B' -> {
                imageUrl.append("bishop");
                piece.setType(BISHOP);
            }
            case 'Q' -> {
                imageUrl.append("queen");
                piece.setType(QUEEN);
            }
            case 'K' -> {
                imageUrl.append("king");
                piece.setType(KING);
            }
        }
        imageUrl.append(".png");
        image = createImage(imageUrl.toString());
        return new ImageView(image);
    }
}
