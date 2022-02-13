package us.piit;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends CommonAPI {

    public HomePage(WebDriver driver){
        super.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='twotabsearchtextbox']")
    WebElement searchField;
    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;
    @FindBy(xpath = "//select[@id='searchDropdownBox']")
    WebElement menuDropdown;
    @FindBy (css = "#searchDropdownBox option")
    List<WebElement> menuDropdownOptions;
    @FindBy (xpath = "//*[text()='Account & Lists']")
    WebElement floatingMenu;
    @FindBy (xpath = "//span[contains(text(),'Watchlist')]]")
    WebElement watchListLink;
    @FindBy(xpath = "//span[text()='Sign in']")
    WebElement signInButton;
    @FindBy(xpath = "//span[@id='nav-cart-count']")
    WebElement cart;

    public void searchElement(String itemToSearch){
        type(searchField, itemToSearch);
        click(searchButton);
    }

    public void searchInOneClick(String itemToSearch){
        typeAndEnter(searchField, itemToSearch);
    }

    public void searchInOneClickAndClear(String itemToSearch){
        typeAndEnter(searchField, itemToSearch);
        clear(searchField);
    }

    public void selectDropdownOption(String option){
        selectDropdownOption(menuDropdown, option);
    }

    public List<WebElement> selectDropdownOptions(){
        return getDropdownOptions(menuDropdown);
    }

    public void clickOnMenuDropDown(){
        click(menuDropdown);
    }

    public void selectFromDropdownOptionsList(String choice){
        List<WebElement> dropdownList = menuDropdownOptions;
        for (WebElement element: dropdownList) {
            if(element.getText().equals(choice)){
                element.click();
            }
        }
    }

    public void hoverOverFlyOutMenu(WebDriver driver){
        hoverOver(driver, floatingMenu);
    }

    public void clickOnWatchList(){
        click(watchListLink);
    }

    public void clickOnSignInButton(){
        click(signInButton);
    }

    public void clickOnCart(){
        click(cart);
    }
}
