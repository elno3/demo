package com.example.demo;

import java.util.List;

import org.junit.Test;

import com.basra.Card;
import com.basra.Pot;
import com.basra.Types;

import org.junit.Assert;

public class PotTest {

	@Test
	public void testWhenPutingCardSeven_thenReturnSixAndOne() {
		List<Card> card = List.of(new Card(Types.HEART, "1"), new Card(Types.CLUB, "4"),new Card(Types.HEART, "6"));
		
		Pot pot = new Pot(card);
		List<List<Card> >matchingCombination = pot.putCardOnTable(new Card(Types.CLUB,"7"));
		System.out.println(matchingCombination);
		List<List<Card>> expectedResult = List.of(List.of(new Card(Types.HEART, "1"),new Card(Types.HEART, "6")));
		Assert.assertEquals(expectedResult, matchingCombination);

	}
	
	@Test
	public void testWhen() {
		List<Card> card = List.of(
				new Card(Types.HEART, "5"), new Card(Types.CLUB, "1"),
				new Card(Types.HEART, "6"),	new Card(Types.HEART, "4"),
				new Card(Types.HEART, "9"));
		
		Pot pot = new Pot(card);
		List<List<Card> >matchingCombination = pot.putCardOnTable(new Card(Types.CLUB,"10"));
		System.out.println(matchingCombination);
		
//		List<List<Card>> expectedResult = List.of(List.of(new Card(Types.HEART, "1"),new Card(Types.HEART, "6")));
//		Assert.assertEquals(expectedResult, matchingCombination);

	}
}
