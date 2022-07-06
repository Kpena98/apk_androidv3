package pages;

import android.AndroidBase;
import driver.DriverManager;
import org.openqa.selenium.support.PageFactory;
import utils.Helpers;
import utils.Logger;

public class CountrySelectionPage extends AndroidBase {
    public CountrySelectionPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }
    Helpers hp = new Helpers();
    public void selectCountry(String string) throws InterruptedException {
        hp.sleep(15);
        try {
            scrollAndFind(string);
        }
        catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
           Logger.error("No se encontro el pais " + string);
        }
    }
    public void clickNext(String string)  {
        try {
            click("//*[@class='android.widget.TextView' and @text='NEXT']");
            Logger.pass("Entrando al mercado de " + string);
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException  ex) {
            Logger.error("No se pudo presionar el botón Next ");
        }
    }
}
