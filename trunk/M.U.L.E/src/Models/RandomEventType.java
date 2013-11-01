package Models;

import java.util.HashMap;

/*
notes: 27% chance a random event will happen at beginning of round
bad events (Catbugs,Uga,Mess) cannot happen to player with lowest score
YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.
A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.
THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $ 8*m.
YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $2*m.
FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $4*m.
MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.
YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $6*m TO CLEAN IT UP.
 */
public enum RandomEventType {
	ALUMNI, TRAVELER, MUSEUM, RAT, CATBUGS, UGA, MESS;
	
	/** The numerical value m for each round. */
	private java.util.Map<Integer,Integer> mValues;
	
	/** 
	 * Build a Map with mvalues (values) for each round (keys). 
	 */
	public void buildmValues() {
		mValues = new HashMap<Integer,Integer>();
		mValues.put(1, 25);
		mValues.put(2, 25);
		mValues.put(3, 25);
		mValues.put(4, 50);
		mValues.put(5, 50);
		mValues.put(6, 50);
		mValues.put(7, 50);
		mValues.put(8, 75);
		mValues.put(9, 75);
		mValues.put(10, 75);
		mValues.put(11, 75);
		mValues.put(12, 100);
	}
}
