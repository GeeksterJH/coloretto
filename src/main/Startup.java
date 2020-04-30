package main;

import java.awt.Button;
import java.awt.event.ActionEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import ui.GUIApplication;



class Startup {
	public static void main(String[] args) {
		//ConsoleApplication.start();
		Application.launch(GUIApplication.class, args);
	}

/*
public class Startup extends Application implements EventHandler<ActionEvent> {
	private Button button;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Oh hi mark");
		
		button = new Button();
		button.setText("Oh hi doggy");
		button.setOnAction(this);
		button.setStyle("-fx-text-fill: red");
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 400, 400);
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void handle(ActionEvent event) {
		System.out.println("y e e t");
	}
	*/
}





