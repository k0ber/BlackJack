package com.kober.blackjack.model;

import com.kober.blackjack.model.players.Player;

public interface Model {

	public int getDefaultBet();
	public boolean addPlayer(Player player);
	public boolean isReadyToStart();
	public void startGame();
	
}
