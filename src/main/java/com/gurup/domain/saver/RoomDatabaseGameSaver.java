package com.gurup.domain.saver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.gurup.domain.DatabaseRequirements;
import com.gurup.domain.Game;
import com.gurup.domain.aliens.Alien;
import com.gurup.domain.powerups.PowerUp;
import com.gurup.domain.room.Key;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.buildingobjects.BuildingObject;

public class RoomDatabaseGameSaver {

	public RoomDatabaseGameSaver() {
		try {
			Class.forName(DatabaseRequirements.driver.getValue());
			init();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init() throws Exception {
		Connection connection = DriverManager.getConnection(DatabaseRequirements.url.getValue(),
				DatabaseRequirements.username.getValue(), DatabaseRequirements.password.getValue());
		String sql = "CREATE TABLE IF NOT EXISTS public.object(name text COLLATE pg_catalog.\"default\",xstart integer,ystart integer,xlimit integer,ylimit integer,iskeyholder boolean, username text)TABLESPACE pg_default;";
		String sql2 = "CREATE TABLE IF NOT EXISTS public.alien(name text COLLATE pg_catalog.\"default\",xstart integer,ystart integer,xlimit integer,ylimit integer, username text)TABLESPACE pg_default;";
		String sql3 = "CREATE TABLE IF NOT EXISTS public.powerup(name text COLLATE pg_catalog.\"default\",xstart integer,ystart integer,xlimit integer,ylimit integer, username text)TABLESPACE pg_default;";
		PreparedStatement createObject = connection.prepareStatement(sql);
		PreparedStatement createAlien = connection.prepareStatement(sql2);
		PreparedStatement createPowerUp = connection.prepareStatement(sql3);
		createObject.executeUpdate();
		createAlien.executeUpdate();
		createPowerUp.executeUpdate();
		connection.close();
	}

	public GameSaverOperationResults trySaveRoom(String username, Room room) throws Exception {
		// TODO Create table according to the requirements below.
		truncate("object");
		truncate("alien");
		truncate("powerup");
		for (BuildingObject b : Room.getObjects()) {
			// Save Object
			saveObject(b, room.getKey());
		}
		savePowerUp(room.getCreated());
		for (Alien a : room.getCreatedAliens()) {
		    saveAlien(a);
		}
		return null;
	}

	private void truncate(String tableName) throws Exception {
		Connection connection = DriverManager.getConnection(DatabaseRequirements.url.getValue(),
				DatabaseRequirements.username.getValue(), DatabaseRequirements.password.getValue());
		String sql = "delete from public." + tableName + " where username=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, Game.getUsername());
		statement.executeUpdate();
		connection.close();
	}

	private GameSaverOperationResults saveObject(BuildingObject b, Key key) throws Exception {
		// String name, int xStart, int yStart, int xLimit, int yLimit
		Connection connection = DriverManager.getConnection(DatabaseRequirements.url.getValue(),
				DatabaseRequirements.username.getValue(), DatabaseRequirements.password.getValue());
		String sql = "INSERT INTO public.object(name, xstart, ystart, xlimit, ylimit, iskeyholder, username)VALUES (?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, b.getName());
		statement.setInt(2, b.getXCurrent());
		statement.setInt(3, b.getYCurrent());
		statement.setInt(4, b.getXLen());
		statement.setInt(5, b.getYLen());
		statement.setBoolean(6, key.getBuildingObject().equals(b));
		statement.setString(7, Game.getUsername());
		int affected = statement.executeUpdate();

		connection.close();
		return affected > 0 ? GameSaverOperationResults.SUCCESS : GameSaverOperationResults.FAIL;
	}

	private GameSaverOperationResults saveAlien(Alien a) throws Exception {
		if (a == null || !a.isActive())
			return GameSaverOperationResults.SUCCESS;
		Connection connection = DriverManager.getConnection(DatabaseRequirements.url.getValue(),
				DatabaseRequirements.username.getValue(), DatabaseRequirements.password.getValue());
		String sql = "INSERT INTO public.alien(name, xstart, ystart, xlimit, ylimit, username)VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, a.getName());
		statement.setInt(2, a.rectArray()[0]);
		statement.setInt(3, a.rectArray()[1]);
		statement.setInt(4, a.rectArray()[2]);
		statement.setInt(5, a.rectArray()[3]);
		statement.setString(6, Game.getUsername());
		int affected = statement.executeUpdate();

		connection.close();
		return affected > 0 ? GameSaverOperationResults.SUCCESS : GameSaverOperationResults.FAIL;
	}

	private GameSaverOperationResults savePowerUp(PowerUp p) throws Exception {
		if (p == null)
			return GameSaverOperationResults.SUCCESS;
		Connection connection = DriverManager.getConnection(DatabaseRequirements.url.getValue(),
				DatabaseRequirements.username.getValue(), DatabaseRequirements.password.getValue());
		String sql = "INSERT INTO public.powerup(name, xstart, ystart, xlimit, ylimit, username)VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, p.getName());
		statement.setInt(2, p.rectArray()[0]);
		statement.setInt(3, p.rectArray()[1]);
		statement.setInt(4, p.rectArray()[2]);
		statement.setInt(5, p.rectArray()[3]);
		statement.setString(6, Game.getUsername());
		int affected = statement.executeUpdate();

		connection.close();
		return affected > 0 ? GameSaverOperationResults.SUCCESS : GameSaverOperationResults.FAIL;
	}
}
