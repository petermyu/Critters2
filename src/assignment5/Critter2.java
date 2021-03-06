/* CRITTERS Critter2.java
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
 * Critter2:
 * When instantiated, Critter2 is given a random direction
 * Overrides toString() and returns "2"
 * Critter2 objects will always fight
 * After doTimeStep is called, the Critter2 object will
 * walk in its given direction. It will then change its
 * direction by 45 degrees
 * 
 */
package assignment5;

import javafx.scene.paint.Color;

public class Critter2 extends Critter
{
	@Override
	public String toString() { return "2"; }
	private int dir;
	
	public Critter2() {
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String not_used) { 
		look(5, true);
		return true;
	}

	@Override
	public void doTimeStep() {
		/* take one step forward */
		walk(dir);
		dir += 1;
		dir = dir%8;
		
	}

	public static String runStats(java.util.List<Critter> crit4){
		String out = new String("" + crit4.size() + " Critter's active");
		return out;
	}

	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return CritterShape.CIRCLE;
	}
	
	@Override
	public Color viewColor(){
		return javafx.scene.paint.Color.CRIMSON;
	}
}
