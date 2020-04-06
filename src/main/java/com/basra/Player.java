package com.basra;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Player {
	

	private String name;
	private Integer score;
	private Integer cardscounter;
	private Pot pot;
	private List<Card> cardsInHand;
	
	
	public void play(Card card) {		
		
		/* pot.add(card);
		 * check if remove something
		 * 
		 * 
		 * 
		 * pot.update();
		 * */
	}
	

}
