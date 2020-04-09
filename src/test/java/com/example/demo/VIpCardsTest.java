package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.basra.Card;
import com.basra.Types;
import com.basra.Utilities;

import org.junit.Assert;;

public class VIpCardsTest {

	@Test
	public void testContatinCard() {

		List<Card> card = List.of(new Card(Types.HEART, "one"), new Card(Types.CLUB, "one"),new Card(Types.HEART, "two"));
		long values = Utilities.getValuesCard(card);
		Assert.assertEquals(11, values);

	}

	@Test
	public void testNumber() {
		List<String> l = List.of("1", "2", "3", "4", "5");
		Set<String> comb = new HashSet<String>();

		String all = "";
		String sub = "";
		for (int i = 0; i < l.size(); i++) {
			all = all + l.get(i);

			if (i < l.size() - 1) {

				comb.add(l.get(i));
			}

			int x = 0;
			while (x <= l.size()) {

				for (int j = x + 1; j < l.size(); j++) {
					sub = sub + l.get(j);
					comb.add(l.get(x) + l.get(j));

				}

				comb.add(sub);
				sub = "";

				comb.add(all);
				x++;
			}
		}
		System.out.println(comb + " " + comb.size());
	}

}
