package com.gurup.domain.aliens;

public interface Alien {


	void setActive(boolean b);

	void setX(int i);

	void setY(int i);

	boolean isActive();

	String getName();

	int[] rectArray();

}
