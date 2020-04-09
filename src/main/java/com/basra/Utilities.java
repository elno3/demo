package com.basra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Utilities {

	public static long getValuesCard(List<Card> card) {

		IntSummaryStatistics collect = 
				card.stream()
				.flatMap(singcard ->
						Arrays.stream(VipCards.values())
							   .filter(vipCard -> vipCard.getCard().equals(singcard)))
				.collect(Collectors.summarizingInt(VipCards::getValue));

		long sum = collect.getSum();

		return sum;
	}

	public static List<List<Card>> getAllPossibileCombinations(Pot pot) {
		
		List<Card> listCardsInput = pot.getCards();

		int listCardsInputSize = listCardsInput.size();
		List<List<Card>> listAllCombinations = new ArrayList<>();

		for (int x = 1; x <= listCardsInputSize; x++) {

			List<Card> lisOneCombination = new ArrayList<Card>();
			int index = 0;
			int end = listCardsInputSize - 1;
			int start = 0;

			collectCombinations(listCardsInput, lisOneCombination, start, end, index, x, listAllCombinations);
		}

		return listAllCombinations;

	}
	

	private static void collectCombinations(List<Card> listCardsInput, List<Card> lisOneCombination, int start, int end,
			int index, int x, List<List<Card>> listAllCombinations) {
		if (index == x) {

			List<Card> singleList = new ArrayList<Card>();
			for (int j = 0; j < x; j++) {

				singleList.add(lisOneCombination.get(j));

			}
			listAllCombinations.add(singleList);
		}

		for (int i = start; i <= end && end - i + 1 >= x - index; i++) {
			lisOneCombination.add(index, listCardsInput.get(i));
			collectCombinations(listCardsInput, lisOneCombination, i + 1, end, index + 1, x, listAllCombinations);
		}
	}

}
