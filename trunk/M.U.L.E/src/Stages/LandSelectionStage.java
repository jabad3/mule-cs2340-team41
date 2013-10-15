package Stages;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Models.FailedTransactionException;
import Models.GameModel;
import Models.LandPlot;
import Models.Map;
import Models.Player;
import Views.LandPlotBtn;
import Views.LandSelectionView;
import Views.MapPanel;

/**
 * LandSelectionStage is the controller for player land selection.
 *     
 * @author Tim
 *
 */
public class LandSelectionStage extends Stage {

    /** The view to be controlled by this stage. */
    private LandSelectionView myView;
    
    /** The map panel to be displayed in the view. */
    private MapPanel mapPanel;
    
    /** Ordered list of players.  Player at index 0 goes first. */
    private List<Player> playerList;
    
    /** The player whose turn it currently is. */
    private Player currentPlayer;
    
    /** The index in the playerList of the player whose turn it currently is. */
    private int currentPlayerIndex;
    
    /** The price that land will be for the current turn */
    private int landPlotPrice;
    
    /**
     * Creates the LandSelectionStage object.
     * 
     * @param mainFrame The container used to display the View
     * @param model The GameModel used throughout the game
     */
    public LandSelectionStage(JFrame mainFrame, GameModel model) {
    	super(mainFrame, model);
    	
    }
    
    /**
     * To start the LandSelectionStage, determine the starting player and land
     * price, then configure and display the View using this information.
     */
    public void start() {
        playerList = gameModel.getSortedPlayerList();
        currentPlayerIndex = 0;
        currentPlayer = playerList.get(currentPlayerIndex);
        
        Map map = gameModel.getMap();
        mapPanel = new MapPanel(map, new LandPlotListener());
        
        // currently, this is our first stage, so increment round upon start
        gameModel.incrementRound();
        
        String currentPlayerName = currentPlayer.getName();
        calculateLandPlotPrice();
    	myView = new LandSelectionView(mapPanel, landPlotPrice, currentPlayerName, new SelectionSkipListener());
    	displayView(myView);
    }
    
    private void calculateLandPlotPrice() {
        int round = gameModel.getCurrentRound();
        if (round <= 2)
            landPlotPrice = 0;
        else
            // TODO
            // currently - temporary formula
            landPlotPrice = 500 + round * round * 5;
    }
    
    /**
     * Chooses the next player in the land selection stage, then updates
     * the View to display this new change.
     * 
     * If there is no next player, then the stage is over, so go to the next
     * stage.
     */
    public void updateViewForNextPlayer() {
        currentPlayerIndex++;
        
        if (allPlayersHaveSelected()){
            showStatusDialog();
            goNextStage();
        } else {  // let the next player go
            currentPlayer = playerList.get(currentPlayerIndex);
            String currentName = currentPlayer.getName();
            myView.setCurrentPlayerName(currentName);
        }
    }
    
    /**
     * For demoing purposes to show the state of the model after
     * land selection is over
     */
    private void showStatusDialog() {
        String playerInfo = "";
        for (Player player:  playerList)
            playerInfo += player.getMyInventoryAsString();
        JOptionPane.showMessageDialog(mainFrame,
                "Land Selection is Over For Round + " + gameModel.getCurrentRound()
                + "\n------Current State of Player Objects----\n" + playerInfo);
        System.out.println("Ending LandSelection Stage");
        System.out.println("\n\n Current state of the model:  \n");
        System.out.println(gameModel);
    }
    
    private boolean allPlayersHaveSelected() {
        return currentPlayerIndex >= playerList.size();
    }
    
    /**
     * A user selects a LandPlot by clicking on it.  This class listens for
     * that selection while providing user-friendly cues to indicate
     * which plot the user's mouse cursor is on.
     * 
     * @author Max
     *
     */
    private class LandPlotListener extends MouseAdapter {
        

        @Override
        /**
         * Responds to the event when a user tries to select a land plot.
         * 
         * When a LandPlot is chosen by the user for land selection, the method
         * first checks to see if it already has an owner.
         * 
         * If no owner, then sell the LandPlot to the current player, set them
         * as the new owner, and let the next player select a land plot.
         */
        public void mouseClicked(MouseEvent e) {
            LandPlotBtn landPlotBtn = (LandPlotBtn) e.getSource();
            LandPlot chosenPlot = landPlotBtn.getMyLandPlot();
            
            if (chosenPlot.isAvailable()) {
                
                try {
                    currentPlayer.buyLandFromSeller(gameModel.getStore(), landPlotPrice);
                    chosenPlot.setOwner(currentPlayer);
                    updateViewForNextPlayer();
                } catch (FailedTransactionException exc) {
                    myView.flashNotEnoughMoneyMessage();
                }
                
            }
            
            
        }

        @Override
        /**
         * Responds to the event when a user is hovering the mouse over a
         * plot.
         * 
         * If the plot is unowned, temporarily set the border to the player's
         * color so that they are aware of which land plot they have
         * highlighted.
         */
        public void mouseEntered(MouseEvent e) {
            LandPlotBtn landPlotBtn = (LandPlotBtn) e.getSource();
            LandPlot highlightedPlot = landPlotBtn.getMyLandPlot();
            
            if (highlightedPlot.isAvailable()) {
                Color tempBorderColor = currentPlayer.getColor();
                landPlotBtn.setBorder(BorderFactory.createLineBorder(tempBorderColor, 3));
            }
        }

        @Override
        /**
         * Responds to the event when a user stops hovering the mouse over a
         * plot
         * 
         * If the plot is unowned, delete the border that was temporarily
         * set by mouseEntered() so that the user knows they are no longer
         * highlighting that land plot
         */
        public void mouseExited(MouseEvent e) {
            LandPlotBtn landPlotBtn = (LandPlotBtn) e.getSource();
            LandPlot highlightedPlot = landPlotBtn.getMyLandPlot();
            
            if (highlightedPlot.isAvailable())
                landPlotBtn.setBorderToDefault();
        }
    }
    
    /**
     * This class listens to the View for when the user triggers the event
     * to skip their turn during Land Selection.
     * 
     * @author Max
     *
     */
    private class SelectionSkipListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            updateViewForNextPlayer();
        }
        
    }
}
