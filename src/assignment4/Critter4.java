

package assignment4;
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
		if(crit.toString().charAt(0) < opponent.charAt(0) ){
			return true;
		}
		else{
			return false;
		}
	}
	public static void runStats(java.util.List<Critter> crit4){
		System.out.println("" + crit4.size() + " Critter4's active");
	}
}
