//Programmer's name:  William Murmann
//Email address:      wmurmann@gmail.com
//Course:             CPSC223j
//Assignment number:  assignment 6
//Due date:           2013-dec-11

//Project: A project made to simulate the original pong game.

//This module defines the ball class which creates the ball for the pong game.

package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class ball extends JPanel 
{
    double x = 300, y = 130;
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        Ellipse2D circle = new Ellipse2D.Double(x,y,20,20);
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