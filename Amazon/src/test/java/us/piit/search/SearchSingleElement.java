package us.piit.search;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchSingleElement extends CommonAPI{

    @Test
    public void searchJavaBook(){
        Assert.assertEquals(getTitle(), "Amazon.com. Spend less. Smile more.");
        type("//*[@id='twotabsearchtextbox']", "java book");
        click("#nav-search-submit-button");
        Assert.assertEquals(getTitle(), "Amazon.com : java book");
    }
    @Test
    public void searchSeleniumBook(){
        type("//*[@id='twotabsearchtextbox']", "selenium book");
        click("#nav-search-submit-button");
        Assert.assertEquals(getTitle(), "Amazon.com : selenium book");
    }
    @Test
    public void searchComputer(){
        typeAndEnter("//*[@id='twotabsearchtextbox']", "computer");
        Assert.assertEquals(getTitle(), "Amazon.com : computer");
    }
}
