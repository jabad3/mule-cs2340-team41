import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

/**
 * LandSelectionStage is the controller for player land selection.
 *     
 * @author Tim
 *
 */
public class LandSelectionStage extends Stage {

    private LandSelectionView myView;
    private MapPanel mapPanel;
    
    private List<Player> playerList;
    private Player currentPlayer;
    private int currentPlayerIndex;
    
    private int landPlotPrice;
    
    public LandSelectionStage(JFrame mainFrame, GameModel model) {
    	super(mainFrame, model);
    	
    }
    
    public void start() {
        playerList = gameModel.getSortedPlayerList();
        currentPlayerIndex = 0;
        currentPlayer = playerList.get(currentPlayerIndex);
        
        Map map = gameModel.getMap();
        mapPanel = new MapPanel(map, new LandPlotListener());
        
        String currentPlayerName = currentPlayer.getName();
    	myView = new LandSelectionView(mapPanel, landPlotPrice, currentPlayerName);
    	displayView(myView);
    }
    
    /**
     * A user selects a LandPlot by clicking on it.  This class listens for
     * that selection.
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
            
            if (chosenPlot.getOwner() == null && chosenPlot.getLandType() != LandPlotType.TOWN) {
                chosenPlot.setOwner(currentPlayer);
                currentPlayerIndex++;
            }
            
            if (allPlayersHaveSelected())
                goNextStage();
            else {  // let the next player go
                currentPlayer = playerList.get(currentPlayerIndex);
                String currentName = currentPlayer.getName();
                myView.setCurrentPlayerName(currentName);
            }
        }
        
        private boolean allPlayersHaveSelected() {
            return currentPlayerIndex >= playerList.size();
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
            
            if (highlightedPlot.getOwner() == null && highlightedPlot.getLandType() != LandPlotType.TOWN) {
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
            
            if (highlightedPlot.getOwner() == null)
                landPlotBtn.setBorderToDefault();
        }
    }
}
