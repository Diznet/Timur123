import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Sweeper.Box;
import Sweeper.Coordinates;
import Sweeper.Ranges;
import Sweeper.Game;
import Sweeper.GState;

public class Sweeper extends JFrame
{
    private Game game;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int Image_Size = 50;

    private JPanel panel;
    private JLabel label;

    public static void main(String[] args)
    {
        new Sweeper();
    }

    private Sweeper()
    {
        game = new Game (COLS,ROWS,BOMBS);
        game.start();
        setImages();
        InitPanel();
        InitLabel();
        InitFrame();
    }

    private void InitLabel()
    {
        label = new JLabel("Welcome!");
        Font font = new Font("Tahoma", Font.BOLD, 18);
        label.setFont(font);
        add (label, BorderLayout.SOUTH);
    }

    private void InitPanel()
    {
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coordinates coordinates : Ranges.getAllCoordinates())

                    g.drawImage((Image) game.getBox(coordinates).image,
                            coordinates.x * Image_Size,
                            coordinates.y * Image_Size, this);
            }
        };

        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int x = e.getX() / Image_Size;
                int y = e.getY() / Image_Size;
                Coordinates coordinates = new Coordinates(x,y);
                switch (e.getButton())
                {
                    case MouseEvent.BUTTON1 : game.LeftButton(coordinates); break;
                    case MouseEvent.BUTTON3 : game.RightButton(coordinates); break;
                    case MouseEvent.BUTTON2 : game.start();break;
                }
                label.setText(getMessage());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * Image_Size,
                Ranges.getSize().y * Image_Size));
        add (panel);
    }

    private void InitFrame()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sweeper");
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void setImages ()
    {
        for (Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
        setIconImage(getImage("Icon"));
    }

    private Image getImage (String name)
    {
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    private String getMessage ()
    {
        switch (game.getState())
        {
            case BOMBED: return "Badluck :)";
            case WINNED: return "Someone got lucky today!";
            case PLAYED:
            default : if (game.getTotalFlagged() == 0)
                            return "GL HF!";
                    else
                        return "You still alive,Flagged " +
                                game.getTotalFlagged() + " of " +
                                game.getTotalBombs() + " bombs.";


        }
    }
}
