package com.gurup.domain.saver;

import com.gurup.domain.room.Room;

public class RoomTXTGameSaverAdapter implements GameSaverAdapter{
    final RoomTXTGameSaver roomTXTGameSaver;

    public RoomTXTGameSaverAdapter() {
        roomTXTGameSaver = new RoomTXTGameSaver();
    }

    @Override
    public void save(String username, Object o) throws Exception {
        // TODO Auto-generated method stub
        roomTXTGameSaver.writeData(username, (Room) o);
    }
}
