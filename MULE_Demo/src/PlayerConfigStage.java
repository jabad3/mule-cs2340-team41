import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author jabad3
 *
 */
public class PlayerConfigStage {
	public String name; //TODO: make enum
	public int race;
	public int color; //TODO: make color
	
	public PlayerConfigStage() {
	}

    private class FinishedListener implements ActionListener {
        
        /**
         * When the View is "finished", set up the model accordingly,
         * then terminate this stage of the game.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
        	PlayerConfigStage.this.configureModel();
            PlayerConfigStage.this.endStage();
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
		
		System.out.println("DOneee");
		
		
	}
}
