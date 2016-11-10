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
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import com.sun.org.apache.xpath.internal.operations.Equals;
import assignment5.Critter.CritterShape;
import assignment5.Critter.TestCritter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
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
		public static GridPane board = new GridPane();
		public static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		public static ScheduledFuture<?> handler;
		public static Runnable beeper;
		private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
		static {
	        myPackage = Critter.class.getPackage().toString().split(" ")[1];
	    }
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			launch(args);
		}
	    @Override
	    public void start(Stage primaryStage) {
	    	Critter.displayWorld();
	        primaryStage.setTitle("Critter2 - Project5");
	        Button btn = new Button();
	        Button show = new Button();
	        Button stats = new Button();
	        Button timeStep = new Button();
	        Button quit = new Button();
	        Button seed = new Button();
	        Button animate = new Button();
	        Button stop = new Button();
	        Button clear = new Button();
	        Text text = new Text();
	        Text controls = new Text();
	        Text makeCrit = new Text();
	        Text numCrit = new Text();
	        TextField seedNum = new TextField();
	        TextField critter = new TextField();
	        TextField steps = new TextField();
	        TextField numCrits = new TextField();
	        critter.setMaxWidth(100);
	        clear.setMaxWidth(150);
	        numCrits.setMaxWidth(100);
	        btn.setMaxWidth(150);
	        steps.setMaxWidth(150);
	        stop.setMaxWidth(150);
	        timeStep.setMaxWidth(150);
	        show.setMaxWidth(150);
	        stats.setMaxWidth(150);
	        seed.setMaxWidth(150);
	        animate.setMaxWidth(150);
	        quit.setMaxWidth(150);
	        steps.setText("0");
	        clear.setText("Clear");
	        btn.setText("Make");
	        show.setText("Show");
	        stats.setText("Stats");
	        quit.setText("Quit");
	        seed.setText("Seed");
	        animate.setText("Animate");
	        stop.setText("Stop");
	        timeStep.setText("Step");
	        makeCrit.setText("Critter Name:");
	        numCrit.setText("Number of Critters:");
	        critter.setText("Critter1");
	        steps.setText("1");
	        numCrits.setText("1");
	        controls.setText("Controls");
	        controls.setFont(new Font(40));
	        timeStep.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	int i = 0;
	            	int st = Integer.parseInt(steps.getText());
	            	
	            	while(i < st){
		            	Critter.worldTimeStep();
		            	Critter.displayWorld();
		            	i++;
	            	}
	            }
	        });
	        clear.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	board.getChildren().clear();
	            	Critter.getPop().clear();
	            	Critter.displayWorld();
	            }
	        });
	        animate.setOnAction(new EventHandler<ActionEvent>() {
	            @Override  
	            public void handle(ActionEvent event) {
	            	btn.setDisable(true);
	            	stats.setDisable(true);
	            	show.setDisable(true);
	            	seed.setDisable(true);
	            	timeStep.setDisable(true);
	            	scheduler = Executors.newScheduledThreadPool(1);
	
	        		    beeper = new Runnable() {
	        		    	public void run() {
	        		    		System.out.println("animate");
	        		    		Critter.worldTimeStep();
	        		    		Critter.displayWorld();
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
	        		     };
	        		     handler = scheduler.scheduleAtFixedRate(beeper, 1,10, TimeUnit.SECONDS);
	            
	            }
	        });
	        stop.setOnAction(new EventHandler<ActionEvent>() {
	            @Override  
	            public void handle(ActionEvent event) {
	            	btn.setDisable(false);
	            	stats.setDisable(false);
	            	show.setDisable(false);
	            	seed.setDisable(false);
	            	timeStep.setDisable(false);
	            	scheduler.shutdown();
	            }
	        });
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override  
	            public void handle(ActionEvent event) {
	                String input = (String) critter.getText();
	                int count = Integer.parseInt(numCrits.getText());
	                while(count >0){
	                
		                try {
		        			Critter.makeCritter(input);
		        		} catch (InvalidCritterException e) {
		        			System.out.println("err");
		        		}
	                count--;
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
	        Scene gridScene = new Scene(border, 500, 500);
	        GridPane grid = new GridPane();
	        border.setCenter(board);
	        border.setRight(grid);
	        border.setMargin(board, new Insets(2));
	      
	        show.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	Critter.displayWorld();
	            	
	            }
	        });
	        
	        board.setGridLinesVisible(true);
	        board.setMaxHeight(Params.world_height);
	        board.setMaxWidth(Params.world_width);
	        grid.getColumnConstraints().add(new ColumnConstraints(150));
	        grid.getColumnConstraints().add(new ColumnConstraints(150));
	        grid.getColumnConstraints().add(new ColumnConstraints(100));
	        grid.getColumnConstraints().add(new ColumnConstraints(20));
	        grid.getRowConstraints().add(new RowConstraints(50));
	        grid.add(btn, 0, 1);
	        grid.add(makeCrit,1, 1);
	        grid.add(critter,2, 1);
	        grid.add(numCrit, 1	, 2);
	        grid.add(controls, 0, 0);
	        grid.add(numCrits,2,2);
	        grid.add(show, 0, 4);
	        grid.add(stats,0, 5);
	        grid.add(text, 1, 5);
	        grid.add(seed, 0, 6);
	        grid.add(seedNum, 1,6);
	        grid.add(animate, 0, 7);
	        grid.add(stop,1,7);
	        grid.add(timeStep, 0, 8);
	        grid.add(steps, 1, 8);
	        grid.add(clear, 0, 9);
	        grid.add(quit, 0, 10);
	    	//primaryStage.setScene(s);
	        primaryStage.setWidth(1500);
	        primaryStage.setHeight(1000);
	        primaryStage.setScene(gridScene);
	        primaryStage.show();
	    }
}

