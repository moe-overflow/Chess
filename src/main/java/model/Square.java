package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static utilities.Utilities.isEvenNum;

public class Square
{
    public static final int squareSize = 80;
    private boolean isWhite;
    private final Position position;
    private ImageView background;
    private ChessPiece chessPiece = null;
    private boolean hasChessPiece = false;

    // todo: variable to prevent movement if checkmate
    private boolean isAttacked;

    public boolean getHasChessPiece()
    {
        return hasChessPiece;
    }

    public void setHasChessPiece(boolean hasChessPiece)
    {
        this.hasChessPiece = hasChessPiece;
    }

    public ChessPiece getChessPiece()
    {
        return chessPiece;
    }


    public void setChessPiece(ChessPiece chessPiece)
    {
        this.chessPiece = chessPiece;
    }

    public Square(Position position)
    {
        this.position = position;
        this.isWhite = this.isWhiteSquare();
    }

    public boolean isWhiteSquare()
    {
        int i = this.position.getX();
        int j = this.position.getY();
        return(isEvenNum(i) && isEvenNum(j) || !isEvenNum(i) && !isEvenNum(j) );
    }

    public boolean isBlackSquare()
    {
        int i = this.position.getX();
        int j = this.position.getY();
        return(!isEvenNum(i) && isEvenNum(j) || isEvenNum(i) && !isEvenNum(j) );
    }



    public void setWhite(boolean white)
    {
        this.isWhite = white;
    }

    public boolean getWhite()
    {
        return this.isWhite;
    }


    public Position getPosition()
    {
        return this.position;
    }

    public void setBackgroundImage(ImageView imageView)
    {
        this.background = imageView;
    }

}
