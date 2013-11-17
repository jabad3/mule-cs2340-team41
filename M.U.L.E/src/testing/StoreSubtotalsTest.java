package testing;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.JFrame;

import org.junit.Test;

import Models.Difficulty;
import Models.Mule;
import Models.Player;
import Models.RaceType;
import Models.Resource;
import Models.Store;
import Views.StorePanel;

public class StoreSubtotalsTest {
	@Test
	public void testBuyNothing() {
		Store store = new Store(Difficulty.BEGINNER);
		store.addResource(Resource.ORE, 10000);
		store.addResource(Resource.FOOD, 10000);
		store.addResource(Resource.ENERGY, 10000);
		store.addResource(Resource.MULE, 10000);
		
		Player player = new Player("Player", RaceType.BONZOID, Color.black, Difficulty.BEGINNER);
		player.addResource(Resource.MONEY, 10000);
		
		StorePanel storePanel = new StorePanel(store, player, null);
		
		storePanel.changeMode(true);
		
		storePanel.oreSpinner.setValue(0);
		storePanel.foodSpinner.setValue(0);
		storePanel.energySpinner.setValue(0);
		storePanel.muleTypeCombobox.setSelectedIndex(0);
		
		HashMap<Resource, Integer> resSubtotals = storePanel.getResSubtotals();
		
		assertEquals(0 * Store.orePrice, resSubtotals.get(Resource.ORE).intValue());
		assertEquals(0 * Store.foodPrice, resSubtotals.get(Resource.FOOD).intValue());
		assertEquals(0 * Store.energyPrice, resSubtotals.get(Resource.ENERGY).intValue());
		assertEquals(0 * Store.mulePrice + 0, resSubtotals.get(Resource.MULE).intValue());
	}
	
	@Test
	public void testBuyOre() {
		Store store = new Store(Difficulty.BEGINNER);
		store.addResource(Resource.ORE, 10000);
		store.addResource(Resource.FOOD, 10000);
		store.addResource(Resource.ENERGY, 10000);
		store.addResource(Resource.MULE, 10000);
		
		Player player = new Player("Player", RaceType.BONZOID, Color.black, Difficulty.BEGINNER);
		player.addResource(Resource.MONEY, 10000);
		
		StorePanel storePanel = new StorePanel(store, player, null);
		
		storePanel.changeMode(true);
		
		storePanel.oreSpinner.setValue(10);
		storePanel.foodSpinner.setValue(0);
		storePanel.energySpinner.setValue(0);
		storePanel.muleTypeCombobox.setSelectedIndex(0);
		
		HashMap<Resource, Integer> resSubtotals = storePanel.getResSubtotals();
		
		assertEquals(10 * Store.orePrice, resSubtotals.get(Resource.ORE).intValue());
		assertEquals(0 * Store.foodPrice, resSubtotals.get(Resource.FOOD).intValue());
		assertEquals(0 * Store.energyPrice, resSubtotals.get(Resource.ENERGY).intValue());
		assertEquals(0 * Store.mulePrice + 0, resSubtotals.get(Resource.MULE).intValue());
	}
	
	@Test
	public void testBuyOreAndEnergy() {
		Store store = new Store(Difficulty.BEGINNER);
		store.addResource(Resource.ORE, 10000);
		store.addResource(Resource.FOOD, 10000);
		store.addResource(Resource.ENERGY, 10000);
		store.addResource(Resource.MULE, 10000);
		
		Player player = new Player("Player", RaceType.BONZOID, Color.black, Difficulty.BEGINNER);
		player.addResource(Resource.MONEY, 10000);
		
		StorePanel storePanel = new StorePanel(store, player, null);
		
		storePanel.changeMode(true);
		
		storePanel.oreSpinner.setValue(10);
		storePanel.foodSpinner.setValue(0);
		storePanel.energySpinner.setValue(7);
		storePanel.muleTypeCombobox.setSelectedIndex(0);
		
		HashMap<Resource, Integer> resSubtotals = storePanel.getResSubtotals();
		
		assertEquals(10 * Store.orePrice, resSubtotals.get(Resource.ORE).intValue());
		assertEquals(0 * Store.foodPrice, resSubtotals.get(Resource.FOOD).intValue());
		assertEquals(7 * Store.energyPrice, resSubtotals.get(Resource.ENERGY).intValue());
		assertEquals(0 * Store.mulePrice + 0, resSubtotals.get(Resource.MULE).intValue());
	}
	
	@Test
	public void testBuyFoodMule() {
		Store store = new Store(Difficulty.BEGINNER);
		store.addResource(Resource.ORE, 10000);
		store.addResource(Resource.FOOD, 10000);
		store.addResource(Resource.ENERGY, 10000);
		store.addResource(Resource.MULE, 10000);
		
		Player player = new Player("Player", RaceType.BONZOID, Color.black, Difficulty.BEGINNER);
		player.addResource(Resource.MONEY, 10000);
		
		StorePanel storePanel = new StorePanel(store, player, null);
		
		storePanel.changeMode(true);
		
		storePanel.oreSpinner.setValue(0);
		storePanel.foodSpinner.setValue(0);
		storePanel.energySpinner.setValue(0);
		storePanel.muleTypeCombobox.setSelectedItem("Food");
		
		HashMap<Resource, Integer> resSubtotals = storePanel.getResSubtotals();
		
		assertEquals(0 * Store.orePrice, resSubtotals.get(Resource.ORE).intValue());
		assertEquals(0 * Store.foodPrice, resSubtotals.get(Resource.FOOD).intValue());
		assertEquals(0 * Store.energyPrice, resSubtotals.get(Resource.ENERGY).intValue());
		assertEquals(1 * Store.mulePrice + Resource.FOOD.getMuleTypeScore(), resSubtotals.get(Resource.MULE).intValue());
	}
	
	@Test
	public void testBuyEnergyMuleAndFood() {
		Store store = new Store(Difficulty.BEGINNER);
		store.addResource(Resource.ORE, 10000);
		store.addResource(Resource.FOOD, 10000);
		store.addResource(Resource.ENERGY, 10000);
		store.addResource(Resource.MULE, 10000);
		
		Player player = new Player("Player", RaceType.BONZOID, Color.black, Difficulty.BEGINNER);
		player.addResource(Resource.MONEY, 10000);
		
		StorePanel storePanel = new StorePanel(store, player, null);
		
		storePanel.changeMode(true);
		
		storePanel.oreSpinner.setValue(0);
		storePanel.foodSpinner.setValue(1);
		storePanel.energySpinner.setValue(0);
		storePanel.muleTypeCombobox.setSelectedItem("Energy");
		
		HashMap<Resource, Integer> resSubtotals = storePanel.getResSubtotals();
		
		assertEquals(0 * Store.orePrice, resSubtotals.get(Resource.ORE).intValue());
		assertEquals(1 * Store.foodPrice, resSubtotals.get(Resource.FOOD).intValue());
		assertEquals(0 * Store.energyPrice, resSubtotals.get(Resource.ENERGY).intValue());
		assertEquals(1 * Store.mulePrice + Resource.ENERGY.getMuleTypeScore(), resSubtotals.get(Resource.MULE).intValue());
	}
	
	@Test
	public void testBuyOreMuleAndEverything() {
		Store store = new Store(Difficulty.BEGINNER);
		store.addResource(Resource.ORE, 10000);
		store.addResource(Resource.FOOD, 10000);
		store.addResource(Resource.ENERGY, 10000);
		store.addResource(Resource.MULE, 10000);
		
		Player player = new Player("Player", RaceType.BONZOID, Color.black, Difficulty.BEGINNER);
		player.addResource(Resource.MONEY, 10000);
		
		StorePanel storePanel = new StorePanel(store, player, null);
		
		storePanel.changeMode(true);
		
		storePanel.oreSpinner.setValue(2);
		storePanel.foodSpinner.setValue(132);
		storePanel.energySpinner.setValue(4);
		storePanel.muleTypeCombobox.setSelectedItem("Ore");
		
		HashMap<Resource, Integer> resSubtotals = storePanel.getResSubtotals();
		
		assertEquals(2 * Store.orePrice, resSubtotals.get(Resource.ORE).intValue());
		assertEquals(132 * Store.foodPrice, resSubtotals.get(Resource.FOOD).intValue());
		assertEquals(4 * Store.energyPrice, resSubtotals.get(Resource.ENERGY).intValue());
		assertEquals(1 * Store.mulePrice + Resource.ORE.getMuleTypeScore(), resSubtotals.get(Resource.MULE).intValue());
	}
	
	@Test
	public void testSellNothing() {
		Store store = new Store(Difficulty.BEGINNER);
		
		Player player = new Player("Player", RaceType.BONZOID, Color.black, Difficulty.BEGINNER);
		player.addResource(Resource.MONEY, 10000);
		player.addResource(Resource.ORE, 10000);
		player.addResource(Resource.FOOD, 10000);
		player.addResource(Resource.ENERGY, 10000);
		
		StorePanel storePanel = new StorePanel(store, player, null);
		
		storePanel.changeMode(false);
		
		storePanel.oreSpinner.setValue(0);
		storePanel.foodSpinner.setValue(0);
		storePanel.energySpinner.setValue(0);
		storePanel.muleTypeCombobox.setSelectedIndex(0);
		
		HashMap<Resource, Integer> resSubtotals = storePanel.getResSubtotals();
		
		assertEquals(0 * Store.orePrice, resSubtotals.get(Resource.ORE).intValue());
		assertEquals(0 * Store.foodPrice, resSubtotals.get(Resource.FOOD).intValue());
		assertEquals(0 * Store.energyPrice, resSubtotals.get(Resource.ENERGY).intValue());
		assertEquals(0 * Store.mulePrice + 0, resSubtotals.get(Resource.MULE).intValue());
	}
	
	@Test
	public void testSellOre() {
		Store store = new Store(Difficulty.BEGINNER);
		
		Player player = new Player("Player", RaceType.BONZOID, Color.black, Difficulty.BEGINNER);
		player.addResource(Resource.MONEY, 10000);
		player.addResource(Resource.ORE, 10000);
		player.addResource(Resource.FOOD, 10000);
		player.addResource(Resource.ENERGY, 10000);
		
		StorePanel storePanel = new StorePanel(store, player, null);
		
		storePanel.changeMode(false);
		
		storePanel.oreSpinner.setValue(10);
		storePanel.foodSpinner.setValue(0);
		storePanel.energySpinner.setValue(0);
		storePanel.muleTypeCombobox.setSelectedIndex(0);
		
		HashMap<Resource, Integer> resSubtotals = storePanel.getResSubtotals();
		
		assertEquals(10 * Store.orePrice, resSubtotals.get(Resource.ORE).intValue());
		assertEquals(0 * Store.foodPrice, resSubtotals.get(Resource.FOOD).intValue());
		assertEquals(0 * Store.energyPrice, resSubtotals.get(Resource.ENERGY).intValue());
		assertEquals(0 * Store.mulePrice + 0, resSubtotals.get(Resource.MULE).intValue());
	}
	
	@Test
	public void testSellOreAndEnergy() {
		Store store = new Store(Difficulty.BEGINNER);
		
		Player player = new Player("Player", RaceType.BONZOID, Color.black, Difficulty.BEGINNER);
		player.addResource(Resource.MONEY, 10000);
		player.addResource(Resource.ORE, 10000);
		player.addResource(Resource.FOOD, 10000);
		player.addResource(Resource.ENERGY, 10000);
		
		StorePanel storePanel = new StorePanel(store, player, null);
		
		storePanel.changeMode(false);
		
		storePanel.oreSpinner.setValue(10);
		storePanel.foodSpinner.setValue(0);
		storePanel.energySpinner.setValue(7);
		storePanel.muleTypeCombobox.setSelectedIndex(0);
		
		HashMap<Resource, Integer> resSubtotals = storePanel.getResSubtotals();
		
		assertEquals(10 * Store.orePrice, resSubtotals.get(Resource.ORE).intValue());
		assertEquals(0 * Store.foodPrice, resSubtotals.get(Resource.FOOD).intValue());
		assertEquals(7 * Store.energyPrice, resSubtotals.get(Resource.ENERGY).intValue());
		assertEquals(0 * Store.mulePrice + 0, resSubtotals.get(Resource.MULE).intValue());
	}
	
	@Test
	public void testSellFoodMule() {
		Store store = new Store(Difficulty.BEGINNER);
		
		Player player = new Player("Player", RaceType.BONZOID, Color.black, Difficulty.BEGINNER);
		player.addResource(Resource.MONEY, 10000);
		player.addResource(Resource.ORE, 10000);
		player.addResource(Resource.FOOD, 10000);
		player.addResource(Resource.ENERGY, 10000);
		
		player.setMule(new Mule(Resource.FOOD));
		
		StorePanel storePanel = new StorePanel(store, player, null);
		
		storePanel.changeMode(false);
		
		storePanel.oreSpinner.setValue(0);
		storePanel.foodSpinner.setValue(0);
		storePanel.energySpinner.setValue(0);
		storePanel.muleTypeCombobox.setSelectedItem("Sell");
		
		HashMap<Resource, Integer> resSubtotals = storePanel.getResSubtotals();
		
		assertEquals(0 * Store.orePrice, resSubtotals.get(Resource.ORE).intValue());
		assertEquals(0 * Store.foodPrice, resSubtotals.get(Resource.FOOD).intValue());
		assertEquals(0 * Store.energyPrice, resSubtotals.get(Resource.ENERGY).intValue());
		assertEquals(1 * Store.mulePrice + Resource.FOOD.getMuleTypeScore(), resSubtotals.get(Resource.MULE).intValue());
	}
	
	@Test
	public void testSellEnergyMuleAndFood() {
		Store store = new Store(Difficulty.BEGINNER);
		
		Player player = new Player("Player", RaceType.BONZOID, Color.black, Difficulty.BEGINNER);
		player.addResource(Resource.MONEY, 10000);
		player.addResource(Resource.ORE, 10000);
		player.addResource(Resource.FOOD, 10000);
		player.addResource(Resource.ENERGY, 10000);
		
		player.setMule(new Mule(Resource.ENERGY));
		
		StorePanel storePanel = new StorePanel(store, player, null);
		
		storePanel.changeMode(false);
		
		storePanel.oreSpinner.setValue(0);
		storePanel.foodSpinner.setValue(1);
		storePanel.energySpinner.setValue(0);
		storePanel.muleTypeCombobox.setSelectedItem("Sell");
		
		HashMap<Resource, Integer> resSubtotals = storePanel.getResSubtotals();
		
		assertEquals(0 * Store.orePrice, resSubtotals.get(Resource.ORE).intValue());
		assertEquals(1 * Store.foodPrice, resSubtotals.get(Resource.FOOD).intValue());
		assertEquals(0 * Store.energyPrice, resSubtotals.get(Resource.ENERGY).intValue());
		assertEquals(1 * Store.mulePrice + Resource.ENERGY.getMuleTypeScore(), resSubtotals.get(Resource.MULE).intValue());
	}
	
	@Test
	public void testSellOreMuleAndEverything() {
		Store store = new Store(Difficulty.BEGINNER);
		
		Player player = new Player("Player", RaceType.BONZOID, Color.black, Difficulty.BEGINNER);
		player.addResource(Resource.MONEY, 10000);
		player.addResource(Resource.ORE, 10000);
		player.addResource(Resource.FOOD, 10000);
		player.addResource(Resource.ENERGY, 10000);
		
		player.setMule(new Mule(Resource.ORE));
		
		StorePanel storePanel = new StorePanel(store, player, null);
		
		storePanel.changeMode(false);
		
		storePanel.oreSpinner.setValue(2);
		storePanel.foodSpinner.setValue(132);
		storePanel.energySpinner.setValue(4);
		storePanel.muleTypeCombobox.setSelectedItem("Sell");
		
		HashMap<Resource, Integer> resSubtotals = storePanel.getResSubtotals();
		
		assertEquals(2 * Store.orePrice, resSubtotals.get(Resource.ORE).intValue());
		assertEquals(132 * Store.foodPrice, resSubtotals.get(Resource.FOOD).intValue());
		assertEquals(4 * Store.energyPrice, resSubtotals.get(Resource.ENERGY).intValue());
		assertEquals(1 * Store.mulePrice + Resource.ORE.getMuleTypeScore(), resSubtotals.get(Resource.MULE).intValue());
	}
}
