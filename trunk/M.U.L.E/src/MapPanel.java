import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class MapPanel extends JPanel{
	
	JLabel temp;
	
	/**
	 * Creates the MapPanel containing a 5x9 grid of buttons
	 * 
	 * @param map The map object being used by the current game
	 * @param commonPlotListener A MouseListener object that will listen to
	 * each LandPlotBtn object
	 */
	public MapPanel(Map map, MouseListener commonPlotListener)
	{
	    LandPlot[][] landPlots = map.landPlotArray();
	    
	    setLayout(new GridLayout(5, 9));
	    
	    for (int row = 0; row < landPlots.length; row++) {
	        for (int col = 0; col < landPlots[0].length; col++) {
	            LandPlot plot = landPlots[row][col];
	            LandPlotBtn plotBtn = new LandPlotBtn(plot, commonPlotListener);
	            this.add(plotBtn);
	        }
	    }
		/*
		setLayout(new GridLayout(5, 9));
		for(int i = 0; i < 9*5; i++)
		{
				
			
			if(i == 25)
				btttnn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
			else if(i == 24)
				btttnn.setBorder(BorderFactory.createLineBorder(Color.green, 3));
			else if(i == 36)
				btttnn.setBorder(BorderFactory.createLineBorder(Color.orange, 3));
			else if(i == 40)
				btttnn.setBorder(BorderFactory.createLineBorder(Color.orange, 3));
			else if(i == 41)
				btttnn.setBorder(BorderFactory.createLineBorder(Color.orange, 3));
			else
				btttnn.setBorder(new EmptyBorder(0, 0, 0, 0));
			if(Math.random() > 0.8)
				btttnn.setImage(new ImageIcon("m2.png").getImage());
			else if(Math.random() > 0.5)
				btttnn.setImage(new ImageIcon("town.png").getImage());
			else
				btttnn.setImage(new ImageIcon("river.png").getImage());

			//btttnn.setBorderPainted(false);
			//btttnn.setContentAreaFilled(false);
			/*JLabel myLabel = new JLabel();
			myLabel.setIcon(new ImageIcon("mapPic.png"));
			btttnn.setLayout(new BorderLayout());
	        btttnn.add(myLabel, BorderLayout.CENTER);
	        add(btttnn, BorderLayout.CENTER);*/
			//add(btttnn);
		//}
		this.setPreferredSize(new Dimension(600, 600));
	}
}
