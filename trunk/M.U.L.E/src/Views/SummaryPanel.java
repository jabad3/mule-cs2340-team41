package Views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Models.FailedTransactionException;
import Models.Game;
import Models.GameModel;
import Models.Mule;
import Models.Player;
import Models.Resource;
import Models.Store;

/**
 * This class shows the players the game's state and allows players to save
 * 
 * @author Tim
 */
public class SummaryPanel extends JPanel {
    /**
     * Creates the SummaryPanel's interface
     * 
     * @param summary
     *            The summary to display
     */
    public SummaryPanel(String summary, final GameModel gameModel) {
	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

	JTextArea textArea = new JTextArea(summary);
	textArea.setEditable(false);
	textArea.setLineWrap(true);
	textArea.setOpaque(false);
	textArea.setBorder(BorderFactory.createEmptyBorder());
	textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(textArea);

	JButton saveBtn = new JButton("Save Game");
	saveBtn.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		gameModel.saveGame();
	    }
	});
	saveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(saveBtn);

	if (gameModel.getCurrentRound() == 0 || gameModel.gameIsOver())
	    saveBtn.setEnabled(false);
    }
}
