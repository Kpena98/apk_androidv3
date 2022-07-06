package pages;


import driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Helpers;
import utils.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class WebPage {
    Helpers hp = new Helpers();
    RemoteWebDriver driver;

    public void iniciarWebDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--enable-javascript");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36");

        caps.setCapability(ChromeOptions.CAPABILITY, options);
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browserstack.local", "false");
        caps.setCapability("browserstack.selenium_version", "4.1.2");

        caps.setCapability("browserstack.idleTimeout", "300");

        caps.setCapability("resolution", "1920x1080");
        caps.setCapability("acceptSslCerts", "true");


        try {
            Thread.sleep(1000);
        } catch(Exception e) {
            System.out.println("error");
        }
        driver = (new RemoteWebDriver(new URL(DriverManager.URL), caps));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void cerrarWebDriver() {
        driver.quit();
    }

    public void validarRegistro (String user, String pass) throws MalformedURLException {
        hp.sleep(30);
        try {
            iniciarWebDriver();
            driver.get("https://login.yahoo.com/?.src=ym&lang=en-US&done=https%3A%2F%2Fmail.yahoo.com%2F");
            WebElement userElement = driver.findElementById("login-username");
            userElement.sendKeys(user);
            WebElement nextButton = driver.findElementById("login-signin");
            nextButton.click();
            Set<String> tabs = driver.getWindowHandles();
            hp.sleep(5);
            if (tabs.size() > 1) {
                driver.switchTo().window(tabs.toArray()[1].toString());
                driver.close();
                driver.switchTo().window(tabs.toArray()[0].toString());
                WebElement nextButton2 = driver.findElementById("login-signin");
                nextButton2.click();
            }
            WebElement passElement = driver.findElementById("login-passwd");
            passElement.sendKeys(pass);
            WebElement nextButton3 = driver.findElementById("login-signin");
            nextButton3.click();
            hp.sleep(5);
            Set<String> tabs2 = driver.getWindowHandles();
            if (tabs2.size() > 1) {
                driver.switchTo().window(tabs2.toArray()[1].toString());
                driver.close();
                driver.switchTo().window(tabs2.toArray()[0].toString());
                WebElement nextButton4 = driver.findElementById("login-signin");
                nextButton4.click();
            }
            hp.sleep(5);
            WebElement correo = driver.findElementByXPath("(//strong[@class=\"u_Z13VSE6\" and contains(text(),\"No Reply McDonalds\")])[1]");
            correo.click();
            WebElement aceptar = driver.findElementByXPath("//a[contains(text(),\"Continue\")]");
            aceptar.click();
            Set<String> tabs3 = driver.getWindowHandles();
            if (tabs3.size() > 1) {
                driver.switchTo().window(tabs3.toArray()[1].toString());
                if (driver.findElementByXPath("//a[contains(text(),\"Your account has been verified\")]").isDisplayed()) {
                    Logger.pass("Registro exitoso");
                } else {
                    Logger.error("Registro fallido");
                }
                driver.close();
            }
            cerrarWebDriver();
        }
        catch (Exception e) {
            Logger.error("Error al validar el registro");
            cerrarWebDriver();
        }

    }




}
