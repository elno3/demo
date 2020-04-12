package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import com.basra.Card;
import com.basra.Pot;
import com.basra.Types;

import org.junit.Assert;

public class PotTest {

	public void testWhenPutingCardSeven_thenReturnSixAndOne() {
		List<Card> card = List.of(new Card(Types.HEART, "1"), new Card(Types.CLUB, "4"), new Card(Types.HEART, "6"));

		Pot pot = new Pot(card);
		List<List<Card>> matchingCombination = pot.putCardOnTable(new Card(Types.CLUB, "7"));
		System.out.println(matchingCombination);
		List<List<Card>> expectedResult = List.of(List.of(new Card(Types.HEART, "1"), new Card(Types.HEART, "6")));
		Assert.assertEquals(expectedResult, matchingCombination);

	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testWhen() {
		List<Card> cards = List.of(new Card(Types.HEART, "5"), new Card(Types.CLUB, "1"), new Card(Types.HEART, "6"),
				new Card(Types.HEART, "2"), new Card(Types.CLUB, "2"), new Card(Types.HEART, "9"));

		Pot pot = new Pot(cards);

		Map<String, Long> collect = cards.stream()
				.collect(Collectors.groupingBy(card -> card.getName(), Collectors.counting()));
		// System.out.println(collect);
		Card playedCard = new Card(Types.CLUB, "10");
		List<List<Card>> allPossibileCombination = pot.putCardOnTable(playedCard);
		System.out.println("All possibile combination");
		allPossibileCombination.forEach(System.out::println);
		
		List<List<Card>> combinationWithJustOneCard = 
		allPossibileCombination.stream()
								.filter(list->list.size()==1).collect(Collectors.toList());

		List<List<Card>> finalResult = new ArrayList<>();

		for (int i = 0; i < allPossibileCombination.size(); i++) {

			List<Card> lastList = new ArrayList<>();
			lastList.addAll(allPossibileCombination.get(i));

			for (int x = i + 1; x < allPossibileCombination.size(); x++) {

				List<Card> currentList = allPossibileCombination.get(x);

				boolean anyInternalMatch = 
						currentList.stream()
									.anyMatch(cardFromCurrentList -> lastList.stream().
																		anyMatch(cardFromLastList -> cardFromCurrentList.equals(cardFromLastList)));

				if (!anyInternalMatch) {
					lastList.addAll(currentList);
				}
			}

			boolean anyExternalMatch = 
					finalResult.stream().
					anyMatch(l -> l.containsAll(lastList));

			if (!anyExternalMatch) {
				
				List<Card> lastListHavingSamePlayedCardName = 
				lastList.stream()
						.filter(c->c.getName().equals(playedCard.getName()))
						.collect(Collectors.toList());
				
				if(lastListHavingSamePlayedCardName.size()==0) {
					combinationWithJustOneCard.forEach(list->lastList.addAll(list));
				}
				
				finalResult.add(lastList);
			}
			
		}
		
		System.out.println("Final result:");
		finalResult.forEach(System.out::println);
	}

}
