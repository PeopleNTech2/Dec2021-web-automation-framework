package us.piit;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultsPage extends CommonAPI {

    public ResultsPage(WebDriver driver){
        super.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    @FindBy(css = ("div[data-cel-widget*='search_result']"))
//    List<WebElement> bestSellerSection;
    @FindBy(xpath = "//span[contains(text(),'Head First Java, 2nd Edition')]")
    WebElement bookLink;

    public String getResultPageTitle(){
        return getTitle();
    }

//    public List<WebElement> getSearchResults(){
//            return bestSellerSection;
//    }
    public void clickOnBestSellerBookLink(){
        click(bookLink);
    }

}
