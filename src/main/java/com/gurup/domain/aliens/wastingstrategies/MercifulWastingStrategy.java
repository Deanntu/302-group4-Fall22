package com.gurup.domain.aliens.wastingstrategies;

import com.gurup.domain.room.Key;
import com.gurup.domain.room.Room;

public class MercifulWastingStrategy implements WastingStrategy {
	
	@Override
	public boolean wasteTime() {
		Key.hideKey(Room.getObjects());
		System.out.println("Hid key by Merciful");
		return false;// should remove alien after replacement
	}
}
