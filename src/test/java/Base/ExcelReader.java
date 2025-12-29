package Base;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    private final XSSFWorkbook wb;
    private final DataFormatter formatter = new DataFormatter();

    public ExcelReader(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        wb = new XSSFWorkbook(fis);
    }

    public String getStringData(String sheetName, int rowNumber, int cellNumber) {
        XSSFSheet sheet = wb.getSheet(sheetName);
        Cell cell = sheet.getRow(rowNumber).getCell(cellNumber);
        return formatter.formatCellValue(cell);
    }

    public int getIntegerData(String sheetName, int rowNumber, int cellNumber) {
        XSSFSheet sheet = wb.getSheet(sheetName);
        Cell cell = sheet.getRow(rowNumber).getCell(cellNumber);
        String value = formatter.formatCellValue(cell);

        return Integer.parseInt(value);
    }

    public int getLastRow(String sheetName) {
        return wb.getSheet(sheetName).getLastRowNum();
    }
}
