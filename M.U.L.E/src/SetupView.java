//import java.awt.CardLayout;
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.*;
///**
// * This class adds all the UI's to a card layout
// * @author Erica Pramer
// * @version 1
// */
//public class SetupView extends JPanel
//{
//	private PlayerConfigPanel playerConfig;
//	private GameConfigView gamePanel;
//	
//	private MapPanel mapPanel;
//	
//	ActionListener listener = new Listener();
//	
//	/**
//	 * Instantiates jpanels, adds them to a cardlayout
//	 * 
//	 */
//	public SetupView(SetupStage controller)
//	{
//    	setLayout(new CardLayout());
//    	setPreferredSize(new Dimension(440,250));
//    
//    	gamePanel = new BasicGameConfigView();
//    	playerConfig = new PlayerConfigPanel(listener, controller);
//    	mapPanel = new MapPanel();
//    	
//    	add(gamePanel);
//    	add(playerConfig);
//    	add(mapPanel);
//	
//	}
//	
//	/**
//	 * Private inner class defines what happens upon button click.
//	 */	
//	private class Listener implements ActionListener
//	{
//		public void actionPerformed(ActionEvent event)
//		{
//			Object source = event.getSource();
//			/*if (source == gamePanel.getNextButton())
//			{
//				((CardLayout) getLayout()).next(SetupView.this);
//				setPreferredSize(new Dimension(700,250));
//			}*/
//			if(source==playerConfig.getNextButton())
//			{
//				((CardLayout) getLayout()).next(SetupView.this);
//			}
//			
//		}
//	}
//
//}
