package assignment5;
import assignment5.Critter.TestCritter;
import javafx.scene.paint.Color;

public class Algae extends TestCritter {

	public String toString() { return "@"; }
	
	public boolean fight(String not_used) { return false; }
	
	public void doTimeStep() {
		setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
	}
	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return CritterShape.SQUARE;
	}
	@Override
	public Color viewColor(){
		return Color.DARKGREEN;
	}
}
