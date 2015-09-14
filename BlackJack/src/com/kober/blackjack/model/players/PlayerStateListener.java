package com.kober.blackjack.model.players;

import com.kober.blackjack.model.cards.Card;

public interface PlayerStateListener {

	public void welcome(Player p);
	public void bet(Player p, int bet);
	public void getMoney(Player p, int reward);
	public void takeCards(Player p, int count);
	public void showFirstCard(Player p, Card card);
	public void showCardsAtTurn(Player p);
	public void showCardsAtWin(Player p);
	public void win(Player p);
	public void declareNoWinners();
	public void pass(Player p);
	public void quit(Player p);

}
