//Programmer's name:  William Murmann
//Email address:      wmurmann@gmail.com
//Course:             CPSC223j
//Assignment number:  assignment 4
//Due date:           2013-nov-4

//Project: A program that simulates a random maze

//This is the top level module.  This module activates the user interface.
package pong;

/**
 * 
 *
 * @author wm
 */
import java.util.Timer;
import javax.swing.JFrame;

public class main
{public static void main(String[] args)
    {
        PongFrame frame = new PongFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,750);
        frame.setVisible(true);
    }//End of main
}//End of class test3filesolutio
