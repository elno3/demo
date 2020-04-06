package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.basra.Card;
import com.basra.PlayingCard;
import com.basra.Pot;
import com.basra.Types;

public class PlayingCardTest {

	@Test
	public void testPalyingCard() {
		PlayingCard playCard = new PlayingCard();

		List<Card> playingCard = playCard.getPlayingCard();
		System.out.println(playingCard.size());
		System.out.println(playingCard);
		//
		//
		// List<Card> mixedPlayingCard = playCard.mixedPlayingCard();
		// System.out.println(mixedPlayingCard.size());
		// System.out.println(mixedPlayingCard);
		//
		// List<Card> mixedPlayingCard2 = playCard.mixedPlayingCard();
		// System.out.println(mixedPlayingCard2.size());
		// System.out.println(mixedPlayingCard2);

	}

	@Test
	public void testPot() {
		List<Card> potCared = new ArrayList();
		potCared.add(new Card(Types.DAIMOD, "seven"));

		Pot.builder().cards(potCared).build();
		new Pot(potCared);
		
	}

}
