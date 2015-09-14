package com.kober.blackjack.model.cards;

public enum Rank {
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	QUEEN(10),
	KING(10),
	ACE(11);
	
	private final int nominal;
	
	Rank(int nominal) {
		this.nominal = nominal;
	}
	
	public int getNominal() {
		return nominal;
	}
	
	public boolean isPicture() {
		return this == JACK || this == QUEEN || this == KING;
	}
}