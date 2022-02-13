package us.piit.menu;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.HomePage;

public class TestCheckWatchList extends CommonAPI{

    HomePage homePage = new HomePage(driver);

    @Test
    public void checkWatchList() {
        homePage.hoverOverFlyOutMenu(driver);
        waitFor(1);
        homePage.clickOnWatchList();
        Assert.assertEquals(getTitle(), "Amazon Sign-In");
    }
}
