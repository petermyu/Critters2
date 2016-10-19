package assignment4;

public class Critter2 extends Critter
{
	@Override
	public String toString() { return "2"; }
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Critter2() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String not_used) { 
		
		return true;
	}

	@Override
	public void doTimeStep() {
		/* take one step forward */
		walk(dir);
		dir += 1;
		
	}

	public static void runStats(java.util.List<Critter> crit2) {
		
	}
}
