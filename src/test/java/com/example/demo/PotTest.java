package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.basra.Card;
import com.basra.Pot;
import com.basra.Types;

import org.junit.Assert;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PotTest {


    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testWhen() {
        List<Card> cards = List.of(
                new Card(Types.HEART, "5"),
                new Card(Types.CLUB, "1"),
                new Card(Types.HEART, "6"),
                new Card(Types.HEART, "2"),
                new Card(Types.CLUB, "2"),
                new Card(Types.HEART, "9"));

        Pot pot = new Pot(cards);

        Map<String, Long> collect = cards.stream()
                .collect(Collectors.groupingBy(card -> card.getName(), Collectors.counting()));
        // System.out.println(collect);
        Card playedCard = new Card(Types.CLUB, "9");
        List<List<Card>> allPossibileCombination = pot.putCardOnTable(playedCard);
        System.out.println("All possibile combination");
        allPossibileCombination.forEach(System.out::println);

        List<List<Card>> combinationWithJustOneCard =
                allPossibileCombination.stream()
                        .filter(list -> list.size() == 1).collect(Collectors.toList());

        List<List<Card>> finalResult = new ArrayList<>();

        for (int i = 0; i < allPossibileCombination.size(); i++) {

            List<Card> lastList = new ArrayList<>();
            lastList.addAll(allPossibileCombination.get(i));

            for (int x = i + 1; x < allPossibileCombination.size(); x++) {

                List<Card> currentList = allPossibileCombination.get(x);

                boolean anyInternalMatch =
                        currentList.stream()
                                .anyMatch(cardFromCurrentList -> lastList.stream().
                                        anyMatch(cardFromLastList -> cardFromCurrentList.equals(cardFromLastList)));

                if (!anyInternalMatch) {
                    lastList.addAll(currentList);
                }
            }

            boolean anyExternalMatch =
                    finalResult.stream().
                            anyMatch(l -> l.containsAll(lastList));

            if (!anyExternalMatch) {

                List<Card> lastListHavingSamePlayedCardName =
                        lastList.stream()
                                .filter(c -> c.getName().equals(playedCard.getName()))
                                .collect(Collectors.toList());

                if (lastListHavingSamePlayedCardName.size() == 0) {
                    combinationWithJustOneCard.forEach(list -> lastList.addAll(list));
                }

                finalResult.add(lastList);
            }

        }

        System.out.println("Final result:");
        finalResult.forEach(System.out::println);
    }


    private List<List<Card>> getAllPossibleCardCombinationToEat(int startIndex, int secondIndex, List<List<Card>> allPossibileCombination, List<List<Card>> finalResult) {

        if (secondIndex == allPossibileCombination.size() && startIndex != allPossibileCombination.size() - 1) {
            startIndex++;
            secondIndex = startIndex + 1;
            return getAllPossibleCardCombinationToEat(startIndex, secondIndex, allPossibileCombination, finalResult);
        }
        List<Card> currentList = new ArrayList<>();
        currentList.addAll(allPossibileCombination.get(startIndex));
        for (int i = secondIndex; i < allPossibileCombination.size(); i++) {
            List<Card> subList = allPossibileCombination.get(i);
            boolean anyInternalMatch =
                    subList.stream()
                            .anyMatch(cardFromCurrentList -> currentList.contains(cardFromCurrentList));
            if (!anyInternalMatch) {
                currentList.addAll(subList);
            }
        }
        boolean anyExternalMatch = finalResult.stream()
                .anyMatch(l -> l.containsAll(currentList));
        if (!anyExternalMatch) {
            finalResult.add(currentList);
        }
        if (startIndex == allPossibileCombination.size() - 1) {
            return finalResult;
        } else {
            return getAllPossibleCardCombinationToEat(startIndex, ++secondIndex, allPossibileCombination, finalResult);
        }
    }

    public void testPossibleCardsToEat(List<Card> potCards, Card playedCard, List<List<Card>> expectedCardsCombinationToEat) {

        System.out.println("Card played: " + playedCard);
        Pot pot = new Pot(potCards);
        System.out.println("********Pot Cards***********:");
        potCards.forEach(System.out::println);
        List<List<Card>> allPossibileCombination = pot.putCardOnTable(playedCard);
        System.out.println("**********All possible combinations********:");
        allPossibileCombination.forEach(System.out::println);
        System.out.println("\n**********Possible Cards to eat********:");
        List<List<Card>> finalResult = new ArrayList<>();
        List<List<Card>> cardCombinationToEat = getAllPossibleCardCombinationToEat(0, 1, allPossibileCombination, finalResult);
        //cardCombinationToEat.forEach(System.out::println);
        System.out.println(cardCombinationToEat.stream().map(l -> l.toString()).collect(joining("\n OR\n")));


        Assert.assertEquals(expectedCardsCombinationToEat, cardCombinationToEat);

    }

    @Test
    public void useCaseOne() {
        List<Card> potCards = List.of(
                new Card(Types.HEART, "5"),
                new Card(Types.CLUB, "1"),
                new Card(Types.HEART, "6"),
                new Card(Types.HEART, "2"),
                new Card(Types.CLUB, "2"),
                new Card(Types.HEART, "4"));

        List<List<Card>> expectedCardsCombinationToEat = List.of(
                List.of(new Card(Types.HEART, "5"), new Card(Types.HEART, "4"), new Card(Types.CLUB, "1"), new Card(Types.HEART, "6"), new Card(Types.HEART, "2")),
                List.of(new Card(Types.HEART, "5"), new Card(Types.HEART, "4"), new Card(Types.CLUB, "1"), new Card(Types.HEART, "6"), new Card(Types.CLUB, "2")),
                List.of(new Card(Types.HEART, "5"), new Card(Types.HEART, "2"), new Card(Types.CLUB, "2")),
                List.of(new Card(Types.CLUB, "1"), new Card(Types.HEART, "2"), new Card(Types.CLUB, "2"), new Card(Types.HEART, "4"))
        );

        testPossibleCardsToEat(potCards, new Card(Types.CLUB, "9"), expectedCardsCombinationToEat);
    }

    @Test
    public void useCaseTwo() {
        List<Card> potCards = List.of(
                new Card(Types.HEART, "5"),
                new Card(Types.CLUB, "1"),
                new Card(Types.HEART, "6"),
                new Card(Types.HEART, "2"),
                new Card(Types.CLUB, "2"),
                new Card(Types.HEART, "9"));

        List<List<Card>> expectedCardsCombinationToEat = List.of(
                List.of(new Card(Types.HEART, "9"), new Card(Types.HEART, "5"), new Card(Types.HEART, "2"), new Card(Types.CLUB, "2")),
                List.of(new Card(Types.HEART, "9"), new Card(Types.CLUB, "1"), new Card(Types.HEART, "6"), new Card(Types.HEART, "2")),
                List.of(new Card(Types.HEART, "9"), new Card(Types.CLUB, "1"), new Card(Types.HEART, "6"), new Card(Types.CLUB, "2"))
        );

        testPossibleCardsToEat(potCards, new Card(Types.CLUB, "9"), expectedCardsCombinationToEat);
    }

    @Test
    public void useCaseThree() {
        List<Card> potCards = List.of(
                new Card(Types.HEART, "3"),
                new Card(Types.CLUB, "7"),
                new Card(Types.HEART, "6"),
                new Card(Types.HEART, "1"),
                new Card(Types.CLUB, "2"),
                new Card(Types.HEART, "9"));

        List<List<Card>> expectedCardsCombinationToEat = List.of(
                List.of(new Card(Types.HEART, "3"), new Card(Types.CLUB, "7"), new Card(Types.HEART, "1"), new Card(Types.HEART, "9")),
                List.of(new Card(Types.HEART, "3"), new Card(Types.HEART, "6"), new Card(Types.HEART, "1")),
                List.of(new Card(Types.CLUB, "7"), new Card(Types.HEART, "1"), new Card(Types.CLUB, "2"))
        );

        testPossibleCardsToEat(potCards, new Card(Types.CLUB, "10"), expectedCardsCombinationToEat);
    }

    @Test
    public void useCaseFour() {
        List<Card> potCards = List.of(
                new Card(Types.HEART, "3"),
                new Card(Types.CLUB, "king"),
                new Card(Types.HEART, "queen"),
                new Card(Types.HEART, "1"),
                new Card(Types.CLUB, "2"),
                new Card(Types.HEART, "4"));

        List<List<Card>> expectedCardsCombinationToEat = List.of(
                List.of(new Card(Types.HEART, "3"), new Card(Types.HEART, "4")),
                List.of(new Card(Types.HEART, "1"), new Card(Types.CLUB, "2"), new Card(Types.HEART, "4"))
        );

        testPossibleCardsToEat(potCards, new Card(Types.CLUB, "7"), expectedCardsCombinationToEat);
    }

    @Test
    public void useCaseFive_Jack() {
        List<Card> potCards = new ArrayList<>();
        potCards.add(new Card(Types.HEART, "10"));
        potCards.add(new Card(Types.CLUB, "king"));
        potCards.add(new Card(Types.HEART, "4"));

        List<List<Card>> expectedCardsCombinationToEat = List.of(
                List.of(new Card(Types.HEART, "10"), new Card(Types.CLUB, "king"), new Card(Types.HEART, "4"), new Card(Types.CLUB, "jack"))
        );

        testPossibleCardsToEat(potCards, new Card(Types.CLUB, "jack"), expectedCardsCombinationToEat);
    }

    @Test
    public void useCaseSix_WhenNoMatches_ThenCardStayesInPot() {
        List<Card> potCards = new ArrayList<>();
        potCards.add(new Card(Types.HEART, "10"));
        potCards.add(new Card(Types.CLUB, "king"));
        potCards.add(new Card(Types.HEART, "4"));

        Pot pot = new Pot(potCards);
        assertEquals(3, pot.getCards().size());

        Card cardIn = new Card(Types.SPADS, "3");
        pot.putCardOnTable(cardIn);
        assertTrue(pot.getCards().contains(cardIn));
        assertEquals(4, pot.getCards().size());
    }
}
