package testing;

import static org.junit.Assert.*;

import java.util.EnumMap;

import org.junit.Before;
import org.junit.Test;

import Models.Inventory;
import Models.Resource;

public class InventoryTest {
	Inventory i;
	@Before
	public void setUp() throws Exception {
		i = new Inventory(10,10,10,10,10);
	}

	@Test
	public void testInventory() {
        assertTrue(i.getResourceCount(Resource.FOOD) == 10);
        assertTrue(i.getResourceCount(Resource.ENERGY) == 10);
        assertTrue(i.getResourceCount(Resource.ORE) == 10);
        assertTrue(i.getResourceCount(Resource.MONEY) == 10);
        assertTrue(i.getResourceCount(Resource.MULE) == 10);
	}

	@Test
	public void testRemoveResource1() {
		i.removeResource(Resource.FOOD, 5);
		assertTrue(i.getResourceCount(Resource.FOOD)==5);
        assertTrue(i.getResourceCount(Resource.ENERGY) == 10);
        assertTrue(i.getResourceCount(Resource.ORE) == 10);
        assertTrue(i.getResourceCount(Resource.MONEY) == 10);
        assertTrue(i.getResourceCount(Resource.MULE) == 10);
	}
	
	@Test
	public void testRemoveResource2() {
		i.removeResource(Resource.FOOD, 10);
		assertTrue(i.getResourceCount(Resource.FOOD)==0);
        assertTrue(i.getResourceCount(Resource.ENERGY) == 10);
        assertTrue(i.getResourceCount(Resource.ORE) == 10);
        assertTrue(i.getResourceCount(Resource.MONEY) == 10);
        assertTrue(i.getResourceCount(Resource.MULE) == 10);
	}
	
	//Preconditions exit, but still..., shouldn't we enforce postconditions?
	@Test
	public void testRemoveResource3() {
		i.removeResource(Resource.FOOD, 11);
		assertTrue(i.getResourceCount(Resource.FOOD)==-1);
        assertTrue(i.getResourceCount(Resource.ENERGY) == 10);
        assertTrue(i.getResourceCount(Resource.ORE) == 10);
        assertTrue(i.getResourceCount(Resource.MONEY) == 10);
        assertTrue(i.getResourceCount(Resource.MULE) == 10);
	}
	
	@Test
	public void testDeductAll() {
		i.deductAll(Resource.ORE);
		assertTrue(i.getResourceCount(Resource.ORE)==0);
        assertTrue(i.getResourceCount(Resource.FOOD) == 10);
        assertTrue(i.getResourceCount(Resource.ENERGY) == 10);
        assertTrue(i.getResourceCount(Resource.MONEY) == 10);
        assertTrue(i.getResourceCount(Resource.MULE) == 10);
	}

	@Test
	public void testAddResource1() {
		i.addResource(Resource.FOOD, 2);
		assertTrue(i.getResourceCount(Resource.FOOD)==12);
	}
	
	@Test
	public void testAddResource2() {
		i.addResource(Resource.FOOD, 10000000);
		assertTrue(i.getResourceCount(Resource.FOOD)==10000010);
        assertTrue(i.getResourceCount(Resource.ENERGY) == 10);
        assertTrue(i.getResourceCount(Resource.ORE) == 10);
        assertTrue(i.getResourceCount(Resource.MONEY) == 10);
        assertTrue(i.getResourceCount(Resource.MULE) == 10);
	}

	@Test
	public void testGetResourceCount() {
		assertTrue(i.getResourceCount(Resource.FOOD)==10);
        assertTrue(i.getResourceCount(Resource.ENERGY) == 10);
        assertTrue(i.getResourceCount(Resource.ORE) == 10);
        assertTrue(i.getResourceCount(Resource.MONEY) == 10);
        assertTrue(i.getResourceCount(Resource.MULE) == 10);
	}

	@Test
	public void testWithdrawMoney() {
		i.withdrawMoney(5);
		assertTrue(i.getResourceCount(Resource.MONEY)==5);
        assertTrue(i.getResourceCount(Resource.FOOD) == 10);
        assertTrue(i.getResourceCount(Resource.ENERGY) == 10);
        assertTrue(i.getResourceCount(Resource.ORE) == 10);
        assertTrue(i.getResourceCount(Resource.MULE) == 10);
	}

	@Test
	public void testDepositMoney() {
		i.depositMoney(600);
		assertTrue(i.getResourceCount(Resource.MONEY)==610);
        assertTrue(i.getResourceCount(Resource.FOOD) == 10);
        assertTrue(i.getResourceCount(Resource.ENERGY) == 10);
        assertTrue(i.getResourceCount(Resource.ORE) == 10);
        assertTrue(i.getResourceCount(Resource.MULE) == 10);
	}

	@Test
	public void testCalculateScore() {
		assertTrue(i.calculateScore()==1060);
        assertTrue(i.getResourceCount(Resource.FOOD) == 10);
        assertTrue(i.getResourceCount(Resource.ENERGY) == 10);
        assertTrue(i.getResourceCount(Resource.ORE) == 10);
        assertTrue(i.getResourceCount(Resource.MONEY) == 10);
        assertTrue(i.getResourceCount(Resource.MULE) == 10);
	}
}
