package us.piit.login;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.HomePage;
import us.piit.LoginPage;
import utility.GetProperties;

import java.util.Properties;

public class TestLogin extends CommonAPI {

    Properties prop = GetProperties.loadProperties("src/test/resources/config.properties");
    String username = prop.getProperty("username");
    String password = prop.getProperty("password");

    @Test
    public void loginWithInvalidEmail(){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.hoverOverFlyOutMenu(driver);
        homePage.clickOnSignInButton();
        Assert.assertEquals(loginPage.getLoginPageTitle(), "Amazon Sign-In");
        loginPage.typeEmailAddress();
        loginPage.clickOnContinueButton();
        Assert.assertEquals(loginPage.getErrorMessage(), "There was a problem");
    }

}
