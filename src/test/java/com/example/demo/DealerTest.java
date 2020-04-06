package com.example.demo;

import org.junit.Test;

import com.basra.Dealer;
import com.basra.Player;
import com.basra.PlayingCard;

import junit.framework.Assert;

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
}
