package testing;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import Models.Difficulty;
import Models.GameModel;
import Models.Player;
import Models.RaceType;
import Models.Resource;

/**
 * These are the tests for the method CalculateTurnTime in the class GameModel
 *
 * @author Erica, epramer3
 */
public class GameModelTest {
	private Color b = Color.blue;
	private Player more = new Player("Bob", RaceType.BONZOID, b, Difficulty.STANDARD);
	private Player less = new Player("Bob", RaceType.BONZOID, b, Difficulty.STANDARD);
	private Player equals = new Player("Bob", RaceType.BONZOID, b, Difficulty.STANDARD);
	private Player none = new Player("Bob", RaceType.BONZOID, b, Difficulty.STANDARD);
	
	@Test
	public void testRound1() {
		GameModel model = new GameModel();
		model.incrementRound();
		
		more.deductAll(Resource.FOOD);
		less.deductAll(Resource.FOOD);
		equals.deductAll(Resource.FOOD);
		none.deductAll(Resource.FOOD);
		
		more.addResource(Resource.FOOD, 3+1);
		less.addResource(Resource.FOOD, 3-1);
		equals.addResource(Resource.FOOD, 3);

		assertEquals(50000,model.calculateTurnTime(more));
		assertEquals(50000,model.calculateTurnTime(equals));
		assertEquals(30000,model.calculateTurnTime(less));
		assertEquals(5000,model.calculateTurnTime(none));
	}
	
	@Test
	public void testRound2() {
		GameModel model = new GameModel();
		for(int i = 1; i <3; i++)
			model.incrementRound();
		
		more.deductAll(Resource.FOOD);
		less.deductAll(Resource.FOOD);
		equals.deductAll(Resource.FOOD);
		none.deductAll(Resource.FOOD);
		
		more.addResource(Resource.FOOD, 3+1);
		less.addResource(Resource.FOOD, 3-1);
		equals.addResource(Resource.FOOD, 3);

		assertEquals(50000,model.calculateTurnTime(more));
		assertEquals(50000,model.calculateTurnTime(equals));
		assertEquals(30000,model.calculateTurnTime(less));
		assertEquals(5000,model.calculateTurnTime(none));
	}
	
	@Test
	public void testRound3() {
		GameModel model = new GameModel();
		for(int i = 1; i <4; i++)
			model.incrementRound();
		
		more.deductAll(Resource.FOOD);
		less.deductAll(Resource.FOOD);
		equals.deductAll(Resource.FOOD);
		none.deductAll(Resource.FOOD);
		
		more.addResource(Resource.FOOD, 3+1);
		less.addResource(Resource.FOOD, 3-1);
		equals.addResource(Resource.FOOD, 3);

		assertEquals(50000,model.calculateTurnTime(more));
		assertEquals(50000,model.calculateTurnTime(equals));
		assertEquals(30000,model.calculateTurnTime(less));
		assertEquals(5000,model.calculateTurnTime(none));
	}
	@Test
	public void testRound4() {
		GameModel model = new GameModel();
		for(int i = 1; i <5; i++)
			model.incrementRound();
		
		more.deductAll(Resource.FOOD);
		less.deductAll(Resource.FOOD);
		equals.deductAll(Resource.FOOD);
		none.deductAll(Resource.FOOD);
		
		more.addResource(Resource.FOOD, 3+1);
		less.addResource(Resource.FOOD, 3-1);
		equals.addResource(Resource.FOOD, 3);

		assertEquals(50000,model.calculateTurnTime(more));
		assertEquals(50000,model.calculateTurnTime(equals));
		assertEquals(30000,model.calculateTurnTime(less));
		assertEquals(5000,model.calculateTurnTime(none));
	}
	@Test
	public void testRound5() {
		GameModel model = new GameModel();
		for(int i = 1; i <6; i++)
			model.incrementRound();
		
		more.deductAll(Resource.FOOD);
		less.deductAll(Resource.FOOD);
		equals.deductAll(Resource.FOOD);
		none.deductAll(Resource.FOOD);
		
		more.addResource(Resource.FOOD, 4+1);
		less.addResource(Resource.FOOD, 4-1);
		equals.addResource(Resource.FOOD, 4);

		assertEquals(50000,model.calculateTurnTime(more));
		assertEquals(50000,model.calculateTurnTime(equals));
		assertEquals(30000,model.calculateTurnTime(less));
		assertEquals(5000,model.calculateTurnTime(none));
		
	}
	@Test
	public void testRound6() {
		GameModel model = new GameModel();
		for(int i = 1; i <7; i++)
			model.incrementRound();
		
		more.deductAll(Resource.FOOD);
		less.deductAll(Resource.FOOD);
		equals.deductAll(Resource.FOOD);
		none.deductAll(Resource.FOOD);
		
		more.addResource(Resource.FOOD, 4+1);
		less.addResource(Resource.FOOD, 4-1);
		equals.addResource(Resource.FOOD, 4);

		assertEquals(50000,model.calculateTurnTime(more));
		assertEquals(50000,model.calculateTurnTime(equals));
		assertEquals(30000,model.calculateTurnTime(less));
		assertEquals(5000,model.calculateTurnTime(none));
	}
	@Test
	public void testRound7() {
		GameModel model = new GameModel();
		for(int i = 1; i <8; i++)
			model.incrementRound();
		
		more.deductAll(Resource.FOOD);
		less.deductAll(Resource.FOOD);
		equals.deductAll(Resource.FOOD);
		none.deductAll(Resource.FOOD);
		
		more.addResource(Resource.FOOD, 4+1);
		less.addResource(Resource.FOOD, 4-1);
		equals.addResource(Resource.FOOD, 4);

		assertEquals(50000,model.calculateTurnTime(more));
		assertEquals(50000,model.calculateTurnTime(equals));
		assertEquals(30000,model.calculateTurnTime(less));
		assertEquals(5000,model.calculateTurnTime(none));
	}
	@Test
	public void testRound8() {
		GameModel model = new GameModel();
		for(int i = 1; i <9; i++)
			model.incrementRound();
		
		more.deductAll(Resource.FOOD);
		less.deductAll(Resource.FOOD);
		equals.deductAll(Resource.FOOD);
		none.deductAll(Resource.FOOD);
		
		more.addResource(Resource.FOOD, 4+1);
		less.addResource(Resource.FOOD, 4-1);
		equals.addResource(Resource.FOOD, 4);

		assertEquals(50000,model.calculateTurnTime(more));
		assertEquals(50000,model.calculateTurnTime(equals));
		assertEquals(30000,model.calculateTurnTime(less));
		assertEquals(5000,model.calculateTurnTime(none));
	}
	@Test
	public void testRound9() {
		GameModel model = new GameModel();
		for(int i = 1; i <10; i++)
			model.incrementRound();
		
		more.deductAll(Resource.FOOD);
		less.deductAll(Resource.FOOD);
		equals.deductAll(Resource.FOOD);
		none.deductAll(Resource.FOOD);
		
		more.addResource(Resource.FOOD, 5+1);
		less.addResource(Resource.FOOD, 5-1);
		equals.addResource(Resource.FOOD, 5);

		assertEquals(50000,model.calculateTurnTime(more));
		assertEquals(50000,model.calculateTurnTime(equals));
		assertEquals(30000,model.calculateTurnTime(less));
		assertEquals(5000,model.calculateTurnTime(none));
	}
	@Test
	public void testRound10() {
		GameModel model = new GameModel();
		for(int i = 1; i <11; i++)
			model.incrementRound();
		
		more.deductAll(Resource.FOOD);
		less.deductAll(Resource.FOOD);
		equals.deductAll(Resource.FOOD);
		none.deductAll(Resource.FOOD);
		
		more.addResource(Resource.FOOD, 5+1);
		less.addResource(Resource.FOOD, 5-1);
		equals.addResource(Resource.FOOD, 5);

		assertEquals(50000,model.calculateTurnTime(more));
		assertEquals(50000,model.calculateTurnTime(equals));
		assertEquals(30000,model.calculateTurnTime(less));
		assertEquals(5000,model.calculateTurnTime(none));
	}
	@Test
	public void testRound11() {
		GameModel model = new GameModel();
		for(int i = 1; i <12; i++)
			model.incrementRound();
		
		more.deductAll(Resource.FOOD);
		less.deductAll(Resource.FOOD);
		equals.deductAll(Resource.FOOD);
		none.deductAll(Resource.FOOD);
		
		more.addResource(Resource.FOOD, 5+1);
		less.addResource(Resource.FOOD, 5-1);
		equals.addResource(Resource.FOOD, 5);

		assertEquals(50000,model.calculateTurnTime(more));
		assertEquals(50000,model.calculateTurnTime(equals));
		assertEquals(30000,model.calculateTurnTime(less));
		assertEquals(5000,model.calculateTurnTime(none));
	}
	
	@Test
	public void testRound12() {
		GameModel model = new GameModel();
		for(int i = 1; i <13; i++)
			model.incrementRound();
		
		more.deductAll(Resource.FOOD);
		less.deductAll(Resource.FOOD);
		equals.deductAll(Resource.FOOD);
		none.deductAll(Resource.FOOD);
		
		more.addResource(Resource.FOOD, 5+1);
		less.addResource(Resource.FOOD, 5-1);
		equals.addResource(Resource.FOOD, 5);

		assertEquals(50000,model.calculateTurnTime(more));
		assertEquals(50000,model.calculateTurnTime(equals));
		assertEquals(30000,model.calculateTurnTime(less));
		assertEquals(5000,model.calculateTurnTime(none));
	}

}
