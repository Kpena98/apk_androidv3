package pages;

import android.AndroidBase;
import driver.DriverManager;
import exceptions.ElementoNoVisibleException;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import utils.Helpers;
import utils.Logger;

public class CountrySelectionPage extends AndroidBase {
    public CountrySelectionPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }
    Helpers hp = new Helpers();
    public void selectCountry(String string) throws InterruptedException {
        hp.sleep(5);
        try {
            scrollAndFind(string);
        }
        catch (org.openqa.selenium.TimeoutException ex){
           Logger.error("No se encontro el pais " + string);
        }
    }
    public void clickNext(String string)  {
        try {
            click("//*[@class='android.widget.TextView' and @text='NEXT']");
            Logger.pass("Entrando al mercado de " + string);
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            Logger.error("No se pudo presionar el botón Next ");
        }
    }
}
