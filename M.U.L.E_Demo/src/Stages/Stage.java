package Stages;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Models.GameModel;

/**
 * Stage objects act as the Controllers for their corresponding MULE stage.
 * 
 * Stages are responsible for gathering relevant user input from their View
 * and changing the gameModel according to that input.
 * 
 * Stages are linked together through the nextStage field.  Once a Stage is
 * finished, the nextStage field allows Stages to start the Stage that follows
 * them.  This allows for an event-driven game, as Stages are linked according
 * to the order specified by the rules of the game.
 * 
 * @author Tim Farley
 * @version 1
 */
public abstract class Stage
{
	/** Primary container to display the Stage's View */
    JFrame mainFrame;
    
    /** The gameModel used by all Stages */
	GameModel gameModel;
	
	/** The Stage that follows the current Stage according to the rules of the game */
	Stage nextStage;
	
	/**
	 * Creates the current Stage object
	 * 
	 * @param mainFrame Reference to the game's JFrame
	 * @param gameModel Reference to the game's GameModel
	 */
	public Stage(JFrame mainFrame, GameModel gameModel) {
		this.mainFrame = mainFrame;
		this.gameModel = gameModel;
	}
	
	/**
	 * Sets the nextStage field
	 * 
	 * @param nextStage The Stage that should follow this Stage
	 */
	public void setNextStage(Stage nextStage) {
		this.nextStage = nextStage;
	}
	
	/**
	 * Starts nextStage.  Call this method when this Stage is finished.
	 * 
	 * @return true if transition to next stage successful
	 */
	public boolean goNextStage() {
		if(nextStage != null)
		{
			nextStage.start();
			return true;
		}
		
		return false;
    }
	
	/**
	 * Displays the Stage's View inside the mainFrame
	 * 
	 * @param myView The View that belongs to this Stage
	 */
	public void displayView(JComponent myView)
	{
    	mainFrame.setContentPane(myView);
    	mainFrame.pack();
	    mainFrame.validate();
	}
	
	/**
	 * Start this Stage.  This Stage should create and setup a View
	 * to display in the mainFrame.
	 */
	public abstract void start();
}