package com.gurup.domain.loader;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.gurup.domain.Bag;
import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.PlayerConstants;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.VestPowerUp;

public class PlayerTXTGameLoader {
    private File file;
    private Player player;

    public Player readData(String username) throws Exception {
        String path = System.getProperty("user.dir") + "\\GameData\\Player" + username + ".xlsx";
        file = new File(path);
        if (file.isFile()) {
            player = new Player(100, 100, PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 100); 

            FileInputStream inputStream = new FileInputStream(file);

            Workbook workbook = WorkbookFactory.create(file);

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch(cell.getColumnIndex()) {
                        case 0:
                           player.setProtected(cell.getBooleanCellValue());
                           break;
                        case 1:
                            player.setRemainingTime((int)cell.getNumericCellValue());
                            break;
                        case 2:
                            player.setLife((int)cell.getNumericCellValue());
                            break;
                        case 3:
                            player.setStartingTime((int)cell.getNumericCellValue());
                            break;
                        case 4:
                            player.setXCurrent((int)cell.getNumericCellValue());
                            break;
                        case 5:
                            player.setYCurrent((int)cell.getNumericCellValue());
                            break;
                        case 6:
                            Game.setBag(new Bag(player));
                            Game.getBag().getPowerUps().put(BottlePowerUp.getInstance(player), (int)cell.getNumericCellValue());
                            break;
                        case 7:
                            Game.getBag().getPowerUps().put(VestPowerUp.getInstance(player), (int)cell.getNumericCellValue());
                            break;
                        case 8:
                            player.setLevel((int)cell.getNumericCellValue());
                            break;
                        case 10:
                            player.setIsKeyFound(cell.getBooleanCellValue());
                            break;
                    }
                }
            }

            workbook.close();
            inputStream.close();
        } else {
            return null;
        }
        return player;
    }
}
