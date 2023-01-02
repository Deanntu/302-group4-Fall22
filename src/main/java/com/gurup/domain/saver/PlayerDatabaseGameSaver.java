package com.gurup.domain.saver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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

	public PlayerDatabaseGameSaver() {
		try {
			Class.forName(DatabaseRequirements.driver.getValue());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GameSaverOperationResults savePlayer(String username, Player player) throws Exception {
		// TODO Create table according to the requirements below.
		isProtected = player.getIsProtected();
		remainingTime = player.getRemainingTime();
		remainingLife = player.getRemainingLife();
		xLocation = player.getX();
		yLocation = player.getY();
		bottlecount = Game.getBag().getPowerUps().get(BottlePowerUp.getInstance(player));
		vestcount = Game.getBag().getPowerUps().get(VestPowerUp.getInstance(player));

		return trySavePlayer(username);
	}

	private GameSaverOperationResults trySavePlayer(String username) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = DriverManager.getConnection(DatabaseRequirements.url.getValue(),
				DatabaseRequirements.username.getValue(), DatabaseRequirements.password.getValue());
		String sql = "INSERT INTO public.player(isprotected, remainingtime, remaininglife, xlocation, ylocation, bottle, vest, username)	VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setBoolean(1, isProtected);
		statement.setInt(2, remainingTime);
		statement.setInt(3, remainingLife);
		statement.setInt(4, xLocation);
		statement.setInt(5, yLocation);
		statement.setInt(6, bottlecount);
		statement.setInt(7, vestcount);
		statement.setString(8, username);
		int affected = statement.executeUpdate();

		connection.close();
		return affected > 0 ? GameSaverOperationResults.SUCCESS : GameSaverOperationResults.FAIL;
	}
}
