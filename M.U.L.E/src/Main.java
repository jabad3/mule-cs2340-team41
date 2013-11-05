import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Models.Game;
import Models.GameModel;
/**
 * This class executes the game of MULE.
 * 
 * @author Erica Pramer
 * @version 1
 */

public class Main {
    /**
     * Creates the main JFrame container for MULE, then runs the Game
     * object.
     */
	
	public Main() {
		JFrame frame = new JFrame("M.U.L.E");
		
		JPanel panel = new JPanel();
		JButton button = new JButton("Load");
		JButton button2 = new JButton("New Game");
		ButtonListener listener = new ButtonListener(button, button2, frame);
		
		button.addActionListener(listener);
		button2.addActionListener(listener);
		
		panel.add(button);
		panel.add(button2);
		frame.add(panel);
		
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public class ButtonListener implements ActionListener {
		JButton button1;
		JButton button2;
		JFrame frame;
		
		public ButtonListener(JButton button1, JButton button2, JFrame frame) {
			// TODO Auto-generated constructor stub
			this.button1 = button1;
			this.button2 = button2;
			this.frame = frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button1) {
				System.out.println("Loading");
				
				GameModel saved = new GameModel();
				saved.loadGame();
				
				//call new constructor
				Game curGame = new Game(frame, saved);
				curGame.start();
			}
			if (e.getSource() == button2) {
				System.out.println("New Game");
				Game curGame = new Game(frame);
				curGame.start();
			}
		}
		
	}
	
	
	public static void main(String[] args)
	{
		Main m = new Main();
		//Game curGame = new Game(frame);
		//curGame.start();
	}

}
	