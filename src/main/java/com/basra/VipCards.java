package com.basra;

public enum VipCards {

	ONE_HEART(new Card(Types.HEART, "1"), 10), 
	ONE_DAIMOND(new Card(Types.DAIMOD, "1"),1), 
	ONE_SPADE(new Card(Types.SPADS, "1"), 1), 
	ONE_CLUB(new Card(Types.CLUB, "1"), 1);

	Card card;
	int value;

	VipCards(Card card, int value) {
		this.card = card;
		this.value = value;
	}

	public Card getCard() {
		return card;
	}
	
	public int getValue() {
		return value;
	}
}
