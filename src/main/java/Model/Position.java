package Model;

public class Position
{
    private int x;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int y;

    public Position(int y, int x)
    {
        this.y = y;
        this.x = x;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public String toString()
    {
        return "Position: (" + getX() + ", " + getY() + ")";
    }
}
