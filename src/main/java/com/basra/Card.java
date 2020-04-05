package com.basra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Card implements Comparable<Card> {

	private Types type;
	private String name;

	@Override
	public int compareTo(Card o) {
		return 0;
	}

}
