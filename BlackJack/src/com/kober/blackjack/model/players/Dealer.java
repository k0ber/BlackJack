package com.kober.blackjack.model.players;

import com.kober.blackjack.model.cards.Card;

public class Dealer extends Player {
	public static final int CASINO_MONEY = 10000;
	public static final Dealer INSTANCE = new Dealer(CASINO_MONEY);
	
	public static Dealer getInstance() {
		return INSTANCE;
	}
	
	private Dealer(int money) {
		super("Dialer", money, null);
	}
	
	@Override
	public boolean decideToBet() {
		return true;
	}

	@Override
	public boolean decideToTakeCard(Card dealerCard) {
		return getScore() < 17;
	}

}
