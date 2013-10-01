import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PlayerConfig extends JPanel {

    private int numPlayers = 4;
    
    public PlayerConfig() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        for (int i = 0; i < numPlayers; i++) {
            add(new PlayerConfiguration(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { }
            }));
        }
        add(new JButton("Next"));
    }
}
