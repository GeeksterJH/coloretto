package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
	private String name;
	private boolean active = true;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Get the amount of cards the player has of every color
	 * @return A map from Color to Integer
	 */
	public Map<Color, Integer> getColorAmounts() {
		Map<Color, Integer> colorAmounts = new HashMap<>();

		for (Card c : cards) {
			if (c instanceof ColorCard) {
				ColorCard card = (ColorCard)c;

				if (colorAmounts.containsKey(card.getColor())) {
					int currentAmount = colorAmounts.get(card.getColor());
					colorAmounts.put(card.getColor(), currentAmount + 1);
				} else {
					colorAmounts.put(card.getColor(), 1);
				}
			}
		}

		return colorAmounts;
	}

	public Map<Color, Integer> getScorePerColor() {
		Map<Color, Integer> colorAmounts = getColorAmounts();

		for (Map.Entry<Color, Integer> entry : colorAmounts.entrySet()) {
			int score = 0;

			switch (entry.getValue()) {
				case 0: break;
				case 1: score += 1; break;
				case 2: score += 3; break;
				case 3: score += 6; break;
				case 4: score += 10; break;
				case 5: score += 15; break;
				default: score += 21; break;
			}

			entry.setValue(score);
		}

		return colorAmounts;
	}
}
