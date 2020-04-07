package com.example.demo;

import java.util.List;

import com.basra.Card;
import com.basra.Types;

class Combination { 

	/* arr[] ---> Input Array 
	data[] ---> Temporary array to store current combination 
	start & end ---> Staring and Ending indexes in arr[] 
	index ---> Current index in data[] 
	r ---> Size of a combination to be printed */
	static void combinationUtil(Card arr[], Card data[], int start, 
								int end, int index, int r) 
	{ 
		// Current combination is ready to be printed, print it 
		if (index == r) 
		{ 
			for (int j=0; j<r; j++) 
				System.out.print(data[j]+" "); 
			System.out.println(""); 
			return; 
		} 

		// replace index with all possible elements. The condition 
		// "end-i+1 >= r-index" makes sure that including one element 
		// at index will make a combination with remaining elements 
		// at remaining positions 
		for (int i=start; i<=end && end-i+1 >= r-index; i++) 
		{ 
			data[index] = arr[i]; 
			combinationUtil(arr, data, i+1, end, index+1, r); 
		} 
	} 

	// The main function that prints all combinations of size r 
	// in arr[] of size n. This function mainly uses combinationUtil() 
	static void printCombination(Card arr[], int n, int r) 
	{ 
		// A temporary array to store all combination one by one 
		Card data[]=new Card[r]; 

		// Print all combination using temprary array 'data[]' 
		combinationUtil(arr, data, 0, n-1, 0, r); 
	} 

	/*Driver function to check for above function*/
	public static void main (String[] args) { 
		
		List<Card> card = List.of(new Card(Types.HEART, "one"), new Card(Types.CLUB, "one"),new Card(Types.HEART, "two"),new Card(Types.HEART, "ten"));
		
		Card[] arr = card.toArray(new Card [] {});
		 
		int n = arr.length; 
		
		for(int i =1;i<=n;i++) {
			
			printCombination(arr, n, i); 
		}
	} 
} 
