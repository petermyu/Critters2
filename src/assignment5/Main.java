/* CRITTERS Main.java
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
package assignment5; // cannot be in default package
import java.util.List;
import java.util.Scanner;

import com.sun.org.apache.xpath.internal.operations.Equals;
import assignment5.Critter.CritterShape;
import assignment5.Critter.TestCritter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.InvocationTargetException;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main extends Application{
		private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
		static {
	        myPackage = Critter.class.getPackage().toString().split(" ")[1];
	    }
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			launch(args);
		}
		public HBox statsText(String str){
			HBox hbox = new HBox();
			Text text = new Text(str);
			hbox.getChildren().add(text);
			return hbox;
		}
	    @Override
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("Critter2");
	        Button btn = new Button();
	        Button show = new Button();
	        Button stats = new Button();
	        Button timeStep = new Button();
	        Button quit = new Button();
	        Button seed = new Button();
	        Button animate = new Button();
	        Text text = new Text();
	        TextField seedNum = new TextField();
	        TextField critter = new TextField();
	        btn.setText("Make");
	        show.setText("Show");
	        stats.setText("Stats");
	        quit.setText("Quit");
	        seed.setText("Seed");
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent event) {
	                String input = (String) critter.getText();
	                System.out.println(input);
	                try {
	        			Critter.makeCritter(input);
	        		} catch (InvalidCritterException e) {
	        			System.out.println("err");
	        		}
	            }
	        });
	        stats.setOnAction(new EventHandler<ActionEvent>(){
	        	@Override
	        	public void handle(ActionEvent event){
	        		String input = critter.getText();
	        		try{
		        		Class<?> crit = Class.forName(myPackage + "." + input);
		    			Class<?>[] types = {List.class};
		        		List<Critter> list = Critter.getInstances(input);
						text.setText((String) crit.getMethod("runStats", types).invoke(null, list))	;
	        		}
	        		catch(InvalidCritterException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e){
	        			text.setText("error");
	        		}
	        		
	        	}
	    	});
	        quit.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	System.exit(1);
	            }
	        });
	       
	        seed.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	try{
	            	int num = Integer.parseInt(seedNum.getText());
	            	Critter.setSeed(num);
	            	}
	            	catch(Exception e){
	            		
	            	}
	            }
	        });
	        BorderPane border = new BorderPane();
	        Scene gridScene = new Scene(border, 600, 600);
	        GridPane grid = new GridPane();
	        GridPane board = new GridPane();
	        border.setCenter(board);
	        border.setRight(grid);
	        board.setGridLinesVisible(true);

	        show.setOnAction(new EventHandler<ActionEvent>() {
	        	
	            @Override
	            public void handle(ActionEvent event) {
	            	Critter.displayWorld();
	            	board.getChildren().clear();
	            	for(int i = 0;i<Critter.getPop().size();i++){
	            		board.setAlignment(Pos.CENTER);
	    	        	int x = Critter.population.get(i).getX();
	    				int y = Critter.population.get(i).getY();
	    				System.out.println(Critter.getPop().size());
	    				System.out.println("(" + i + ")" + x + ", " + y);
	    				CritterShape shape = Critter.getPop().get(i).viewShape();
	    				switch(shape){
	    				case TRIANGLE: 
	    					Polygon crit = new Polygon();
	    					crit.getPoints().addAll(new Double[]{
	    						    0.0, 0.0,
	    						    20.0, 10.0,
	    						    10.0, 20.0 });
	    					board.add(crit, x, y);
	    					break;
	    				case SQUARE:
	    					Polygon crit1 = new Polygon();
	    					crit1.getPoints().addAll(new Double[]{
	    						    0.0, 0.0,
	    						    20.0, 0.0,
	    						    0.0, 20.0,
	    						    20.0, 20.0});
	    					board.add(crit1, x, y);
	    					break;
	    				
	    				case DIAMOND:
	    					Polygon crit2 = new Polygon();
	    					crit2.getPoints().addAll(new Double[]{
	    						    10.0, 0.0,
	    						    20.0, 10.0,
	    						    0.0, 10.0,
	    						    10.0, 20.0});
	    					board.add(crit2, x, y);
	    					break;
	    				case CIRCLE:
	    					Circle crit3 = new Circle();
	    					board.add(crit3, x, y);
	    					break;
	    				}
	    				
	    	        }
	            }
	        });
	        
	        grid.add(btn, 0, 0);
	        grid.add(critter,1, 0);
	        grid.add(show, 0, 3);
	        grid.add(stats,0, 4);
	        grid.add(text, 1, 4);
	        grid.add(seed, 0, 5);
	        grid.add(seedNum, 1,5);
	        grid.add(quit, 0, 6);

	        
	    	//primaryStage.setScene(s);
	        primaryStage.setScene(gridScene);
	        primaryStage.show();
	    }
}

