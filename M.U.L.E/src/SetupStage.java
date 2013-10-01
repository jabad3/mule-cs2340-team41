/**
 * This class lets the player(s) configure the game
 * @author Tim Farley
 * @version 1
 */

public class SetupStage implements Stage {
	public void takeControl()
	{
		DeckPanel deck = new DeckPanel();
		Main.curGame.mainFrame.getContentPane().add(deck);
	}
}
