package us.piit.login;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.HomePage;
import us.piit.LoginPage;
import utility.Utilities;

import java.util.Properties;

public class TestLogin extends CommonAPI {

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
