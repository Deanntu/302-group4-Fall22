package com.gurup.domain.room;

import java.util.ArrayList;

import com.gurup.domain.room.buildingobjects.BuildingObject;

public class Key {

	private BuildingObject buildingObject;
	
	public BuildingObject getBuildingObject() {
		return buildingObject;
	}
	
	public void setBuildingObject(BuildingObject buildingObject) {
		this.buildingObject = buildingObject;
	}
	
	public void hideKey(ArrayList<BuildingObject> objects) {
		int index = getRandomNumber(0,objects.size());
		System.out.println(index);
		this.buildingObject = objects.get(index);		
	}
	private int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
}