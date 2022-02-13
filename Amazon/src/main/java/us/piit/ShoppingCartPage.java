package us.piit;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage extends CommonAPI {

    public ShoppingCartPage(WebDriver driver){
        super.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//body/div[@id='a-page']/div[4]/div[1]/div[3]/div[1]/div[2]/div[4]/div[1]/form[1]/div[2]/div[3]/div[4]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[1]/span[1]/a[1]/span[1]/span[1]/span[2]")
    WebElement bookTitle;

    public String getShoppingCartTitle(){
        return getTitle();
    }

    public String getBookTitle(){
        return getText(bookTitle);
    }
}
