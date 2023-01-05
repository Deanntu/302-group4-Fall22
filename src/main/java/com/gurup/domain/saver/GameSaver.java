package com.gurup.domain.saver;

public class GameSaver {
	GameSaverAdapter gameSaverAdapter;

	public GameSaver(SaverType type, SaverType state) {
		if (type.equals(SaverType.DATABASE)) {
			if (state.equals(SaverType.PLAYER)) {
				gameSaverAdapter = new PlayerDatabaseGameSaverAdapter();
			}
			else if (state.equals(SaverType.ROOM)) {
				gameSaverAdapter = new RoomDatabaseGameSaverAdapter();
			}
			
		} else if (type.equals(SaverType.TXT)) {
			// gameSaverAdapter = new ObjectDrawerAdapter();
		}
	}

	public void save(String username,Object o) throws Exception {
		gameSaverAdapter.save(username, o);
	}
}
