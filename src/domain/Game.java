package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game {
	private static final int DECK_SIZE = 90;
	private static final int ROWS_AMOUNT = 4;

	/**
	 * All players in the game
	 */
	private List<Player> players;

	/**
	 * All players that are currently active in the game
	 */
	private List<Player> activePlayers;
	private int currentPlayerIndex = 0;
	private List<Player> amountActivePlayers = activePlayers;
	
	 
	private List<Card> deck = new ArrayList<>(DECK_SIZE);
	private List<List<Card>> rows = new ArrayList<>(ROWS_AMOUNT);

	private Random rng = new Random();

	public Game(String[] playerNames) {
		// Set up rows
		for (int i = 0; i < ROWS_AMOUNT; i++) {
			rows.add(new ArrayList<>());
		}

		// Add players to list
		players = new ArrayList<>(playerNames.length);
		activePlayers = new ArrayList<>(playerNames.length);

		for (int i = 0; i < playerNames.length; i++) {
			Player player = new Player(playerNames[i]);
			players.add(player);
			activePlayers.add(player);
		}

		// Add 9 cards of each color to the deck
		for (int colorIndex = 0; colorIndex < Color.AMOUNT; colorIndex++) {
			for (int cardIndex = 0; cardIndex < 9; cardIndex++) {
				deck.add(new Card(Color.get(colorIndex)));
			}
		}

		distributeCards();

		// Shuffle the deck
		for (int i = deck.size() - 1; i > 0; i--) {
			int randomIndex = rng.nextInt(i);
			Card temp = deck.get(i);
			deck.set(i, deck.get(randomIndex));
			deck.set(randomIndex, temp);
		}
	}

	public void nextTurn() {
		currentPlayerIndex =
			(currentPlayerIndex >= activePlayers.size() - 1)
			? 0
			: currentPlayerIndex + 1;
	}

	public Player getCurrentPlayer() {
		return activePlayers.get(currentPlayerIndex);
	}

	public Card getTopCard() {
		return deck.get(0);
	}

	public void moveTopCardToRow(int rowIndex) {
		Card card = deck.remove(0);
		rows.get(rowIndex).add(card);
	}

	public void giveRowToPlayer(int rowIndex) {
		List<Card> row = rows.remove(rowIndex);
		getCurrentPlayer().giveCardRow(row);
		activePlayers.remove(getCurrentPlayer());
	}

	/**
	 * Give every player one card of a random color.
	*/
	private void distributeCards() {
		// 1. This list contains one card of each color
		List<Card> colorCards = new ArrayList<>(Color.AMOUNT);

		for (int i = 0; i < deck.size(); i += 9) {
			colorCards.add(deck.remove(i));
		}

		// 2. In this loop we give each player a random card from the colorCards list
		for (Player p : players) {
			int randomColorCardIndex = rng.nextInt(colorCards.size());
			p.giveCard(colorCards.remove(randomColorCardIndex));
		}

		// 3. We put the remaining cards back into the deck
		deck.addAll(colorCards);
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("===== Spelstatus =====\n");

		for (Player player : activePlayers) {
			Map<Color, Integer> colorAmounts = player.getColorAmounts();
			builder.append(player.getName());
			builder.append(": ");

			for (Map.Entry<Color, Integer> entry : colorAmounts.entrySet()) {
				builder.append(entry.getKey().toString());
				builder.append("(");
				builder.append(entry.getValue());
				builder.append(") ");
			}

			builder.append("\n");
		}

		builder.append("\n");

		for (int i = 0; i < rows.size(); i++) {
			List<Card> row = rows.get(i);

			builder.append("Rij ");
			builder.append(i + 1);
			builder.append(": ");

			if (row.isEmpty()) {
				builder.append("/");
			} else {
				for (int cardIndex = 0; cardIndex < row.size(); cardIndex++) {
					builder.append(row.get(cardIndex).getColor().toString());

					if (cardIndex != row.size() - 1) {
						builder.append(" - ");
					}
				}
			}

			builder.append("\n");
		}

		builder.append("======================");

		return builder.toString();
		}
		private boolean isGameOver() {
			return false;
		}
		{
		if  (activePlayers  == amountActivePlayers ) {
			 boolean isGameOver=true;
		}
	}
	

}
