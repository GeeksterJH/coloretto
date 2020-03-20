package ui;

import java.util.Map;

import domain.Color;
import domain.DomainController;

public class ConsoleApplication {
	public static void start() {
		System.out.println("===== COLORETTO =====");
		System.out.println("  1) Begin nieuw spel");
		System.out.println("  2) Toon high scores");
		System.out.println("  3) Exit");

		int option;

		do {
			try {
				option = Input.getInt();
			} catch (NumberFormatException e) {
				System.out.println("Geef een getal in!");
				option = 0;
				continue;
			}

			switch (option) {
				case 1: {
					startNewGame();
				} break;

				case 2: {
					showHighScores();
				} break;
			}
		} while (option != 3);

		System.out.println("Exiting...");
	}

	private static void startNewGame() {
		System.out.print("Enter amount of players (4/5): ");
		int amountOfPlayers = Input.askInt("Enter either 4 or 5", (int num) -> num == 4 || num == 5);

		String[] playerNames = new String[amountOfPlayers];

		for (int i = 0; i < amountOfPlayers; i++) {
			System.out.printf("Enter name for player %d: ", i + 1);
			String name = Input.getString();
			playerNames[i] = name;
		}

		DomainController.startNewGame(playerNames);

		while (!DomainController.isGameOver()) {
			System.out.println(DomainController.getGameState());

			System.out.println("1: draw card | 2: take row");
			System.out.printf("%s >>> ", DomainController.getCurrentPlayerName());
			int moveType = Input.askInt("Enter option 1 or 2!", (int num) -> num == 1 || num == 2);

			switch (moveType) {
				case 1: {
					System.out.printf("You drew a %s card%n", DomainController.getTopCard());
					System.out.println("Where do you want to put the card?");
					int rowNr = Input.askInt("Enter a number between 1 and 4", (int num) -> num >= 1 && num <= 4);
					DomainController.moveTopCardToRow(rowNr);
				} break;

				case 2: {
					System.out.print("Which row do you want to take: ");
					int rowNr = Input.askInt("Enter a number between 1 and 4", (int num) -> num >= 1 && num <= 4);
					DomainController.giveRowToPlayer(rowNr);
				} break;
			}

			DomainController.nextTurn();
		}

		endGame();
	}

	private static void endGame() {
		Map<String, Map<Color, Integer>> scores = DomainController.getPlayerScoresPerColor();

		for (Map.Entry<String, Map<Color, Integer>> player : scores.entrySet()) {
			System.out.printf("%s:%n", player.getKey());

			for (Map.Entry<Color, Integer> score : player.getValue().entrySet()) {
				System.out.printf("  - %s: %d%n", score.getKey().toString(), score.getValue());
			}
		}

		System.out.println();
	}

	private static void showHighScores() {
		System.out.println("Toon high scores");
	}
}
