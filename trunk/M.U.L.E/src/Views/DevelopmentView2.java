package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import Models.MapFactory;


public class DevelopmentView2 extends JLayeredPane implements KeyListener {
    
    JLabel playerNameLabel;
    MapPanel mapPanel;
    PlayerPawn currentPawn;
     
    public DevelopmentView2(MapPanel mapPanel, PlayerPawn playerPawn) {
        playerNameLabel = new JLabel("Whose turn is it?");
        this.mapPanel = mapPanel;
        this.currentPawn = playerPawn;
        this.mapPanel.setSize(mapPanel.getPreferredSize());
        this.mapPanel.setLocation(0, 0);
        playerNameLabel.setSize(playerNameLabel.getPreferredSize());
        playerNameLabel.setLocation(0, this.mapPanel.getHeight() - 100);
        currentPawn.setSize(currentPawn.getPreferredSize());
        
        System.out.println((int)currentPawn.location.getX() +", "+ (int)currentPawn.location.getY());
        currentPawn.setLocation(new Point((int)currentPawn.location.getX(), (int)currentPawn.location.getY()));
        System.out.println((int)currentPawn.location.getX() +", "+ (int)currentPawn.location.getY());
        //currentPawn.setLocation(280,265);
        
        this.add(mapPanel, new Integer(0));  // place map underneath everything
        this.add(playerNameLabel, new Integer(1));
        this.add(currentPawn, new Integer(2));
        
        this.setPreferredSize(mapPanel.getPreferredSize());
        addKeyListener(this);
    }

    public void setCurrentPlayerName(String playerName) {
        playerNameLabel.setText(playerName);
    }

    public void setCurrentPawn(PlayerPawn newPawn) {
        currentPawn = newPawn;
    }

    public void resetView() {
        // TODO
    }

    public void showMap() {
        // TODO
    }

    public void showTown() {
        
    }
    
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
		this.validate();
    	currentPawn.repaint();
    	//g.fillOval(10, 10, 800, 800);
    	System.out.println("done repainting");
    }    	

	@Override
	public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int keyCode = e.getKeyCode();
			String keyWord = e.getKeyText(keyCode);
			System.out.println("omg it works, key pressed: " + keyWord);
			double x = currentPawn.location.getX();
			double y = currentPawn.location.getY();
			currentPawn.setLocation(new Point((int)x+10,(int)y+10));
			System.out.println(currentPawn.getLocation());
			
			this.validate();
			this.repaint();
			mapPanel.repaint();
			currentPawn.repaint();
			System.out.println("done?");
		}

	@Override
	public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
	public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    public static void main(String[] args) {
        JFrame jf = new JFrame("Test Dev't View");
        MapPanel mapPanel = new MapPanel(MapFactory.buildMap("Default"), null);
        PlayerPawn pawn = new PlayerPawn(new ImageIcon("Buzzite.png"));
        
        DevelopmentView2 dv = new DevelopmentView2(mapPanel, pawn);
        dv.setFocusable(true);
        
        jf.getContentPane().add(dv);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}

