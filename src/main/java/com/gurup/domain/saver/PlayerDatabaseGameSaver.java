package com.gurup.domain.saver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gurup.domain.DatabaseRequirements;
import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.VestPowerUp;

public class PlayerDatabaseGameSaver {
	private Boolean isProtected;
	private int remainingTime;
	private int remainingLife;
	private int xLocation;
	private int yLocation;
	private int bottlecount;
	private int vestcount;
	private int level;
	private int startingTime;
	private Boolean isKeyFound;

	public PlayerDatabaseGameSaver() {
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
		String sql = "CREATE TABLE IF NOT EXISTS public.player ( isprotected boolean, remainingtime integer, remaininglife integer, xlocation integer, ylocation integer, bottle integer, vest integer, level integer, startingtime integer, iskeyfound boolean, username text COLLATE pg_catalog.\"default\" ) TABLESPACE pg_default;";
		PreparedStatement createPlayer = connection.prepareStatement(sql);
		createPlayer.executeUpdate();
		connection.close();
	}

	public GameSaverOperationResults trySavePlayer(String username, Player player) throws Exception {
		// TODO Create table according to the requirements below.
	    level = player.getLevel();
		isProtected = player.isProtected();
		remainingTime = player.getRemainingTime();
		remainingLife = player.getLife();
		xLocation = player.getXCurrent();
		yLocation = player.getYCurrent();
		bottlecount = Game.getBag().getPowerUps().get(BottlePowerUp.getInstance(player));
		vestcount = Game.getBag().getPowerUps().get(VestPowerUp.getInstance(player));
		startingTime = player.getStartingTime();
		isKeyFound = player.getIsKeyFound();
		System.out.println(isKeyFound);
		
		return findByUserName(username) ? update(username) : savePlayer(username);
	}

	private GameSaverOperationResults savePlayer(String username) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = DriverManager.getConnection(DatabaseRequirements.url.getValue(),
				DatabaseRequirements.username.getValue(), DatabaseRequirements.password.getValue());
		String sql = "INSERT INTO public.player(isprotected, remainingtime, remaininglife, xlocation, ylocation, bottle, vest, level, startingtime, iskeyfound, username)	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setBoolean(1, isProtected);
		statement.setInt(2, remainingTime);
		statement.setInt(3, remainingLife);
		statement.setInt(4, xLocation);
		statement.setInt(5, yLocation);
		statement.setInt(6, bottlecount);
		statement.setInt(7, vestcount);
		statement.setInt(8, level);
		statement.setInt(9, startingTime);
		statement.setBoolean(10, isKeyFound);
		statement.setString(11, username);
		int affected = statement.executeUpdate();

		connection.close();
		return affected > 0 ? GameSaverOperationResults.SUCCESS : GameSaverOperationResults.FAIL;
	}

	private Boolean findByUserName(String username) throws Exception {
		Connection connection = DriverManager.getConnection(DatabaseRequirements.url.getValue(),
				DatabaseRequirements.username.getValue(), DatabaseRequirements.password.getValue());
		String sql = "select * from public.player where username = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			connection.close();
			return true;
		}
		return false;
	}

	private GameSaverOperationResults update(String username) throws Exception {
		Connection connection = DriverManager.getConnection(DatabaseRequirements.url.getValue(),
				DatabaseRequirements.username.getValue(), DatabaseRequirements.password.getValue());

		String sql = "UPDATE public.player SET isprotected=?, remainingtime=?, remaininglife=?, xlocation=?, ylocation=?, bottle=?, vest=?, level=?, startingtime=?, iskeyfound=?, username=? WHERE username = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setBoolean(1, isProtected);
		statement.setInt(2, remainingTime);
		statement.setInt(3, remainingLife);
		statement.setInt(4, xLocation);
		statement.setInt(5, yLocation);
		statement.setInt(6, bottlecount);
		statement.setInt(7, vestcount);
		statement.setInt(8, level);
		statement.setInt(9, startingTime);
		statement.setBoolean(10, isKeyFound);
		statement.setString(11, username);
		statement.setString(12, username);
		int affected = statement.executeUpdate();

		connection.close();
		return affected > 0 ? GameSaverOperationResults.SUCCESS : GameSaverOperationResults.FAIL;
	}

}
