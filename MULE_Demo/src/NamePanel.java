import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 * This class lets the user enter their name
 * @author Erica Pramer
 * @version 1
 */
public class NamePanel extends JPanel{
	
	private JTextField textField;
	private JLabel nameOutput;
    /**
     * Initializes components on the JPanel
     * 
     */
	public NamePanel()
	{
		textField = new JTextField("Type Player Name");
		nameOutput = new JLabel("Player Name:");
		
		setLayout(new GridLayout(1,2));
		setPreferredSize(new Dimension(1000,100));
		
		TextListener listener = new TextListener();
		textField.addActionListener(listener);
		
		add(nameOutput);
		add(textField);
	}
	
	
	/**
	 * Private inner class defines what happens upon button click.
	 */	
	private class TextListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String text = textField.getText();
			nameOutput.setText("Player Name: " + text);
		}
	}
	
}
