package Stages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Models.Game;
import Models.GameModel;



public class StartScreen {
	
	public StartScreen() { 
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
				
				GameModel saved = GameModel.loadGame();
				
				//call new game constructor
				Game curGame = new Game(frame, saved);
				curGame.start();
			}
			if (e.getSource() == button2) {
				System.out.println("Starting New Game");
				Game curGame = new Game(frame);
				curGame.start();
			}
		}
	}
}


