package Stages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
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
import Views.StorePanel;
import Views.StoreView;
import Views.SummaryPanel;

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
        // currently, this is our first stage, so increment round upon start
        gameModel.incrementRound();
        gameModel.updatePlayerOrder();  // player order used for rest of round
        
        playerList = gameModel.getSortedPlayerList();
        currentPlayerIndex = 0;
        currentPlayer = playerList.get(currentPlayerIndex);
        
        Map map = gameModel.getMap();
        mapPanel = new MapPanel(map, new LandPlotListener());
        
        // TODO
        // Temporary until we have a proper summary/end screen
        if (gameModel.gameIsOver()) {
            JOptionPane.showMessageDialog(myView, "Congratulations, you actually"
                                          + " made it to the end!!!\n\n"
                    + "Click okay to see the final results, then the game will end.");
            showStatusDialog();
            System.out.println("Exiting game...");
            System.exit(0);
        }
        
        String currentPlayerName = currentPlayer.getName();
        calculateLandPlotPrice();
    	myView = new LandSelectionView(mapPanel, landPlotPrice, currentPlayerName, new SelectionSkipListener());
    	
    	displayView(myView);
    }
    
    /**
     * Determines the price of land for the current round.
     */
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
     * Assign plots to appropriate owners.  Both the plot and the owner
     * must be aware of one another.
     * 
     * Pre-condition:  plot must be available to be owned by another player
     * 
     * @param plot Unowned plot that will become owned
     * @param owner The new owner of the plot
     */
    private void assignPlotToOwner(LandPlot plot, Player owner) {
        plot.setOwner(owner);
        owner.addLandPlot(plot);
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
        
        if (allPlayersHaveSelected()) {
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
            playerInfo += player.getMyInventoryAsString() + "\n";
        
        JDialog summaryDialog = new JDialog();
        summaryDialog.setModal(true);
    	SummaryPanel summaryPanel = new SummaryPanel("SUMMARY:\nLand Selection is Over For Round #"
    			+ gameModel.getCurrentRound()
                + "\n\n" + playerInfo, gameModel);
    	summaryPanel.setPreferredSize(new Dimension(300, 550));
    	summaryDialog.setContentPane(summaryPanel);
    	summaryDialog.pack();
    	summaryDialog.setVisible(true);
    	
        System.out.println("Ending LandSelection Stage");
        System.out.println("\n\n Current state of the model:  \n");
        System.out.println(gameModel);
    }
    
    /**
     * Determines whether or not all players have had a turn during
     * land selection.
     * 
     * @return True if all players have had a turn during land selection
     */
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
                    currentPlayer.paySeller(gameModel.getStore(), landPlotPrice);
                    assignPlotToOwner(chosenPlot, currentPlayer);
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
