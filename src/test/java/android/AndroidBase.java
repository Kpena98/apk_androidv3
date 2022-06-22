package android;

import driver.DriverManager;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Helpers;

import java.util.List;
import java.util.Set;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;
@SuppressWarnings("unchecked")
public class AndroidBase {
    AndroidDriver<AndroidElement> driver = DriverManager.getDriver();

    Helpers hp = new Helpers();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    WebDriverWait fastWait = new WebDriverWait(driver, 2);


    public void click(String xpath) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        element.click();
    }

    public void fastClick(String xpath) {
        WebElement element = fastWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        element.click();
    }

    public String getTextId(String id) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        return element.getText();
    }

    public void clickId(String id) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        element.click();
    }
    public void fastClickId(String id) {
        WebElement element = fastWait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        element.click();
    }
    public WebElement findElement(String xpath) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
    public WebElement fastFindElement(String xpath) {
        return fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
    public WebElement fastFindElementId(String id) {
        return fastWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }
    public void  type (String xpath, String text) {
       WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
       element.sendKeys(text);
    }

    public void  typeId (String id, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        element.sendKeys(text);
    }
    public void  fastTypeId (String id, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        element.sendKeys(text);
    }
    public void typeKeyId(String id, Keys key) {
        driver.executeScript("experitest:client.elementSendText(\"NATIVE\", \"<ELEMENT TO SEND KEYS TO>']\", \"0\", \"{\" TAB}\")");
    }
    public void scrollAndFind(String text) throws InterruptedException {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));").click();
    }

    public void scrollAndSearch(String text) throws InterruptedException {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));");
    }
    public void scrollAndSearchChild(String text) throws InterruptedException {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().textContains(\""+ text+"\"))");

    }

    public void scrollAndSFindChild(String text) throws InterruptedException {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().textContains(\""+ text+"\"))").click();

    }

    public void scrollH(String text) throws InterruptedException {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)."
                + "resourceId(\"com.mcdo.mcdonalds_debug.debug:id/homeTabLayout\"))"
                + ".setAsHorizontalList().scrollIntoView(new UiSelector().text(\""+text+"\"))");
    }

}
