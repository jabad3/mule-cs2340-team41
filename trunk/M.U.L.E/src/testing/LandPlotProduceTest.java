package testing;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import Models.Difficulty;
import Models.LandPlot;
import Models.LandPlotType;
import Models.Mule;
import Models.Player;
import Models.RaceType;
import Models.Resource;

/**
 * This class is responsible for testing all possible scenarios for the
 * produce() method inside the LandPlot class.  The tests check to make sure
 * that:  1) the owner's inventory changes as expected, and 2) the land plots'
 * lastAmountProduced value changes as expected.
 * 
 * Because land plot production is dependent on both the land plot type and
 * the resource type, all combinations are tested.
 * 
 * Even though a land plot with type TOWN will never have a mule or owner
 * due to trust boundaries, it's produce method can still be called under
 * normal play, so TOWN type is tested along with the other land plot types.
 * In each case, produce() on TOWN should not do anything.
 * 
 * Expected values for all other land plots are based on the table given in M2
 * (in addition to whether or not they have mules and/or owners).
 * 
 * @author Max
 *
 */
public class LandPlotProduceTest {
    
    /**
     * Denotes int value for one energy unit.
     * For readability in method calls.
     */
    private static final int ONE_ENERGY = 1;
    
    /**
     * Denotes int value for zero energy units.
     * For readability in method calls.
     */
    private static final int NO_ENERGY = 0;
    
    /** 
     * Holds six LandPlots (one of each type).
     * Format is as follows (index - type)
     * 0 - RIVER
     * 1 - PLAIN
     * 2 - MTN_1
     * 3 - MTN_2
     * 4 - MTN_3
     * 5 - TOWN
     */
    private LandPlot[] plotArray;
    
    /**
     * Holds the expected food count for each of the above LandPlot's owners
     * after production.
     * Order in this array matches the order in the plotArray.
     */
    private int[] expectedOwnerFood;
    
    /**
     * Holds the expected energy count for each of the above LandPlot's owners
     * after production.
     * Order in this array matches the order in the plotArray.
     */
    private int[] expectedOwnerEnergy;
    
    /**
     * Holds the expected ore count for each of the above LandPlot's owners
     * after production.
     * Order in this array matches the order in the plotArray.
     */
    private int[] expectedOwnerOre;
    
    /**
     * Holds the expected results for the lastAmountProduced value for each of
     * the above LandPlots.
     * Order in this array matches the order in the plotArray.
     */
    private int[] expectedLastAmountProduced;

    @Before
    /**
     * Initialize all plots and the plot array.
     * The plotArray will be of length 6 and will have river, plain, mtn1,
     * mtn2, mtn3, and town land plots as elements (in that order).
     * 
     * @throws Exception
     */
    public void setUp() throws Exception {
        LandPlot riverPlot = new LandPlot(LandPlotType.RIVER);
        LandPlot plainPlot = new LandPlot(LandPlotType.PLAIN);
        LandPlot mtn1Plot = new LandPlot(LandPlotType.MTN_1);
        LandPlot mtn2Plot = new LandPlot(LandPlotType.MTN_2);
        LandPlot mtn3Plot = new LandPlot(LandPlotType.MTN_3);
        LandPlot townPlot = new LandPlot(LandPlotType.TOWN);
        
        plotArray = new LandPlot[] {riverPlot, plainPlot, mtn1Plot,
                                    mtn2Plot, mtn3Plot, townPlot};
    }
    
    /**
     * Sets owners and mules to all land plots, then calls produce for all
     * land plots.
     * 
     * This method should only be called by tests that use owned land plots
     * with mules.
     * 
     * @param energyUnits The energy units to place in the owner's inventory
     * @param muleType The resource the mule will be configured to produce
     */
    private void setupAndProduceOwnedPlotsWithMules(int energyUnits,
                                                    Resource muleType) {
        assignPlotsOwnersWithEnergyOf(energyUnits);
        assignPlotsMulesOfType(muleType);
        callProduceOnAllPlots();
    }
    
    /**
     * All plots are assigned separate owners with the same characteristics.
     * Owners will have nothing in their inventory except for the specified
     * amount of energy units.
     * 
     * @param energyUnits The energy units that the plots' owners will have.
     */
    private void assignPlotsOwnersWithEnergyOf(int energyUnits) {
        for (LandPlot plot: plotArray) {
            Player owner = createPlayerWithEnergyOf(energyUnits);
            plot.setOwner(owner);
        }
    }
    
    /**
     * Creates a Player object that will have 0 food and 0 ore, but will
     * have the specified amount of energy.  The name, race, color, and
     * difficulty, which are irrelevant for this test case, 
     * will be Name, Human, and Red, respectively.
     * 
     * @param energyUnits The energy units that the Player will have
     * @return The Player object with the specified energy units.
     */
    private Player createPlayerWithEnergyOf(int energyUnits) {
        Player newPlayer = new Player("Name", RaceType.HUMAN,
                                      Color.RED, Difficulty.BEGINNER);
        
        // make food, energy, ore become 0
        newPlayer.deductAll(Resource.FOOD);
        newPlayer.deductAll(Resource.ENERGY);
        newPlayer.deductAll(Resource.ORE);
        
        // add appropriate energy amount
        newPlayer.addResource(Resource.ENERGY, energyUnits);
        
        return newPlayer;
    }
    
    /**
     * Gives each plot a separate Mule object of the specified type.
     * 
     * @param type The type of Mule the plot should have.
     */
    private void assignPlotsMulesOfType(Resource type) {
        for (LandPlot plot:  plotArray) {
            Mule mule = new Mule(type);
            plot.setMule(mule);
        }
    }
    
    /**
     * Calls produce method on all land plots.
     */
    private void callProduceOnAllPlots() {
        for (LandPlot plot:  plotArray)
            plot.produce();
    }
    
    /**
     * Checks whether or not each of the land plot's lastAmountProduced value
     * is what was expected after production occured.
     * 
     * @return True if the expected value matches the actual value, 
     * false otherwise.
     */
    private boolean lastAmountProducedMatchesExpected() {
        for (int i = 0; i < plotArray.length; i++) {
            int actual = plotArray[i].getLastAmountProduced();
            int expected = expectedLastAmountProduced[i];
            if (actual != expected)
                return false;
        }
        return true;
    }
    
    /**
     * For each land plot, checks whether or not each of the owner's resource
     * counts matches the expected resource count after production is over.
     * 
     * @return
     */
    private boolean ownerResourceCountsMatchesExpected() {
        for (int i = 0; i < plotArray.length; i++) {
            Player owner = plotArray[i].getOwner();
            int actualFood = owner.getFood();
            int actualEnergy = owner.getEnergy();
            int actualOre = owner.getOre();
            
            int expectedFood = expectedOwnerFood[i];
            int expectedEnergy = expectedOwnerEnergy[i];
            int expectedOre = expectedOwnerOre[i];
            
            if (actualFood != expectedFood
                    || actualEnergy != expectedEnergy
                    || actualOre != expectedOre)
                return false;
        }
        return true;
    }

    @Test
    public void testProduceNoOwner() {
        callProduceOnAllPlots();

        expectedLastAmountProduced = new int[] {0, 0, 0, 0, 0, 0};
        
        /* Note:  no owner-related tests because there isn't one! */
        assertTrue(lastAmountProducedMatchesExpected());
    }
    
    @Test
    public void testProduceOwnerWithoutMuleWithPower() {
        assignPlotsOwnersWithEnergyOf(1);
        callProduceOnAllPlots();

        expectedOwnerFood = new int[] {0, 0, 0, 0, 0, 0};
        expectedOwnerEnergy = new int[] {1, 1, 1, 1, 1, 1};
        expectedOwnerOre= new int[] {0, 0, 0, 0, 0, 0};
        expectedLastAmountProduced = new int[] {0, 0, 0, 0, 0, 0};
        
        assertTrue(lastAmountProducedMatchesExpected());
        assertTrue(ownerResourceCountsMatchesExpected());
    }
    
    @Test
    public void testProduceOwnerWithoutMuleWithoutPower() {
        assignPlotsOwnersWithEnergyOf(0);
        callProduceOnAllPlots();

        expectedOwnerFood = new int[] {0, 0, 0, 0, 0, 0};
        expectedOwnerEnergy = new int[] {0, 0, 0, 0, 0, 0};
        expectedOwnerOre= new int[] {0, 0, 0, 0, 0, 0};
        expectedLastAmountProduced = new int[] {0, 0, 0, 0, 0, 0};
        
        assertTrue(lastAmountProducedMatchesExpected());
        assertTrue(ownerResourceCountsMatchesExpected());
    }
    
    @Test
    public void testProduceFoodMuleWithPower() {
        setupAndProduceOwnedPlotsWithMules(ONE_ENERGY, Resource.FOOD);

        expectedOwnerFood = new int[] {4, 2, 1, 1, 1, 0};
        expectedOwnerEnergy = new int[] {0, 0, 0, 0, 0, 1};
        expectedOwnerOre= new int[] {0, 0, 0, 0, 0, 0};
        expectedLastAmountProduced = new int[] {4, 2, 1, 1, 1, 0};
        
        assertTrue(lastAmountProducedMatchesExpected());
        assertTrue(ownerResourceCountsMatchesExpected());
    }
    
    @Test
    public void testProduceEnergyMuleWithPower() {
        setupAndProduceOwnedPlotsWithMules(ONE_ENERGY, Resource.ENERGY);

        /* Note:  energy mules will not consume energy! */
        expectedOwnerFood = new int[] {0, 0, 0, 0, 0, 0};
        expectedOwnerEnergy = new int[] {3, 4, 2, 2, 2, 1};
        expectedOwnerOre= new int[] {0, 0, 0, 0, 0, 0};
        expectedLastAmountProduced = new int[] {2, 3, 1, 1, 1, 0};
        
        assertTrue(lastAmountProducedMatchesExpected());
        assertTrue(ownerResourceCountsMatchesExpected());
    }
    
    @Test
    public void testProduceOreMuleWithPower() {
        setupAndProduceOwnedPlotsWithMules(ONE_ENERGY, Resource.ORE);

        expectedOwnerFood = new int[] {0, 0, 0, 0, 0, 0};
        expectedOwnerEnergy = new int[] {0, 0, 0, 0, 0, 1};
        expectedOwnerOre= new int[] {0, 1, 2, 3, 4, 0};
        expectedLastAmountProduced = new int[] {0, 1, 2, 3, 4, 0};
        
        assertTrue(lastAmountProducedMatchesExpected());
        assertTrue(ownerResourceCountsMatchesExpected());
    }
    
    @Test
    public void testProduceUnoutfittedMuleWithPower() {
        setupAndProduceOwnedPlotsWithMules(ONE_ENERGY, null);

        expectedOwnerFood = new int[] {0, 0, 0, 0, 0, 0};
        expectedOwnerEnergy = new int[] {1, 1, 1, 1, 1, 1};
        expectedOwnerOre= new int[] {0, 0, 0, 0, 0, 0};
        expectedLastAmountProduced = new int[] {0, 0, 0, 0, 0, 0};
        
        assertTrue(lastAmountProducedMatchesExpected());
        assertTrue(ownerResourceCountsMatchesExpected());
    }
    
    @Test
    public void testProduceFoodMuleWithoutPower() {
        setupAndProduceOwnedPlotsWithMules(NO_ENERGY, Resource.FOOD);

        expectedOwnerFood = new int[] {0, 0, 0, 0, 0, 0};
        expectedOwnerEnergy = new int[] {0, 0, 0, 0, 0, 0};
        expectedOwnerOre= new int[] {0, 0, 0, 0, 0, 0};
        expectedLastAmountProduced = new int[] {0, 0, 0, 0, 0, 0};
        
        assertTrue(lastAmountProducedMatchesExpected());
        assertTrue(ownerResourceCountsMatchesExpected());
    }
    
    @Test
    public void testProduceEnergyMuleWithoutPower() {
        setupAndProduceOwnedPlotsWithMules(NO_ENERGY, Resource.ENERGY);

        /* Energy mules do not need energy units from owner to produce. */
        expectedOwnerFood = new int[] {0, 0, 0, 0, 0, 0};
        expectedOwnerEnergy = new int[] {2, 3, 1, 1, 1, 0};
        expectedOwnerOre= new int[] {0, 0, 0, 0, 0, 0};
        expectedLastAmountProduced = new int[] {2, 3, 1, 1, 1, 0};
        
        assertTrue(lastAmountProducedMatchesExpected());
        assertTrue(ownerResourceCountsMatchesExpected());
    }
    
    @Test
    public void testProduceOreMuleWithoutPower() {
        setupAndProduceOwnedPlotsWithMules(NO_ENERGY, Resource.ORE);

        expectedOwnerFood = new int[] {0, 0, 0, 0, 0, 0};
        expectedOwnerEnergy = new int[] {0, 0, 0, 0, 0, 0};
        expectedOwnerOre= new int[] {0, 0, 0, 0, 0, 0};
        expectedLastAmountProduced = new int[] {0, 0, 0, 0, 0, 0};
        
        assertTrue(lastAmountProducedMatchesExpected());
        assertTrue(ownerResourceCountsMatchesExpected());
    }
    
    @Test
    public void testProduceUnoutfittedMuleWithoutPower() {
        setupAndProduceOwnedPlotsWithMules(NO_ENERGY, null);

        expectedOwnerFood = new int[] {0, 0, 0, 0, 0, 0};
        expectedOwnerEnergy = new int[] {0, 0, 0, 0, 0, 0};
        expectedOwnerOre= new int[] {0, 0, 0, 0, 0, 0};
        expectedLastAmountProduced = new int[] {0, 0, 0, 0, 0, 0};
        
        assertTrue(lastAmountProducedMatchesExpected());
        assertTrue(ownerResourceCountsMatchesExpected());
    }

}
