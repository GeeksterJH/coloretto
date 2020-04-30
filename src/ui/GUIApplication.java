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
	//@Override
	public void start(Scene highScoresScene) throws Exception {
		Button back = new Button("back");
		Stage window = null;
		/*
		Editor's note: Window (lijn 88 en 89) begon ambetant te doen, hij zwijgt enkel ask hier window definieer.
		Oftewel als 'null' oftewel als 'new Stage()'
		Aangezien ik niet zeker weet wie, hoe wat waar of nog vaagwoorden laat ik het zo staan
		aangezien hij nu geen errors geeft.
		*/
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

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
        
        
        ArrayList<Score> score = new ArrayList<>();
        	Score.score(1, "Karen the Soccer Mom", 20);
        	Score.score(2, "Wommy", 19);
        	Score.score(3, "Count Braakula", 18);
        	Score.score(4, "Buns Buggy", 17);
        	Score.score(5, "Scott the Woz", 16);
        	Score.score(6, "Too sexy for my shirt", 15);
        	Score.score(7, "Hoeveel moek er zo nog schrijven?", 14);
        	Score.score(8, "When will Will Smith smith?", 13);
        	Score.score(9, "My username is too long for this sh", 12);
        	Score.score(10, "Darth Plagueis the Wise", 11);

	}   
}
