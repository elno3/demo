package com.basra;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PlayingCard {

	final private Set<Card> playingCard;

	public PlayingCard() {

		Set<String> of = Set.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king");

		playingCard = of.stream()
				.flatMap(nameOfCard -> 
									Arrays.stream(Types.values())
									.map(type -> new Card(type, nameOfCard)))
				.collect(Collectors.toSet());

	}
}
