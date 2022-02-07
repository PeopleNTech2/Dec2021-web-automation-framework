package us.piit.menu;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import us.piit.HomePage;

import java.util.List;

public class SelectMenuOptions extends CommonAPI{

    HomePage homePage = new HomePage();

    @Test
    public void clickOnBabyChoice() {
        homePage.selectDropdownOption("books");
        waitFor(3);
    }

    @Test
    public void clickOnBooksOptionInDropdownList(){
        List<WebElement> list = homePage.selectDropdownOptions();
        for (WebElement element: list) {
            if(element.getText().equals("Books")){
                element.click();
                System.out.println("clicked!!!!");
            }
        }
    }

    //@Test
    public void clickOnDropdownOptionInListWithoutSelectClass() {
        homePage.clickOnMenuDropDown();
        homePage.selectFromDropdownOptionsList("Alexa skills");
    }
}
