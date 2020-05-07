package ui;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUIApplication extends Application {
	private Scene loginScene, gameScene, highScoresScene;
    public static final Color BACKGROUND_COLOR = Color.web("#677C88");
    public static final Background BACKGROUND = new Background(new BackgroundFill(BACKGROUND_COLOR, new CornerRadii(0), new Insets(0, 0, 0, 0)));
	
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
		
		Button playgame = new Button("play game");
		playgame.setOnAction(e -> stage.setScene(gameScene));
		layout2.getChildren().add(playgame);
		
		Button highscoresscene  = new Button("Show High Scores");
		gotoHighScoresScene.setOnAction(e -> stage.setScene(gameScene));
		layout2.getChildren().add(highscoresscene);
		
		Button exitgame  = new Button("Exit");
		exitgame.setOnAction(e -> System.exit(0));
		layout2.getChildren().add(exitgame);
		
		layout1.setBackground(BACKGROUND);
		loginScene = new Scene(layout1);
		gameScene = new Scene(layout2);
		highScoresScene = new Scene(layout3);
		
		stage.setScene(loginScene);
		stage.show();
	}
}
