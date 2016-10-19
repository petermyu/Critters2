package assignment4;

public class Critter1 extends Critter
{
	@Override
	public String toString() { return "1"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Critter1() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String not_used) { // Critter1 will fight
		return true; 
	}

	@Override
	public void doTimeStep() {
		/* take one step forward */
		walk(dir);
		dir += 3;
	}

	public static void runStats(java.util.List<Critter> craigs) {

	}
}
