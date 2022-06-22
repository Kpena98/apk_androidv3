package pages;

import android.AndroidBase;
import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Helpers;
import utils.Logger;
import java.util.Set;

public class PaymentPage extends AndroidBase {
    public PaymentPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    Helpers hp = new Helpers();

    public void codigoPromocional(String codigo) {
        typeId("com.mcdo.mcdonalds_debug.debug:id/etCouponCode", codigo);
        clickId("com.mcdo.mcdonalds_debug.debug:id/btCouponApply");
        try {
            if(fastFindElement("//android.widget.TextView[@text=\"McDonald's Q\"]").isDisplayed()) {
                clickId("btn_accept_delivery");
            }
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {

        }



    }

    public void paymentData(String metodoPago, String codigo) {
        clickId("btPayMethod");
        hp.sleep(5);
        try {
            fastTypeId("firstNameText", "Prueba");
            fastTypeId("lastNameText", "Prueba");
            fastTypeId("suffixText", "9 2131 2124");
            fastTypeId("documentText", "27321231-0");
            clickId("btConfirm");
        }
        catch (org.openqa.selenium.TimeoutException ex){

        }
        codigoPromocional(codigo);
        click("//*[@class='android.widget.TextView' and @text='" + metodoPago + "']");
    }



    public void pay(String nTarjeta, String Fvencimiento, String cvv) {
        String month = Fvencimiento.split("/")[0];
        String year = Fvencimiento.split("/")[1];
        hp.sleep(10);
        clickId("btPay");
        try {
            if (findElement("//*[contains(@text, \"Get the most\")]").isDisplayed()) {
                clickId("tvSkip");
            }
            type("//android.widget.EditText[@resource-id='form-checkout__cardNumber']", nTarjeta);
            type("//android.widget.EditText[@resource-id='form-checkout__cardExpirationMonth']", month);
            type("//android.widget.EditText[@resource-id='form-checkout__cardExpirationYear']", year);
            type("//android.widget.EditText[@resource-id='form-checkout__securityCode']", cvv);
            click("//android.widget.Button[@resource-id='checkout-btn']");
            try {
                if (getTextId("txt_alert_title_text").equals("Your payment was successful.")) {
                    clickId("btn_accept_delivery");
                    try {
                        if (getTextId("tvTitle").equals("Go to restaurant")) {
                            clickId("btn_accept_delivery");
                            click("//*[contains(@text, \"Arrived at the restaurant\")]");
                        }
                        else {
                            clickId("btHelp");
                            click("//android.widget.TextView[contains(@text, 'Go to restaurant')]//ancestor::android.view.ViewGroup//following-sibling::android.widget.LinearLayout//descendant::android.widget.Button[contains(@text, 'Arrived')] ");
                        }
                        click("//android.widget.TextView[contains(@text, 'Table pickup')] ");
                        typeId("com.mcdo.mcdonalds_debug.debug:id/cvParkingNumber ", "101");
                        clickId("com.mcdo.mcdonalds_debug.debug:id/btReady");
                        clickId("btn_accept_delivery");
                        clickId("com.mcdo.mcdonalds_debug.debug:id/btReady");
                    } catch (org.openqa.selenium.TimeoutException ex) {

                    }
                } else {
                    Logger.error("No se pudo realizar el pago");
                }
            } catch (org.openqa.selenium.TimeoutException ex){
                Logger.error("El pago no se realizo de forma correcta");
                }


            hp.sleep(15);


        }
        catch (org.openqa.selenium.TimeoutException ex){
        }

    }

    public void payDelivery(String nTarjeta, String Fvencimiento, String cvv) {
        String month = Fvencimiento.split("/")[0];
        String year = Fvencimiento.split("/")[1];
        hp.sleep(10);
        clickId("btPay");
        comprobarBadLogin();
        try {
            if (findElement("//*[contains(@text, \"Get the most\")]").isDisplayed()) {
                clickId("tvSkip");
            }
            try{
                type("//android.widget.EditText[@resource-id='form-checkout__cardNumber']", nTarjeta);
                type("//android.widget.EditText[@resource-id='form-checkout__cardExpirationMonth']", month);
                type("//android.widget.EditText[@resource-id='form-checkout__cardExpirationYear']", year);
                type("//android.widget.EditText[@resource-id='form-checkout__securityCode']", cvv);
                click("//android.widget.Button[@resource-id='checkout-btn']");
            }
            catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){

                type("//android.widget.EditText[@resource-id='adyen-checkout-encryptedCardNumber-1655905172156']",nTarjeta);
            }
            click("//android.widget.Button[@resource-id='checkout-btn']");
            try {
                if (getTextId("txt_alert_title_text").equals("Your payment was successful.")) {
                    clickId("btn_accept_delivery");
                    try {
                        if (getTextId("tvTitle").equals("Go to restaurant")) {
                            click("btn_accept_delivery");
                        }
                        else {
                            clickId("btHelp");
                            click("//android.widget.TextView[contains(@text, 'McDelivery')]//ancestor::android.view.ViewGroup//following-sibling::android.widget.LinearLayout//descendant::android.widget.Button[contains(@text, 'Track my order')]");
                        }

                    } catch (org.openqa.selenium.TimeoutException ex) {
                        Logger.error("No se encontro mensaje de retiro ");
                    }
                } else {
                    Logger.error("No se pudo realizar el pago");
                }
            } catch (org.openqa.selenium.TimeoutException ex){
                Logger.error("El pago no se realizo de forma correcta");
            }


            hp.sleep(15);


        }
        catch (org.openqa.selenium.TimeoutException ex){
        }

    }
    public void comprobarBadLogin() {
        try {
            WebElement BadLogin = fastFindElement("//android.widget.TextView[@text=\"McDonald's Q\"]");
            if (BadLogin.isDisplayed()) {
                clickId("btn_accept");
            }
        }
        catch (org.openqa.selenium.TimeoutException ex){

        }
    }

}
