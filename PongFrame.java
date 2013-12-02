//Programmer's name:  William Murmann
//Email address:      wmurmann@gmail.com
//Course:             CPSC223j
//Assignment number:  assignment 4
//Due date:           2013-nov-4



//Project: A program that simulates a random maze

//This module defines the properties of the user interface

package pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class PongFrame extends JFrame
{
    //variables
    double angle = 0;
    boolean start = false;
    boolean running = false;
    boolean ballMoving = false;
    boolean gamePause = false;
    int leftScore = 0;
    int rightScore = 0;
    boolean gameOver = false;
    
    //objects
    public ball b;
    public Player left,right;
    private JButton newGame, go, pause, exit;
    private JLabel NewGame, Speed, LeftPlayer, RightPlayer, Table, Winner;
    private JTextField speed, lPlayer, rPlayer,winnerField;
    private Timer timer;
    public logic functions;
    
    //layout managers
    private GridBagLayout grid;
    private FlowLayout field;
    private FlowLayout global;
    //panels
    private JPanel GameField, input,spacer;
    private ActionListener actionlistener;
    
    public PongFrame()
    {
        grid = new GridBagLayout();
        global = new FlowLayout(FlowLayout.CENTER,0,0);
        setLayout(global);
        
        timer = new Timer();
        
        GridBagConstraints c = new GridBagConstraints();
        Font gen = new Font("Sans-Serif", Font.PLAIN, 18);
        
        //Input fields
        b = new ball();
        b.setPreferredSize(new Dimension(600,300));
        b.setSize(600,300);
        b.setBackground(Color.black);
        b.setBorder(null);
        b.setLayout(new GridBagLayout());
        
        left = new Player();
        left.setPreferredSize(new Dimension(600,300));
        left.setOpaque(false);
        c.gridx = 0;
        c.gridy = 0;
        b.add(left,c);
       
        right = new Player();
        right.setPreferredSize(new Dimension(600,300));
        right.setOpaque(false);
        right.changePosition(575, 80);
        c.gridx = 0;
        c.gridy = 0;
        b.add(right,c);
        
        spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(1200,5));

        
        JPanel input = new JPanel();
        input.setLayout(grid);
        
        Speed = new JLabel("Speed");
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0,10,20,0);
        input.add(Speed, c);
        
        speed = new JTextField();
        speed.setPreferredSize(new Dimension(75,30));
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0,10,20,0);
        input.add(speed, c);
        
        LeftPlayer = new JLabel("Left PLayer");
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(0,10,20,0);
        input.add(LeftPlayer, c);
        
        lPlayer = new JTextField();
        lPlayer.setPreferredSize(new Dimension(75,30));
        lPlayer.setEditable(false);
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(0,10,20,0);
        input.add(lPlayer, c);
        
        RightPlayer = new JLabel("Right PLayer");
        c.gridx = 3;
        c.gridy = 0;
        c.insets = new Insets(0,10,20,0);
        input.add(RightPlayer, c);
        
        rPlayer = new JTextField();
        rPlayer.setPreferredSize(new Dimension(75,30));
        rPlayer.setEditable(false);
        c.gridx = 3;
        c.gridy = 1;
        c.insets = new Insets(0,10,20,0);
        input.add(rPlayer, c);
        
        Winner = new JLabel("Winner");
        c.gridx = 4;
        c.gridy = 0;
        c.insets = new Insets(0,10,20,0);
        input.add(Winner, c);
        
        winnerField = new JTextField();
        winnerField.setPreferredSize(new Dimension(75,30));
        winnerField.setEditable(false);
        c.gridx = 4;
        c.gridy = 1;
        c.insets = new Insets(0,10,20,0);
        input.add(winnerField, c);
        
        newGame = new JButton("New");
        c.gridx = 0;
        c.gridy = 3;
        input.add(newGame, c);
        
        go = new JButton("Start");
        c.gridx = 1;
        c.gridy = 3;
        input.add(go, c);
        
        pause = new JButton("Pause");
        c.gridx = 2;
        c.gridy = 3;
        input.add(pause, c);
        
        exit = new JButton("Exit");
        c.gridx = 3;
        c.gridy = 3;
        input.add(exit, c);
        
        buttonhandler action = new buttonhandler();
        KeyWatcher watch = new KeyWatcher();
        newGame.addActionListener(action);
        go.addActionListener(action);
        go.addKeyListener(watch);
        pause.addActionListener(action);
        exit.addActionListener(action);
        add(b);
        add(spacer);
        add(input);
    }
    private class buttonhandler implements ActionListener 
    {
        public void actionPerformed(ActionEvent event)
        {
            angle = logic.getAngle();
            if(event.getSource() == newGame && !"".equals(speed.getText()))
            {
               timer.cancel();
               start = false;
               running = false;
               ballMoving = false;
               leftScore = 0;
               rightScore = 0;
               angle = logic.getAngle();
               b.changePosition(300, 130);
               
               winnerField.setText("");
               lPlayer.setText((String.valueOf(leftScore)));
               rPlayer.setText((String.valueOf(rightScore)));
            }
            else if(event.getSource() == go &&  start == false && !gamePause || gamePause)
            {
                timer.cancel();
                start = true;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PongFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                startTimer(logic.setSpeed(Integer.parseInt(speed.getText())));
                ballMoving = true;
                gamePause = true;
            }
            else if(event.getSource() == pause)
            {
                timer.cancel();
                gamePause = true;
                //start = false;
                //ballMoving = false;
            }
            else if(event.getSource() == exit)
            {
                System.exit(0);
            }
        }
        
        public void startTimer(int HSeconds)
        {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(start)
                    {
                        move();
                    }
                    ballMoving = true;
                }
            }, 0, HSeconds);
        }
        public void move()
        {
            boolean paddle = false;
            double X,Y;
            double angleTemp = angle;
            if(gameOver)
            {
                timer.cancel();
                b.changePosition(300, 130);
            }
            if(b.x < 0 )
            {
                b.changePosition(300, 130);
                angle = logic.getAngle();
                rightScore++;
                rPlayer.setText(String.valueOf(rightScore));
                
                try 
                {
                    TimeUnit.SECONDS.sleep(2);
                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(PongFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(b.x > 600)
            {
                b.changePosition(300, 130);
                angle = logic.getAngle();
                leftScore++;
                lPlayer.setText(String.valueOf(leftScore));
                
                try 
                {
                    TimeUnit.SECONDS.sleep(2);
                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(PongFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(b.y <= 0 || b.y >= 280)
            {
                angle = 180 - angle;
            }
            else if(b.x >= 555)
            {
                 if(rightScore == 10)
                {
                    leftScore = 0;
                    rightScore = 0;
                    speed.setText("");
                    winnerField.setText("Right Player");
                    gameOver = true;
                }
                else if(leftScore == 10)
                {
                    leftScore = 0;
                    rightScore = 0;
                    speed.setText("");
                    winnerField.setText("Left Player");
                    gameOver = true;
                }
                else if(b.y >= right.y - 20  && b.y <= right.y + 60)
                {
                    //b.changePosition(300, 130);
                    angle = 360 - angle;
                    paddle = true;
                }
            }
            else if(b.x <= 25)
            {
                if(rightScore == 9)
                {
                    leftScore = 0;
                    rightScore = 0;
                    speed.setText("");
                    winnerField.setText("Right Player");
                    gameOver = true;
                }
                else if(leftScore == 9)
                {
                    leftScore = 0;
                    rightScore = 0;
                    speed.setText("");
                    winnerField.setText("Left Player");
                    gameOver = true;
                }
                else if(b.y >= left.y - 20  && b.y <= left.y + 60)
                {
                    angle = 360 - angle;
                    paddle = true;
                }
            }
            X = Math.sin(Math.toRadians(angle));
            Y = Math.cos(Math.toRadians(angle));
            b.changePosition(b.x + X,b.y + Y);
        }
    }
    private class KeyWatcher implements KeyListener
    {
        boolean rightMove = false;
        boolean leftMove = false;
        char rightDirection = 'u';
        char leftDirection = 'u';
        
        
        
        public void move()
        {
                if(rightMove)
                {
                    if(rightDirection == 'u' && right.y - 1 >= 0)
                    {
                        right.changePosition(right.x , right.y - 1);                    
                    }
                    else if(right.y + 1 <= 230)
                    {
                        right.changePosition(right.x , right.y + 1);
                    }
                }
                if(leftMove)
                {
                    if(leftDirection == 'u' && left.y - 1 >= 0)
                    {
                        left.changePosition(left.x , left.y - 1);
                    }
                    else if(left.y + 1 <= 230)
                    {
                        left.changePosition(left.x , left.y + 1);
                    }
                }
            
        }
        public void startTimer(int HSeconds)
        {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    move();
                }
            }, 0, HSeconds);
        }
        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(start && !running)
            {
                startTimer(8);
                running = true;          
            }
            else if(!start)
            {
                timer.cancel();
            }
            
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    if(right.y > 0)
                    {
                        rightMove = true;
                        rightDirection = 'u';
                    }
                break;
                case KeyEvent.VK_DOWN:
                    if(right.y < 230)
                    {
                        rightMove = true;
                        rightDirection = 'd';
                    }
                    
                break;
                case KeyEvent.VK_W:
                    if(left.y > 0)
                    {
                        leftMove = true;
                        leftDirection = 'u';
                    }
                    
                break;
                case KeyEvent.VK_S:
                    if(left.y < 230)
                    {
                        leftMove = true;
                        leftDirection = 'd';
                    }
                    
                break;
            }
            move();
        }

        @Override
        public void keyReleased(KeyEvent e) 
        {
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    if(right.y > 0)
                    {
                        rightMove = false;
                    }
                break;
                case KeyEvent.VK_DOWN:
                    if(right.y < 225)
                    {
                        rightMove = false;
                    }
                    
                break;
                case KeyEvent.VK_W:
                    if(left.y > 0)
                    {
                        leftMove = false;
                    }
                    
                break;
                case KeyEvent.VK_S:
                    if(left.y < 225)
                    {
                        leftMove = false;
                    }
                    
                break;
            }
        }
    
      
    }
}//end  











