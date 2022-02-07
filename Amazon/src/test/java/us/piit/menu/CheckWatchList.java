package us.piit.menu;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.HomePage;

public class CheckWatchList extends CommonAPI{

    HomePage homePage = new HomePage();

    @Test
    public void checkWatchList() {
        homePage.hoverOverHelloMenu(driver);
        waitFor(1);
        homePage.clickOnWatchList();
        Assert.assertEquals(getTitle(), "Amazon Sign-In");
    }
}
