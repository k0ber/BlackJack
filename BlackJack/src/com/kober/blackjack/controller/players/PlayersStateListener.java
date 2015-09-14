package com.kober.blackjack.controller.players;

import com.kober.blackjack.model.cards.Card;
import com.kober.blackjack.model.players.Player;
import com.kober.blackjack.model.players.PlayerStateListener;
import com.kober.blackjack.view.View;

public class PlayersStateListener implements PlayerStateListener {
	View view;

	public PlayersStateListener(View view) {
		this.view = view;
	}
	
	@Override
	public void welcome(Player p) {
		view.showLabel(p.getName() + " greets everyone");
	}

	@Override
	public void bet(Player p, int bet) {
		view.showLabel(p.getName() + " bets: " + bet + "$ he has " + p.getMoney() + "$ left");
	}

	@Override
	public void getMoney(Player p, int reward) {
		view.showLabel(p.getName() + " gets " + reward +"$ he have " + p.getMoney() + "$ now");
	}

	@Override
	public void takeCards(Player p, int count) {
		view.showLabel(p.getName() + " takes a card [x" + count + "]");
	}

	@Override
	public void pass(Player p) {
		view.showLabel(p.getName() + " decide to pass at this turn");
	}

	@Override
	public void quit(Player p) {
		view.showLabel(p.getName() + " decide to quit, he take with him " + p.getMoney() + "$");
	}

	@Override
	public void showFirstCard(Player p, Card card) {
	}

	@Override
	public void showCardsAtTurn(Player p) {
		view.showLabel(p.getName() + " have cards:");
		for (Card card : p.getCards()) {
			view.showLabel('\t' + card.getRank().name() + '\t' + card.getSuit());
		}
	}

	@Override
	public void showCardsAtWin(Player p) {
		view.showLabel(p.getName() + " have his winning combination:");
		for (Card card : p.getCards()) {
			view.showLabel('\t' + card.getRank().name() + '\t' + card.getSuit());
		}
	}
	
	@Override
	public void win(Player p) {
		view.showLabel(p.getName() + " wins, he got " + p.getScore() + " score");
	}

	@Override
	public void declareNoWinners() {
	}

}
