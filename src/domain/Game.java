package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Game {
	private List<Player> players = new ArrayList<>();
	private List<Card> deck = new ArrayList<>();
	private int currentRound = 1;
	private String currentPlayer;
	private int amountOfPlayers;

	public void start() {
		System.out.println("Aantal spelers (4 of 5): ");
		Scanner input = new Scanner(System.in);

		//int amountOfPlayers;

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
	
	//method add currentPlayer to Game
	private void currentPlayer(String currentPlayer) {
		int amountOfPlayers = 0;
		switch (amountOfPlayers) {
		case 1: amountOfPlayers = 4;
		Random order1 = new Random();
		List<String> usedPlayers4 = Arrays.asList("Player 1", "Player 2", "Player 3", "Player 4");
		
		for (int p = 0; p < amountOfPlayers; p++) {
			int randomIndex = order1.nextInt(usedPlayers4.size());
			String randomPlayer = usedPlayers4.get(randomIndex);
		}
		System.out.println("De huidige speler is: " + currentPlayer);
			break;
		case 2: amountOfPlayers = 5;
		Random order2 = new Random();
		List<String> usedPlayers5 = Arrays.asList("Player 1", "Player 2", "Player 3", "Player 4", "Player 5");
				
		for (int p = 0; p < amountOfPlayers; p++) {
			int randomIndex = order2.nextInt(usedPlayers5.size());
			String randomPlayer = usedPlayers5.get(randomIndex);
		}
		System.out.println("De huidige speler is: " + currentPlayer);
			break;
		}
	}

	private void startGame() {
		boolean isGameOver = false;

		while (!isGameOver) {

		}
	}
}
