/* CRITTERS Critter.java

 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Peter Yu>
 * <pmy89>
 * <Student1 5-digit Unique No.>
 * <Christopher Ong>
 * <cio247>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4;

import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private static List<Critter> critters = new java.util.ArrayList<Critter>();
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	protected final void walk(int direction) {
		
		switch(direction){
		//right
		case 0: this.x_coord++;
				break;
		case 1: this.x_coord++;
				this.y_coord++;
				break;
		case 2: this.y_coord++;
				break;
		case 3: this.x_coord--;
				this.y_coord++;
		case 4: this.x_coord--;
				break;
		case 5: this.x_coord--;
				this.y_coord--;
				break;
		case 6: this.y_coord--;
				break;
		case 7: this.y_coord--;
				this.x_coord++;
				break;
		}
		this.energy = this.energy-Params.walk_energy_cost;
		
	}
	
	protected final void run(int direction) {
		
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if(this.energy < Params.min_reproduce_energy){
			return;
		}
		offspring.energy = this.energy;			//no rounding yet
		this.energy = this.energy / 2;
		switch(direction){
		//right
		case 0: offspring.x_coord = this.x_coord++;
				break;
		case 1: offspring.x_coord = this.x_coord++;
				offspring.y_coord = this.y_coord++;
				break;
		case 2: offspring.y_coord = this.y_coord++;
				break;
		case 3: offspring.x_coord = this.x_coord--;
				offspring.y_coord = this.y_coord++;
				break;
		case 4: offspring.x_coord = this.x_coord--;
				break;
		case 5: offspring.x_coord = this.x_coord--;
				offspring.y_coord = this.y_coord--;
				break;
		case 6: offspring.y_coord = this.y_coord--;
				break;
		case 7: offspring.y_coord = this.y_coord--;
				offspring.x_coord = this.x_coord++;
				break;
		}
		babies.add(offspring);
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			Class<?> critClass = Class.forName(myPackage+"."+critter_class_name);
			Object crit = critClass.newInstance();
			((TestCritter) crit).setEnergy(Params.start_energy);
			((TestCritter) crit).setX_coord(getRandomInt(Params.world_width));
			((TestCritter) crit).setY_coord(getRandomInt(Params.world_height));
			((Critter) crit).population.add((Critter) crit);
			
		}
			catch(IllegalAccessException a){
				throw new InvalidCritterException(critter_class_name);
			}
			catch(InstantiationException c){
				throw new InvalidCritterException(critter_class_name);
			}
			catch(ClassNotFoundException b){
				System.out.println("ClassNotFoundException");
			}
			
		}
	
		

			/*@Override												// not sure we need this
			public void doTimeStep() {								//
				// TODO Auto-generated method stub					//
				
			}

			@Override
			public boolean fight(String oponent) {
				// TODO Auto-generated method stub
				return false;										//
			}
			
		critters.add(crit);
	}
	
	*
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();

		try{
			Class<?> critClass = Class.forName(myPackage+"."+critter_class_name);
			if(critClass == null){
				throw new InvalidCritterException(critter_class_name);
			}
			result = TestCritter.getPopulation();
		}
		catch(ClassNotFoundException b){
			System.out.println("ClassNotFoundException");
		}
		
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
	}
	
	public static void worldTimeStep() {								// all calls to critters list will probably be change to population list
		int energyA = 0;
		int energyB = 0;
			for(int i = 0; i < critters.size(); i++) {
				critters.get(i).doTimeStep();
			}
			for(int j = 0; j < critters.size()-1; j++){					// nested for loop for checking critters in same spot(encounter)
				for(int k = j+1; k < critters.size(); k++){
					if((critters.get(j).x_coord == critters.get(k).x_coord) && (critters.get(j).y_coord == critters.get(k).y_coord)  ){
						//TODO call fight later when it is written
						boolean critA = critters.get(j).fight(critters.get(k).toString());
						boolean critB = critters.get(k).fight(critters.get(j).toString());
						if((critters.get(j).x_coord == critters.get(k).x_coord) && (critters.get(j).y_coord == critters.get(k).y_coord)){
							if(critA == true){
								energyA = getRandomInt(critters.get(j).energy);
							}
							else if(critA == false){
								energyA = 0;
							}
							if(critB == true){
								energyB = getRandomInt(critters.get(k).energy);
							}
							else if(critB == false){
								energyB = 0;
							}
							if(energyA >= energyB){				//Critter A wins
								critters.get(j).energy = critters.get(j).energy + (critters.get(k).energy/2);
								critters.remove(k);
							}
							else{								//Critter B wins
								critters.get(k).energy = critters.get(k).energy + (critters.get(j).energy/2);
								critters.remove(j);
							}
						}
					}	
				}
			}
			for(int i = 0; i < babies.size(); i++){
				critters.add(babies.get(i));
			}
			for(int j =0; j < critters.size(); j++){
				critters.get(j).energy = critters.get(j).energy - Params.rest_energy_cost;
			}
			//TODO apply Params.rest_energy_cost
			for(int l = 0; l < critters.size(); l++){
				if(critters.get(l).energy <= 0){
					critters.remove(l);
				}
			}

		}
	
	public static void displayWorld() {
		int width = Params.world_width+2;
		int height = Params.world_height+2;
		
		String[][] graph = new String[width][height];
		//create graph borders
		graph[0][0] ="+";
		graph[0][height-1]= "+";
		graph[width-1][0]="+";
		graph[width-1][height-1] = "+";
		for(int i = 1;i<width-1;i++){
			graph[i][0] = "|";
			graph[i][height-1] = "|";
		}
		for(int i = 1;i<height-1;i++){
			graph[0][i] = "-";
			graph[width-1][i] = "-";
		}
		//insert Algae to graph
		for(int i = 0;i<Algae.getPopulation().size();i++){
			int x = Algae.getPopulation().get(i).x_coord;
			int y = Algae.getPopulation().get(i).y_coord;
			graph[x][y] = Algae.getPopulation().get(i).toString();
		}
		for(int i = 0;i<width;i++){
			for(int j = 0;j<height;j++){
				if(graph[i][j]==null){
					graph[i][j] = " ";
				}
				System.out.print(graph[i][j]);
			}
			System.out.println();
		}

		
	}
}
