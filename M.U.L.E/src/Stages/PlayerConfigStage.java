package Stages;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Models.GameModel;
import Models.Player;
import Views.PlayerConfigView;

/**
 * PlayerConfigStage is responsible for updating the Model using information
 * in the View during Player Configuration. The user is able to choose
 * characteristics for each Player, and these Player objects are created
 * so that they can later be added to the Model.
 * 
 * @author jabad3
 *
 */
public class PlayerConfigStage extends Stage {
	
    /** The View that this PlayerConfigStage will control. */
    PlayerConfigView myView;
    
    /** The player number (Ex:  Player 1) being configured.  Starts at 1. */
	public int playerAt = 1;
	
	// used to setup the View
	
    
    /**
     * A list of Color objects representing colors that cannot be chosen.
     * 
     * Colors must be disabled after they are picked so that no two Players
     * have the same Color.  
     * 
     * This list grows as more Players select their colors. 
     */
    private List<Color> disabledColorOptions = new ArrayList<>();
	
    /**
     * Create the PlayerConfigStage object.
     * 
     * @param mainFrame The primary container that will hold this Stage's view
     * @param model The GameModel that the Stage will manipulate
     */
	public PlayerConfigStage(JFrame mainFrame, GameModel model) {
    	super(mainFrame, model);
    	
    }
	
	/**
	 * Configures the view for the current player number, then displays the
	 * view to the user.
	 */
	public void showPlayerConfigPane() {
		myView = new PlayerConfigView();
		myView.setPlayerNum(playerAt);
		myView.setDisabledColorOptions(disabledColorOptions);
		myView.addFinishedListener(new FinishedListener());
    	displayView(myView);
	}
	
	/**
	 * Starts the stage by displaying the View to the user.
	 */
	public void start() {
		showPlayerConfigPane();
	}
	
	/**
	 * FinishedListener listens for when the user is finished configuring
	 * a Player.
	 * 
	 * If there are still players left to configure, continue displaying
	 * PlayerConfigViews to the user.  Otherwise, there are no players left,
	 * so the FinishedListener will take action to end the stage.
	 * 
	 * @author Max
	 *
	 */
	private class FinishedListener implements ActionListener
	{
	    @Override
		public void actionPerformed(ActionEvent e)
		{
			// take no action if user does not select a race or color
		    if(myView.getRace() == null || myView.getColor() == null)
			{
				return;
			}
			
			// Ensures that no color is selected twice
			disabledColorOptions.add(myView.getColor());
			Player player = new Player(myView.getText(), myView.getRace(), myView.getColor(), gameModel.getDifficulty());
			gameModel.addPlayer(player);
			playerAt++;
			
			if(playerAt > gameModel.getNumPlayers())
			{
				System.out.println("Game Model info after configuring... \n" + gameModel);
			    System.out.println("Last player configed, going to map!");
				goNextStage();
			}
			else
			{
				showPlayerConfigPane();
			}
		}
	}
}