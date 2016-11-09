package assignment5;

import assignment5.Critter.CritterShape;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Board {

	public GridPane gameBoard;
	
	public Board(){
		gameBoard = new GridPane();
		
	}
	public void add(CritterShape shape, int col, int row){
		
		switch(shape){
		case TRIANGLE: 
			Polygon crit = new Polygon();
			crit.getPoints().addAll(new Double[]{
				    0.0, 0.0,
				    20.0, 10.0,
				    10.0, 20.0 });
			gameBoard.add(crit, col, row);
			break;
		case SQUARE:
			Polygon crit1 = new Polygon();
			crit1.getPoints().addAll(new Double[]{
				    0.0, 0.0,
				    20.0, 0.0,
				    0.0, 20.0,
				    20.0, 20.0});
			gameBoard.add(crit1, col, row);
			break;
		
		case DIAMOND:
			Polygon crit2 = new Polygon();
			crit2.getPoints().addAll(new Double[]{
				    10.0, 0.0,
				    20.0, 10.0,
				    0.0, 10.0,
				    10.0, 20.0});
			gameBoard.add(crit2, col, row);
			break;
		case CIRCLE:
			Circle crit3 = new Circle();
			gameBoard.add(crit3, col, row);
			break;
		}
	}
}
