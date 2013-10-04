/**
 * This interface is implemented by the controllers
 * @author Tim Farley
 * @version 1
 */

public abstract class Stage
{
	Game game;
	
	public Stage(Game game)
	{
		this.game = game;
	}
	
	public abstract void takeControl();
}