package base;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utility.Utilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CommonAPI {

    public WebDriver driver;

    //public Properties prop = Utilities.loadProperties(Utilities.projectPath()+"/config.properties");
//    String browserstackUsername = prop.getProperty("browserstack.username");
//    String browserstackPassword = prop.getProperty("browserstack.password");
//    String takeScreenshot = prop.getProperty("take.screenshot", "false");
//    String waitTime = prop.getProperty("wait.time", "10");
//    String windowMaximize = prop.getProperty("window.maximize", "false");

    public static com.relevantcodes.extentreports.ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }
    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
//        if (takeScreenshot.equalsIgnoreCase("true")){
            if (result.getStatus() == ITestResult.FAILURE) {
                takeScreenshot(result.getName());
            }
//        }
        driver.quit();
    }
    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    @Parameters({"useCloudEnv","cloudEnvName","os","os_version","browserName","browserVersion","url"})
    @BeforeMethod
    public void setUp(@Optional("false") boolean useCloudEnv, @Optional String cloudEnvName,
                      @Optional("windows") String os, @Optional ("10") String os_version,
                      @Optional("chrome") String browserName, @Optional("34") String browserVersion,
                      @Optional("https://wwww.google.com") String url) throws MalformedURLException {
        if(useCloudEnv == true){
            if(cloudEnvName.equalsIgnoreCase("browserstack")){
                //getCloudDriver(cloudEnvName, browserstackUsername, browserstackPassword, os, os_version, browserName, browserVersion);
            }else if(cloudEnvName.equalsIgnoreCase("saucelabs")){
                getCloudDriver(cloudEnvName, "", "", os, os_version, browserName, browserVersion);
            }
        }else{
            getLocalDriver(os, browserName);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        if(windowMaximize.equalsIgnoreCase("true")){
            driver.manage().window().maximize();
//        }
        driver.get(url);
    }
    public WebDriver getLocalDriver(String os, String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            if(os.equalsIgnoreCase("windows")){
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\PeopleNTech NY Class\\IdeaProjects\\Dec2021-web-automation-framework\\Generic\\src\\drivers\\chromedriver.exe");
            }else if(os.equalsIgnoreCase("OS X")){
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\PeopleNTech NY Class\\IdeaProjects\\Dec2021-web-automation-framework\\Generic\\src\\drivers\\chromedriver");
            }
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")){
            if(os.equalsIgnoreCase("windows")){
                System.setProperty("webdriver.gecko.driver", Utilities.projectPath()+"../Generic/src/drivers/geckodriver.exe");
            }else if(os.equalsIgnoreCase("OS X")){
                System.setProperty("webdriver.gecko.driver", Utilities.projectPath()+"/Generic/src/drivers/geckodriver");
            }
            driver = new FirefoxDriver();
        }
        return driver;
    }
    public WebDriver getCloudDriver(String envName, String EnvUsername, String envAccessKey, String os, String os_version, String browserName, String browserVersion) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browser", browserName);
        cap.setCapability("browser_version", browserVersion);
        cap.setCapability("os", os);
        cap.setCapability("os_version", os_version);
        if(envName.equalsIgnoreCase("sauselabs")){
            driver = new RemoteWebDriver(new URL("http://"+ EnvUsername + ":" + envAccessKey + "@ondemand.saucelabs.com:80/wd/hub"), cap);
        }else if(envName.equalsIgnoreCase("browserstack")){
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://"+ EnvUsername + ":" + envAccessKey + "@hub-cloud.browserstack.com:80/wd/hub"), cap);
        }
        return driver;
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

    public void typeAndEnter(WebElement element, String text){
        element.sendKeys(text, Keys.ENTER);
    }
    public void click(WebElement element){
        element.click();
    }
    public void clear(WebElement element){
        element.clear();
    }
    public void type(WebElement element, String text){
        element.sendKeys(text);
    }
    public String getText(WebElement element){
        String text;
        text = element.getText();
        return text;
    }
    public void selectDropdownOption(WebElement element, String option){
        Select select = new Select(element);
        try {
            select.selectByVisibleText(option);
        }catch (Exception e){
            select.selectByValue(option);
        }
    }
    public List<WebElement> getDropdownOptions(WebElement element){
        Select select = new Select(element);
        List<WebElement> list = select.getOptions();
        return list;
    }
    public void hoverOver(WebDriver driver, WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public void pressEnter(WebElement element){
        element.sendKeys(Keys.ENTER);
    }
    public void checkIfChecked(WebElement element){
        Assert.assertTrue(element.isSelected());
    }
    public void checkIfNotChecked(WebElement element){
        Assert.assertFalse(element.isSelected());
    }
    public void checkIfVisible(WebElement element){
        Assert.assertTrue(element.isSelected());
    }
    public void checkIfNotVisible(WebElement element){
        Assert.assertFalse(element.isDisplayed());
    }
    public void checkIfEnabled(WebElement element){
        Assert.assertTrue(element.isEnabled());
    }
    public void checkIfDisabled(WebElement element){
        Assert.assertFalse(element.isEnabled());
    }
    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }
    public void dismissAlert(){
        driver.switchTo().alert().dismiss();
    }
    public void scrollToView(WebElement element){
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }
    public void switchToFrame(String id){
        try {
            driver.switchTo().frame(id);
        }catch (Exception e){
            driver.switchTo().frame(Integer.parseInt(id));
        }

    }
    public void captureScreenshot() {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File("screenshots/screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeScreenshot(String screenshotName){
        DateFormat df = new SimpleDateFormat("(MM.dd.yyyy-HH:mma)");
        Date date = new Date();
        df.format(date);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir")+File.separator+ "screenshots"+File.separator+screenshotName+" "+df.format(date)+".png"));
            System.out.println("Screenshot captured");
        } catch (Exception e) {
            String path = System.getProperty("user.dir")+ "/screenshots/"+screenshotName+" "+df.format(date)+".png";
            System.out.println(path);
            System.out.println("Exception while taking screenshot "+e.getMessage());;
        }
    }
}
