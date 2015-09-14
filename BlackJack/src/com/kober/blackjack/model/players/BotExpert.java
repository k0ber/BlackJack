package com.kober.blackjack.model.players;

import com.kober.blackjack.model.GameModel;
import com.kober.blackjack.model.cards.Card;

public class BotExpert extends Player {

	public BotExpert(String name, int money, PlayerStateListener listener) {
		super("(hard) " + name, money, listener);
	}
	
	@Override
	public boolean decideToTakeCard(Card dealerCard) {
		int dealersCardValue = dealerCard.getValue();
		if (score <= 11) {
			return true;
		}
		if (score == 12 && (dealersCardValue < 4 || dealersCardValue > 6)) {
			return true;
		}
		if (score >= 13 && score < 17 && dealersCardValue > 6) {
			return true;
		}
		return false;
	}

	@Override
	public boolean decideToBet() {
		return money > GameModel.DEFAULT_BET * 4;
	}

}
