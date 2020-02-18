package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Game {
	private List<Player> players = new ArrayList<>();
	private List<Card> colorCards = new ArrayList<>();
	private int currentRound = 1;

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
				colorCards.add(new Card(Color.get(colorIndex)));
			}
		}

		shuffleDeck();
	}

	private void shuffleDeck() {
		Random rng = new Random();

		for (int i = colorCards.size() - 1; i > 0; i--) {
			int randomIndex = rng.nextInt(i);
			Card temp = colorCards.get(i);
			colorCards.set(i, colorCards.get(randomIndex));
			colorCards.set(randomIndex, temp);
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

	private void startGame() {
		boolean isGameOver = false;

		while (!isGameOver) {

		}
	}
}
