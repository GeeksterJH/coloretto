package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Score {
	public int id;
	public String name;
	public int score;
	
	private Score(int id, String name, int score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}
	
	public static List<Score> getTop10() {
		String query = "SELECT * FROM highScores ORDER BY score DESC";
		ResultSet result = Database.query(query);
		int counter = 0;
		List<Score> scores = new ArrayList<>(10);
		
		try {
			while (result.next()) {
				if (counter >= 10) {
					break;
				}
				
				int id = result.getInt("id");
				String name = result.getString("name");
				int score = result.getInt("score");
				
				scores.add(new Score(id, name, score));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return scores;
	}
}
