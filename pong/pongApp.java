//Programmer's name:  William Murmann
//Email address:      wmurmann@gmail.com
//Course:             CPSC223j
//Assignment number:  assignment 6
//Due date:           2013-dec-11

//Project: A project made to simulate the original pong game.

//This is the top level applet module it controls the applets life cycle.
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
