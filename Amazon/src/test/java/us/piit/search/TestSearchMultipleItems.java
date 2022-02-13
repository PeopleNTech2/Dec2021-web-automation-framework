package us.piit.search;

import base.CommonAPI;
import org.testng.annotations.Test;
import us.piit.HomePage;

import java.util.ArrayList;
import java.util.List;

public class TestSearchMultipleItems extends CommonAPI{

    HomePage homePage = new HomePage(driver);

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
        List<String> items = getItemValues();
        for (String item: items) {
            homePage.searchInOneClickAndClear(item);
        }

    }
}