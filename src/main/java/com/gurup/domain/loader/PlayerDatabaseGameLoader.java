package com.gurup.domain.loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gurup.domain.Bag;
import com.gurup.domain.DatabaseRequirements;
import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.PlayerConstants;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.VestPowerUp;

public class PlayerDatabaseGameLoader {
    Player player = null;

    public PlayerDatabaseGameLoader() {

    }

    public Player loadPlayer(String username) throws Exception {
        Connection connection = DriverManager.getConnection(DatabaseRequirements.url.getValue(),
                DatabaseRequirements.username.getValue(), DatabaseRequirements.password.getValue());
        String sql = "SELECT * FROM public.player where username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        return parsePlayer(resultSet);
    }

    private Player parsePlayer(ResultSet resultSet) throws Exception {
        // TODO Auto-generated method stub
        if (resultSet.next()) {
            // isprotected, remainingtime, remaininglife, xlocation, ylocation, bottle,
            // vest, username
            player = new Player(resultSet.getInt("xlocation"), resultSet.getInt("ylocation"),
                    PlayerConstants.xLen.getValue(), PlayerConstants.yLen.getValue(),
                    resultSet.getInt("startingtime"));
            player.setRemainingTime(resultSet.getInt("remainingtime"));
            player.setLife(resultSet.getInt("remaininglife"));
            player.setProtected(resultSet.getBoolean("isprotected"));
            player.setLevel(resultSet.getInt("level"));
            player.setIsKeyFound(resultSet.getBoolean("iskeyfound"));
            int count = resultSet.getInt("bottle");
            BottlePowerUp.getInstance(player);
            Game.setBag(new Bag(player));
            Game.getBag().getPowerUps().put(BottlePowerUp.getInstance(player), count);
            Game.getBag().getPowerUps().put(VestPowerUp.getInstance(player), resultSet.getInt("vest"));
        }
        System.out.println("Player succesfully: " + player.getRemainingTime());
        return player;
    }

}