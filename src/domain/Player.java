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
			if (colorAmounts.containsKey(c.getColor())) {
				int currentAmount = colorAmounts.get(c.getColor());
				colorAmounts.put(c.getColor(), currentAmount + 1);
			} else {
				colorAmounts.put(c.getColor(), 1);
			}
		}

		return colorAmounts;
	}

	public int getScore() {
		Map<Color, Integer> colorAmounts = getColorAmounts();

		if (colorAmounts.size() > 3) {
			int colorsToRemove = colorAmounts.size() - 3;

			String colorsText = colorsToRemove == 1 ? "kleur" : "kleuren";
			System.out.printf("Je hebt meer dan drie kleuren! Kies %d %s om niet mee te tellen:%n", colorsToRemove, colorsText);

			int index = 1;

			for (Map.Entry<Color, Integer> entry : colorAmounts.entrySet()) {
				System.out.printf("%d) %s - %d%n", index, entry.getKey().toString(), entry.getValue());
				index++;
			}

			String[] colors = Console.getString().split(" ");
			List<Integer> colorIndices = new ArrayList<>(colors.length);

			for (int i = 0; i < colors.length; i++) {
				colorIndices.add(Integer.parseInt(colors[i]) - 1);
			}

			int mapIndex = 0;

			for (Map.Entry<Color, Integer> entry : colorAmounts.entrySet()) {
				if (colorIndices.contains(mapIndex)) {
					colorAmounts.remove(entry.getKey());
				}

				mapIndex++;
			}
		}

		int totalScore = 0;

		for (Map.Entry<Color, Integer> entry : colorAmounts.entrySet()) {
			switch (entry.getValue()) {
				case 0: break;
				case 1: totalScore += 1; break;
				case 2: totalScore += 3; break;
				case 3: totalScore += 6; break;
				case 4: totalScore += 10; break;
				case 5: totalScore += 15; break;
				default: totalScore += 21; break;
			}
		}

		return totalScore;
	}
}
