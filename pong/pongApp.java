//Programmer's name:  William Murmann
//Email address:      wmurmann@gmail.com
//Course:             CPSC223j
//Assignment number:  assignment 4
//Due date:           2013-nov-4

//Project: A program that simulates a random maze

//This is the top level module.  This module activates the user interface.
package pong;

import javax.swing.JApplet;

public class pongApp extends JApplet
{
    private PongFrame appletFrame;
    private ball b;
    @Override
    public void init()
    {
        appletFrame = new PongFrame(this);
        appletFrame.setVisible (true);
        appletFrame.setEnabled (true);
    }//End of main
    public void close()
    {
        appletFrame.dispose();
        appletFrame=null;
    }
}
