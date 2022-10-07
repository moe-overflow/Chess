package Model;

import Model.Interfaces.Gui;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Square
{
    public static final int squareSize = 80;
    private boolean isWhite;
    private final Position position;
    private ImageView background;
    private ChessPiece chessPiece = null;
    private boolean hasChessPiece = false;

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

    // creating an empty square
    public static ImageView createTile(boolean white)
    {
        Image image;
        if(white) image = createImage(Gui.WHITE_TILE_URL);
        else image = createImage(Gui.BLACK_TILE_URL);
        return new ImageView(image);
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

    private static boolean isEvenNum(int x)
    {
        return (x%2==0);
    }

    public void setWhite(boolean white)
    {
        this.isWhite = white;
    }

    public boolean getWhite()
    {
        return this.isWhite;
    }


    public static Image createImage(String url)
    {
        return new Image(url, squareSize, squareSize, true, true);
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
