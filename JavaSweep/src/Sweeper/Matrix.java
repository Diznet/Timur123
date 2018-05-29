package Sweeper;

public class Matrix
{
    private Box [][] matrix;

    Matrix (Box box)
    {
        matrix = new Box [Ranges.getSize().x] [Ranges.getSize().y];
        for (Coordinates coordinates : Ranges.getAllCoordinates())
            matrix[coordinates.x][coordinates.y] = box;
    }

    Box get (Coordinates coordinates)
    {
        if (Ranges.inRange(coordinates))
            return matrix[coordinates.x][coordinates.y];
        return null;

    }

    Void set (Coordinates coordinates,Box box)
    {
        if (Ranges.inRange(coordinates))
            matrix[coordinates.x][coordinates.y] = box;
        return null;
    }
}
