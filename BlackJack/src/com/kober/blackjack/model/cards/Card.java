package com.kober.blackjack.model.cards;

public class Card {
	private Rank rank;
	private Suit suit;
	private int value;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		this.value = rank.getNominal();
	}
	
	public boolean isAce() {
		return rank == Rank.ACE;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public Rank getRank() {
		return rank;
	}
	
}
