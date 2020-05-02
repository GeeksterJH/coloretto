package domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Score {
		private SimpleIntegerProperty rank;
		private SimpleStringProperty player;
		private SimpleIntegerProperty score;
		/*
		 * Property = value waar een getter en setter van is
		 * SimpleProperty maakt daar een object van. Nodig voor JavaFX.
		*/
	public Score(int rank, String player, int score) {
		this.rank = new SimpleIntegerProperty(rank);
		this.player = new SimpleStringProperty(player);
		this.score = new SimpleIntegerProperty(score);
	}
	public int getRank() {
		//Get van een property
		return rank.get();
	}
	public void setRank(int rank) {
		//Set van een property
		this.rank.set(rank);
	}
	public String getPlayer() {
		return player.get();
	}
	public void setPlayer(String player) {
		this.player.set(player);
	}
	public int getScore() {
		return score.get();
	}
	public void setScore(int score) {
		this.score.set(score);
	}
}
