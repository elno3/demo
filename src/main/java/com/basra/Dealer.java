package com.basra;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dealer {

	private PlayingCard playingCard;
	private Pot pot;
	private Player abuMalek;
	private Player elno3;
	
	public void start() {
		// TODO Auto-generated method stub
		playingCard.shufflePlayingCard();
		
		abuMalek.setCardsInHand(playingCard.getFourCards());
		elno3.setCardsInHand(playingCard.getFourCards());
		List<Card> cardForPot = playingCard.getFourCards();
		this.pot = new Pot(cardForPot);
		
		System.out.println(cardForPot);
		
		
	}
	
	public int getNumberOfRemainingCards() {
		return playingCard.getPlayingCard().size();
	}
}
