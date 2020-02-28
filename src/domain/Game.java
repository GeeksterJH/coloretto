package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game {
	private static final int MAX_ROWS_AMOUNT = 4;

	private List<Player> players = new ArrayList<>();
	private List<Card> deck = new ArrayList<>();
	private List<List<Card>> rows = new ArrayList<>(MAX_ROWS_AMOUNT);
	private int currentPlayerIndex = 0;

	private Random rng = new Random();

	public Game() {
		for (int i = 0; i < MAX_ROWS_AMOUNT; i++) {
			rows.add(new ArrayList<>());
		}
	}

	public void start() {
		System.out.println("Aantal spelers (4 of 5): ");

		int amountOfPlayers;

		do {
			try {
				amountOfPlayers = Console.getInt();
			} catch (NumberFormatException e) {
				System.out.println("Geef een getal in!");
				amountOfPlayers = 0;
				continue;
			}

			if (amountOfPlayers < 4 || amountOfPlayers > 5) {
				System.out.println("Kies tussen 4 of 5 spelers.");
			}
		} while (amountOfPlayers < 4 || amountOfPlayers > 5);

		enterPlayerNames(amountOfPlayers);
		createCards();
		startGame();
	}

	/**
	 * Put 9 cards of each color in the deck
	 */
	private void createCards() {
		for (int colorIndex = 0; colorIndex < Color.AMOUNT; colorIndex++) {
			for (int cardIndex = 0; cardIndex < 9; cardIndex++) {
				deck.add(new Card(Color.get(colorIndex)));
			}
		}

		distributeCards();

		shuffleDeck();
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

	private void shuffleDeck() {
		for (int i = deck.size() - 1; i > 0; i--) {
			int randomIndex = rng.nextInt(i);
			Card temp = deck.get(i);
			deck.set(i, deck.get(randomIndex));
			deck.set(randomIndex, temp);
		}
	}

	/**
	 * Prompt the user for a name for every player.
	 * @param amount The amount of players
	 */
	private void enterPlayerNames(int amount) {
		for (int i = 0; i < amount; i++) {
			System.out.printf("Geef de naam van speler %d%n", i + 1);
			String name = Console.getString();
			players.add(new Player(name));
		}
	}

	/**
	 * Give the turn to the next player
	 */
	private void nextTurn() {
		currentPlayerIndex = currentPlayerIndex == players.size() - 1 ? 0 : currentPlayerIndex + 1;
	}

	private Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	private void printState() {
		System.out.println("===== Spelstatus =====");

		for (Player p : players) {
			Map<Color, Integer> colorAmounts = p.getColorAmounts();
			System.out.printf("%s: ", p.getName());

			for (Map.Entry<Color, Integer> entry : colorAmounts.entrySet()) {
				System.out.printf("%s(%d) ", entry.getKey().toString(), entry.getValue());
			}

			System.out.println();
		}

		System.out.println();

		for (int i = 0; i < rows.size(); i++) {
			List<Card> row = rows.get(i);

			System.out.printf("Rij %d: ", i + 1);

			if (row.isEmpty()) {
				System.out.print("/");
			} else {
				for (int cardIndex = 0; cardIndex < row.size(); cardIndex++) {
					System.out.printf("%s", row.get(cardIndex).getColor().toString());

					if (cardIndex != row.size() - 1) {
						System.out.print(" - ");
					}
				}
			}

			System.out.println();
		}

		System.out.println("======================");
	}

	/**
	 * Let the current player make a move
	 */
	private void makeMove() {
		Player p = getCurrentPlayer();
		System.out.printf(">> Beurt aan \"%s\" <<%n", p.getName());
		System.out.println("  1) Trek kaart van dek");
		System.out.println("  2) Neem rij kaarten");
		int option = 0;

		do {
			try {
				option = Console.getInt();
			} catch (NumberFormatException e) {
				System.out.println("!!Geef een getal in!!");
				option = 0;
				continue;
			}

			switch (option) {
				case 1: {
					Card card = deck.remove(0);
					System.out.printf("De getrokken kaart heeft als kleur: %s%n", card.getColor().toString());
					System.out.println("Op welke rij wil je de kaart leggen?");
					int row = Console.getInt();
					rows.get(row - 1).add(card);
					break;
				}
				case 2: {
					System.out.println("Welke rij wil je nemen?");
					int rowIndex = Console.getInt() - 1;
					List<Card> row = rows.remove(rowIndex);
					p.giveCardRow(row);
					break;
				}
				default:
					System.out.println("!!Geef 1 of 2 in!!");
					break;
			}
		} while (option < 1 || option > 2);
	}

	/**
	 * Main game loop
	 */
	private void startGame() {
		boolean isGameOver = false;

		while (!isGameOver) {
			printState();
			makeMove();
			printState();
			nextTurn();
		}
	}
}
