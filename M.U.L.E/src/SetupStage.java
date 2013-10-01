/**
 * This class lets the player(s) configure the game
 * @author Tim Farley
 * @version 1
 */

public class SetupStage implements Stage {
	public void takeControl(Game game)
	{
		DeckPanel deck = new DeckPanel();
		game.mainFrame.getContentPane().add(deck);
	}
}
