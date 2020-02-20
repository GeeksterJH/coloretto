package domain;

import java.util.Scanner;

public class Console {
	private static Scanner input = new Scanner(System.in);

	private Console() {
	}

	public static void close() {
		input.close();
	}

	public static String getString() {
		try {
			return input.nextLine();
		} catch (Exception e) {
			return "";
		}
	}

	public static int getInt() throws NumberFormatException {
		String line = input.nextLine();
		return Integer.parseInt(line);
	}
}
