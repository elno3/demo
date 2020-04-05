package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ManualSortList {

	@Test
	public void sortList() {

		// I commented the line below because a LIST created with the method Arrays.asList IS NOT MODIFIABLE
		// List<Integer> asList = Arrays.asList(5, 9, 2, 44);
		
		// To do my sort I need to create the list in standard way
		List<Integer> listSource = new ArrayList<>();
		listSource.add(6);
		listSource.add(1);
		listSource.add(6);		
		listSource.add(2);
		listSource.add(3);
		listSource.add(0);
		listSource.add(9);
		listSource.add(2);
		listSource.add(6);
		
		// the result of my sort
		List<Integer> listTarget = new ArrayList<Integer>();

		Integer lastNumber = null;

		while (!listSource.isEmpty()) {

			for (Integer currentNumber : listSource) {
				if (lastNumber == null || currentNumber < lastNumber) {
					lastNumber = currentNumber;
				}
			}

			for (Integer testToRemove : listSource) {
				if (testToRemove == lastNumber) {
					listTarget.add(lastNumber);					
				}
			}
			listSource.removeAll(listTarget);

			lastNumber = null;

		}

		System.out.println("List orderd " + listTarget);

	}

}
