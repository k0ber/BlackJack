package com.kober.blackjack.model.players;

import java.util.Random;

import com.kober.blackjack.model.GameModel;
import com.kober.blackjack.model.cards.Card;

public class Bot extends Player {
	Random random;
	
	public Bot(String name, int money, PlayerStateListener listener) {
		super("(easy) " + name, money, listener);
		random = new Random();
	}

	@Override
	public boolean decideToTakeCard(Card dealerCard) {
		if (random.nextInt(3) == 0) {
			return score <= 18;
		} else {
			return score <= 16 && dealerCard.getValue() != 10;
		}
	}
	
	@Override
	public boolean decideToBet() {
		return money >= GameModel.DEFAULT_BET;
	}
}