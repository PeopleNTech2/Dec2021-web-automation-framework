package us.piit;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PracticePage extends CommonAPI {

    //@Test
    public void radioButton(){
        List<WebElement> radioButtons = driver.findElements(By.xpath("//*[@name='radioButton']"));
        for (WebElement radioButton: radioButtons) {
            radioButton.click();
            waitFor(2);
        }
    }
    //@Test
    public void suggestionDropdown(){
        type("#autocomplete", "can");
        for (int i = 0; i < 4; i++) {
            driver.findElement(By.cssSelector("#autocomplete")).sendKeys(Keys.ARROW_DOWN);
            waitFor(1);
        }
        driver.findElement(By.cssSelector("#autocomplete")).sendKeys(Keys.ENTER);
        waitFor(1);

//        type("#autocomplete", "can");
//        List<WebElement> options = driver.findElements(By.xpath("//li[@class='ui-menu-item']"));
//        for (int i = 0; i < options.size(); i++) {
////            if(options.get(i).getText().equals("Central African Republic")){
////                options.get(i).click();
////            }
//        }
    }
    //@Test
    public void staticDropdown(){
        selectDropdownOption("#dropdown-class-example","Option2");
        waitFor(3);
    }
    //@Test
    public void checkbox(){
        List<WebElement> checkboxes = driver.findElements(By.xpath("//label//input[@type='checkbox']"));
        for (WebElement checkbox: checkboxes) {
            checkbox.click();
        }
        waitFor(3);
        for (int i = 0; i < checkboxes.size(); i++) {
            if(i == 1){
                Assert.assertTrue(checkboxes.get(i).isSelected());
                waitFor(3);
                checkboxes.get(i).click();
                waitFor(3);
                Assert.assertFalse(checkboxes.get(i).isSelected());
                waitFor(3);
            }
        }
    }
    //@Test
    public void handleNewWindow(){
        click("//*[@id='openwindow']");
        waitFor(3);
        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandle = driver.getWindowHandles();
        for (String window: windowHandle) {
            if(!parentWindow.contentEquals(window)){
                driver.switchTo().window(window);
                break;
            }
        }
        click("//button[text()='NO THANKS']");
    }
    //@Test
    public void handleTap(){
        click("#opentab");
        Set<String> windows = driver.getWindowHandles();

        Iterator<String> iterator = windows.iterator();
        while (iterator.hasNext()){
            String parentTap = iterator.next();
            String newTab = iterator.next();
            driver.switchTo().window(newTab);
            click("//*[text()='Login']");
            waitFor(2);
        }
    }
    //@Test
    public void handleAlert(){
        type("//*[@id='name']", "Joe");
        click("#alertbtn");
        waitFor(2);
        driver.switchTo().alert().accept();
        waitFor(2);
        type("//*[@id='name']", "Joe");
        click("#confirmbtn");
        waitFor(2);
        driver.switchTo().alert().accept();
        waitFor(2);
        type("//*[@id='name']", "Joe");
        click("#confirmbtn");
        waitFor(2);
        driver.switchTo().alert().dismiss();
        waitFor(2);
    }
    //@Test
    public void table(){
        System.out.println(driver.findElement(By.cssSelector("table[name='courses'] tr:nth-child(2) td:nth-child(1)")).getText());
        System.out.println(driver.findElement(By.cssSelector("table[name='courses'] tr:nth-child(7) td:nth-child(2)")).getText());
        System.out.println(driver.findElement(By.cssSelector("table[name='courses'] tr:nth-child(11) td:nth-child(3)")).getText());
    }
    //@Test
    public void hideShow(){
        Assert.assertTrue(driver.findElement(By.cssSelector("#displayed-text")).isDisplayed());
        click("#hide-textbox");
        Assert.assertFalse(driver.findElement(By.cssSelector("#displayed-text")).isDisplayed());
        click("#show-textbox");
        Assert.assertTrue(driver.findElement(By.cssSelector("#displayed-text")).isDisplayed());
    }
    //@Test
    public void scrollDownAndGetText(){
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(".tableFixHead table")));
        waitFor(3);
        js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(".tableFixHead table tbody tr:nth-child(9)")));
        waitFor(3);
        String lastRow = driver.findElement(By.cssSelector(".tableFixHead table tbody tr:nth-child(9)")).getText();
        System.out.println(lastRow);
    }
    //@Test
    public void textManipulation(){
        String text = driver.findElement(By.xpath("//div[contains(text(),'Total Amount Collected: 296')]")).getText();
        String newText = text.replaceAll("[^0-9]", "");
        Assert.assertEquals(Integer.parseInt(newText), 296);
    }
    //@Test
    public void hoverOver(){
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector("#mousehover")));
        waitFor(3);
        hoverOver("#mousehover");
        waitFor(3);
    }
    @Test
    public void handleIframe(){
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector("#mousehover")));
        waitFor(3);
        driver.switchTo().frame("iframe-name");
        System.out.println("switched to iframe");
        driver.findElement(By.xpath("//a[text()='Login']")).click();
    }
}
