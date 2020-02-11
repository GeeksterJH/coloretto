package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	private List<Player> players = new ArrayList<>();

	public void start() {
		System.out.println("Aantal spelers (4 of 5): ");
		Scanner input = new Scanner(System.in);

		int amountOfPlayers;

		do {
			amountOfPlayers = input.nextInt();
			switch (amountOfPlayers) {
			case 4:
				amountOfPlayers = 4;
				break;
			case 5:
				amountOfPlayers = 5;
				break;
			default:
				System.out.println("Kies tussen 4 of 5 spelers.");
				break;
			}
		} while (amountOfPlayers < 4 || amountOfPlayers > 5);

		enterPlayerNames(amountOfPlayers);
	}
	
	private void enterPlayerNames(int amount) {
		for (int i = 0; i < amount; i++) {
			System.out.printf("Geef de naam van speler %d%n", i + 1);
			Scanner input = new Scanner(System.in);
			String name = input.nextLine();
			players.add(new Player(name));
		}
	}
}
