package com.example.demo;

import java.util.List;

import org.junit.Test;

import com.basra.Card;
import com.basra.Types;
import com.basra.Utilities;

import junit.framework.Assert;

public class VIpCardsTest {
	
	@Test
	public void testContatinCard() {
		
		List<Card> card = List.of(new Card(Types.HEART, "one"),new Card(Types.CLUB, "one"), new Card(Types.HEART, "two"));
		long values = Utilities.getValues(card);
		Assert.assertEquals(11, values);
		
	}

}
