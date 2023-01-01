package com.gurup.domain.saver;

public class GameSaver {
	GameSaverAdapter gameSaverAdapter;
	public GameSaver(SaverType type, SaverType state) {
		if(type.equals(SaverType.DATABASE)) {
			gameSaverAdapter = new DatabaseGameSaverAdapter(state);
		}
		else if(type.equals(SaverType.TXT)) {
			//gameSaverAdapter = new ObjectDrawerAdapter();
		}
	}
	public void draw(String username) {
		gameSaverAdapter.save();
	}
}
