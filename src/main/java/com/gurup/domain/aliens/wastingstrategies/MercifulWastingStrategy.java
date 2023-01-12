package com.gurup.domain.aliens.wastingstrategies;

import com.gurup.domain.room.Key;
import com.gurup.domain.room.Room;

public class MercifulWastingStrategy implements WastingStrategy {
	
	@Override
	public boolean wasteTime() {
		Key.hideKey(Room.getObjects());
		System.out.println("Hid key by Merciful");
		System.out.println("New Key Coordinate is: x: " + Key.getInstance().getBuildingObject().getXCurrent() + " y: " + Key.getInstance().getBuildingObject().getYCurrent());
		return false;// should remove alien after replacement
	}
}
