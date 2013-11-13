package Stages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Models.GameModel;
import Models.Map;
import Models.Player;
import Views.ProductionMapPanel;
import Views.ProductionView;


/**
 * The ProductionStage acts as the controller during the production phase of
 * the game.
 * 
 * During production, land plots produce resources that are added to their
 * owners' inventories.  The view is then updated to indicate to the users
 * how their players' inventories have changed.
 * 
 * @author Max
 *
 */
public class ProductionStage extends Stage {

    /** The view that belongs to the ProductionStage. */
    private ProductionView myView;
    
    /** The map model object used in the game. */
    private Map map;
    
    /** Used to display the map to the user inside the stage's view. */
    private ProductionMapPanel productionMapPanel;
    
    /**
     * Create a ProductionStage object.
     * 
     * @param mainFrame The frame to hold the Stage's view
     * @param gameModel The game model used during the game
     */
    public ProductionStage(JFrame mainFrame, GameModel gameModel) {
        super(mainFrame, gameModel);
    }

    @Override
    public void start() {
        map = gameModel.getMap();
        map.produceAllRandomOrder();
        productionMapPanel = new ProductionMapPanel(map);
        myView = new ProductionView(productionMapPanel, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    goNextStage();
                }
                    
            });
        //displayView(myView);
        
        //productionMapPanel.displayLatestProduction();
        // PLACE HOLDER UNTIL PRODUCTION VIEW IS FINISHED
        showStatusDialog();
        goNextStage();
        // END OF PLACE HOLDER
        
        
        
    }
    
    /**
     * For demoing purposes to show the state of the model after
     * land selection is over
     */
    private void showStatusDialog() {
        List<Player> playerList = gameModel.getPlayerList();
        String playerInfo = "";
        for (Player player:  playerList)
            playerInfo += player.getMyInventoryAsString() + "\n";
        JOptionPane.showMessageDialog(mainFrame,
                "Production has taken place for Round #" + gameModel.getCurrentRound()
                + "\n\n" + playerInfo);
        System.out.println("Ending LandSelection Stage");
        System.out.println("\n\n Current state of the model:  \n");
        System.out.println(gameModel);
    }

}
