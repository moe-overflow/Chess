package view;

import control.ChessBoardController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ChessBoard;
import model.ChessPiece;
import control.GuiConstants;
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

    public static HBox createLettersView()
    {
        HBox letterBox = new HBox();

        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};


        for(String letter : letters)
        {
            Label label = new Label(letter);
            setLabelProperties(label);
            letterBox.getChildren().add(label);
        }
        letterBox.setAlignment(Pos.CENTER);
        return letterBox;
    }

    public static VBox createNumbersView()
    {
        VBox numbersBox = new VBox();

        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};
        for(Integer number : numbers)
        {
            Label label = new Label(number.toString());
            setLabelProperties(label);
            numbersBox.getChildren().add(label);
        }
        numbersBox.setAlignment(Pos.CENTER);
        return numbersBox;
    }

    private static void setLabelProperties(Label label)
    {
        label.setMinWidth(80); // todo: determine value automatically
        label.setMinHeight(80); // todo: determine value automatically
        label.setId("label");
        label.setAlignment(Pos.CENTER);
    }

}
