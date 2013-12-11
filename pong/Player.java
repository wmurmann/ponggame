//Programmer's name:  William Murmann
//Email address:      wmurmann@gmail.com
//Course:             CPSC223j
//Assignment number:  assignment 6
//Due date:           2013-dec-11

//Project: A project made to simulate the original pong game.

//This module difines the paddle class called player. 

package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class Player extends JPanel 
{
    double x = 5, y = 80, height = 70, width = 20; 
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        Rectangle2D circle = new Rectangle2D.Double(x,y,width,height);
        g2.setColor(Color.red);
        g2.fill(circle);
    }
    public void changePosition(double xc, double yc)
    {
        this.x = xc;
        this.y = yc;
        repaint();
    }
}