package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.HighScores;
import domain.Card;
import domain.ColorCard;
import domain.Game;
import domain.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameScene {
	private Game game;
	private Label currentPlayerLabel;
	private VBox mainContainer = new VBox(10);
	private Rectangle topCardRect = new Rectangle(200, 320);
	private List<VBox> rows = new ArrayList<>(Game.ROWS_AMOUNT);
	private VBox playersContainer = new VBox(10);
	private List<HBox> playerContainers = new ArrayList<>(5);
	
	public GameScene(String[] playerNames) {
		game = new Game(playerNames);
		currentPlayerLabel = new Label("Current player: " + game.getCurrentPlayer().getName());
		
		for (int i = 0; i < game.getPlayers().size(); i++) {
			HBox playerHBox = new HBox(10);
			
			Label nameLabel = new Label(game.getPlayers().get(i).getName() + ":");
			nameLabel.setTextFill(Color.web("#fff"));
			nameLabel.setStyle("-fx-font-size: 16pt");
			playerHBox.getChildren().add(nameLabel);
			
			for (Card c : game.getPlayers().get(i).getCards()) {
				Rectangle cardRect = new Rectangle(60, 60);
				
				if (c instanceof ColorCard) {
					ColorCard card = (ColorCard)c;
					cardRect.setFill(Color.web(card.getColor().toString()));
				}
				
				playerHBox.getChildren().add(cardRect);
			}
			
			playersContainer.getChildren().add(playerHBox);
			playerContainers.add(playerHBox);
		}
		
		for (int i = 0; i < Game.ROWS_AMOUNT; i++) {
			VBox row = new VBox(10);
			List<Card> cardRow = game.getRows().get(i);

			Button takeButton = new Button("Take Row");
			row.getChildren().add(takeButton);
			takeButton.setOnAction(e -> {
				if (game.getRows().get(game.getRows().indexOf(cardRow)).isEmpty()) {
					return;
				}
				
				HBox playerHBox = playerContainers.get(game.getPlayers().indexOf(game.getCurrentPlayer()));
				
				for (Card c : cardRow) {
					Rectangle cardRect = new Rectangle(60, 60);
					
					if (c instanceof ColorCard) {
						ColorCard card = (ColorCard)c;
						cardRect.setFill(Color.web(card.getColor().toString()));
					}
					
					playerHBox.getChildren().add(cardRect);
				}
				
				((HBox)(row.getParent())).getChildren().remove(row);
				
				game.giveRowToPlayer(game.getRows().indexOf(cardRow));
				
				if (game.isGameOver()) {
					System.out.println("Game over");
					mainContainer.getChildren().clear();
					
					VBox gameOverContainer = new VBox(20);
					gameOverContainer.setAlignment(Pos.CENTER);
					Label gameOverTitle = new Label("Game Over");
					gameOverTitle.setTextFill(Color.WHITE);
					gameOverTitle.setStyle("-fx-font-size: 28pt");
					gameOverContainer.getChildren().add(gameOverTitle);
					
					for (Player p : game.getPlayers()) {
						Map<domain.Color, Integer> scores = p.getScorePerColor();
						int score = 0;
						
						for (Map.Entry<domain.Color, Integer> scoreEntry : scores.entrySet()) {
							score += scoreEntry.getValue();
						}
						
						Label label = new Label(p.getName() + ": " + score);
						label.setTextFill(Color.WHITE);
						label.setStyle("-fx-font-size: 18pt");
						gameOverContainer.getChildren().add(label);
						
						HighScores.updateHighScores(p, score);
					}

					mainContainer.getChildren().add(gameOverContainer);
					
					return;
				}
				
				game.nextTurn();
				currentPlayerLabel.setText("Current player: " + game.getCurrentPlayer().getName());
			});
			
			Button addCardButton = new Button("Add Card");
			row.getChildren().add(addCardButton);
			addCardButton.setOnAction(e -> {
				Card topCard = game.getTopCard();
				
				if (topCard instanceof ColorCard) {
					ColorCard card = (ColorCard)topCard;
					Rectangle cardRect = new Rectangle(50, 50);
					cardRect.setFill(Color.web(card.getColor().toString()));
					row.getChildren().add(cardRect);
					topCardRect.setFill(Color.WHITE);
					game.nextTurn();
					currentPlayerLabel.setText("Current player: " + game.getCurrentPlayer().getName());
				}
				
				game.moveTopCardToRow(game.getRows().indexOf(cardRow));
			});
			
			rows.add(row);
		}
	}
	
	public Scene build() {
		mainContainer.setBackground(GUIApplication.BACKGROUND);
		mainContainer.setPadding(new Insets(25, 25, 25, 25));
		
		Label titleLabel = new Label("Coloretto");
		titleLabel.setStyle("-fx-font-size: 28pt");
		titleLabel.setTextFill(Color.web("#fff"));
		mainContainer.getChildren().add(titleLabel);
		
		Rectangle titleBorder = new Rectangle(250, 3);
		titleBorder.setFill(Color.WHITE);
		mainContainer.getChildren().add(titleBorder);
		
		currentPlayerLabel.setPadding(new Insets(10, 0, 0, 0));
		currentPlayerLabel.setStyle("-fx-font-size: 14pt");
		currentPlayerLabel.setTextFill(Color.WHITE);
		mainContainer.getChildren().add(currentPlayerLabel);
		
		HBox rowsContainer = new HBox(10);
		rowsContainer.setAlignment(Pos.CENTER);
		
		for (VBox row : rows) {
			rowsContainer.getChildren().add(row);
		}
		
		mainContainer.getChildren().add(rowsContainer);
		
		topCardRect.setFill(Color.WHITE);
		mainContainer.getChildren().add(topCardRect);
		
		Button showTopCardButton = new Button("Show Top Card");
		showTopCardButton.setOnAction(e -> {
			Card topCard = game.getTopCard();
			
			if (topCard instanceof ColorCard) {
				ColorCard card = (ColorCard)topCard;
				topCardRect.setFill(Color.web(card.getColor().toString()));
			}
		});
		mainContainer.getChildren().add(showTopCardButton);
		
		mainContainer.getChildren().add(playersContainer);
		
		return new Scene(mainContainer);
	}
}
