package cocson.lawrence;

import java.util.Random;

/**
 * Representation of an n sided die
 */

public class Die {
	private int value;
	private int nSides;
	
	Die(int sides){
		nSides = sides;
		roll();
		value = getValue();
	}
	
	/**
	 * Simulate a single die roll
	 */
	
	public int roll() {
		Random rand = new Random ();
		value = rand.nextInt(1,nSides+1);
		
		return value;
	}
	
	/**
	 * Provide face value of die
	 * @return Current face value
	 */
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return ("Die has " + nSides + " sides\n" + "Value is " + value);
	}

}
