package us.piit;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookPage extends CommonAPI {

    public BookPage(WebDriver driver){
        super.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    WebElement addToCartButton;

    public String getBookPageTitle(){
        return getTitle();
    }

    public void clickOnAddToCartBtn(){
        click(addToCartButton);
    }
}
