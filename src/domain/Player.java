package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	public List<Card> cards = new ArrayList<>();

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void giveCard(Card c) {
		cards.add(c);
	}
}
