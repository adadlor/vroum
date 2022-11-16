package src;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.*; // Pour pouvoir utiliser les fichiers
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;



public class Fenetre extends JFrame
{


    public Fenetre(String title, int width, int height)
    {
        this.setTitle(title);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setVisible(true);

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Mouse clicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                System.out.println("Mouse pressed");
                /*
                Point p = e.getPoint();
                int x = p.x;
                int y = p.y;
                System.out.println("x = " + x + " y = " + y);
                draw(x, y,1,1,Color.BLACK);

                 */
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.println("Mouse released");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                System.out.println("Mouse entered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                System.out.println("Mouse exited");
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                System.out.println("Mouse wheel moved");

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                System.out.println("dragged");

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                System.out.println("moved");
            }
        });




    }

    public void draw(int x, int y, int width, int height, Color color) {
        Graphics g = this.getGraphics();
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }


}



// INFO COULEURS

// Color.WHITE, Color.WHITE, Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY , Color.RED, Color.GREEN,
// Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.GRAY, Color.PINK, Color.YELLOW