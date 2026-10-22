package com.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.*;

public final class ExcelUtils {
    private ExcelUtils() {
    }

    public static String readCell(String fileName, String sheetName, int rowIndex, int columnIndex)
            throws IOException {
        Path file = Path.of(fileName);
        try (InputStream input = Files.newInputStream(file);
             Workbook workbook = WorkbookFactory.create(input)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            Row row = sheet.getRow(rowIndex);
            if (row == null || row.getCell(columnIndex) == null) {
                return "";
            }

            return new DataFormatter(Locale.ROOT).formatCellValue(row.getCell(columnIndex));
        }
    }

    public static void writeCell(String fileName, String sheetName, int rowIndex, int columnIndex, String value)
            throws IOException {
        Path file = Path.of(fileName);
        Path parent = file.toAbsolutePath().getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (Workbook workbook = openOrCreateWorkbook(file)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }

            Cell cell = row.getCell(columnIndex);
            if (cell == null) {
                cell = row.createCell(columnIndex);
            }
            cell.setCellValue(value);

            try (OutputStream output = Files.newOutputStream(file)) {
                workbook.write(output);
            }
        }
    }

    private static Workbook openOrCreateWorkbook(Path file) throws IOException {
        if (Files.exists(file)) {
            try (InputStream input = Files.newInputStream(file)) {
                return WorkbookFactory.create(input);
            }
        }

        String fileName = file.getFileName().toString().toLowerCase(Locale.ROOT);
        if (fileName.endsWith(".xlsx")) {
            return new XSSFWorkbook();
        }
        if (fileName.endsWith(".xls")) {
            return new HSSFWorkbook();
        }
        throw new IllegalArgumentException("Excel filename must end with .xls or .xlsx: " + fileName);
    }

    public static List<String> getDataForTest(String testName) throws IOException {
        // identify the sheet's row for headings by reading first row
        // identify the purchase row from sheet and provide ot to the test case
        List<String> testDataList = new ArrayList<>();
        FileInputStream fis = new FileInputStream("C:\\Users\\praausek\\Documents\\Courses\\Rest API Testing (Automation) from Scratch-Rest Assured Java\\Book1.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheetsCount = workbook.getNumberOfSheets(); // get total number of sheets
        for (int i = 0; i < sheetsCount; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("sheet1")) {// get each sheet
                XSSFSheet sheet = workbook.getSheetAt(i);
                // get the rows
                Iterator<Row> rowIt = sheet.iterator();

                Row firstRow = rowIt.next();
                Iterator<Cell> firstCellOf1stRow = firstRow.cellIterator();// get

                int col = 0;
                int k = 0;
                while (firstCellOf1stRow.hasNext()) {
                    Cell cellValue = firstCellOf1stRow.next();
                    if (cellValue.getStringCellValue().equalsIgnoreCase("TestCase")) {
                        col = k;
                        System.out.println("column of TestCase:" + col);
                        break;
//                        System.out.println(cellValue);
                    }
                    k++;
                }

                while (rowIt.hasNext()) {
                    Row r = rowIt.next();
                    if (r.getCell(col).getStringCellValue().equalsIgnoreCase(testName)) {
                        Iterator<Cell> cellIt = r.cellIterator();
                        while (cellIt.hasNext()) {
                            Cell c = cellIt.next();

                            if (c.getCellType() == CellType.STRING) {
                                testDataList.add(c.getStringCellValue());
                            } else if (c.getCellType() == CellType.NUMERIC) {
                                testDataList.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }
                }

            }
        }
        return testDataList;
    }
}