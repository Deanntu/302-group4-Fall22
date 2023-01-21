package com.gurup.domain.saver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.VestPowerUp;

public class PlayerTXTGameSaver {
    private File file;
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

    public void writeData(String username, Player player) throws Exception {
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
        String path = System.getProperty("user.dir") + "\\GameData\\Player"+username+".xlsx";
        file = new File(path);
        OutputStream fileOut = new FileOutputStream(file);
        Workbook wb = new HSSFWorkbook();
        writePlayer(username,fileOut,wb);
        wb.write(fileOut);
        wb.close();
            
    }
    private void writePlayer(String username, OutputStream fileOut, Workbook wb) {

        Sheet sheet = wb.createSheet("PowerUp");
        int rowCount = 0;
        Row row = sheet.createRow(rowCount);
        
        List<Object> values = new ArrayList<>();
        values.add(isProtected);
        values.add(remainingTime);
        values.add(remainingLife);
        values.add(startingTime);
        values.add(xLocation);
        values.add(yLocation);
        values.add(bottlecount);
        values.add(vestcount);
        values.add(level);
        values.add(username);
        values.add(isKeyFound);
        int columnCount = 0;
        for (Object value : values) {
            Cell cell = row.createCell(columnCount);
            if (value instanceof String) {
                cell.setCellValue((String) value);
            } else if (value instanceof Integer) {
                cell.setCellValue((Integer) value);
            } else if (value instanceof Boolean) {
                cell.setCellValue((Boolean) value);
            }
            columnCount++;
        }
    
    }
}
