package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonAPI {

    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/nacer-zimu/IdeaProjects/Dec2021-web-automation-framework/Generic/src/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void typeAndEnter(String locator, String text){
        try {
            driver.findElement(By.xpath(locator)).sendKeys(text, Keys.ENTER);
        }catch (Exception e){
            driver.findElement(By.cssSelector(locator)).sendKeys(text, Keys.ENTER);
        }
    }
    public void click(String locator){
        try {
            driver.findElement(By.xpath(locator)).click();
        }catch (Exception e){
            driver.findElement(By.cssSelector(locator)).click();
        }
    }
    public void clickById(String locator){
        driver.findElement(By.id(locator)).click();

    }
    public void clear(String locator){
        try {
            driver.findElement(By.xpath(locator)).clear();
        }catch (Exception e){
            driver.findElement(By.cssSelector(locator)).clear();
        }
    }
    public void type(String locator, String text){
        try {
            driver.findElement(By.xpath(locator)).sendKeys(text);
        }catch (Exception e){
            driver.findElement(By.cssSelector(locator)).sendKeys(text);
        }
    }
    public void getText(String locator){
        try{
            driver.findElement(By.xpath(locator)).getText();
        }catch (Exception e){
            driver.findElement(By.cssSelector(locator)).getText();
        }

    }
    public void selectDropdownOption(String dropdownLocator, String option){
        try{
            WebElement element = driver.findElement(By.xpath(dropdownLocator));
            Select select = new Select(element);
            try {
                select.selectByVisibleText(option);
            }catch (Exception e){
                select.selectByValue(option);
            }

        }catch (Exception e){
            WebElement element = driver.findElement(By.cssSelector(dropdownLocator));
            Select select = new Select(element);
            try {
                select.selectByVisibleText(option);
            }catch (Exception e1){
                select.selectByValue(option);
            }
        }
    }
    public List<WebElement> getDropdownOptions(String dropdownLocator){
        WebElement dropdown = driver.findElement(By.xpath(dropdownLocator));
        Select select = new Select(dropdown);
        List<WebElement> list = select.getOptions();
        return list;
    }
    public void hoverOver(String locator){
        Actions action = new Actions(driver);
        try {
            WebElement element = driver.findElement(By.xpath(locator));
            action.moveToElement(element).build().perform();
        }catch (Exception e){
            WebElement element = driver.findElement(By.cssSelector(locator));
            action.moveToElement(element).build().perform();
        }
    }
}
