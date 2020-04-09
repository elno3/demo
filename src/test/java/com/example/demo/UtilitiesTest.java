package com.example.demo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.basra.Card;
import com.basra.Pot;
import com.basra.Types;
import com.basra.Utilities;

public class UtilitiesTest {

	@Test
	public void testGetAllCombinations() {

		List<Card> cards = List.of(new Card(Types.HEART, "one"), new Card(Types.CLUB, "two"),
				new Card(Types.HEART, "three"), new Card(Types.HEART, "ten"));

		Pot pot = Pot.builder().cards(cards).build();

		List<List<Card>> allPossibileCombinations = Utilities.getAllPossibileCombinations(pot);

		System.out.println(allPossibileCombinations);
		Assert.assertEquals(15, allPossibileCombinations.size());
	}

}
