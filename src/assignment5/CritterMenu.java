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
package assignment4;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CritterMenu extends Application{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Critter Menu");
        Label lbl = new Label();
        Button btn = new Button();
        Button show = new Button();
        Button stats = new Button();
        lbl.setText("" );
        btn.setText("Make");
        show.setText("Show");
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
        show.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            	Critter.displayWorld();
            }
        });
        
        VBox vbox = new VBox(8);
        vbox.getChildren().add(btn);
        vbox.getChildren().add(lbl);
        vbox.getChildren().add(comboBox);
        vbox.getChildren().add(show);
        primaryStage.setScene(new Scene(vbox, 300, 250));
        primaryStage.show();
    }
}
