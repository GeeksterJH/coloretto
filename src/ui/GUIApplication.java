package ui;

import java.util.List;

import domain.Card;
import domain.Game;
import domain.ColorCard;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	private static final Color BACKGROUND_COLOR = Color.web("#677C88");
	private static final Background BACKGROUND = new Background(new BackgroundFill(BACKGROUND_COLOR, new CornerRadii(0), new Insets(0, 0, 0, 0)));

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
		// NOTE: Temporarily disabled for faster testing
		// int i = 1;
		// List<String> playerNames = new ArrayList<>();

		// for (PlayerNamePrompt.Result r = PlayerNamePrompt.display(i); !r.finished; r = PlayerNamePrompt.display(++i)) {
		// 	playerNames.add(r.name);
		// }
		
		// String[] playerNameArray = new String[playerNames.size()];
		// playerNames.toArray(playerNameArray);

		String[] playerNameArray = new String[] { "Tommy", "Mark", "Michael Rosen", "Sven" };
		Game game = new Game(playerNameArray);

		VBox mainContainer = new VBox();
		mainContainer.setBackground(BACKGROUND);
		mainContainer.setPadding(new Insets(25, 25, 25, 25));

		Label titleLabel = new Label("Coloretto");
		titleLabel.setStyle("-fx-font-size: 28pt");
		titleLabel.setTextFill(Color.web("#fff"));
		mainContainer.getChildren().add(titleLabel);

		Rectangle titleBorder = new Rectangle(250, 3);
		titleBorder.setFill(Color.WHITE);
		mainContainer.getChildren().add(titleBorder);

		Label currentPlayerLabel = new Label("Current player: " + game.getCurrentPlayer().getName());
		currentPlayerLabel.setPadding(new Insets(10, 0, 0, 0));
		currentPlayerLabel.setStyle("-fx-font-size: 14pt");
		currentPlayerLabel.setTextFill(Color.WHITE);
		mainContainer.getChildren().add(currentPlayerLabel);

		// NOTE: Card rows
		{
			HBox rowsContainer = new HBox();
			rowsContainer.setAlignment(Pos.CENTER);

			for (List<Card> row : game.getRows()) {
				VBox rowContainer = new VBox(10);
				rowContainer.setAlignment(Pos.CENTER);
				rowContainer.setPadding(new Insets(50));

				Rectangle cardRect = new Rectangle(120, 200);
				cardRect.setFill(Color.web("#aa3300"));
				rowContainer.getChildren().add(cardRect);
				cardRect.setOnMouseEntered(e -> {
					rowContainer.setCursor(Cursor.HAND);
					cardRect.setFill(Color.WHITE);
				});
				cardRect.setOnMouseExited(e -> {
					rowContainer.setCursor(Cursor.DEFAULT);
					cardRect.setFill(Color.web("#aa3300"));
				});

				Button takeBtn = new Button("Take");
				rowContainer.getChildren().add(takeBtn);

				rowsContainer.getChildren().add(rowContainer);
			}

			mainContainer.getChildren().add(rowsContainer);
		}

		// NOTE: Deck & players
		{
			HBox bottomContainer = new HBox();

			VBox deckContainer = new VBox(20);

			Rectangle topCardRect = new Rectangle(120, 200);
			topCardRect.setFill(Color.WHITE);
			deckContainer.getChildren().add(topCardRect);

			Button showTopCardButton = new Button("Show top card");
			showTopCardButton.setOnAction(e -> {
				Card c = game.getTopCard();

				if (c instanceof ColorCard) {
					ColorCard colorCard = (ColorCard)c;
					topCardRect.setFill(Color.web(colorCard.getColor().toString()));
				}
			});
			deckContainer.getChildren().add(showTopCardButton);

			bottomContainer.getChildren().add(deckContainer);
			mainContainer.getChildren().add(bottomContainer);
		}

		gameScene = new Scene(mainContainer);
	}
}
