package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Models.MapFactory;


/**
 * The DevelopmentView class allows the user to move their player around the
 * map and interact with the different shops in the town.
 * 
 * It should allow the user to move a PlayerPawn around the map and the town.
 * The user should also be able to interact with the different shops inside the
 * town.
 * 
 * @author jabad3
 *
 */
public class DevelopmentView extends JLayeredPane {
    
    /** Label to display the current Player's name. */
    JLabel playerNameLabel;
    
    /** Displays the land plots to the user. */
    MapPanel mapPanel;
    
    /** The current PlayerPawn object to be displayed to the user. */
    PlayerPawn currentPawn;
    
    /**
     * Create the Development View.
     * 
     * @param mapPanel The map panel to display to the user
     * @param playerPawn The first pawn to display to the user
     */
    public DevelopmentView(MapPanel mapPanel, PlayerPawn playerPawn) {
        playerNameLabel = new JLabel("Whose turn is it?");
        this.mapPanel = mapPanel;
        this.currentPawn = playerPawn;
        
        // Because JLayeredPane layout manager is null, manually set size,
        // location of components to add to it
        this.mapPanel.setSize(mapPanel.getPreferredSize());
        this.mapPanel.setLocation(0, 0);
        
        playerNameLabel.setSize(playerNameLabel.getPreferredSize());
        playerNameLabel.setLocation(0, this.mapPanel.getHeight() - 100);
        
        currentPawn.setSize(currentPawn.getPreferredSize());
        currentPawn.setLocation(100, 50);
        
        this.add(mapPanel, new Integer(0));  // place map underneath everything
        this.add(playerNameLabel, new Integer(1));
        this.add(currentPawn, new Integer(2));
        this.mapPanel.setFocusable(true);
        this.mapPanel.requestFocusInWindow();
        //this.mapPanel.addKeyListener(currentPawn.getListener());
        System.out.println(this.mapPanel.requestFocusInWindow()); //debug statement, returns false, this is the problem
        this.setPreferredSize(mapPanel.getPreferredSize());
        //this.setPreferredSize(new Dimension(400, 300));
    }

    /**
     * Set the current Player's name
     * 
     * @param playerName The new name to display
     */
    public void setCurrentPlayerName(String playerName) {
        playerNameLabel.setText(playerName);
    }
    
    /**
     * Set the current pawn to a new pawn.
     * 
     * @param newPawn The new PlayerPawn to be displayed
     */
    public void setCurrentPawn(PlayerPawn newPawn) {
        currentPawn = newPawn;
    }
    
    /**
     * Shows the map and positions the current pawn to its initial position in the map.
     */
    public void resetView() {
        // TODO
    }
    
    /**
     * Display the map to the user.
     */
    public void showMap() {
        // TODO
    }
    
    /**
     * Display the town to the user.
     */
    public void showTown() {
        
    }
 
    /**
     * To test the development view.
     * 
     * @param args
     */
    public static void main(String[] args) {
        JFrame jf = new JFrame("Test Dev't View");
        MapPanel mapPanel = new MapPanel(MapFactory.buildMap("Default"), null);
        PlayerPawn pawn = new PlayerPawn(new ImageIcon("Buzzite.png"));
        
        DevelopmentView dv = new DevelopmentView(mapPanel, pawn);
        
        jf.getContentPane().add(dv);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
    
    /*
     JFrame jf = new JFrame("JLayeredPane Test");
        Map map = MapFactory.buildMap("Default");
        MapPanel gridpanel = new MapPanel(map, null);
        gridpanel.setSize(gridpanel.getPreferredSize());
        gridpanel.setLocation(0,0);
        JLayeredPane layerpane = new JLayeredPane();
        JLabel label = new JLabel("I should be on top");
        label.setSize(label.getPreferredSize());
        label.setLocation(100, 100);
        layerpane.add(gridpanel, JLayeredPane.DEFAULT_LAYER);
        layerpane.add(label, JLayeredPane.PALETTE_LAYER);
        //layerpane.setPreferredSize(gridpanel.getPreferredSize());
        //layerpane.add(label, JLayeredPane.PALETTE_LAYER);
        jf.getContentPane().add(layerpane);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
     */
    
}

