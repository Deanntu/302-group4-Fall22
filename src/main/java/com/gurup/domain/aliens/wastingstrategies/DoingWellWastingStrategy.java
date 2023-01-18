package com.gurup.domain.aliens.wastingstrategies;

import com.gurup.domain.room.Key;
import com.gurup.domain.room.Room;

public class DoingWellWastingStrategy implements WastingStrategy {
	
	private static int wasteCounter = 3;
	
	@Override
	public boolean wasteTime() {
		// TODO Auto-generated method stub
		if (wasteCounter == 3) {
			wasteCounter = 1;
			Key.hideKey(Room.getObjects());
			System.out.println("Hid key by DoingWell");
			System.out.println("New Key Coordinate is: x: " + Key.getInstance().getBuildingObject().getXCurrent() + " y: " + Key.getInstance().getBuildingObject().getYCurrent());
		}
		else {
			wasteCounter++;
		}
		return true;// alien never disappears in this case
	}

}
