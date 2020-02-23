package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	private List<Player> players = new ArrayList<>();
	private List<Card> deck = new ArrayList<>();
	private int currentRound = 1;

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
		// 1. This list contains one card of each color
		List<Card> colorCards = new ArrayList<>(Color.AMOUNT);

		for (int i = 0; i < deck.size(); i += 9) {
			colorCards.add(deck.remove(i));
		}

		// 2. In this loop we give each player a random card from the colorCards list
		Random rng = new Random();

		for (Player p : players) {
			int randomColorCardIndex = rng.nextInt(colorCards.size());
			p.giveCard(colorCards.remove(randomColorCardIndex));
		}

		// 3. We put the remaining cards back into the deck
		deck.addAll(colorCards);
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
			String name = Console.getString();
			players.add(new Player(name));
		}
	}

	private void startGame() {
		boolean isGameOver = false;

		while (!isGameOver) {

		}
	}
}
