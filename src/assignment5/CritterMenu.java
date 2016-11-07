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
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.text.Text;
import javafx.scene.canvas.*;

public class CritterMenu extends Application{
	
	public String pack =  Critter.class.getPackage().toString().split(" ")[1];
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
        primaryStage.setTitle("Critter Menu");
        Button btn = new Button();
        Button show = new Button();
        Button stats = new Button();
        Button timeStep = new Button();
        Button quit = new Button();
        Text text = new Text();


        btn.setText("Make");
        show.setText("Show");
        stats.setText("Stats");
        quit.setText("Quit");
        ObservableList<String> options = 
        	    FXCollections.observableArrayList(
        	        "Algae",
        	        "Critter1",
        	        "Critter2",
        	        "Critter3",
        	        "Critter4"
        	    );
        final ComboBox comboBox = new ComboBox(options);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String critter = (String) comboBox.getValue();
                try {
        			Critter.makeCritter(critter);
        		} catch (InvalidCritterException e) {
        			System.out.println("err");
        		}
            }
        });
        stats.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent event){
        		String critter = (String) comboBox.getValue();
        		try{
	        		Class<?> crit = Class.forName(pack + "." + critter);
	    			Class<?>[] types = {List.class};
	        		List<Critter> list = Critter.getInstances(critter);
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
        show.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            	Critter.displayWorld();
            }
        });
        
        GridPane grid = new GridPane();
        final Canvas canvas = new Canvas(250,250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(100, 100, 100, 100);
        grid.add(canvas, 10, 10);

 
        grid.add(btn, 0, 0);
        grid.add(comboBox,1, 0);
        grid.add(show, 0, 3);
        grid.add(stats,0, 4);
        grid.add(text, 0, 6);
        grid.add(quit, 0, 15);
        Scene gridScene = new Scene(grid, 500, 500);
    	//primaryStage.setScene(s);
        primaryStage.setScene(gridScene);
        primaryStage.show();
    }
}
