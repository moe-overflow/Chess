package model;

import javafx.scene.image.ImageView;

public class ChessPiece
{
    private ChessPieceType type;
    private boolean isWhite;
    private ImageView image;
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


    @Override
    public String toString()
    {
        return isWhite ? "WHITE " + getType() : "BLACK " + getType() ;
    }


}
