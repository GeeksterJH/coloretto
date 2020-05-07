package ui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

		// TODO: Add login and high-scores scene here
		createGameScene();

		// TODO: Start at login scene
		stage.setScene(gameScene);
		stage.show();
	}
	
	private void createGameScene() {
		int i = 1;
		List<String> playerNames = new ArrayList<>();

		for (PlayerNamePrompt.Result r = PlayerNamePrompt.display(i); !r.finished; r = PlayerNamePrompt.display(++i)) {
		 	playerNames.add(r.name);
		}
		
		String[] playerNameArray = new String[playerNames.size()];
		playerNames.toArray(playerNameArray);
		gameScene = new GameScene(playerNameArray).build();
	}
}
