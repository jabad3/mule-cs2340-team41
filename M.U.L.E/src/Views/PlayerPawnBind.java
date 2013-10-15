package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Models.InputType;
import Models.MapFactory;
import Models.RaceType;

/**
 * @author jabad3
 *
 */
public class PlayerPawnBind extends PlayerPawn {
    
    /**
     * Create a PlayerPawn object to represent a Player.
     * 
     * @param myRace The RaceType of the Player object represented by this pawn
     * @param myColor The ownership color of the Player object represented by this pawn
     */
    /*public PlayerPawn(RaceType myRace, Color myColor){
        ImageIcon stockIcon = myRace.getStockImageIcon();
        // TODO
        // Color the appropriate parts of the stockIcon with the player's color
        pawnIcon = stockIcon;
        this.setIcon(pawnIcon);
        this.setPreferredSize(new Dimension(50, 50));
    }*/
    
    /**
     * Create a PlayerPawn object to represent a Player using the Player's
     * icon.
     * 
     * @param playerIcon The icon associated with a Player object
     */
    public PlayerPawnBind(ImageIcon playerIcon) {
        super(playerIcon);
    }
    
    /**
     * Move the pawn left, right, up, or down.
     * 
     * @param key Specifies the direction to move the pawn
     */
    public void move(InputType key) {
        // TODO
    }
    
    /**
     * Binds the given key to the action that moves the pawn up.
     */
    public void bindUpKey(KeyStroke key) {
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();
        inputMap.put(key, "Up");
        actionMap.put("Up", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    int x = getX();
                    int y = getY();
                    int dy = getHeight() / 10;
                    setLocation(x, y - dy);
                }
            });
    }
    
    /**
     * Binds the given key to the action that moves the pawn down.
     */
    public void bindDownKey(KeyStroke key) {
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();
        inputMap.put(key, "Down");
        actionMap.put("Down", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    int x = getX();
                    int y = getY();
                    int dy = getHeight() / 10;
                    setLocation(x, y + dy);
                }
            });
    }
    
    /**
     * Binds the given key to the action that moves the pawn left.
     */
    public void bindLeftKey(KeyStroke key) {
    	InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();
        inputMap.put(key, "Left");
        actionMap.put("Left", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    int x = getX();
                    int y = getY();
                    int dx = getHeight() / 10;
                    setLocation(x - dx, y);
                }
            });
    }
    
    /**
     * Binds the given key to the action that moves the pawn right.
     */
    public void bindRightKey(KeyStroke key) {
    	InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();
        inputMap.put(key, "Right");
        actionMap.put("Right", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    int x = getX();
                    int y = getY();
                    int dx = getHeight() / 10;
                    setLocation(x + dx, y);
                }
            });
    }
    
    /**
     * Sets the given input type to be either on or off.
     * 
     * @param input The input type to update
     * 
     * @param on The state that the input type will be set to.  True means the
     * input should be turned on
     */
    public void setInputStatus(InputType input, boolean on) {
        // TODO
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("Display a pawn");
        PlayerPawnBind pawn = new PlayerPawnBind(new ImageIcon("buzzite.png"));
        
        
        pawn.bindUpKey(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
        pawn.bindDownKey(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
        pawn.bindLeftKey(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
        pawn.bindRightKey(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));
        
        MapPanel mapPanel = new MapPanel(MapFactory.buildMap("Default"), null);
        DevelopmentView view = new DevelopmentView(mapPanel, new TownPanel(), pawn);
        
        
        jf.getContentPane().add(view);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
