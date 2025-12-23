package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static Object[][] readExcel(String filePath, String sheetName) {

        try {
            FileInputStream fis = new FileInputStream(filePath);

            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet(sheetName);

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {
                	Cell cell = row.getCell(j);

                	switch (cell.getCellType()) {
                	    case STRING:
                	        data[i-1][j] = cell.getStringCellValue();
                	        break;

                	    case NUMERIC:
                	        data[i-1][j] = String.valueOf((int) cell.getNumericCellValue()); // remove .0
                	        break;

                	    default:
                	        data[i-1][j] = cell.toString();
                	}

                }
            }

            wb.close();
            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void writeLoginData(String email, String password, String name) {

        String filePath = "C:\\Users\\DELL\\eclipse-workspace\\AutomationExercise\\src\\test\\resources\\testDate.xlsx";

        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook wb = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = wb.getSheet("login");

            int rowIndex = -1;

            // Find first truly empty row
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || row.getCell(0) == null) {
                    rowIndex = i;
                    break;
                }
            }

            // if no empty row found → append at end
            if (rowIndex == -1) {
                rowIndex = sheet.getLastRowNum() + 1;
            }

            Row row = sheet.createRow(rowIndex);

            row.createCell(0).setCellValue(email);
            row.createCell(1).setCellValue(password);
            row.createCell(2).setCellValue(name);

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                wb.write(fos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteLastLoginData() {

        String filePath = "C:\\Users\\DELL\\eclipse-workspace\\AutomationExercise\\src\\test\\resources\\testDate.xlsx";

        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook wb = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = wb.getSheet("login");

            int lastRowNum = sheet.getLastRowNum();

            // Find last non-empty row
            while (lastRowNum > 0 && sheet.getRow(lastRowNum) == null) {
                lastRowNum--;
            }

            Row lastRow = sheet.getRow(lastRowNum);
            if (lastRow != null) {
                sheet.removeRow(lastRow);
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                wb.write(fos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
