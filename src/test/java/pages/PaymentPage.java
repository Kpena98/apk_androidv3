package pages;

import android.AndroidBase;
import com.google.gson.annotations.Since;
import driver.DriverManager;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Helpers;
import utils.Logger;

import java.util.Arrays;
import java.util.List;


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
            System.out.println("el codigo promocional no se pudo aplicar");
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("el mensaje de error no se pudo mostrar");
        }
    }

    public void paymentData(String metodoPago, String codigo) {
        try {
            clickId("btPayMethod");
            hp.sleep(5);
            try {
                fastTypeId("firstNameText", "Prueba");
                fastTypeId("lastNameText", "Prueba");
                fastTypeId("suffixText", "9 2131 2124");
                fastTypeId("documentText", "27321231-0");
                clickId("btConfirm");
            } catch (org.openqa.selenium.TimeoutException ex) {

            }
            codigoPromocional(codigo);
            click("//*[@class='android.widget.TextView' and @text='" + metodoPago + "']");
            Logger.pass("Se ingresa codigo promocional valido exclusivamente para chile " + codigo + " y  se selecciona el metodo de pago: " + metodoPago);
        }
        catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
            Logger.error("No se pudo ingresar a la pasarela de pago");
        }
    }



    public void pay(String pais, String nTarjeta, String Fvencimiento, String cvv, String nombre) {
        String month = Fvencimiento.split("/")[0];
        String year = Fvencimiento.split("/")[1];
        hp.sleep(2);
        try {
            clickId("btPay");
            if (findElement("//*[contains(@text, \"Get the most\")]").isDisplayed()) {
                clickId("tvSkip");
            }
            List<String> xpath = hp.xpathPay(pais, nTarjeta, Fvencimiento, month, year, cvv, nombre);
            hp.sleep(15);
            for (int i = 0; i < xpath.size(); i++) {
                if (pais.equals("Costa Rica") || pais.equals("Panama") || pais.equals("Uruguay") ) {
                    if (i == 0){
                        type("//android.widget.EditText[contains(@resource-id,\"Name\")]", xpath.get(i));
                    }
                    else if (i == 1){
                        type("//android.widget.EditText[@resource-id=\"pan-input-expiration\"]", xpath.get(i));
                    }
                    else if (i == 2){
                        type("//android.widget.EditText[contains(@resource-id,\"expiration-input-pan\")]", xpath.get(i));
                    }
                    else if (i == 3){
                        type("//android.widget.EditText[contains(@resource-id,\"cvv\")]", xpath.get(i));
                    }
                }
                else if (pais.equals("Puerto Rico")) {
                    if (i<=2){
                        type("(//android.widget.EditText)["+(i+2)+"]", xpath.get(i));
                    }
                    else {
                        if (i == 3){
                            scrollAndSFindChild("Continuar");
                            scrollAndSFindChild("Prepago");
                        }
                        else {
                            type("(//android.widget.EditText)["+(i-2)+"]", xpath.get(i));
                        }
                    }
                }
                else {
                    type("(//android.widget.EditText)["+(i+1)+"]", xpath.get(i));
                }
            }
            if (pais.equals("Brasil")) {
                type("(//android.widget.EditText)[5]", xpath.get(4));
            }
            if(!pais.equals("Puerto Rico")){
                click("//android.widget.Button[@resource-id='checkout-btn']");
            }
            hp.sleep(30);
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
                        click("//android.widget.TextView[contains(@text, 'Table pickup')]");
                        typeId("com.mcdo.mcdonalds_debug.debug:id/cvParkingNumber ", "101");
                        clickId("com.mcdo.mcdonalds_debug.debug:id/btReady");
                        clickId("btn_accept_delivery");
                        clickId("com.mcdo.mcdonalds_debug.debug:id/btReady");
                        Logger.pass("Se realiza pago con tarjeta de credito");
                    } catch (org.openqa.selenium.TimeoutException |java.lang.NoClassDefFoundError  ex) {
                        Logger.error("No se pudo realizar el pago");
                    }
                } else {
                    Logger.error("No se pudo realizar el pago");
                }
            } catch (org.openqa.selenium.TimeoutException ex){
                Logger.error("El pago no se realizo de forma correcta");
                }
        }
        catch (TimeoutException | InterruptedException ex){
        }

    }

    public void payDelivery(String pais, String nTarjeta, String Fvencimiento, String cvv, String nombre) throws InterruptedException {
        String month = Fvencimiento.split("/")[0];
        String year = Fvencimiento.split("/")[1];
        hp.sleep(10);
        try {
            clickId("btPay");
            comprobarBadLogin();
            if (findElement("//*[contains(@text, \"Get the most\")]").isDisplayed()) {
                clickId("tvSkip");
            }
            try{
                List<String> xpath = hp.xpathPay(pais, nTarjeta, Fvencimiento, month, year, cvv, nombre);
                hp.sleep(15);
                for (int i = 0; i < xpath.size(); i++) {
                    if (pais.equals("Costa Rica") || pais.equals("Panama") || pais.equals("Uruguay") ) {
                        if (i == 0){
                            type("//android.widget.EditText[contains(@resource-id,\"Name\")]", xpath.get(i));
                        }
                        else if (i == 1){
                            type("//android.widget.EditText[@resource-id=\"pan-input-expiration\"]", xpath.get(i));
                        }
                        else if (i == 2){
                            type("//android.widget.EditText[contains(@resource-id,\"expiration-input-pan\")]", xpath.get(i));
                        }
                        else if (i == 3){
                            type("//android.widget.EditText[contains(@resource-id,\"cvv\")]", xpath.get(i));
                        }
                    }
                    else if (pais.equals("Puerto Rico")) {
                        if (i<=2){
                            type("(//android.widget.EditText)["+(i+2)+"]", xpath.get(i));
                        }
                        else {
                            if (i == 3){
                                scrollAndSFindChild("Continuar");
                                scrollAndSFindChild("Prepago");
                            }
                            else {
                                type("(//android.widget.EditText)["+(i-2)+"]", xpath.get(i));
                            }
                        }
                    }
                    else {
                        type("(//android.widget.EditText)["+(i+1)+"]", xpath.get(i));
                    }
                }
                if (pais.equals("Brasil")) {
                    type("(//android.widget.EditText)[5]", xpath.get(4));
                }
                if(!pais.equals("Puerto Rico")){
                    click("//android.widget.Button[@resource-id='checkout-btn']");
                }
                hp.sleep(30);
            }
            catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException ex){
                Logger.error("No se pudo ingresar a la pasarela de pago");
            }
            hp.sleep(10);
            click("//android.widget.Button[@text='Understood']");
            try {
                if(getText("//android.widget.TextView[contains(@text, 'Your payment')]").equals("Your payment was successful.")){
                    Logger.pass("Se realiza pago con tarjeta de credito");
                    clickId("btn_accept_delivery");
                    try {
                        if (getTextId("tvTitle").equals("Go to restaurant")) {
                            click("btn_accept_delivery");
                        }
                        else {
                            clickId("btHelp");
                            click("//android.widget.TextView[contains(@text, 'McDelivery')]//ancestor::android.view.ViewGroup//following-sibling::android.widget.LinearLayout//descendant::android.widget.Button[contains(@text, 'Track my order')]");
                        }

                    } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                        Logger.error("No se encontro mensaje de retiro ");
                    }
                }
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
                Logger.error("El pago no se realizo de forma correcta");
            }
        }
        catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
            Logger.error("No se pudo realizar el pago");
        }

    }
    public void comprobarBadLogin() {
        try {
            WebElement BadLogin = fastFindElement("//android.widget.TextView[@text=\"McDonald's Q\"]");
            if (BadLogin.isDisplayed()) {
                clickId("btn_accept");
            }
        }
        catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
            System.out.println("No se encontro mensaje de de bad login");
        }
    }

}
