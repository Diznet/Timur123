package Sweeper;

public class Game
{
    private Bomb bomb;
    private Flag flag;

    private GState state;

    public Game (int cols,int rows,int bombs)
    {
        Ranges.setSize(new Coordinates(cols,rows));
        bomb = new Bomb (bombs);
        flag = new Flag ();
    }

    public void start ()
    {
        bomb.start();
        flag.start();
        state = GState.PLAYED;
    }

    public Box getBox (Coordinates coordinates)
    {
        if (Box.OPENED == flag.get(coordinates))
            return bomb.get(coordinates);
        else
            return flag.get (coordinates);
    }

    public void LeftButton (Coordinates coordinates)
    {
        if(GState.PLAYED == state) {
            openBox(coordinates);
            checkWin();
        }
    }

    public void RightButton (Coordinates coordinates)
    {
        flag.toggleFLaggedToBox (coordinates);
    }

    public GState getState()
    {
        return state;
    }

    public int getTotalBombs ()
    {
        return bomb.getTotalBombs();
    }

    public int getTotalFlagged()
    {
        return flag.getTotalFlaged();
    }

    private boolean GAMEOVER ()
    {
        if (GState.PLAYED != state)
        {
            start();
            return true;
        }
        return false;
    }
    private void checkWin ()
    {
        if (GState.PLAYED == state)
            if(flag.getTotalClosed() == bomb.getTotalBombs())
            {
                state = GState.WINNED;
                flag.setFLaggedToLastClosedBoxes();
            }
    }

    private void openBox (Coordinates coordinates)
    {
        switch (flag.get(coordinates))
        {
            case OPENED : OpenedToCloseAround (coordinates); break;
            case FLAGGED:  break;
            case CLOSED :
                switch (bomb.get (coordinates))
                {
                    case ZERO: openBoxAroundZero(coordinates); break;
                    case BOMB: OpenBombs(coordinates);break;
                    default:flag.setOpenedToBox(coordinates);break;
                }
        }
    }

    void OpenedToCloseAround(Coordinates coordinates)
    {
        if (Box.BOMB != bomb.get(coordinates))
            if (bomb.get(coordinates).getNumber() == flag.FLagCount (coordinates))
                for (Coordinates around : Ranges.getCoordsAround(coordinates))
                    if (flag.get(around) == Box.CLOSED)
                        openBox(around);
    }

    private void OpenBombs (Coordinates bombedCoord)
    {
        flag.setBombedToBox (bombedCoord);
        for (Coordinates coordinates : Ranges.getAllCoordinates())
            if (bomb.get(coordinates) == Box.BOMB)
                flag.setOpenedToClosedBOx(coordinates);
        else
                flag.setNoBombToFlagged (coordinates);
        state = GState.BOMBED;
    }

    private void openBoxAroundZero (Coordinates coordinates)
    {
        System.out.println(coordinates.x + " " + coordinates.y);
        flag.setOpenedToBox(coordinates);
        for(Coordinates around: Ranges.getCoordsAround(coordinates))
         openBox(around);
    }
}
