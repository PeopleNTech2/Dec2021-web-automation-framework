package us.piit.menu;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckWatchList extends CommonAPI{

    @Test
    public void checkWatchList() {
        hoverOver("//*[text()='Account & Lists']");
        waitFor(1);
        click("//span[contains(text(),'Watchlist')]");
        Assert.assertEquals(getTitle(), "Amazon Sign-In");
    }
}
