package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

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
public class PlayerPawn extends JLabel {

    /** The Point representing the top-right-most position of the PlayerPawn */
	private Point location;
	
	/** The Player model object that this pawn is representing */
	private ImageIcon pawnIcon;
	
	/**
	 * Create a PlayerPawn object to represent a Player.
	 * 
	 * @param myRace The RaceType of the Player object represented by this pawn
	 * @param myColor The ownership color of the Player object represented by this pawn
	 */
	public PlayerPawn(RaceType myRace, Color myColor){
		ImageIcon stockIcon = myRace.getStockImageIcon();
		// TODO
		// Color the appropriate parts of the stockIcon with the player's color
		pawnIcon = stockIcon;
		this.setIcon(pawnIcon);
		this.setPreferredSize(new Dimension(50, 50));
	}
	
	/**
	 * Create a PlayerPawn object to represent a Player using the Player's
	 * icon.
	 * 
	 * @param playerIcon The icon associated with a Player object
	 */
	public PlayerPawn(ImageIcon playerIcon) {
	    pawnIcon = playerIcon;
	    this.setIcon(pawnIcon);
	    this.setPreferredSize(new Dimension(50, 50));
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
	
	public static void main(String[] args) {
	    JFrame jf = new JFrame("Display a pawn");
	    JPanel panel = new JPanel();
	    panel.add(new PlayerPawn(new ImageIcon("Buzzite.png")));
	    jf.getContentPane().add(panel);
	    jf.pack();
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf.setVisible(true);
	}

}
