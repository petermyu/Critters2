/* CRITTERS Critter.java
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
package assignment5;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import assignment5.Critter.CritterShape;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;


/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
		/* NEW FOR PROJECT 5 */ 
	public enum CritterShape { CIRCLE, SQUARE, TRIANGLE, DIAMOND, STAR
		} /* the default color is white, which I hope makes critters invisible by
		default
		 * If you change the background color of your View component, then update
		the default
		 * color to be the same as you background  *  * critters must override at least one of the following three methods, it
		is not
		 * proper for critters to remain invisible in the view  *  * If a critter only overrides the outline color, then it will look like a
		non-filled
		 * shape, at least, that's the intent. You can edit these default methods
		however you
		 * need to, but please preserve that intent as you implement them.  */
		public javafx.scene.paint.Color viewColor() {
			return javafx.scene.paint.Color.WHITE;
		}
		public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); } 
		public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
		public abstract CritterShape viewShape();
		private static String myPackage;
		//public static Board gameBoard = new Board();
		// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
		static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
		}
	private static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	public static List<Critter> newPopulation = new java.util.ArrayList<Critter>();
	

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	protected String look(int direction, boolean steps) {
		int x = 0;
		int y = 0;
		if(steps = false){
			switch(direction){
			case 0: x = this.x_coord + 1;
					y = this.y_coord;
					break;
			case 1: x = this.x_coord + 1;
					y = this.y_coord + 1;
					break;
			case 2: x = this.x_coord;
					y = this.y_coord + 1;
					break;
			case 3: x = this.x_coord - 1;
					y = this.y_coord + 1;
					break;
			case 4: x = this.x_coord - 1;
					y = this.y_coord;
					break;
			case 5: x = this.x_coord - 1;
					y = this.y_coord - 1;
					break;
			case 6: x = this.x_coord;
					y = this.y_coord - 1;
					break;
			case 7: x = this.x_coord + 1;
					y = this.y_coord - 1;
					break;
			}
			for(int i = 0; i < population.size(); i++){
				if(population.get(i).x_coord == x && population.get(i).y_coord == y){
					population.get(i).energy = population.get(i).energy - Params.look_energy_cost;
					return population.get(i).toString();
				}
				else{
					population.get(i).energy = population.get(i).energy - Params.look_energy_cost;
					return null;
				}
			}
		}
		else if(steps = true){
			switch(direction){
			case 0: x = this.x_coord + 2;
					y = this.y_coord;
					break;
			case 1: x = this.x_coord + 2;
					y = this.y_coord + 2;
					break;
			case 2: x = this.x_coord;
					y = this.y_coord + 2;
					break;
			case 3: x = this.x_coord - 2;
					y = this.y_coord + 2;
					break;
			case 4: x = this.x_coord - 2;
					y = this.y_coord;
					break;
			case 5: x = this.x_coord - 2;
					y = this.y_coord - 2;
					break;
			case 6: x = this.x_coord;
					y = this.y_coord - 2;
					break;
			case 7: x = this.x_coord + 2;
					y = this.y_coord - 2;
					break;
			}
			for(int i = 0; i < population.size(); i++){
				if(population.get(i).x_coord == x && population.get(i).y_coord == y){
					population.get(i).energy = population.get(i).energy - Params.look_energy_cost;
					return population.get(i).toString();
				}
				else{
					population.get(i).energy = population.get(i).energy - Params.look_energy_cost;
					return null;
				}
			}
		}
		return null;
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
	private boolean moveFlag = false;
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	protected int getX(){
		return this.x_coord;
	}
	protected int getY(){
		return this.y_coord;
	}
	public static List<Critter> getPop(){
		return population;
	}
	protected final void walk(int direction) {
		int backDir  = 0;
		if(moveFlag == false){
			switch(direction){
				case 0: this.x_coord++;
					moveFlag = true;
					backDir = 4;
					break;
				case 1: this.x_coord++;
					this.y_coord++;
					moveFlag = true;
					backDir = 5;
					break;
				case 2: this.y_coord++;
					moveFlag = true;
					backDir = 6;
					break;
				case 3: this.x_coord--;
					this.y_coord++;
					moveFlag = true;
					backDir = 7;
				case 4: this.x_coord--;
					moveFlag = true;
					backDir = 0;
					break;
				case 5: this.x_coord--;
					this.y_coord--;
					moveFlag = true;
					backDir = 1;
					break;
				case 6: this.y_coord--;
					moveFlag = true;
					backDir = 2;
					break;
				case 7: this.y_coord--;
					this.x_coord++;
					moveFlag = true;
					backDir = 3;
					break;
			}
			this.energy = this.energy-Params.walk_energy_cost;
			for(int i = 0; i < population.size(); i++){
				if(this.x_coord == population.get(i).x_coord && this.y_coord == population.get(i).y_coord){
					switch(backDir){
						case 0: this.x_coord++;
							moveFlag = true;
							break;
						case 1: this.x_coord++;
							this.y_coord++;
							moveFlag = true;
							break;
						case 2: this.y_coord++;
							moveFlag = true;
							break;
						case 3: this.x_coord--;
							this.y_coord++;
							moveFlag = true;
						case 4: this.x_coord--;
							moveFlag = true;
							break;
						case 5: this.x_coord--;
							this.y_coord--;
							moveFlag = true;
							break;						
						case 6: this.y_coord--;
							moveFlag = true;
							break;
						case 7: this.y_coord--;
							this.x_coord++;
							moveFlag = true;
							break;
					}
				}
			}
		}
		else if(moveFlag = true){
			this.energy = this.energy-Params.walk_energy_cost;
		}
		if(this.x_coord >= Params.world_width){
			this.x_coord = this.x_coord - (Params.world_width);
		}
		if(this.x_coord < 0){
			this.x_coord = this.x_coord + (Params.world_width);
		}
		if(this.y_coord >= Params.world_height){
			this.y_coord = this.y_coord - (Params.world_height);
		}
		if(this.y_coord < 0){
			this.y_coord = this.y_coord + (Params.world_height);
		}
	}
	
	
	protected final void run(int direction) {
		int backDir = 0;
		if(moveFlag == false){
			switch(direction){
			case 0: this.x_coord+=2;
					backDir = 4;
					break;
			case 1: this.x_coord+=2;
					this.y_coord+=2;
					backDir = 5;
					break;
			case 2: this.y_coord+=2;
					backDir = 6;
					break;
			case 3: this.x_coord-=2;
					this.y_coord+=2;
					backDir = 7;
					break;
			case 4: this.x_coord-=2;
					backDir = 0;
					break;
			case 5: this.x_coord-=2;
					this.y_coord-=2;
					backDir = 1;
					break;
			case 6: this.y_coord-=2;
					backDir = 2;
					break;
			case 7: this.y_coord-=2;
					this.x_coord+=2;
					backDir = 3;
					break;
			}
			this.energy = this.energy-Params.run_energy_cost;
			for(int i = 0; i < population.size(); i++){
				if(this.x_coord == population.get(i).x_coord && this.y_coord == population.get(i).y_coord){
					switch(backDir){
						case 0: this.x_coord+=2;
								break;
						case 1: this.x_coord+=2;
								this.y_coord+=2;
								break;
						case 2: this.y_coord+=2;
								break;
						case 3: this.x_coord-=2;
								this.y_coord+=2;
								break;
						case 4: this.x_coord-=2;
								break;
						case 5: this.x_coord-=2;
								this.y_coord-=2;
								break;
						case 6: this.y_coord-=2;
								break;
						case 7: this.y_coord-=2;
								this.x_coord+=2;
								break;
					}
				}
			}
		}
		else if(moveFlag == true){
			this.energy = this.energy-Params.run_energy_cost;
		}
		if(this.x_coord >= Params.world_width){
			this.x_coord = this.x_coord - (Params.world_width);
		}
		if(this.x_coord < 0){
			this.x_coord = this.x_coord + (Params.world_width);
		}
		if(this.y_coord >= Params.world_height){
			this.y_coord = this.y_coord - (Params.world_height);
		}
		if(this.y_coord < 0){
			this.y_coord = this.y_coord + (Params.world_height);
		}
	}
	
	
	protected final void reproduce(Critter offspring, int direction) {
		if(this.energy < Params.min_reproduce_energy){
			return;
		}
		offspring.energy = this.energy / 2;			//no rounding yet
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
		if(this.x_coord >= Params.world_width){
			this.x_coord = this.x_coord - (Params.world_width);
		}
		if(this.x_coord < 0){
			this.x_coord = this.x_coord + (Params.world_width);
		}
		if(this.y_coord >= Params.world_height){
			this.y_coord = this.y_coord - (Params.world_height);
		}
		if(this.y_coord < 0){
			this.y_coord = this.y_coord + (Params.world_height);
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
			Constructor<?> newConstructor = critClass.getConstructor();
			Critter crit = (Critter) newConstructor.newInstance();
			Critter newCrit = (Critter) crit;
			((Critter) newCrit).energy = Params.start_energy;
			((Critter) newCrit).x_coord = getRandomInt(Params.world_width);
			((Critter) newCrit).y_coord = getRandomInt(Params.world_height);
			Critter.population.add((Critter) newCrit);
			
		}
			catch(IllegalAccessException a){
				throw new InvalidCritterException(critter_class_name);
			}
			catch(InstantiationException c){
				throw new InvalidCritterException(critter_class_name);
			}
			catch(ClassNotFoundException b){
				throw new InvalidCritterException(critter_class_name);
			}
			catch(NoClassDefFoundError b){
				throw new InvalidCritterException(critter_class_name);
			} catch (NoSuchMethodException e) {
				throw new InvalidCritterException(critter_class_name);
			} catch (SecurityException e) {
				throw new InvalidCritterException(critter_class_name);
			} catch (IllegalArgumentException e) {
				throw new InvalidCritterException(critter_class_name);
			} catch (InvocationTargetException e) {
				throw new InvalidCritterException(critter_class_name);
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
	public static String runStats(List<Critter> critters) {
		String out = new String("" + critters.size() + " critters as follows -- ");
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
			out.concat(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		return "";
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
		newPopulation = population;
			for(int i = 0; i < population.size(); i++) {
				population.get(i).doTimeStep();
			}
			population = newPopulation;
			for(int j = 0; j < population.size()-1; j++){					// nested for loop for checking critters in same spot(encounter)
				for(int k = j+1; k < population.size(); k++){
					if((population.get(j).x_coord == population.get(k).x_coord) && (population.get(j).y_coord == population.get(k).y_coord)  ){
						//TODO call fight later when it is written
						boolean critA = population.get(j).fight(population.get(k).toString());
						boolean critB = population.get(k).fight(population.get(j).toString());
						if((population.get(j).x_coord == population.get(k).x_coord) && (population.get(j).y_coord == population.get(k).y_coord)){
							if(population.get(j).energy > 0 && population.get(k).energy > 0){
								if(critA == true){
									energyA = getRandomInt(population.get(j).energy);
								}
								else if(critA == false){
									energyA = 0;
								}
								if(critB == true){
									energyB = getRandomInt(population.get(k).energy);
								}
								else if(critB == false){
									energyB = 0;
								}
								if(energyA >= energyB){				//Critter A wins
									population.get(j).energy = population.get(j).energy + (population.get(k).energy/2);
									population.remove(k);
									j = 0;
									break;
								}
								else{								//Critter B wins
									population.get(k).energy = population.get(k).energy + (population.get(j).energy/2);
									population.remove(j);
									j = 0;
									break;
								}
							}
						}
					}	
				}
			}
			for(int i = 0; i < babies.size(); i++){
				population.add(babies.get(i));
			}
			for(int j =0; j < population.size(); j++){
				population.get(j).energy = population.get(j).energy - Params.rest_energy_cost;
				population.get(j).moveFlag = false;
			}
			for(int l = 0; l < population.size(); l++){
				if(population.get(l).energy <= 0){
					population.remove(l);
				}
			}
			for(int i = 0; i < Params.refresh_algae_count; i++){
			
				try {
					makeCritter("Algae");
				} catch (InvalidCritterException e) {
					
				}
			}
			

		}
	
	public static void displayWorld() {
	//	Main.board.getChildren().clear();
		Main.board.setAlignment(Pos.CENTER);
		//test
		int containerWidth;
		
		int size = (int) Math.ceil(Math.sqrt(Params.world_height*Params.world_width));
		double scaledSquare = 800/size;
		double starpoints = scaledSquare*.8;
		for(int j = 0; j<Params.world_height; j++){
			for(int k = 0; k<Params.world_width;k++){
				
				Rectangle blank = new Rectangle();
				blank.setWidth(scaledSquare);
				blank.setHeight(scaledSquare);
				blank.fillProperty().set(Color.WHITE);
				blank.setStroke(Color.BLACK);
				blank.setStrokeWidth(.1);
				Main.board.add(blank, k, j);
			}
		}
    	for(int i = 0;i<Critter.population.size();i++){
    		
        	int x = Critter.population.get(i).getX();
			int y = Critter.population.get(i).getY();
			CritterShape shape = Critter.population.get(i).viewShape();
			switch(shape){
			case TRIANGLE: 
				Polygon crit = new Polygon();
				crit.getPoints().addAll(new Double[]{
						scaledSquare*.1, scaledSquare*.1,
						scaledSquare*.9, scaledSquare*.1,
						scaledSquare/2, scaledSquare*.9});
				crit.setFill(Critter.population.get(i).viewColor());
				crit.setStroke(Color.BLACK);
				Main.board.add(crit, x, y);
				break;
			case SQUARE:
				Rectangle crit1 = new Rectangle();
				crit1.setHeight(scaledSquare);
				crit1.setWidth(scaledSquare);
				crit1.setFill(Critter.population.get(i).viewColor());
				crit1.setStroke(Color.BLACK);
				Main.board.add(crit1, x, y);
				break;
			
			case DIAMOND:
				Polygon crit2 = new Polygon();
				crit2.getPoints().addAll(new Double[]{
						scaledSquare*.1, scaledSquare/2,
						scaledSquare/2, scaledSquare*.1,
						scaledSquare*.9, scaledSquare/2,
						scaledSquare/2,scaledSquare*.9
						});
				crit2.setFill(Critter.population.get(i).viewColor());
				crit2.setStroke(Color.BLACK);
				Main.board.add(crit2, x, y);
				break;
			case CIRCLE:
				Circle crit3 = new Circle();
				crit3.setFill(Critter.population.get(i).viewColor());
				crit3.setRadius(scaledSquare*.9/2);
				Main.board.add(crit3, x, y);
				break;
			case STAR:
				Polygon crit4 = new Polygon();
				
				crit4.getPoints().addAll(new Double[]{
						0.0, starpoints/4,
						starpoints*2/5,starpoints/4,
						starpoints/2,0.0,
						starpoints*3/5,starpoints/4,
						starpoints,starpoints/4,
						starpoints*3/4,starpoints/2,
						starpoints,starpoints,
						starpoints/2,starpoints*3/4,
						0.0,starpoints,
						starpoints/4,starpoints/2
						});
				crit4.setFill(Critter.population.get(i).viewColor());
				crit4.setStroke(Color.BLACK);
				Main.board.add(crit4, x, y);
			}
			
		
        }
	}
}
