package us.piit.purchase;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.BookPage;
import us.piit.HomePage;
import us.piit.ResultsPage;
import us.piit.ShoppingCartPage;

import java.util.List;
import java.util.WeakHashMap;

public class TestPurchase extends CommonAPI {

    @Test
    public void addToCartAndCheckItem(){
        HomePage homePage = new HomePage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);
        BookPage bookPage = new BookPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertEquals(homePage.getTitle(), "Amazon.com. Spend less. Smile more.");
        homePage.searchElement("java book");
        Assert.assertEquals(resultsPage.getResultPageTitle(), "Amazon.com : java book");
        resultsPage.clickOnBestSellerBookLink();
        Assert.assertEquals(bookPage.getBookPageTitle(), "Head First Java, 2nd Edition: Sierra, Kathy, Bates, Bert: 8601404235726: Amazon.com: Books");
        bookPage.clickOnAddToCartBtn();
        homePage.clickOnCart();
        Assert.assertEquals(shoppingCartPage.getShoppingCartTitle(), "Amazon.com Shopping Cart");
        Assert.assertEquals(shoppingCartPage.getBookTitle(), "Head First Java, 2nd Edition");
    }
}
