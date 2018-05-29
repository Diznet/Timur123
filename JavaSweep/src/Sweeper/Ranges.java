package Sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges
{
    static private Coordinates size;
    static private ArrayList<Coordinates> allCoordinates;
    static private Random random = new Random();

    static void setSize (Coordinates size)
    {
        Ranges.size = size;
        allCoordinates = new ArrayList<Coordinates>();
        for (int x = 0; x < size.x; x++)
            for (int y = 0 ;y < size.y; y++ )
                allCoordinates.add(new Coordinates(x,y));
    }

    static public Coordinates getSize ()
    {
        return size;
    }

    static public ArrayList<Coordinates> getAllCoordinates()
    {
        return allCoordinates;
    }

    static boolean inRange(Coordinates coordinates)
    {
        return coordinates.x >= 0 && coordinates.x < size.x &&
                coordinates.y >= 0 && coordinates.y < size.y ;
    }

    static Coordinates getRandomCoordinates ()
    {
        return new Coordinates(random.nextInt(size.x),random.nextInt(size.y));
    }

    static ArrayList<Coordinates> getCoordsAround (Coordinates coordinates)
    {
        Coordinates around;
        ArrayList<Coordinates> list = new ArrayList<Coordinates>();
        for (int x = coordinates.x - 1; x <= coordinates.x + 1 ; x ++)
            for(int y = coordinates.y - 1; y <= coordinates.y + 1 ; y ++)
                if(inRange(around = new Coordinates(x, y)))
                    if (!around.equals(coordinates))
                        list.add (around);
        return list;
    }

    static int getSquare()
    {
        return size.x * size.y;
    }
}
