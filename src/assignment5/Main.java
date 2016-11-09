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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

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
		public static GridPane board = new GridPane();
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
	        Button stop = new Button();
	        Text text = new Text();
	        TextField seedNum = new TextField();
	        TextField critter = new TextField();
	        btn.setText("Make");
	        show.setText("Show");
	        stats.setText("Stats");
	        quit.setText("Quit");
	        seed.setText("Seed");
	        animate.setText("Animate");
	        stop.setText("Stop");
	        timeStep.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	Critter.worldTimeStep();
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
	            	Timer t = new Timer();
	            	//Set the schedule function and rate
	            	t.scheduleAtFixedRate(new TimerTask() {
	            	    @Override
	            	    public void run() {
	            	        //Called each time when 1000 milliseconds (1 second) (the period parameter)
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
	            	},
	            	//Set how long before to start calling the TimerTask (in milliseconds)
	            	0,
	            	//Set the amount of time between each execution (in milliseconds)
	            	5000);
	            	/*ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	            	final Runnable animate = new Runnable() {
	            	       public void run() { 
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
	            	final ScheduledFuture<?> animateTimer = scheduler.scheduleAtFixedRate(animate, 5, 5, TimeUnit.SECONDS);*/
	            	
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
	            }
	        });
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
	        border.setCenter(board);
	        border.setRight(grid);
	      
	        show.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	Critter.displayWorld();
	            	
	            }
	        });
	        
	        board.setGridLinesVisible(true);
	        board.setMaxHeight(Params.world_height);
	        board.setMaxWidth(Params.world_width);
	        grid.add(btn, 0, 0);
	        grid.add(critter,1, 0);
	        grid.add(show, 0, 3);
	        grid.add(stats,0, 4);
	        grid.add(text, 1, 4);
	        grid.add(seed, 0, 5);
	        grid.add(seedNum, 1,5);
	        grid.add(animate, 0, 6);
	        grid.add(stop,1,6);
	        grid.add(timeStep, 0, 7);
	        grid.add(quit, 0, 8);

	        
	    	//primaryStage.setScene(s);
	        primaryStage.setScene(gridScene);
	        primaryStage.show();
	    }
}

