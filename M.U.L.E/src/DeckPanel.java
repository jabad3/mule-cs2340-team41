import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * This class adds all the UI's to a card layout
 * @author Erica Pramer
 * @version 1
 */
public class DeckPanel extends JPanel
{
	private PlayerConfig playerConfig;
	private GameConfig gamePanel;
	
	ActionListener listener = new Listener();
	
	/**
	 * Instantiates jpanels, adds them to a cardlayout
	 * 
	 */
	public DeckPanel()
	{
	setLayout(new CardLayout());
	setPreferredSize(new Dimension(440,250));
	gamePanel = new GameConfig(listener);
	playerConfig = new PlayerConfig();
	
	add(gamePanel);
	add(playerConfig);
	
	}
	
	/**
	 * Private inner class defines what happens upon button click.
	 */	
	private class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if (source == gamePanel.getNextButton())
			{
				((CardLayout) getLayout()).next(DeckPanel.this);
				setPreferredSize(new Dimension(700,250));
			}
			
		}
	}

}
