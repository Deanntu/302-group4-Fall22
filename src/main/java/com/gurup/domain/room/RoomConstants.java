package com.gurup.domain.room;

import java.awt.Toolkit;

public enum RoomConstants {
	xStart(50),
	yStart(50),
	xLimit(Toolkit.getDefaultToolkit().getScreenSize().width - 100),
	yLimit(Toolkit.getDefaultToolkit().getScreenSize().height - 175);
	private final int value;
	RoomConstants(int value) {
		// TODO Auto-generated constructor stub
		 this.value = value;
	}
	public int getValue() {
		return value;
	}
}
