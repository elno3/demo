package com.basra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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
		System.out.println("playing card:"+ card);
		/* pot.add(card);
		 * check if remove something
		 * 
		 * 
		 * 
		 * pot.update();
		 * */
	}
	

}
