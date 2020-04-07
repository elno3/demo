package com.example.demo;

import org.junit.Test;

import com.basra.Dealer;
import com.basra.Player;
import com.basra.PlayingCard;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DealerTest {

    @Test
    public void testDealer() {

        Player abuMalek = Player.builder().name("anas").build();
        Player elno3 = Player.builder().name("elno3").build();

        Dealer dealer = Dealer.builder()
                .playingCard(new PlayingCard())
                .abuMalek(abuMalek)
                .elno3(elno3)
                .build();

        dealer.start();

        Assert.assertEquals(40, dealer.getNumberOfRemainingCards());

//		abuMalek.play(card);

    }

    @Test
    public void name() {
        List<String> list = Arrays.asList("1", "2", "3", "4","5");
        final List<String> result = doCombination(0, 1, list, new ArrayList<>());
        System.out.println("result is: " + result);

    }

    private List<String> doCombination(int i, int secondCursor, List<String> list, List<String> combo) {

        if (i < list.size()) {
            String sub = list.get(i);
            System.out.println("sub: " + sub);
            for (int j = secondCursor; j < list.size(); j++) {
                sub = sub + list.get(j);
                combo.add(sub);
                System.out.println("combo:" + combo);
            }
            secondCursor++;
            
            if (secondCursor > list.size() || secondCursor == list.size()) {
                i++;
                System.out.println("moving to i:" + i);
                secondCursor =i+1;
            }
            System.out.println("calling sort with i: " + i + " and second cursor: " + secondCursor);
            return doCombination(i, secondCursor, list, combo);
        }
        return combo;
    }
}
