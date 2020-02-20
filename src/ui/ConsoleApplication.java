package ui;

import domain.Console;
import domain.DomainController;

public class ConsoleApplication {
	public static void main(String[] args) {
		boolean isRunning = true;

		while (isRunning) {
			System.out.println("Welkom bij Coloretto");
			System.out.println("1) Begin spel");
			System.out.println("2) Toon high scores");
			System.out.println("3) Exit");

			try {
				int option = Console.getInt();

				switch (option) {
					case 1:
						DomainController.startGame();
						break;
					case 2:
						// toon high scores
						System.out.println("High scores");
						break;
					case 3:
						isRunning = false;
						break;
					default:
						System.out.println("Geef 1, 2 of 3 in ");
						break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Geef een getal in!");
			}
		}

		Console.close();
	}
}
