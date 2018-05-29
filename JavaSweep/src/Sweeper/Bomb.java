package Sweeper;

class Bomb
{
    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombs)
    {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start()
    {
        bombMap = new Matrix(Box.ZERO);
        for (int j = 0; j < totalBombs; j ++)
            planting();
    }

    Box get(Coordinates coordinates)
    {
        return bombMap.get(coordinates);
    }

    int getTotalBombs()
    {
        return totalBombs;
    }

    private void fixBombsCount ()
    {
        int maxBombs = Ranges.getSquare() / 2;
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    private void planting()
    {
        while (true)
        {
            Coordinates coordinates = Ranges.getRandomCoordinates();
            if (Box.BOMB == bombMap.get(coordinates))
                continue;
            bombMap.set(coordinates, Box.BOMB);
            IncNumsArround(coordinates);
            break;
        }
    }

    private void IncNumsArround(Sweeper.Coordinates coordinates) {
        for (Coordinates around : Ranges.getCoordsAround(coordinates))
            if (Box.BOMB != bombMap.get(around))
                bombMap.set (around,bombMap.get(around).nextNumBox());
    }
}
