package Views;

import Models.LandPlot;

/**
 * Shop entry listeners listen for and respond to events that occur when the
 * user attempts to interact with any of the shops inside the town.
 * 
 * TownEventListeners are notified whenver any of the following happens: 1) User
 * attempts to interact with mule store 2) User attempts to interact with land
 * office 3) User attempts to interact with pub 4) User attempts to interact
 * with assay office
 * 
 * @author Max
 * 
 */
public interface ShopEntryListener {

    /**
     * This method is called whenever the user attempts to interact with the
     * store.
     */
    public void enteredStore();

    /**
     * This method is called whenever the user attempts to interact with the
     * land office.
     */
    public void enteredLandOffice();

    /**
     * This method is called whenever the user attempts to interact with the
     * pub.
     */
    public void enteredPub();

    /**
     * This method is called whenever the user attempts to interact with the
     * assay office.
     */
    public void enteredAssayOffice();

    /**
     * This method is called whenver the user attempts to interact with a land
     * plot.
     * 
     * @param plot
     *            The LandPlot that the user wishes to interact with
     */
    public void enteredLandPlot(LandPlot plot);
}
