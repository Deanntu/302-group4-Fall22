package com.gurup.domain.loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gurup.domain.DatabaseRequirements;
import com.gurup.domain.Game;
import com.gurup.domain.aliens.Alien;
import com.gurup.domain.aliens.BlindAlien;
import com.gurup.domain.aliens.ShooterAlien;
import com.gurup.domain.aliens.TimeWastingAlien;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.HealthPowerUp;
import com.gurup.domain.powerups.HintPowerUp;
import com.gurup.domain.powerups.PowerUp;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.powerups.TimePowerUp;
import com.gurup.domain.powerups.VestPowerUp;
import com.gurup.domain.room.Key;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.RoomConstants;
import com.gurup.domain.room.buildingobjects.BuildingObject;
import com.gurup.domain.room.buildingobjects.BuildingObjectFactory;

public class RoomDatabaseGameLoader {
    private Room room;

    public Room loadRoam(String username) throws Exception {
        ArrayList<BuildingObject> loadedObjects = null;
        room = new Room("Student Center", RoomConstants.xStart.getValue(), RoomConstants.yStart.getValue(),
                RoomConstants.xLimit.getValue(), RoomConstants.yLimit.getValue(), Game.getPlayer());
        loadedObjects = getListofObjects(username);
        room.setObjects(loadedObjects);
        room.setCreated(getPowerUp(username));
        room.setCreatedAliens(getAliens(username));
        return room;
    }

    private Alien[] getAliens(String username) throws Exception {
        Connection connection = DriverManager.getConnection(DatabaseRequirements.url.getValue(),
                DatabaseRequirements.username.getValue(), DatabaseRequirements.password.getValue());
        String sql = "SELECT * FROM public.alien where username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        Alien[] aliens = new Alien[3];
        while (resultSet.next()) {
            Alien alien = parseAlien(resultSet);
            if (alien instanceof BlindAlien) {
                aliens[0] = alien;
            }
            else if (alien instanceof ShooterAlien) {
                aliens[1] = alien;
            }
            else if (alien instanceof TimeWastingAlien) {
                aliens[2] = alien;
            }
            else {
                throw new Exception("alien is not an instance of any alien type, should be impossible");
            }
        }
        return aliens;
    }

    private PowerUp getPowerUp(String username) throws Exception {
        Connection connection = DriverManager.getConnection(DatabaseRequirements.url.getValue(),
                DatabaseRequirements.username.getValue(), DatabaseRequirements.password.getValue());
        String sql = "SELECT * FROM public.powerup where username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        return parsePowerUp(resultSet);

    }

    private ArrayList<BuildingObject> getListofObjects(String username) throws Exception {
        Connection connection = DriverManager.getConnection(DatabaseRequirements.url.getValue(),
                DatabaseRequirements.username.getValue(), DatabaseRequirements.password.getValue());
        String sql = "SELECT * FROM public.object where username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<BuildingObject> buildingObjectList = new ArrayList<>();
        while (resultSet.next()) {
            BuildingObject buildingObjet = parseObject(resultSet);
            buildingObjectList.add(buildingObjet);
        }
        return buildingObjectList;

    }

    private BuildingObject parseObject(ResultSet resultSet) throws Exception {
        BuildingObjectFactory b = new BuildingObjectFactory();
        String name = resultSet.getString("name");
        int xCurrent = resultSet.getInt("xstart");
        int yCurrent = resultSet.getInt("ystart");
        int xLen = resultSet.getInt("xlimit");
        int yLen = resultSet.getInt("ylimit");
        boolean isKeyHolder = resultSet.getBoolean("iskeyholder");
        BuildingObject bObj = b.createBuildingObject(name, xCurrent, yCurrent, xLen, yLen);
        if (isKeyHolder) {
            Key.getInstance();
            Key.setBuildingObject(bObj);
  
        }
        return bObj;
    }

    private PowerUp parsePowerUp(ResultSet resultSet) throws Exception {
        PowerUp p = null;
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            int xCurrent = resultSet.getInt("xstart");
            int yCurrent = resultSet.getInt("ystart");
            int xLen = resultSet.getInt("xlimit");
            int yLen = resultSet.getInt("ylimit");
            switch (name) {
            case "bottle":
                p = BottlePowerUp.getInstance(Game.getPlayer());
                break;
            case "vest":
                p = VestPowerUp.getInstance(Game.getPlayer());
                break;
            case "health":
                p = HealthPowerUp.getInstance(Game.getPlayer());
                break;
            case "thrownbottle":
                p = ThrownBottlePowerUp.getInstance(Game.getPlayer());
                break;
            case "time":
                p = TimePowerUp.getInstance(Game.getPlayer());
                break;
            case "hint":
                p = HintPowerUp.getInstance(Game.getPlayer());
                break;
            }
            p.setXCurrent(xCurrent);
            p.setYCurrent(yCurrent);
            p.setXLen(xLen);
            p.setYLen(yLen);
            p.setIsActive(true);
        }

        return p;
    }

    private Alien parseAlien(ResultSet resultSet) throws Exception {
        Alien a = null;
        String name = resultSet.getString("name");
        int xCurrent = resultSet.getInt("xstart");
        int yCurrent = resultSet.getInt("ystart");
        int xLen = resultSet.getInt("xlimit");
        int yLen = resultSet.getInt("ylimit");
        switch (name) {
        case "Blind":
            a = new BlindAlien(xCurrent, yCurrent, xLen, yLen);
            break;
        case "Shooter":
            a = new ShooterAlien(xCurrent, yCurrent, xLen, yLen,Game.getPlayer());
            break;
        case "TimeWasting":
            a = new TimeWastingAlien(xCurrent, yCurrent, xLen, yLen,Game.getPlayer());
        }
        a.setXCurrent(xCurrent);
        a.setYCurrent(yCurrent);
        a.setActive(true);
        return a;
    }
}
