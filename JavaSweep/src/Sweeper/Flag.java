package Sweeper;

class Flag
{
    private Matrix flagMap;

    private int totalFlaged;
    private int totalClosed;

    void start()
    {
        flagMap = new Matrix(Box.CLOSED);
        totalFlaged = 0;
        totalClosed = Ranges.getSquare ();
    }

    Box get (Coordinates coordinates)
    {
        return flagMap.get(coordinates);
    }

    void setOpenedToBox(Coordinates coordinates)
    {
        flagMap.set (coordinates,Box.OPENED);
        totalClosed --;
    }

    int getTotalFlaged()
    {
        return totalFlaged;
    }

    int getTotalClosed()
    {
        return totalClosed;
    }

    void setFLaggedToBox(Coordinates coordinates)
    {
        flagMap.set (coordinates,Box.FLAGGED);
        totalFlaged ++;
    }

    void setClosedToBox(Coordinates coordinates)
    {
        flagMap.set (coordinates,Box.CLOSED);
        totalFlaged --;
    }

    void toggleFLaggedToBox(Coordinates coordinates)
    {
        switch (flagMap.get (coordinates))
        {
            case FLAGGED : setClosedToBox (coordinates); break;
            case CLOSED : setFLaggedToBox(coordinates) ; break;
        }
    }

    void setFLaggedToLastClosedBoxes()
    {
        for (Coordinates coordinates : Ranges.getAllCoordinates())
            if (Box.CLOSED == flagMap.get(coordinates))
                setFLaggedToBox(coordinates);
    }

    void setBombedToBox(Coordinates coordinates)
    {
        flagMap.set (coordinates,Box.BOMBED);
    }

    void setOpenedToClosedBOx(Coordinates coordinates)
    {
        if (Box.CLOSED == flagMap.get (coordinates))
            flagMap.set(coordinates,Box.OPENED);
    }

    void setNoBombToFlagged(Coordinates coordinates)
    {
        if (Box.FLAGGED == flagMap.get(coordinates))
            flagMap.set(coordinates,Box.NOBOMB);
    }

    int FLagCount(Coordinates coordinates)
    {
        int count = 0;
        for (Coordinates around : Ranges.getCoordsAround(coordinates))
            if (flagMap.get(around) == Box.FLAGGED)
                count ++;
        return count;
    }
}
