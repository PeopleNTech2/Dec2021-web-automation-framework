package utility;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    XSSFWorkbook excelWBook;
    XSSFSheet excelWSheet;
    XSSFCell cell;

    public String getDataFromCell(String path, String sheet, int rowNum, int colNum){
        try {
            FileInputStream excelFile = new FileInputStream(path);
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheet);
            cell = excelWSheet.getRow(rowNum).getCell(colNum);
            String cellValue = cell.getStringCellValue();
            excelFile.close();
            return cellValue;
        }catch (Exception e){
            System.out.println("no data found");
            return "";
        }
    }

    public List<String> getEntireColumnData(String path, String sheet, int rowStart, int colNum){
        List<String> columnData = new ArrayList<>();
        try {
            FileInputStream excelFile = new FileInputStream(path);
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheet);
            int i = rowStart;
            while (excelWSheet.getRow(i).getCell(colNum).getStringCellValue() != null){
                columnData.add(excelWSheet.getRow(i).getCell(colNum).getStringCellValue());
                i++;
            }
            excelFile.close();
        }catch (Exception e){
            //System.out.println("no data found");
        }
        return columnData;
    }

    public List<String> getEntireColumnForGivenHeader(String path, String sheet, String headerName){
        int i = 0;
        while (getDataFromCell(path, sheet, 0, i) != null){
            if(getDataFromCell(path, sheet, 0, i).equalsIgnoreCase(headerName)){
                getEntireColumnData(path, sheet, 1, i);
                break;
            }
            i++;
        }
        return getEntireColumnData(path, sheet, 1, i);
    }

}
