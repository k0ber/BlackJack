package com.kober.blackjack.model;

import java.util.ArrayList;

import com.kober.blackjack.model.cards.Deck;
import com.kober.blackjack.model.players.Dealer;
import com.kober.blackjack.model.players.Player;

public class GameModel implements Model {
	public static final int DEFAULT_BET = 20;
	public static final int MAXIMUM_PLAYERS = 6;
	
	private boolean isCacheRemains;
	private boolean isDealerInGame;
	private ArrayList<Player> players;
	private int cache;
	
	public GameModel() {
		isCacheRemains = false;
		isDealerInGame = false;
		players = new ArrayList<Player>();
		cache = 0;
	}
	
	@Override
	public boolean addPlayer(Player player) {
		boolean isPlayerDealer = player instanceof Dealer;
		if (isPlayerDealer && isDealerInGame) {
			return false;
		}
		if (players.size() < MAXIMUM_PLAYERS || isPlayerDealer) {
			boolean status = players.add(player);
			if (status) {
				player.welcome();
				if (isPlayerDealer) {
					isDealerInGame = true;
				}
			}
			return status;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isReadyToStart() {
		return isDealerInGame && players.size() > 1;
	}
	
	@Override
	public int getDefaultBet() {
		return DEFAULT_BET;
	}
	
	/**
	 * Can be called many times, deck will be recreated if it is on low quantity
	 */
	@Override
	public void startGame() {
		if (Deck.getInstance().isLowCardsQuantity()) {
			Deck.getInstance().initNewCards();
			Deck.getInstance().shuffle();
		}
		for (Player player : players) {
			player.resetCards();
		}
		ArrayList<Player> winners = playRoundAndGetWinners();
		giveAwardsToWinners(winners);
		if (!isCacheRemains) {
			cache = 0;
		}
	}
	
	private ArrayList<Player> playRoundAndGetWinners() {
		ArrayList<Player> playersWithBlackJack = new ArrayList<Player>();
		ArrayList<Player> quitedPlayers = new ArrayList<Player>();
		// all players making bet
		for (Player player : players) {
			if (player.decideToBet()) {
				player.bet(DEFAULT_BET);
				cache += DEFAULT_BET;
			} else {
				quitedPlayers.add(player);
				player.quit();
			}
		}
		players.removeAll(quitedPlayers);
		
		// all players get cards
		for (Player player : players) {
			player.takeCard(Deck.getInstance().getCard(), Deck.getInstance().getCard());
			if (player.isHavingBlackJack()) {
				playersWithBlackJack.add(player);
			}
		}
		// dealer show his first card
		Dealer.getInstance().showFirstCard();
		
		// decide the winners
		if (playersWithBlackJack.size() > 0) {
			// if dealer not the only have BJ, then players wins
			if (playersWithBlackJack.size() > 1 
					&& playersWithBlackJack.contains(Dealer.getInstance())) {
				playersWithBlackJack.remove(Dealer.getInstance());
			}
			return playersWithBlackJack;
		} else {
			makeTurns();
			return getWinnersByScore();
		}
	}
	
	/**
	 * Ask all players to take a card until all players decide to stop
	 */
	private void makeTurns() {
		ArrayList<Player> activePlayers = new ArrayList<Player>(players);
		while (activePlayers.size() > 0) {
			ArrayList<Player> passedPlayers = new ArrayList<Player>();
			for (Player player : activePlayers) {
				if (player.decideToTakeCard(Dealer.getInstance().getCards().get(0)) 
						&& player.isCanTakeMore()) {
					player.takeCard(Deck.getInstance().getCard());
				} else {
					player.pass();
					passedPlayers.add(player);
				}
			}
			activePlayers.removeAll(passedPlayers);
		}
	}
	
	/**
	 * Find the players with maximum score that not higher than 21
	 * @return The list of winners
	 */
	private ArrayList<Player> getWinnersByScore() {
		int maxScore = 0;
		ArrayList<Player> winners = new ArrayList<Player>();
		
		// define max score
		for (Player player : players) {
			int playerScore = player.getScore();
			if (playerScore <= 21 && playerScore > maxScore) {
				maxScore = playerScore;
			};
		}
		// find all players who have max score
		for (Player player : players) {
			if (player.getScore() == maxScore) {
				winners.add(player);
			}
		}
		return winners;
	}

	private void giveAwardsToWinners(ArrayList<Player> winners) {
		if (winners == null || winners.size() == 0) {
			Dealer.getInstance().declareNoWinners();
			isCacheRemains = true;
		}
		for (Player winner : winners) {
			winner.win();
			winner.takeReward(cache / winners.size());
			winner.showCardsAtWin();
		}
	}

}
