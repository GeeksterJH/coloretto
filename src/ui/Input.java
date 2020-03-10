package ui;

import java.util.Scanner;

interface AskIntFunction {
	boolean condition(int num);
}

public class Input {
	private static Scanner input = new Scanner(System.in);

	private Input() {
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

	public static int askInt(String errorMessage, AskIntFunction func) {
		int result;

		do {
			try {
				result = getInt();
			} catch (NumberFormatException e) {
				result = 0;
				System.out.println(errorMessage);
				continue;
			}

			if (!func.condition(result)) {
				System.out.println(errorMessage);
			}
		} while (!func.condition(result));

		return result;
	}
}
