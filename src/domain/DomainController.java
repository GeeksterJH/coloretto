package domain;

public class DomainController {
	private static Game game;

	public static void startNewGame(String[] playerNames) {
		game = new Game(playerNames);
	}

	public static String getGameState() {
		return game.toString();
	}

	public static void nextTurn() {
		game.nextTurn();
	}

	public static String getCurrentPlayerName() {
		return game.getCurrentPlayer().getName();
	}

	public static String getTopCard() {
		return game.getTopCard().getColor().toString();
	}

	public static void moveTopCardToRow(int rowIndex) {
		game.moveTopCardToRow(rowIndex - 1);
	}

	public static void giveRowToPlayer(int rowIndex) {
		game.giveRowToPlayer(rowIndex - 1);
	}
}
