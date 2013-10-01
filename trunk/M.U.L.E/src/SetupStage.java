/**
 * This class lets the player(s) configure the game
 * @author Tim Farley
 * @version 1
 */

public class SetupStage extends Stage {
	public SetupStage(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void takeControl()
	{
		SetupView setupView = new SetupView(this);
		game.mainFrame.getContentPane().add(setupView);
	}
	
	public void setDifficulty(int difficulty)
	{
		game.gameConfig.difficulty = difficulty;
		
		System.out.println("Difficulty is now: " + game.gameConfig.difficulty);
	}
}
