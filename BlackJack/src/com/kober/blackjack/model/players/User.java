package com.kober.blackjack.model.players;

import com.kober.blackjack.model.cards.Card;

public abstract class User extends Player{

	public User(String name, int money, PlayerStateListener listener) {
		super("(user) " + name, money, listener);
	}

	public abstract boolean decideToTakeCard(Card dealerCard);
	public abstract boolean decideToBet();
	
}
