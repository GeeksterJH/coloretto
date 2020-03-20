package domain;

import java.util.HashMap;
import java.util.Map;

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
		return game.getTopCard().toString();
	}

	public static void moveTopCardToRow(int rowIndex) {
		game.moveTopCardToRow(rowIndex - 1);
	}

	public static void giveRowToPlayer(int rowIndex) {
		game.giveRowToPlayer(rowIndex - 1);
	}

	public static boolean isGameOver() {
		return game.isGameOver();
	}

	public static Map<String, Map<Color, Integer>> getPlayerScoresPerColor() {
		Map<String, Map<Color, Integer>> result = new HashMap<>();

		for (Player p : game.getPlayers()) {
			Map<Color, Integer> scores = p.getScorePerColor();
			result.put(p.getName(), scores);
		}

		return result;
	}
}
