import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class MapPanel extends JPanel{
	
	JLabel temp;
	public MapPanel()
	{
		//char[][] charMap = MapGenerator.generateDefaultMap('A','B','C','P','R','T');
		//a is one mountain, b is two, c is three
		
		//map = new LandPlot[CharMap.length][CharMap[0].length];
		// ^ a map of landplots (what class are we using for that?) as opposed to characters
		/*temp = new JLabel();
		temp.setIcon(new ImageIcon("mapPic.png","this will be replaced"));
		add(temp);*/
		
		setLayout(new GridLayout(5, 9));
		for(int i = 0; i < 9*5; i++)
		{
				
			LandPlotBtn btttnn = new LandPlotBtn();
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
			add(btttnn);
		}
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		//create a new map object and do drawMap(page)
	}
}
