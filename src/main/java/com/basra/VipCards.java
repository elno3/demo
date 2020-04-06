package com.basra;

public enum VipCards {

	ONE_HEART(new Card(Types.HEART, "one"), 10), 
	ONE_DAIMOND(new Card(Types.DAIMOD, "one"),1), 
	ONE_SPADE(new Card(Types.SPADS, "one"), 1), 
	ONE_CLUB(new Card(Types.CLUB, "one"), 1);

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
