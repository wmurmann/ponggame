//Programmer's name:  William Murmann
//Email address:      wmurmann@gmail.com
//Course:             CPSC223j
//Assignment number:  assignment 6
//Due date:           2013-dec-11

//Project: A project made to simulate the original pong game.
//This defines the the logic module which handles the getting angles and speed for the ball class.
package pong;

import java.util.Random;

public class logic
{
    public static int getAngle()
    {
        Random r = new Random();
        int randomInt = r.nextInt(300 - 60) + 60;
        if(randomInt == 90 || randomInt == - 90)
        {
            Random k = new Random();
            int ran = k.nextInt(2) + 1;
            if( ran % 2 == 0)
            {
                randomInt = randomInt - 4;
            }
            else
            {
                randomInt = randomInt + 5;
            }
        }
        int heads = r.nextInt(2 - 1) + 1;
        if(heads % 2 == 0)
        {
            randomInt = randomInt *-1;        
        }
        return randomInt;
    }
    public static int setSpeed(int speed)
    {
        if(speed <=1)
        {
            speed = 16;
        }
        else if(speed == 2)
        {
            speed = 15;
        }
        else if(speed == 3)
        {
            speed = 14;
        }
        else if(speed == 4)
        {
            speed = 13;
        }
        else if(speed == 5)
        {
            speed = 12;
        }
        else if(speed == 6)
        {
            speed = 10;
        }
        else if(speed == 7)
        {
            speed = 8;
        }
        else if(speed == 8)
        {
            speed = 6;
        }
        else if(speed == 9)
        {
            speed = 4;
        }
        else
        {
            speed = 2;
        }
        return speed;
    }
}