package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIApplication extends Application {
	private Scene loginScene, gameScene, highScoresScene;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Coloretto");
		stage.setWidth(1280);
		stage.setHeight(720);
		
		Button gotoGameScene = new Button("Go to game scene");
		gotoGameScene.setOnAction(e -> stage.setScene(gameScene));
		VBox layout1 = new VBox(20);
		layout1.getChildren().add(new Label("Welcome to the login scene"));
		layout1.getChildren().add(gotoGameScene);
		
		Button gotoHighScoresScene = new Button("Go to high scores");
		gotoHighScoresScene.setOnAction(e -> stage.setScene(highScoresScene));
		VBox layout2 = new VBox(20);
		layout2.getChildren().add(new Label("Welcome to the game scene"));
		layout2.getChildren().add(gotoHighScoresScene);
		
		Button back = new Button("back");
		back.setOnAction(e -> stage.setScene(loginScene));
		VBox layout3 = new VBox(20);
		layout3.getChildren().add(new Label("Welcome to the high scores scene"));
		layout3.getChildren().add(back);
		
		loginScene = new Scene(layout1);
		gameScene = new Scene(layout2);
		highScoresScene = new Scene(layout3);
		
		stage.setScene(loginScene);
		stage.show();
	}
}
