package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {
	public boolean IsActive;
	private String name;
	private List<Card> cards = new ArrayList<>();

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void giveCard(Card c) {
		cards.add(c);
	}
	
	public void giveCardRow(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public int calculateScore() {
		int score = 0;
		int amount = 0;
		Color color;

		while (!cards.isEmpty()) {
			color = cards.get(0).getColor();
			cards.remove(0);
			amount++;

			Iterator<Card> cardIterator = cards.iterator();

			while (cardIterator.hasNext()) {
				Card card = cardIterator.next();

				if (card.getColor() == color) {
					cardIterator.remove();
					amount++;
				}
			}

			score += amount;
			amount = 0;
		}

		return score;
	}
}
