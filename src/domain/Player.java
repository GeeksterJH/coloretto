package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
	private static final boolean getCards = false;
	public boolean IsActive;
	private String name;
	public List<Card> cards = new ArrayList<>();
	public boolean getRows;
	public List<Card> getCards(){
		return cards;
	}

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void giveCard(Card c) {
		cards.add(c);
	}
	public List<Card> Getcard() {
		Scanner choice = new Scanner(System.in);
		boolean Getcard = false;
		if (Getcard) {
			return cards;
		} 
		else if (getRows) {
			return cards;
	}
		return cards;
		
		
	}}

	

