package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Models.InputType;
import Models.RaceType;

/**
 * @author jabad3
 *
 */
public class PlayerPawn extends ResizableIcon {

    /** The Point representing the top-right-most position of the PlayerPawn */
	private Point location;
	private KeyListener directionListener = new DirectionListener();
	
	
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
	public void move(InputType key) {
	    // TODO
	}
	
	/**
	 * Manually position the pawn to the desired location.
	 * 
	 * @param newLocation The new location to place the pawn
	 */
	public void setLocation(Point newLocation) {
	    location = newLocation;
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
	
	/**
	 * Returns the KeyListener of the PlayerPawn so other classes can use it
	 * 
	 * @return the pawn's keylistener
	 */
	public KeyListener getListener()
	{
		return directionListener;
	}
	
	public static void main(String[] args) {
	    JFrame jf = new JFrame("Display a pawn");
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    panel.add(new PlayerPawn(new ImageIcon("Buzzite.png")));
	    panel.add(new ResizableIcon(new ImageIcon("flapper.png")));
	    panel.add(new JLabel(new ImageIcon("bonzoid.png")));
	    panel.setPreferredSize(new Dimension(300, 200));
	    jf.getContentPane().add(panel);
	    jf.pack();
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf.setVisible(true);
	}

	
	/**
	 * @author epramer3
	 *
	 */
	private class DirectionListener implements KeyListener
	{
	
/**
 * Moves the player according to which key was pressed
 * @param event The key that was pressed
 */
		public void keyPressed (KeyEvent event)
		{
			switch (event.getKeyCode())
			{
				case KeyEvent.VK_UP:
					System.out.println("up"); //debug statement
					location.translate(0,-30);
					break;
				case KeyEvent.VK_DOWN:
					location.translate(0,30);
					break;
				case KeyEvent.VK_LEFT:
					location.translate(-30,0);
					break;
				case KeyEvent.VK_RIGHT:
					location.translate(0,30);
					break;
			}
			repaint();
		
		}
		public void keyReleased(KeyEvent event) {}
		public void keyTyped(KeyEvent event) {}
	}

}
