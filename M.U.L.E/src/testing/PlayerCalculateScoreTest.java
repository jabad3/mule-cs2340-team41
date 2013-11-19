package testing;

import static org.junit.Assert.*;

import java.awt.Color;

import Models.Difficulty;
import Models.FailedTransactionException;
import Models.LandPlot;
import Models.LandPlotType;
import Models.Mule;
import Models.Player;
import Models.RaceType;
import Models.Resource;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class responsible for testing the calculateScore() method 
 * in the Player class, under various conditions.
 * 
 * @author kevin
 *
 */
public class PlayerCalculateScoreTest {

	/** a Player used for testing */
	Player testPlayer1;
	
	/** a Player used for testing */
	Player testPlayer2;

	/** a Player used for testing */
	Player testPlayer3;
	
	/**
	 * Performs initial setup for tests, instantiating Players and LandPlots
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		testPlayer1 = new Player("testPlayer", RaceType.HUMAN, Color.RED,Difficulty.BEGINNER); 
		
		LandPlot mtn1Plot = new LandPlot(LandPlotType.MTN_1);
		testPlayer1.addLandPlot(mtn1Plot);
		mtn1Plot.setOwner(testPlayer1);
		
		LandPlot mtn2Plot = new LandPlot(LandPlotType.MTN_2);
		testPlayer1.addLandPlot(mtn2Plot);
		mtn2Plot.setOwner(testPlayer1);
		
		LandPlot mtn3Plot = new LandPlot(LandPlotType.MTN_3);
		testPlayer1.addLandPlot(mtn3Plot);
		mtn3Plot.setOwner(testPlayer1);
		
		LandPlot plainPlot = new LandPlot(LandPlotType.PLAIN);
		testPlayer1.addLandPlot(plainPlot);
		plainPlot.setOwner(testPlayer1);
		
		LandPlot riverPlot = new LandPlot(LandPlotType.RIVER);
		testPlayer1.addLandPlot(riverPlot);
		riverPlot.setOwner(testPlayer1);
		
		testPlayer2 = new Player("testPlayer2", RaceType.HUMAN, Color.RED, Difficulty.BEGINNER);
	}

	/**
	 * Tests testsPlayer1's scoring with a set of empty LandPlots, 
	 * with no Mules.
	 * 
	 */
	@Test
	public void testWithEmptyLandPlots() {
		assertTrue(testPlayer1.calculateScore() == 3440); 
	}
	
	/**
	 * Tests scoring on a player "muleTester" that owns LandPlots occupied by Mules
	 * 
	 */
	@Test
	public void testWithMules() {
		Player muleTester = new Player("muleTester", RaceType.HUMAN, Color.RED, Difficulty.BEGINNER);
		
		LandPlot mulePlot1 = new LandPlot(LandPlotType.MTN_1);
		muleTester.addLandPlot(mulePlot1);
		mulePlot1.setMule(new Mule(Resource.ORE));
		assertTrue(muleTester.calculateScore() == 1515);
		
		LandPlot mulePlot2 = new LandPlot(LandPlotType.MTN_2);
		muleTester.addLandPlot(mulePlot2);
		mulePlot2.setMule(new Mule(Resource.ENERGY));
		assertTrue(muleTester.calculateScore() == 2065);
		
		LandPlot mulePlot3 = new LandPlot(LandPlotType.PLAIN);
		muleTester.addLandPlot(mulePlot3);
		mulePlot3.setMule(new Mule(Resource.FOOD));
		assertTrue(muleTester.calculateScore() == 2590);
	}
	
	/**
	 * Test case: tests scoring on brand new Players of each race, with Beginner
	 * difficulty 
	 *   
	 *
	 * @throws FailedTransactionException
	 */
	@Test
	public void testWithBrandNewPlayer() throws FailedTransactionException {
		Player testPlayer3 = new Player("testPlayer3", RaceType.HUMAN, Color.RED, Difficulty.BEGINNER);
		assertTrue(testPlayer3.calculateScore() == 940);  
		
		Player testHuman = new Player("testHuman", RaceType.HUMAN, Color.RED, Difficulty.BEGINNER);
		assertTrue(testHuman.calculateScore() == 940);
		
		Player testBonzoid = new Player("testHuman", RaceType.BONZOID, Color.RED, Difficulty.BEGINNER);
		assertTrue(testBonzoid.calculateScore() == 1340);
		
		Player testBuzzite = new Player("testHuman", RaceType.BUZZITE, Color.RED, Difficulty.BEGINNER);
		assertTrue(testBuzzite.calculateScore() == 1340);
		
		Player testUgaite = new Player("testHuman", RaceType.UGAITE, Color.RED, Difficulty.BEGINNER);
		assertTrue(testUgaite.calculateScore() == 1340);
		
		Player testFlapper = new Player("testHuman", RaceType.FLAPPER, Color.RED, Difficulty.BEGINNER);
		assertTrue(testFlapper.calculateScore() == 1940);
	}
	
	/**
	 * Test case: tests scoring on testPlayer1 while manipulating
	 * its inventory resource contents
	 * 
	 * @throws FailedTransactionException
	 */
	@Test
	public void testWithInventory() throws FailedTransactionException {
		testPlayer1.addResource(Resource.ENERGY, 10);
		assertTrue(testPlayer1.calculateScore() == 3690); 
		
		testPlayer1.addResource(Resource.ORE, 10);
		assertTrue(testPlayer1.calculateScore() == 4190);
		
		testPlayer1.addResource(Resource.FOOD, 10);
		assertTrue(testPlayer1.calculateScore() == 4490);
		
		testPlayer1.removeResource(Resource.ENERGY, 14);
		testPlayer1.removeResource(Resource.ORE, 10);
		testPlayer1.removeResource(Resource.FOOD, 18);
		assertTrue(testPlayer1.calculateScore() == 3100);
	}
	
	

}
