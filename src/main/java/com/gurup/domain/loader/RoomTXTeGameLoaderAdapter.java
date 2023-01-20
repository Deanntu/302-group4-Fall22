package com.gurup.domain.loader;

public class RoomTXTeGameLoaderAdapter implements GameLoaderAdapter {
    RoomTXTGameLoader roomTXTGameLoader;

    public RoomTXTeGameLoaderAdapter() {
        roomTXTGameLoader = new RoomTXTGameLoader();
    }
    @Override
    public Object load(String username) throws Exception {
        // TODO Auto-generated method stub
        return roomTXTGameLoader.loadRoom(username);
    }
}