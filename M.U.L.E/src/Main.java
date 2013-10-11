import javax.swing.JFrame;

import Models.Game;
/**
 * This class executes the game of MULE.
 * 
 * @author Erica Pramer
 * @version 1
 */

public class Main
{
    /**
     * Creates the main JFrame container for MULE, then runs the Game
     * object.
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
	