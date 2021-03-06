package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class ManualSortList {
	
	@Test
	public void sortcollectionCaller() {

		// I commented the line below because a LIST created with the method Arrays.asList IS NOT MODIFIABLE
		// if you try to remove an element from the list below, an exception is throwed.. 
		// List<Integer> asList = Arrays.asList(5, 9, 2, 44);
		
		// To do my sort I need to create the list in standard way
		List<String> listToSort = new ArrayList<>();
		listToSort.add(6+"");
		listToSort.add(1+"");
		listToSort.add(6+"");		
		listToSort.add(2+"");
		listToSort.add(3+"");
		listToSort.add(0+"");
		listToSort.add(9+"");		
		listToSort.add(-5+"");
		listToSort.add(2+"");
		listToSort.add(6+"");
		Collection<String> sortCollection = sortCollection(listToSort);
		System.out.println(sortCollection);
		}
	

	@Test
	public void sortList() {

		// I commented the line below because a LIST created with the method Arrays.asList IS NOT MODIFIABLE
		// if you try to remove an element from the list below, an exception is throwed.. 
		// List<Integer> asList = Arrays.asList(5, 9, 2, 44);
		
		// To do my sort I need to create the list in standard way
		List<Integer> listToSort = new ArrayList<>();
		listToSort.add(6);
		listToSort.add(1);
		listToSort.add(6);		
		listToSort.add(2);
		listToSort.add(3);
		listToSort.add(0);
		listToSort.add(9);		
		listToSort.add(-5);
		listToSort.add(2);
		listToSort.add(6);
		
		// temporany list to keep the order
		List<Integer> tempList = new ArrayList<Integer>();

		Integer lastNumber = null;

		while (!listToSort.isEmpty()) {

			for (Integer currentNumber : listToSort) {
				if (lastNumber == null || currentNumber < lastNumber) {
					lastNumber = currentNumber;
				}
			}

			for (Integer testToRemove : listToSort) {
				if (testToRemove == lastNumber) {
					tempList.add(lastNumber);					
				}
			}
			listToSort.removeAll(tempList);

			lastNumber = null;

		}
		
		listToSort.addAll(tempList);

		System.out.println("List orderd " + listToSort);

	}
	
	

	public <T extends Comparable<? super T>> Collection<T> sortCollection(Collection<T> collectionIn) {

		// temporany list to keep the order
		Collection<T> tempList = new ArrayList<T>();

		T lastNumber = null;

		while (!collectionIn.isEmpty()) {

			for (T currentNumber : collectionIn) {
				if (lastNumber == null || currentNumber.compareTo(lastNumber) < 0) {
					lastNumber = currentNumber;
				}
			}

			for (T testToRemove : collectionIn) {
				if (testToRemove == lastNumber) {
					tempList.add(lastNumber);
				}
			}
			collectionIn.removeAll(tempList);

			lastNumber = null;

		}

		collectionIn.addAll(tempList);
		
		return collectionIn;

	}

}
