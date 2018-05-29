package Sweeper;

public class Coordinates
{
    public int x;
    public int y;

    public Coordinates(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Coordinates))
            return super.equals(obj);
        Coordinates to = (Coordinates) obj;
        return to.x == x && to.y == y;
    }
}
