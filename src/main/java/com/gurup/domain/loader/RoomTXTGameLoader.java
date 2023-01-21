package com.gurup.domain.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.gurup.domain.Game;
import com.gurup.domain.aliens.Alien;
import com.gurup.domain.aliens.BlindAlien;
import com.gurup.domain.aliens.ShooterAlien;
import com.gurup.domain.aliens.TimeWastingAlien;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.HealthPowerUp;
import com.gurup.domain.powerups.HintPowerUp;
import com.gurup.domain.powerups.PowerUp;
import com.gurup.domain.powerups.TimePowerUp;
import com.gurup.domain.powerups.VestPowerUp;
import com.gurup.domain.room.Key;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.RoomConstants;
import com.gurup.domain.room.buildingobjects.BuildingObject;
import com.gurup.domain.room.buildingobjects.BuildingObjectFactory;

public class RoomTXTGameLoader {
    private List<BuildingObject> objectArray;
    private Alien[] alienArray; // 0 -> blind, 1 -> shooter, 2 -> time-wasting
    private File file;
    private Key key;
    private PowerUp powerUp;
    private Room room;

    public Room loadRoom(String username) throws Exception {
        String path = System.getProperty("user.dir") + "\\GameData\\Room" + username + ".xlsx";
        file = new File(path);
        if (file.isFile()) {
            room = new Room("Student Center", RoomConstants.xStart.getValue(), RoomConstants.yStart.getValue(),
                    RoomConstants.xLimit.getValue(), RoomConstants.yLimit.getValue(), Game.getPlayer());
            // FileInputStream inputStream = new FileInputStream(file);
            // Workbook workbook = new XSSFWorkbook(file);
            Workbook workbook = WorkbookFactory.create(file);
            room.setCreatedAliens(loadAliens(workbook));
            room.setObjects(loadObjects(workbook));
            room.setCreated(loadPowerUp(workbook));
            return room;
        }
        return null;

    }

    private Alien[] loadAliens(Workbook workbook) throws NullPointerException {
        alienArray = new Alien[3];
        Sheet alienSheet = workbook.getSheet("Aliens");
        Iterator<Row> iterator = alienSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Alien a = null;
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();
                switch (cell.getColumnIndex()) {
                case 0:
                    a = createAlien(cell.getStringCellValue());
                    if (a == null) {
                        return alienArray;
                    }
                    break;
                case 1:
                    a.setXCurrent((int) cell.getNumericCellValue());
                    break;
                case 2:
                    a.setYCurrent((int) cell.getNumericCellValue());
                    break;
                case 3:
                    a.setXLen((int) cell.getNumericCellValue());
                    break;
                case 4:
                    a.setYLen((int) cell.getNumericCellValue());
                    break;
 
                }
                a.setActive(true);
                switch (a.getName()) {
                case "Blind":
                    alienArray[0] = a;
                case "TimeWasting":
                    alienArray[1] = a;
                case "Shooter":
                    alienArray[2] = a;
                }

            }
        }

        return alienArray;
    }

    private ArrayList<BuildingObject> loadObjects(Workbook workbook) throws NullPointerException {
        ArrayList<BuildingObject> buildingObjects = new ArrayList<>();
        Sheet objectSheet = workbook.getSheet("Objects");
        Iterator<Row> iterator = objectSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            BuildingObject bo = null;
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();
                switch (cell.getColumnIndex()) {
                case 0:
                    bo = createBuildingObject(cell.getStringCellValue());
                    if (bo == null) {
                        return buildingObjects;
                    }
                    break;
                case 1:
                    bo.setXCurrent((int) cell.getNumericCellValue());
                    break;
                case 2:
                    bo.setYCurrent((int) cell.getNumericCellValue());
                    break;
                case 3:
                    bo.setXLen((int) cell.getNumericCellValue());
                    break;
                case 4:
                    bo.setYLen((int) cell.getNumericCellValue());
                    break;
                case 5:
                    if (cell.getBooleanCellValue()) {
                        Key.setBuildingObject(bo);
                    }
                }
                buildingObjects.add(bo);
            }
        }

        return buildingObjects;
    }

    private PowerUp loadPowerUp(Workbook workbook) {
        powerUp = null;
        Sheet objectSheet = workbook.getSheet("Objects");
        Iterator<Row> iterator = objectSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();
                switch (cell.getColumnIndex()) {
                case 0:
                    powerUp = createPowerUp(cell.getStringCellValue());
                    if (powerUp == null) {
                        return powerUp;
                    }
                    break;
                case 1:
                    powerUp.setXCurrent((int) cell.getNumericCellValue());
                    break;
                case 2:
                    powerUp.setYCurrent((int) cell.getNumericCellValue());
                    break;
                case 3:
                    powerUp.setXLen((int) cell.getNumericCellValue());
                    break;
                case 4:
                    powerUp.setYLen((int) cell.getNumericCellValue());
                }
                powerUp.setIsActive(true);
                
            }
            
        }
        return powerUp;

    }

    private PowerUp createPowerUp(String name) {
        PowerUp po = null;
        switch (name) {
        case "bottle":
            po = BottlePowerUp.getInstance(Game.getPlayer());
            break;
        case "health":
            po = HealthPowerUp.getInstance(Game.getPlayer());
            break;
        case "time":
            po = TimePowerUp.getInstance(Game.getPlayer());
            break;
        case "vest":
            po = VestPowerUp.getInstance(Game.getPlayer());
            break;
        case "hint":
            po = HintPowerUp.getInstance(Game.getPlayer());
            break;
        }
        return po;
    }

    private BuildingObject createBuildingObject(String name) {
        BuildingObjectFactory b = new BuildingObjectFactory();
        BuildingObject bObj = b.createBuildingObject(name, 0, 0, 0, 0);
        return bObj;

    }

    private Alien createAlien(String name) {
        switch (name) {
        case "Blind":
            BlindAlien ba = new BlindAlien();
            return ba;
        case "TimeWasting":
            TimeWastingAlien twa = new TimeWastingAlien(0, 0, 0, 0, Game.getPlayer());
            return twa;
        case "Shooter":
            ShooterAlien sa = new ShooterAlien();
            return sa;
        default:
            return null;
        }
    }
}
