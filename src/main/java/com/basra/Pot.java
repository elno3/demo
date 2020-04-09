package com.basra;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.UtilitiesTest;

import ch.qos.logback.classic.net.SyslogAppender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Pot {

	private List<Card> cards;
	static private List<Card> cardsImageKQ = List.of(new Card(Types.CLUB,"king"),new Card(Types.DAIMOD,"king"),new Card(Types.HEART,"king"),new Card(Types.SPADS,"king"),new Card(Types.CLUB,"queen"),new Card(Types.DAIMOD,"queen"),new Card(Types.HEART,"queen"),new Card(Types.SPADS,"queen"));

	@SuppressWarnings("deprecation")
	public List<List<Card>> putCardOnTable(Card cardIn) {
		
		List<List<Card>> ListToReturn = new ArrayList<>();
		
		String nameCard = cardIn.getName();
		
		if(nameCard.equals("jack")) {
			
			 cards.add(cardIn);
			 ListToReturn.add(cards);
			 return ListToReturn;
			 
			
		}else if (nameCard.equals("queen") ||nameCard.equals("king")) {
			
			List<Card> listToReturn =
			cards.stream()
				 .filter(card->card.getName().equals(nameCard))
				 .collect(Collectors.toList());
			listToReturn.add(cardIn);
			ListToReturn.add(listToReturn);
			return ListToReturn;
					
			
		}else {
			
			List<List<Card>> allPossibileCombinations = Utilities.getAllPossibileCombinations(this);			
			
			List<List<Card>> listCombinationNoImg = allPossibileCombinations.stream()
			.filter(listSingleComb-> Collections.disjoint(listSingleComb, cardsImageKQ))
			.collect(Collectors.toList());
			
			List<List<Card>> combinationsMatchInputSumCards = 
			listCombinationNoImg
			.stream()
			.filter(cardList -> cardList.stream()
										.mapToInt(card -> Integer.valueOf(card.getName()) )
										.sum() == Integer.valueOf(nameCard))
			.collect(Collectors.toList());
			
			
			
			return combinationsMatchInputSumCards;
		}
		
		 

	}

	private void removeCards(List<Card> cardsToRemove) {
		cards.removeAll(cardsToRemove);
	}

	private void removeallCards() {
		cards.clear();
	}

}
