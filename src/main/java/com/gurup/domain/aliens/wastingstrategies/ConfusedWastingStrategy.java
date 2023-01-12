package com.gurup.domain.aliens.wastingstrategies;

public class ConfusedWastingStrategy implements WastingStrategy {
	
	private static int confusionCounter = 0;
	
	@Override
	public boolean wasteTime() {
		// TODO Auto-generated method stub
		if (confusionCounter == 2) {
			// waited two seconds, remove alien
			return false;
		}
		confusionCounter++;
		return false;
	}

}
