package com.kober.blackjack.controller.players;

import com.kober.blackjack.model.cards.Card;
import com.kober.blackjack.model.players.PlayerStateListener;
import com.kober.blackjack.model.players.User;
import com.kober.blackjack.view.View;

public class UserController extends User {
	View view;
	
	public UserController(String name, int money, PlayerStateListener listener, View view) {
		super(name, money, listener);
		this.view = view;
	}

	@Override
	public boolean decideToTakeCard(Card dealerCard) {
		while (true) {
			String result = view.askQuestion("Do you want to take a card, " + name + "? (y/n)");
			switch (result) {
				case "y":
					return true;
				case "n":
					return false;
			}
		}
	}
	
	@Override
	public boolean decideToBet() {
		while (true) {
			String result = view.askQuestion("Do you want to bet, " + name + "? You have " + getMoney() + "$ (y/n)");
			switch (result) {
				case "y": 
					return true;
				case "n":
					return false;
			}
		}
	}
	
}
