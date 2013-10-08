import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

/**
 * LandSelectionStage is the controller for player land selection.
 *     
 * @author Tim
 *
 */
public class LandSelectionStage extends Stage {

    private LandSelectionView myView;
    private List<Player> playerList;
    private int currentPlayerIndex = 0;
    private MapPanel mapPanel = new MapPanel(new LandPlotButtonListener());
    private int landPlotPrice;
    
    public LandSelectionStage(JFrame mainFrame, GameModel model) {
    	super(mainFrame, model);
    	playerList = model.getSortedPlayerList();
    }
    
    public void start() {
        String currentPlayerName = playerList.get(currentPlayerIndex).getName();
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
    private class LandPlotButtonListener implements ActionListener {
        
        /**
         * When a LandPlot is chosen by the user for land selection, the method
         * first checks to see if it already has an owner.
         * 
         * If no owner, then set the owner to the current player and let the
         * next player go
         */
        public void actionPerformed(ActionEvent e) {
            LandPlotBtn button = (LandPlotBtn) e.getSource();
            LandPlot chosenPlot = button.getMyLandPlot();
            
            if (chosenPlot.getOwner() == null) {
                Player currentPlayer = playerList.get(currentPlayerIndex);
                chosenPlot.setOwner(currentPlayer);
                currentPlayerIndex++;
            }
            
            if (allPlayersHaveSelected())
                goNextStage();
            else { // update the View to display new name
                Player currentPlayer = playerList.get(currentPlayerIndex);
                String currentName = currentPlayer.getName();
                myView.setCurrentPlayerName(currentName);
            }
        }
        
        private boolean allPlayersHaveSelected() {
            return currentPlayerIndex >= playerList.size();
        }
    }
}
