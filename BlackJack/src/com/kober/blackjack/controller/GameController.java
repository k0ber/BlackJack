package com.kober.blackjack.controller;

import com.kober.blackjack.controller.players.DealerStateListener;
import com.kober.blackjack.controller.players.PlayersStateListener;
import com.kober.blackjack.controller.players.UserController;
import com.kober.blackjack.model.GameModel;
import com.kober.blackjack.model.Model;
import com.kober.blackjack.model.players.*;
import com.kober.blackjack.view.ConsoleView;
import com.kober.blackjack.view.View;

public class GameController {
	private static Model model;
	private static View view;

	public static void main(String[] args) {
		model = new GameModel();
		view = new ConsoleView();
		addDealer();
		boolean wantExit = false;

		view.showLabel("The default bet is: " + model.getDefaultBet() + "$");
		while (!wantExit) {
			String answer = view.askOptions(new String[] { "[1][Add player]", "[2][Add bot (easy)]",
					"[3][Add bot (expert)]", "[4][Play]", "[5][Quit]" });
			switch (answer) {
			case "1":
				showAddResult(addPlayer());
				break;
			case "2":
				showAddResult(addBot(false));
				break;
			case "3":
				showAddResult(addBot(true));
				break;
			case "4":
				play();
				break;
			case "5":
				wantExit = true;
			}
		}
		view.exit();
	}

	private static void addDealer() {
		Dealer dealer = Dealer.getInstance();
		dealer.setStateListener(new DealerStateListener(view));
		model.addPlayer(dealer);
	}

	/**
	 * 
	 * @return
	 */
	private static boolean addPlayer() {
		String name = view.askQuestion("What is player's name?").trim();
		if (name.isEmpty()) {
			return false;
		}
		String money = view.askQuestion("How much money do you have?");
		try {
			model.addPlayer(new UserController(name, Integer.valueOf(money), new PlayersStateListener(view), view));
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private static boolean addBot(boolean isExpert) {
		String name = view.askQuestion("What is bot's name?").trim();
		if (name.isEmpty()) {
			return false;
		}
		String money = view.askQuestion("How much money does he have?");
		try {
			model.addPlayer(isExpert ? new BotExpert(name, Integer.valueOf(money), new PlayersStateListener(view))
					: new Bot(name, Integer.valueOf(money), new PlayersStateListener(view)));
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private static void play() {
		model.addPlayer(Dealer.getInstance());
		if (model.isReadyToStart()) {
			model.startGame();
		} else {
			view.showLabel("You cannot start game now.");
		}
	}

	private static void showAddResult(boolean success) {
		if (!success) {
			view.showLabel("Something incorrect is enter. Try again!");
		}
	}
}
