package com.kober.blackjack.model.cards;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
	private static ArrayList<Card> cards;
	private static final int LOW_QUANTITY = 52/3;
	
	private static final Deck INSTANCE = new Deck();

	private Deck() {
		this.initNewCards();
	};
	
	public static Deck getInstance() {
		return INSTANCE;
	}

	public void initNewCards() {
		cards = new ArrayList<Card>();
		for (Suit suit : Suit.values()) {
			for(Rank rank : Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
		shuffle();
	}
	
	public Card getCard() {
		if (cards.size() <= 1) {
			initNewCards();
			shuffle();
		}
		return cards.remove(0);
	}
	
	public boolean isLowCardsQuantity() {
		return cards.size() <= LOW_QUANTITY;
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
}
