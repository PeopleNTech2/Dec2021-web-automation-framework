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
            for (int i = rowStart; i < excelWSheet.getLastRowNum(); i++){
                columnData.add(excelWSheet.getRow(i).getCell(colNum).getStringCellValue());
            }
//            int i = rowStart;
//            while (excelWSheet.getRow(i).getCell(colNum).getStringCellValue() != null){
//                columnData.add(excelWSheet.getRow(i).getCell(colNum).getStringCellValue());
//                i++;
//            }
            excelFile.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("no data found");
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

    public String getValueForGivenHeaderAndKey(String path, String sheet, String headerName, String key){
        String value = null;
        int i = 0;
        while (getDataFromCell(path, sheet, 0, i) != null){
            if(getDataFromCell(path, sheet, 0, i).equalsIgnoreCase(headerName)){
                for (int j = 0; j < getEntireColumnData(path, sheet, 1, i).size(); j++){
                    if(getEntireColumnData(path, sheet, 1, i).get(j).equalsIgnoreCase(key)){
                        value = getEntireColumnData(path, sheet, 1, i+1).get(j);
                    }
                }
                break;
            }
            i++;
        }
        return value;
    }

//    public static void main(String[] args) {
//        String path = "C:\\Users\\PeopleNTech NY Class\\IdeaProjects\\Dec2021-web-automation-framework\\Amazon\\data\\my_data.xlsx";
//        DataReader dr = new DataReader();
//        System.out.println(dr.getValueForGivenHeaderAndKey(path, "Sheet1", "id", "id001"));
//    }

}
