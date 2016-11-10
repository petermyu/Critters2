

package assignment5;

import javafx.scene.paint.Color;

/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Peter Yu>
 * <pmy89>
 * <16455>
 * <Christopher Ong>
 * <cio247>
 * <16445>
 * Slip days used: <0>
 * Fall 2016
 */
/*
 * Critter3:
 * When instantiated, Critter4 is given a random direction
 * Overrides toString() and returns "4"
 * After doTimeStep is called, the Critter1 object will
 * run in its given direction. It then has a 50% chance 
 * of calling the reproduce method
 * Critter4 objects have a 25% chance of fighting an
 * opponent Critter
 * runStats prints how many Critter4 objects are alive
 */
public class Critter4 extends Critter{
	private int dir;

	public Critter4(){
		dir = Critter.getRandomInt(8);
	}
	@Override
	public String toString(){
		return "4";
	}
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		walk(dir);
		Critter3 offspring = new Critter3();
		int x = Critter.getRandomInt(3);
		if(x%2 == 0){
			reproduce(offspring,dir);
		}
	}

	@Override
	public boolean fight(String opponent) {
		// TODO Auto-generated method stub
		Critter3 crit = new Critter3();
		int x = Critter.getRandomInt(3);
		if(x == 0){
			return true;
		}
		else{
			return false;
		}
	}
	public static String runStats(java.util.List<Critter> crit4){
		String out = new String("" + crit4.size() + " Critter's active");
		return out;
	}
	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return CritterShape.TRIANGLE;
	}
	@Override
	public Color viewColor(){
		return Color.PURPLE;	
	}
}
