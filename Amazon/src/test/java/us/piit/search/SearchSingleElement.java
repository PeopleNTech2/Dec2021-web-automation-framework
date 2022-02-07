package us.piit.search;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.HomePage;

public class SearchSingleElement extends CommonAPI{

    HomePage homePage = new HomePage();

    @Test
    public void searchJavaBook(){
        Assert.assertEquals(getTitle(), "Amazon.com. Spend less. Smile more.");
        homePage.searchElement("java book");
        Assert.assertEquals(getTitle(), "Amazon.com : java book");
    }
    @Test
    public void searchSeleniumBook(){
        homePage.searchElement("selenium book");
        Assert.assertEquals(getTitle(), "Amazon.com : selenium book");
    }
    @Test
    public void searchComputer(){
        homePage.searchInOneClick("computer");
        Assert.assertEquals(getTitle(), "Amazon.com : computer");
    }
}
