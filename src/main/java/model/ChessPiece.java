package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static model.ChessPieceType.*;

public class ChessPiece
{
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


    public Position getPosition()
    {
        return this.position;
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
