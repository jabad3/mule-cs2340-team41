package Views;

import java.awt.Graphics;

import Models.LandPlot;
import Models.Map;
import Models.Resource;

/**
 * This class is a MapPanel with the ability to display production results
 * on each plot icon.  ProductionMapPanel also ignores repaint so that
 * the displayed production results do not get erased.
 * 
 * @author Max
 *
 */
public class ProductionMapPanel extends MapPanel {

    /**
     * Create a ProductionMapPanel.
     * 
     * @param map The current map model
     */
    public ProductionMapPanel(Map map) {
        super(map);
        this.setIgnoreRepaint(true);  // so what we draw does not get erased by automatic repaint
    }
    
    /**
     * Display the latest production results onto the panel.
     * The resource type and quantity will be displayed.
     */
    public void displayLatestProduction() {
        for (int i = 0; i < ROWS*COLS; i++) {
            displayLatestProductionForPlot(i);
        }
    }
    
    /**
     * Displays the production results for the given plot.
     * @param index The plot component index in the map panel container
     */
    private void displayLatestProductionForPlot(int index) {
        LandPlotBtn currentComponent = (LandPlotBtn) super.getComponent(index);
        //LandPlotBtn currentComponent = btnList.get(index);
        Graphics g = currentComponent.getGraphics();
        LandPlot currentPlot = currentComponent.getMyLandPlot();
        
        int quantity = currentPlot.getLastAmountProduced();
        Resource resource = currentPlot.getResourceForProduction();
        
        int resourceX = 0;
        int resourceY = currentComponent.getHeight() / 3;
        g.drawString(resource + ":  ", resourceX, resourceY);
        
        int quantityX = 0;
        int quantityY = currentComponent.getHeight() * 2 / 3;
        g.drawString("" + quantity, quantityX, quantityY);
    }

}
