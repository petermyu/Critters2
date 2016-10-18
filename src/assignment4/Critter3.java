package assignment4;

public class Critter3 extends Critter{

	private int dir;
	private int str;
	public Critter3(){
		int x = Critter.getRandomInt(10);
		if(x%2 == 1){
			str = 100;
		}
		else{
			str = 0;
		}
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
	
}
