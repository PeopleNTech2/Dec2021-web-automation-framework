package us.piit.search;

import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.HomePage;
import utility.Utilities;

public class TestSearchSingleItem extends CommonAPI{

    @Test
    public void searchJavaBook(){
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(getTitle(), "Amazon.com. Spend less. Smile more.");
        homePage.searchElement("java book");
        Assert.assertEquals(getTitle(), "Amazon.com : java book");
    }
    @Test
    public void searchSeleniumBook(){
        HomePage homePage = new HomePage(driver);
        homePage.searchElement("selenium book");
        Assert.assertEquals(getTitle(), "Amazon.com : selenium book");
    }
    @Test
    public void searchComputer(){
        HomePage homePage = new HomePage(driver);
        homePage.searchInOneClick("computer");
        Assert.assertEquals(getTitle(), "Amazon.com : computer");
    }

}
