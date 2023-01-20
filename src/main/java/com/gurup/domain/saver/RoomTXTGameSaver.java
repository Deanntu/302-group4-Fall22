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

import com.gurup.domain.aliens.Alien;
import com.gurup.domain.powerups.PowerUp;
import com.gurup.domain.room.Key;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.buildingobjects.BuildingObject;

public class RoomTXTGameSaver {
    private List<BuildingObject> objectArray;
    private Alien[] alienArray; // 0 -> blind, 1 -> shooter, 2 -> time-wasting
    private File file;
    private Key key;
    private PowerUp powerUp;
    public void writeData(String username, Room room) throws Exception {
        String path = System.getProperty("user.dir") + "\\GameData\\Room"+username+".xlsx";
        file = new File(path);
        key = room.getKey();
        objectArray = room.getObjects();
        alienArray = room.getCreatedAliens();
        powerUp = room.getCreated();
        OutputStream fileOut = new FileOutputStream(file);
        Workbook wb = new HSSFWorkbook();
        writeObjects(username,fileOut,wb);
        writeAliens(username,fileOut,wb);
        writePowerUp(username,fileOut,wb);
        System.out.println("File exists");
        wb.write(fileOut);
        wb.close();
            
    }
    private void writePowerUp(String username, OutputStream fileOut, Workbook wb) {
        Sheet sheet = wb.createSheet("PowerUp");
        int rowCount = 0;
        Row row = sheet.createRow(rowCount);
        if (powerUp == null) return;
        String powerUpName = powerUp.getName();
        int xStart = powerUp.rectArray()[0];
        int yStart = powerUp.rectArray()[1];
        int xLimit = powerUp.rectArray()[2];
        int yLimit = powerUp.rectArray()[3];
        List<Object> values = new ArrayList<>();
        values.add(powerUpName);
        values.add(xStart);
        values.add(yStart);
        values.add(xLimit);
        values.add(yLimit);
        values.add(username);
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
    private void writeAliens(String username, OutputStream fileOut, Workbook wb) throws Exception {
        
        Sheet sheet = wb.createSheet("Aliens");
        int rowCount = 0;
        for (Alien a : alienArray) {
            if(a != null && a.isActive()) {
                Row row = sheet.createRow(rowCount);
                String alienName = a.getName();
                int xStart = a.rectArray()[0];
                int yStart = a.rectArray()[1];
                int xLimit = a.rectArray()[2];
                int yLimit = a.rectArray()[3];
                List<Object> values = new ArrayList<>();
                values.add(alienName);
                values.add(xStart);
                values.add(yStart);
                values.add(xLimit);
                values.add(yLimit);
                values.add(username);
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
                rowCount++;
            }
            
        }
        
    }
    private void writeObjects(String username, OutputStream fileOut, Workbook wb) throws Exception {
        Sheet sheet = wb.createSheet("Objects");
        int rowCount = 0;

        for (BuildingObject bo : objectArray) {
            Row row = sheet.createRow(rowCount);
            String objectName = bo.getName();
            int xCurrent = bo.getXCurrent();
            int yCurrent = bo.getYCurrent();
            int xLen = bo.getXLen();
            int yLen = bo.getYLen();
            boolean isHasKey = key.getBuildingObject().equals(bo);
            List<Object> values = new ArrayList<>();
            values.add(objectName);
            values.add(xCurrent);
            values.add(yCurrent);
            values.add(xLen);
            values.add(yLen);
            values.add(isHasKey);
            values.add(username); //6h index
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
            rowCount++;
        }
        
    }
}
