package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
	private static final boolean getCards = false;
	public boolean IsActive;
	private String name;
	public List<Card> cards = new ArrayList<>();
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
	if (getCards) {
		return cards;
	} 
	else if (getRow) {
		return List<Cards> ;
}
		
		
	}}

	

