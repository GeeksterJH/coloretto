package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private List<Card> cards = new ArrayList<>();
	private int Score;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void giveCard(Card c) {
		cards.add(c);
	}
	
	//Give players a row of cards
	public void giveCardRow(List<Card> cards) {
		
	}
	
	//Calculate score per player
	public int calculateScore() {
		int score = 0;
		int aantal = 0;
		Color color;
		
		while(!cards.isEmpty()) {
			color = cards.get(1).getColor();
			cards.remove(0);
			aantal++;
			for(Card card : cards) {
				if(card.getColor() == color) {
					cards.remove(card);
					aantal++;
				}
			}
			score += aantal;
		}
		return score;
	}	
}
