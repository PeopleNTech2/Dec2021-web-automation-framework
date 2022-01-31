package us.piit.menu;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

public class SelectMenuOptions extends CommonAPI{

    @Test
    public void clickOnBabyChoice() {
        selectDropdownOption("//select[@id='searchDropdownBox']", "Books");
        waitFor(3);
    }

    @Test
    public void clickOnBooksOptionInDropdownList(){
        List<WebElement> list = getDropdownOptions("//select[@id='searchDropdownBox']");
        for (WebElement element: list) {
            if(element.getText().equals("Books")){
                element.click();
                System.out.println("clicked!!!!");
            }
        }
    }

    //@Test
    public void clickOnDropdownOptionInListWithoutSelectClass() {
        click("#searchDropdownBox");

        List<WebElement> dropdownList = driver.findElements(By.cssSelector("#searchDropdownBox option"));
        System.out.println(dropdownList.size());

        for (WebElement element: dropdownList) {
            if(element.getText().equals("Alexa Skills")){
                element.click();
                System.out.println("clicked!!!!");
                waitFor(5);
            }
            //System.out.println(element.getText());
        }
    }
}
