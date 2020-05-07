package db;

import domain.Player;

public class HighScores {
	public static void updateHighScores(Player p, int score) {
		Database.update(String.format("INSERT INTO highScores (name, score) VALUES ('%s', %d)", p.getName(), score));
	}
}
