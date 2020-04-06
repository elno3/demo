package com.basra;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Utilities {

	
	public static long getValues(List<Card> card) {
		
				
		IntSummaryStatistics collect = 
		card.stream()
		.flatMap(singcard -> Arrays.stream(VipCards.values()).filter(vipCard -> vipCard.getCard().equals(singcard)))
		.collect(Collectors.summarizingInt(VipCards::getValue));
		
		long sum = collect.getSum();
		
		return sum;
		
		
	}
}
