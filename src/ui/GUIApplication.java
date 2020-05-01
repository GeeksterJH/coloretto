package ui;
import java.util.ArrayList;

import domain.Player;
import domain.Score;
import domain.Rank;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	//High Scores screen
	@Override
	public void setupHighScoresScene() throws Exception {
		Button back = new Button("back");
		TableView<Player> highScoresTable;
		
		//Rank column
		TableColumn<Rank, Integer> rankColumn = new TableColumn<>("Rank");
		rankColumn.setMinWidth(160);
		rankColumn.setCellValueFactory(new PropertyValueFactory<>("Rank"));
		
		//Player column
		TableColumn<Player, String> playerColumn = new TableColumn<>("Player");
		playerColumn.setMinWidth(160);
		playerColumn.setCellValueFactory(new PropertyValueFactory<>("Player"));
		
		//Score column
		TableColumn<Score, Integer> scoreColumn = new TableColumn<>("Score");
		scoreColumn.setMinWidth(160);
		scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));
		
		highScoresTable = new TableView<>();
		highScoresTable.getColumns();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(highScoresTable);
        
        ArrayList<Score> score = new ArrayList<>();
    	score.add(new Score(1, "Karen the Soccer Mom", 20));
    	score.add(new Score(2, "Wommy", 19));
    	score.add(new Score(3, "Count Braakula", 18));
    	score.add(new Score(4, "Buns Buggy", 17));
    	score.add(new Score(5, "Scott the Woz", 16));
    	score.add(new Score(6, "Too sexy for my shirt", 15));
    	score.add(new Score(7, "Hoeveel moek er zo nog schrijven?", 14));
    	score.add(new Score(8, "When will Will Smith smith?", 13));
    	score.add(new Score(9, "My username is too long for this sh", 12));
    	score.add(new Score(10, "Darth Plagueis the Wise", 11));
    	
        highScoresScene = new Scene(vBox);
	}   
}
