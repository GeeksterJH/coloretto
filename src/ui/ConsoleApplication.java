package ui;

import domain.Game;
import domain.DomainController;

import java.util.Scanner;

public class ConsoleApplication {
	public static void main(String[] args) {
		boolean isRunning = true;

		while (isRunning) {
			System.out.println("Welkom bij Coloretto");
			System.out.println("1) Begin spel");
			System.out.println("2) Toon high scores");
			System.out.println("3) Exit");

			Scanner input = new Scanner(System.in);
			int option = input.nextInt();

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
		}
	}
}
