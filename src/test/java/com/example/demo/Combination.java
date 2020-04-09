package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.basra.Card;
import com.basra.Types;

class Combination {

	private static List<List<Card>> listAllComb = new ArrayList<>();

	/*
	 * arr[] ---> Input Array data[] ---> Temporary array to store current
	 * combination start & end ---> Staring and Ending indexes in arr[] index --->
	 * Current index in data[] r ---> Size of a combination to be printed
	 */
	private static void combinationUtil(List<Card> listCards, List<Card> outputCombination, int start, int end,
			int index, int r) {
		// Current combination is ready to be printed, print it
		if (index == r) {

			List<Card> singleList = new ArrayList<Card>();
			for (int j = 0; j < r; j++) {

				singleList.add(outputCombination.get(j));

			}
			listAllComb.add(singleList);
			return;
		}

		// replace index with all possible elements. The condition
		// "end-i+1 >= r-index" makes sure that including one element
		// at index will make a combination with remaining elements
		// at remaining positions
		for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
			outputCombination.add(index, listCards.get(i));
			combinationUtil(listCards, outputCombination, i + 1, end, index + 1, r);
		}
	}


	/* Driver function to check for above function */
	public static void main(String args[]) {

		List<Card> card = List.of(new Card(Types.HEART, "one"), new Card(Types.CLUB, "two"),
				new Card(Types.HEART, "three"), new Card(Types.HEART, "ten"));

		List<List<Card>> allPossibileCombinations = getAllPossibileCombinations(card);

		System.out.println(allPossibileCombinations);
		System.out.println(allPossibileCombinations.size());
	}

	public static List<List<Card>> getAllPossibileCombinations(List<Card> card) {

		int n = card.size();

		for (int i = 1; i <= n; i++) {

			List<Card> lisCombination = new ArrayList<Card>();

			combinationUtil(card, lisCombination, 0, n - 1, 0, i);
		}

		return listAllComb;

	}

}
