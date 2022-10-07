package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static Model.ChessPieceType.*;

public class ChessPiece
{
    private static final String CHESS_PIECE_URL = "file:./src/main/java/Resources/Pics/" ;
    private ChessPieceType type;
    private boolean isWhite;
    private ImageView image;

    private boolean firstMove = true;

    private Position position;

    public ChessPiece(/*boolean isWhite*/)
    {
        //this.isWhite = isWhite;
    }

    public ChessPieceType getType()
    {
        return type;
    }

    public void setType(ChessPieceType type)
    {
        this.type = type;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    // returns Image of Chess piece
    public ImageView getChessPieceImage(char pieceSymbol)
    {
        Image image;
        StringBuilder imageUrl = new StringBuilder();
        imageUrl.append(CHESS_PIECE_URL);
        if(this.isWhite) imageUrl.append("w"); else imageUrl.append("b");
        imageUrl.append("-");
        switch (pieceSymbol) {
            case 'P' -> {
                imageUrl.append("pawn");
                 this.type = PAWN;
            }
            case 'R' -> {
                imageUrl.append("rook");
                this.type = ROOK;
            }
            case 'N' -> {
                imageUrl.append("knight");
                this.type = KNIGHT;
            }
            case 'B' -> {
                imageUrl.append("bishop");
                this.type = BISHOP;
            }
            case 'Q' -> {
                imageUrl.append("queen");
                this.type = QUEEN;
            }
            case 'K' -> {
                imageUrl.append("king");
                this.type = KING;
            }
        }
        imageUrl.append(".png");
        image = createImage(imageUrl.toString());
        return new ImageView(image);
    }

    private static Image createImage(String url)
    {
        return new Image(url, Square.squareSize, Square.squareSize, true, true);
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public boolean isFirstMove()
    {
        return firstMove;
    }

    public void setFirstMove(boolean firstMoveOfPawn)
    {
        this.firstMove = firstMoveOfPawn;
    }

    @Override
    public String toString()
    {
        return isWhite ? "WHITE " + getType() : "BLACK " + getType() ;
    }


}
