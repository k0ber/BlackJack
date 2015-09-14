package com.kober.blackjack.controller.players;

import com.kober.blackjack.model.cards.Card;
import com.kober.blackjack.model.players.Player;
import com.kober.blackjack.model.players.PlayerStateListener;
import com.kober.blackjack.view.View;

public class DealerStateListener implements PlayerStateListener {
	private View view;
	
	public DealerStateListener(View view) {
		this.view = view;
	}
	
	@Override
	public void welcome(Player p) {
		view.showLabel("Dealer greets all and invites to the BlackJack!");
		view.showLabel("The casino cache is " + p.getMoney() + "$");
	}

	@Override
	public void bet(Player p, int bet) {
		view.showLabel("Dealer makes a bet");
	}

	@Override
	public void getMoney(Player p, int reward) {
		view.showLabel("Dealer gets reward: " + reward);
	}

	@Override
	public void takeCards(Player p, int count) {
		view.showLabel("Dealer takes a card [x" + count + "]");
	}

	@Override
	public void pass(Player p) {
		view.showLabel("Dealer passes");
	}

	@Override
	public void quit(Player p) {
		view.showLabel("Dealer has tired, he decides to go home");
	}

	@Override
	public void showFirstCard(Player p, Card card) {
		view.showLabel("Dealer show his first card to all. It is \n\t" + card.getRank() + " " + card.getSuit());
	}

	@Override
	public void showCardsAtTurn(Player p) {
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
		view.showLabel("Dealer wins, he got " + p.getScore() + " score");
	}

	@Override
	public void declareNoWinners() {
		view.showLabel("Dealer announces that no one won. The cache remains on the table.");
	}
	
}
