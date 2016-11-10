/* CRITTERS Critter1.java
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
 * Critter1:
 * When instantiated, Critter1 is given a random direction
 * Overrides toString() and returns "1"
 * Critter1 objects will always fight
 * After doTimeStep is called, the Critter1 object will
 * walk in its given direction. It will then change its
 * direction by 120 degrees
 * 
 */
package assignment5;

import javafx.scene.paint.Color;

public class Critter1 extends Critter
{
	@Override
	public String toString() { return "1"; }
	private int dir;
	
	public Critter1() {

		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String not_used) { // Critter1 will fight
		return false; 
	}

	@Override
	public void doTimeStep() {
		/* take one step forward */
		look(dir, false);
		walk(dir);
		dir += 3;
		dir = dir%8;
	}

	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return CritterShape.STAR;
	}
	@Override
	public Color viewColor(){
		return javafx.scene.paint.Color.BLUE;
	}
	public static String runStats(java.util.List<Critter> crit4){
		String out = new String("" + crit4.size() + " Critter's active");
		return out;
	}
}
