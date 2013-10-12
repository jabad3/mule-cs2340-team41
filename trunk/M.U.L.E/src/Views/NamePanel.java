package Views;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 * This class lets the user enter their name
 * @author Erica Pramer
 * @version 1
 */
public class NamePanel extends JPanel {
	
	/** Holds the player name input */
	private JTextField textField;
	
    /**
     * Initializes components on the JPanel
     * 
     */
	public NamePanel()
	{
		textField = new JTextField("Type Player Name");
		JLabel nameOutput = new JLabel("Player Name:");
		
		setLayout(new GridLayout(1,2));
		setPreferredSize(new Dimension(300,75));
		
		add(nameOutput);
		add(textField);
	}
	
	/**
	 * Gets the name input by the user
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return textField.getText();
	}
	
}
