package com.gurup.domain.saver;

import com.gurup.domain.room.Room;

public class RoomDatabaseGameSaverAdapter implements GameSaverAdapter {
	final RoomDatabaseGameSaver roomDatabaseGameSaver;

	public RoomDatabaseGameSaverAdapter() {
		roomDatabaseGameSaver = new RoomDatabaseGameSaver();
	}

	@Override
	public void save(String username, Object o) throws Exception {
		// TODO Auto-generated method stub
		roomDatabaseGameSaver.trySaveRoom(username, (Room) o);
	}

}
