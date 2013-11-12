import Models.IntroTrack;
import Stages.StartScreen;

/**
 * This class executes the game of MULE.
 * 
 * @author Erica Pramer
 * @version 1
 */

public class Main {
    /** 
     * This is a driver class that brings up an introductory screen (with New Game and Load Game options) and
     * consequently starts our game.
     */
	public static void main(String[] args) {
		StartScreen startScreen = new StartScreen();
    	IntroTrack introTrack = new IntroTrack();
    	introTrack.start();
	}
}
	