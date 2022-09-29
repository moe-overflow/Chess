package Model;

public class Position
{
    private final int x;
    private final int y;

    public Position(int y, int x)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString()
    {
        return ("Position: " + getX() + ", " + getY());
    }
}
