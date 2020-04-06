package com.basra;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Pot {

	private List<Card> cards;

	
	
	public List<Card> addCard(Card cardIn) {
		
		cards.add(cardIn);
		
		//TODO : calculate if wine somtihing and how much
		 
		return null;

	}

	private void removeCards(List<Card> cardsToRemove) {
		cards.removeAll(cardsToRemove);
	}

	private void removeallCards() {
		cards.clear();
	}

}
