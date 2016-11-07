/* CRITTERS Critter3.java
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
 * When instantiated, Critter3 is given a random direction
 * Overrides toString() and returns "3"
 * Critter3 objects will always fight
 * After doTimeStep is called, the Critter1 object will
 * run in its given direction. It then has a 50% chance 
 * of calling the reproduce method
 * Critter3 objects will fight another Critter if the 
 * opponent Critter's toString character value is less than
 * its own
 * runStats prints how many Critter3 objects are alive
 */
package assignment4;
public class Critter3 extends Critter{

	private int dir;
	public Critter3(){
		dir = Critter.getRandomInt(8);
	}
	@Override
	public String toString(){
		return "3";
	}
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		run(dir);
		Critter3 offspring = new Critter3();
		int x = Critter.getRandomInt(1);
		if(x == 0){
		reproduce(offspring,dir);
		}
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		Critter3 crit = new Critter3();
		if(crit.toString().charAt(0) > oponent.charAt(0) ){
			return true;
		}
		else{
			return false;
		}
	}
	public static void runStats(java.util.List<Critter> crit3){
		System.out.println("" + crit3.size() + " Critter3's active");
	}
	
	
}
