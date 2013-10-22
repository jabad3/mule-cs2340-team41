package Views;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import Models.InputType;
import Models.MapFactory;

/**
 * @author jabad3
 *
 */
public class PlayerPawn extends ResizableIcon {

    /** True if left key is pressed. */
    public boolean leftKey = false;
    
    /** True if right key is pressed. */
    public boolean rightKey = false;
    
    /** True if up key is pressed. */
    public boolean upKey = false;
    
    /** True if down key is pressed. */
    public boolean downKey = false;
    
    /** True if the action key is pressed. */
    public boolean actionKey = false;
    
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
	 * Move the pawn left, right, up, and/or down depending on which keys
	 * are currently pressed.
	 */
	public void move() {
	    int speed = 2;
	    Point premovePos = getLocation();
	    Point postmovePos = premovePos;
        
        if(leftKey)
        	postmovePos.x -= speed;
        if(rightKey)
        	postmovePos.x += speed;
        if(upKey)
        	postmovePos.y -= speed;
        if(downKey)
        	postmovePos.y += speed;
        
        setLocation(postmovePos);
	}
	
	/**
	 * Reset all key states back to zero.
	 */
	public void resetStates() {
	    leftKey = false;
	    rightKey = false;
	    upKey = false;
	    downKey = false;
	    actionKey = false;
	}
    
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
                case KeyEvent.VK_SPACE:
                actionKey = true;
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
                case KeyEvent.VK_SPACE:
                actionKey = false;
                break;
            }
        }
    }
    
    /*class MoveTask extends TimerTask {
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
    }*/
    
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
