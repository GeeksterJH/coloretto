package ui;

import java.util.ArrayList;
import java.util.List;

import db.Score;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUIApplication extends Application {
	public static final Color BACKGROUND_COLOR = Color.web("#677C88");
	public static final Background BACKGROUND = new Background(new BackgroundFill(BACKGROUND_COLOR, new CornerRadii(0), new Insets(0, 0, 0, 0)));

	private Scene loginScene, gameScene, highScoresScene;

	static class PlayerNamePrompt {
		public static class Result {
			public String name;
			public boolean finished;
		}

		public static Result display(int playerNumber) {
			Stage window = new Stage();
			Result result = new Result();

			window.initModality(Modality.APPLICATION_MODAL);
			window.initStyle(StageStyle.UNDECORATED);
			window.setTitle("Enter a player name");
			window.setMinWidth(250);
			window.setMinHeight(100);

			VBox layout = new VBox();
			layout.setPadding(new Insets(10));
			layout.setBackground(BACKGROUND);

			Label label = new Label("Enter name for player " + playerNumber + ":");
			label.setTextFill(Color.WHITE);
			layout.getChildren().add(label);

			TextField nameField = new TextField();
			layout.getChildren().add(nameField);

			if (playerNumber <= 4) {
				Button okBtn = new Button("Ok");
				okBtn.setOnAction(e -> {
					if (nameField.getText().length() > 0) {
						result.finished = false;
						result.name = nameField.getText();
						window.close();
					}
				});
				layout.getChildren().add(okBtn);
			}

			if (playerNumber >= 4) {
				Button doneButton = new Button("Done");
				doneButton.setOnAction(e -> {
					if (nameField.getText().length() > 0) {
						result.finished = true;
						result.name = nameField.getText();
						window.close();
					}
				});
				layout.getChildren().add(doneButton);
			}
			
			window.setScene(new Scene(layout));
			window.showAndWait();
			
			return result;
		}
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Coloretto");
		stage.setWidth(1920);
		stage.setHeight(1080);

		createLogInScene();

		stage.setScene(loginScene);
		stage.show();
	}
	
	private void createLogInScene() {
		VBox root = new VBox(20);
		root.setBackground(BACKGROUND);
		root.setAlignment(Pos.CENTER);
		
		Label title = new Label("Coloretto");
		title.setTextFill(Color.WHITE);
		title.setStyle("-fx-font-size: 40pt");
		root.getChildren().add(title);
		
		HBox buttonsContainer = new HBox(20);
		buttonsContainer.setAlignment(Pos.CENTER);
		
		Button startGameButton = new Button("Start Game");
		buttonsContainer.getChildren().add(startGameButton);
		startGameButton.setOnAction(e -> {
			createGameScene();
			Stage window = (Stage)startGameButton.getScene().getWindow();
			window.setScene(gameScene);
		});
		
		Button highScoresButton = new Button("Show High Scores");
		buttonsContainer.getChildren().add(highScoresButton);
		highScoresButton.setOnAction(e -> {
			createHighScoresScene();
			Stage window = (Stage)highScoresButton.getScene().getWindow();
			window.setScene(highScoresScene);
		});
		
		Button exitButton = new Button("Exit");
		buttonsContainer.getChildren().add(exitButton);
		exitButton.setOnAction(e -> {
			System.exit(0);
		});
		
		root.getChildren().add(buttonsContainer);
		
		loginScene = new Scene(root);
	}
	
	private void createGameScene() {
		int i = 1;
		List<String> playerNames = new ArrayList<>();
		PlayerNamePrompt.Result r;

		for (r = PlayerNamePrompt.display(i); !r.finished; r = PlayerNamePrompt.display(++i)) {
		 	playerNames.add(r.name);
		}
		playerNames.add(r.name);
		
		String[] playerNameArray = new String[playerNames.size()];
		playerNames.toArray(playerNameArray);
		gameScene = new GameScene(playerNameArray).build();
	}
	
	private void createHighScoresScene() {
		List<Score> highScores = Score.getTop10();
		
		ObservableList<HighScore> scores = FXCollections.observableArrayList();
		
		for (int rank = 1; rank <= highScores.size(); rank++) {
			Score s = highScores.get(rank - 1);
			scores.add(new HighScore(rank, s.name, s.score));
		}
		
		VBox root = new VBox(10);
		root.setPadding(new Insets(25, 25, 25, 25));
		root.setBackground(BACKGROUND);
		
		Label titleLabel = new Label("Coloretto");
		titleLabel.setStyle("-fx-font-size: 28pt");
		titleLabel.setTextFill(Color.web("#fff"));
		root.getChildren().add(titleLabel);
		
		Rectangle titleBorder = new Rectangle(250, 3);
		titleBorder.setFill(Color.WHITE);
		root.getChildren().add(titleBorder);
		
		TableColumn rankCol = new TableColumn("rank");
		rankCol.setMinWidth(100);
		TableColumn playerCol = new TableColumn("player");
		playerCol.setMinWidth(400);
		TableColumn scoreCol = new TableColumn("score");
		scoreCol.setMinWidth(100);
		
		rankCol.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("rank"));
		playerCol.setCellValueFactory(new PropertyValueFactory<HighScore, String>("name"));
		scoreCol.setCellValueFactory(new PropertyValueFactory<HighScore, Integer>("score"));
		
		TableView table = new TableView<>();
		table.setItems(scores);
		table.getColumns().addAll(rankCol, playerCol, scoreCol);
		table.setStyle("-fx-font-size: 18pt");
		
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> {
			Stage window = (Stage)root.getScene().getWindow();
			window.setScene(loginScene);
		});
		root.getChildren().add(backButton);
		
		root.getChildren().add(table);
		
		highScoresScene = new Scene(root);
	}
	
	public static class HighScore {
		private SimpleIntegerProperty rank;
		private SimpleStringProperty name;
		private SimpleIntegerProperty score;
		
		public HighScore(int rank, String name, int score) {
			this.rank = new SimpleIntegerProperty(rank);
			this.name = new SimpleStringProperty(name);
			this.score = new SimpleIntegerProperty(score);
		}
		
		public int getRank() {
			return rank.get();
		}
		
		public String getName() {
			return name.get();
		}
		
		public int getScore() {
			return score.get();
		}
		
		public void setRank(int rank) {
			this.rank.set(rank);
		}
		
		public void setName(String name) {
			this.name.set(name);
		}
		
		public void setScore(int score) {
			this.score.set(score);
		}
	}
}
