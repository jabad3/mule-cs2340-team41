package Views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Models.InputType;
import Models.MapFactory;
import Models.RaceType;

/**
 * @author jabad3
 *
 */
public class PlayerPawn extends ResizableIcon {

	/**
	 * Create a PlayerPawn object to represent a Player.
	 * 
	 * @param myRace The RaceType of the Player object represented by this pawn
	 * @param myColor The ownership color of the Player object represented by this pawn
	 */
	/*public PlayerPawn(RaceType myRace, Color myColor){
		ImageIcon stockIcon = myRace.getStockImageIcon();
		// TODO
		// Color the appropriate parts of the stockIcon with the player's color
		pawnIcon = stockIcon;
		this.setIcon(pawnIcon);
		this.setPreferredSize(new Dimension(50, 50));
	}*/
	
	/**
	 * Create a PlayerPawn object to represent a Player using the Player's
	 * icon.
	 * 
	 * @param playerIcon The icon associated with a Player object
	 */
	public PlayerPawn(ImageIcon playerIcon) {
	    super(playerIcon);
	}
	
	/**
	 * Move the pawn left, right, up, or down.
	 * 
	 * @param key Specifies the direction to move the pawn
	 */
	public void move() {
	    int speed = 2;
        
        if(leftKey)
            setLocation(getX() - speed, getY());
        if(rightKey)
            setLocation(getX() + speed, getY());
        if(upKey)
            setLocation(getX(), getY() - speed);
        if(downKey)
            setLocation(getX(), getY() + speed);
	}
	
	/**
	 * Sets the given input type to be either on or off.
	 * 
	 * @param input The input type to update
	 * 
	 * @param on The state that the input type will be set to.  True means the
	 * input should be turned on
	 */
	public void setInputStatus(InputType input, boolean on) {
	    // TODO
	}
    
    public boolean leftKey = false;
    public boolean rightKey = false;
    public boolean upKey = false;
    public boolean downKey = false;
    
    private class PlayerKeyListener extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                leftKey = true;
                break;
                case KeyEvent.VK_RIGHT:
                rightKey = true;
                break;
                case KeyEvent.VK_UP:
                upKey = true;
                break;
                case KeyEvent.VK_DOWN:
                downKey = true;
                break;
            }
        }
        public void keyReleased(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                leftKey = false;
                break;
                case KeyEvent.VK_RIGHT:
                rightKey = false;
                break;
                case KeyEvent.VK_UP:
                upKey = false;
                break;
                case KeyEvent.VK_DOWN:
                downKey = false;
                break;
            }
        }
    }
    
    class MoveTask extends TimerTask {
        private PlayerPawn playerToMove;
        
        public MoveTask(PlayerPawn playerToMove) {
            this.playerToMove = playerToMove;
        }
        
        public void run() {
            int speed = 2;
            
            if(playerToMove.leftKey)
                playerToMove.setLocation(playerToMove.getX() - speed, playerToMove.getY());
            if(playerToMove.rightKey)
                playerToMove.setLocation(playerToMove.getX() + speed, playerToMove.getY());
            if(playerToMove.upKey)
                playerToMove.setLocation(playerToMove.getX(), playerToMove.getY() - speed);
            if(playerToMove.downKey)
                playerToMove.setLocation(playerToMove.getX(), playerToMove.getY() + speed);
        }
    }
    
    public void enableMovement(Component listenToThis)
    {
        listenToThis.setFocusable(true);
        listenToThis.addKeyListener(new PlayerKeyListener());
        
        //Timer fires every 16ms
        //Timer timer = new Timer();
        //timer.schedule(new MoveTask(this), 0, 16);
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("Display a pawn");
        PlayerPawn pawn = new PlayerPawn(new ImageIcon("buzzite.png"));
        
        MapPanel mapPanel = new MapPanel(MapFactory.buildMap("Default"), null);
        TownPanel townPanel = new TownPanel();
        DevelopmentView view = new DevelopmentView(mapPanel, townPanel, pawn, new MuleTimerPanel(10000));
        
        pawn.enableMovement(view);
        
        
        
        jf.getContentPane().add(view);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

}
