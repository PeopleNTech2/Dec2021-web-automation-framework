package us.piit;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonAPI {

    public LoginPage(WebDriver driver){
        super.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='ap_email']")
    WebElement emailField;
    @FindBy(xpath = "//input[@id='continue']")
    WebElement continueButton;
    @FindBy(xpath = "//h4[text()='There was a problem']")
    WebElement errorMessage;

    public String getLoginPageTitle(){
        return getTitle();
    }

    public void typeEmailAddress(){
        type(emailField, "wrongemail@wrongdoamin.com");
    }

    public void clickOnContinueButton(){
        click(continueButton);
    }

    public String getErrorMessage(){
        return getText(errorMessage);
    }
}
