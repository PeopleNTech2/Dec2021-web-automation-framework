package us.piit.search;

import base.CommonAPI;
import org.testng.annotations.Test;
import us.piit.HomePage;
import utility.DataReader;
import utility.Utilities;

import java.util.ArrayList;
import java.util.List;

public class TestSearchMultipleItems extends CommonAPI{

    DataReader dr = new DataReader();
//    String excelSheetPath = prop.getProperty("excel.sheet");

//    public List<String> getItemsFromExcel(){
//        return dr.getEntireColumnForGivenHeader(excelSheetPath, "Sheet1",  "items");
//    }

    public static List<String> getItemValues(){
        List<String> itemList = new ArrayList<>();
        itemList.add("java book");
        itemList.add("selenium book");
        itemList.add("laptop");
        itemList.add("flowers");
        itemList.add("toys");
        itemList.add("ps5");
        itemList.add("iphone");
        itemList.add("soccer ball");
        return itemList;
    }



    @Test
    public void searchItems(){
        HomePage homePage = new HomePage(driver);
//        for (String item: getItemsFromExcel()) {
//            homePage.searchInOneClickAndClear(item);
//        }

    }
}
