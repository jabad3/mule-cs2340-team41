import javax.swing.JFrame;
/**
 * This class executes the game of MULE
 * @author Erica Pramer
 * @version 1
 */

public class Main
{
/**
 * Main method; sets up JFrame and puts a DeckPanel on the JFrame so that the 
 * game can be played
 */
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("M.U.L.E");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Game curGame = new Game(frame);
		curGame.start();
		
		frame.pack();
		frame.setVisible(true);
	}

}
	