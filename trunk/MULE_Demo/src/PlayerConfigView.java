import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * This class allows for configuration of the player including race, name, and color
 * @author Erica Pramer
 * @version 1
 */

public class PlayerConfigView extends JPanel{
	
	private PlayerConfig controller;
	private NamePanel name;
	private RaceButtonPanel race;
	private ColorPanel color;
	private JButton next;
	
	
	/**
	 * Instantiates all instance data, sets layout, adds action listener to the buttons
	 * 
	 * @param L
	 *            action listener passed in by the DeckPanel to facilitate
	 *            movement between jpanels using buttons
	 */
	public PlayerConfigView()//ActionListener L, PlayerConfig controller)
	{
		this.controller = controller;

	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	setPreferredSize(new Dimension(1000,400));
	
	name = new NamePanel();
	race = new RaceButtonPanel();
	next = new JButton("next");
	color = new ColorPanel();
	
	next.setMaximumSize( next.getPreferredSize() );
	
	add(name);
	add(race);
	add(color);
	add(next);
	FinishedListener ter = new FinishedListener();
	next.addActionListener(ter);
	
	}
	
	/**
	 * This method returns the next button on the player configuration panel
	 * 
	 * @return a next button to go to the next jpanel
	 */
	public JButton getNextButton()
	{
		return next;
	}
	
	
    private class FinishedListener implements ActionListener {
        
        /**
         * When the View is "finished", set up the model accordingly,
         * then terminate this stage of the game.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
        	PlayerConfigView.this.configureModel();
            PlayerConfigView.this.endStage();
        }
    }

	public void configureModel() {
		// TODO Auto-generated method stub
		
	}

	public void endStage() {
		JFrame frame = new JFrame("mapPanel0.o!");
		JPanel panel = new JPanel();
		
		BufferedImage myPicture = null;
		
		String filePath = new File("").getAbsolutePath();
	    filePath = filePath.concat("/mapPic.png");
		try {
			myPicture = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		frame.add(picLabel);
		
		frame.pack();
		frame.setVisible(true);
		
		System.out.println("DOneee");
		
		
	}
	
//	public static void main(String[] args){
//		JFrame temp = new JFrame("temp");
//		PlayerConfigPanel temp2 = new PlayerConfigPanel();
//		temp.add(temp2);
//		temp.pack();
//		temp.setVisible(true);
//		System.out.println("finished");
//	}
//	
}
