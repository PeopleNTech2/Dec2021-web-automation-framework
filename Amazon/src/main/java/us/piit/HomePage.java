package us.piit;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends CommonAPI {

    @FindBy(xpath = "//*[@id='twotabsearchtextbox']")
    WebElement searchField;
    @FindBy(id = "nav-search-submit-button]")
    WebElement searchButton;
    @FindBy(xpath = "//select[@id='searchDropdownBox']")
    WebElement menuDropdown;
    @FindBy (css = "#searchDropdownBox option")
    List<WebElement> menuDropdownOptions;
    @FindBy (xpath = "//*[text()='Account & Lists']")
    WebElement helloMenu;
    @FindBy (xpath = "//span[contains(text(),'Watchlist')]]")
    WebElement watchListLink;

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

    public void hoverOverHelloMenu(WebDriver driver){
        hoverOver(driver, helloMenu);
    }

    public void clickOnWatchList(){
        clickById(watchListLink);
    }
}
