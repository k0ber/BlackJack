package com.kober.blackjack.model.players;

import java.util.ArrayList;
import java.util.Arrays;

import com.kober.blackjack.model.cards.Card;
import com.kober.blackjack.model.cards.Rank;

public abstract class Player {
	protected int money;
	protected int score;
	protected ArrayList<Card> cards;
	protected String name;
	private PlayerStateListener listener;
	
	public abstract boolean decideToTakeCard(Card dealerCard);
	public abstract boolean decideToBet();
	
	public Player(String name, int money, PlayerStateListener listener) {
		this.name = name;
		this.money = money;
		this.listener = listener;
		score = 0;
		cards = new ArrayList<Card>();
	}
	
	public void welcome() {
		if (listener != null) {
			listener.welcome(this);
		}
	}
	
	public void bet(int bet) {
		money -= bet;
		if (listener != null) {
			listener.bet(this, bet);
		}
	}
	
	public void takeReward(int reward) {
		money += reward;
		if (listener != null) {
			listener.getMoney(this, reward);
		}
	}
	
 	public boolean isHavingBlackJack() {
		if (cards.size() == 2) {
			Rank firstCardRank = cards.get(0).getRank();
			Rank secondCardRank = cards.get(1).getRank();
			if ((firstCardRank == Rank.ACE && secondCardRank.isPicture())
					|| (firstCardRank.isPicture() && secondCardRank == Rank.ACE)) {
				return true;
			}
		}
		return false;
	}
 	
 	public boolean isCanTakeMore() {
 		return score < 21;
 	}
	
	public void takeCard(Card... cards) {
		this.cards.addAll(Arrays.asList(cards));
		listener.takeCards(this, cards.length);
		if (listener != null) {
			listener.showCardsAtTurn(this);
		}
		calcScore();
	}
	
	private void calcScore() {
		int result = 0;
		ArrayList<Integer> acesIndexes = new ArrayList<Integer>();
		for (int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			if (card.isAce()) {
				acesIndexes.add(i);
			}
			result += card.getValue();
		}
		while (result > 21 && acesIndexes.size() > 0) {
			Card ace = cards.get(acesIndexes.remove(0));
			if (ace.getValue() == Rank.ACE.getNominal()) {
				ace.setValue(1);
				result -= 10;
			}
		}
		score = result;
	}
	
	public void resetCards() {
		cards.clear();
	}
	
	public void showFirstCard() {
		if (listener != null) {
			listener.showFirstCard(this, cards.size() > 0 ? cards.get(0) : null);
		}
	}

	public void showCardsAtTurn() {
		if (listener != null) {
			listener.showCardsAtTurn(this);
		}
	}
	
	public void showCardsAtWin() {
		if (listener != null) {
			listener.showCardsAtWin(this);
		}
	}
	
	public void quit() {
		if (listener != null) {
			listener.quit(this);
		}
	}
	
	public void pass() {
		if (listener != null) {
			listener.pass(this);
		}
	}
	
	public void win() {
		if (listener != null) {
			listener.win(this);
		}
	}
	
	public void declareNoWinners() {
		if (listener != null) {
			listener.declareNoWinners();
		}
	}
	
	public int getMoney() {
		return money;
	}

	public int getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void setStateListener(PlayerStateListener listener) {
		this.listener = listener;
	}
	
}
