package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Game {
	private List<Player> players = new ArrayList<>();
	private List<Card> deck = new ArrayList<>();
	private int currentPlayerIndex = 0;

	public void start() {
		System.out.println("Aantal spelers (4 of 5): ");
		Scanner input = new Scanner(System.in);

		int amountOfPlayers;

		do {
			amountOfPlayers = input.nextInt();

			if (amountOfPlayers < 4 || amountOfPlayers > 5) {
				System.out.println("Kies tussen 4 of 5 spelers.");
			}
		} while (amountOfPlayers < 4 || amountOfPlayers > 5);

		enterPlayerNames(amountOfPlayers);
		createCards();
		startGame();
	}

	private void createCards() {
		for (int colorIndex = 0; colorIndex < Color.AMOUNT; colorIndex++) {
			for (int cardIndex = 0; cardIndex < 9; cardIndex++) {
				deck.add(new Card(Color.get(colorIndex)));
			}
		}

		distributeCards();

		shuffleDeck();
	}

	private void distributeCards() {
		List<Integer> usedPlayers = new ArrayList<>();
		Random rng = new Random();

		for (int i = 0; i < deck.size(); i += 9) {
			int randomIndex;

			do {
				randomIndex = rng.nextInt(players.size());
			} while (usedPlayers.contains(randomIndex));

			usedPlayers.add(randomIndex);

			Card c = deck.remove(i);
			players.get(randomIndex).giveCard(c);

			if (usedPlayers.size() >= players.size()) {
				break;
			}
		}
	}

	private void shuffleDeck() {
		Random rng = new Random();

		for (int i = deck.size() - 1; i > 0; i--) {
			int randomIndex = rng.nextInt(i);
			Card temp = deck.get(i);
			deck.set(i, deck.get(randomIndex));
			deck.set(randomIndex, temp);
		}
	}

	private void enterPlayerNames(int amount) {
		for (int i = 0; i < amount; i++) {
			System.out.printf("Geef de naam van speler %d%n", i + 1);
			Scanner input = new Scanner(System.in);
			String name = input.nextLine();
			players.add(new Player(name));
		}
	}

	private void nextTurn() {
		currentPlayerIndex = currentPlayerIndex == players.size() - 1 ? 0 : currentPlayerIndex + 1;
	}

	private Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	private void startGame() {
		boolean isGameOver = false;

		while (!isGameOver) {
			// TODO: Let current player make a move
			// TODO: this.getCurrentPlayer().makeMove();
			nextTurn();
		}
	}
}
