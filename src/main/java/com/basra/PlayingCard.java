package com.basra;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PlayingCard {

    final private List<Card> playingCard;

    public PlayingCard() {

        List<String> of = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack",
                "queen", "king");

        playingCard = of.stream()
                .flatMap(nameOfCard -> Arrays.stream(Types.values())
                        .map(type -> new Card(type, nameOfCard))
                )
                .collect(Collectors.toList());

        shufflePlayingCard();

    }

    public void shufflePlayingCard() {
        Collections.shuffle(playingCard);
    }

    public List<Card> getFourCards() {
        List<Card> fourCards = IntStream.range(0, 4)
                .mapToObj(index -> playingCard.remove(index))
                .collect(Collectors.toList());
        return fourCards;
    }
}
