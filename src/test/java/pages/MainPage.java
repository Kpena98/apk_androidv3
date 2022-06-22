package pages;

import android.AndroidBase;
import driver.DriverManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Helpers;
import utils.Logger;



public class MainPage extends AndroidBase {

    public MainPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }


    Helpers hp = new Helpers();

    public void comprobarBadLogin() {
        try {
            WebElement BadLogin = fastFindElement("//android.widget.TextView[@text=\"McDonald's Q\"]");
            if (BadLogin.isDisplayed()) {
                fastClickId("btn_accept");
            }
        }
        catch (org.openqa.selenium.TimeoutException ex){

        }
    }

    public void comprobarMensajes() {
        try{
            if (fastFindElementId("termsMessage").isDisplayed()) {
                clickId("dialogDismissButton");
            }
        }
        catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){

        }
    }

    public boolean comprobarLogin(){
        try {
            try {
                fastClick("//*[@class='android.widget.TextView' and @text='" + hp.orders().get(0) + "']");
            }catch (org.openqa.selenium.TimeoutException ex){
                fastClick("//*[@class='android.widget.TextView' and @text='" + hp.orders().get(1) + "']");
            }
            return false;
        }
        catch (org.openqa.selenium.TimeoutException ex){
            WebElement loginPage = findElement("//*[@class='android.widget.TextView' and @text='LOGIN']");
            return loginPage.isDisplayed();
        }

    }



    public void comprobarPolicy(){
        try {
            if(fastFindElement("//*[@class='android.widget.TextView' and @text='New privacy policy']").isDisplayed()){
                clickId("dialog_rate_skip_button");
            }
        }
        catch (org.openqa.selenium.TimeoutException ex){
            System.out.println("No aparece el texto de política de privacidad");
        }
        hp.sleep(2);
    }


    public void login(String user, String pass) {
        try {
            typeId( "mail_input_text", user);
            typeId("password_input_text", pass);
            clickId("login_button_container");
            hp.sleep(30);
            if (comprobarFav()){
                chooseYourFav();
            }
            for (int i = 0; i < 3; i++) {
                clickId("dialogAcceptButton");
            }
            goToPedidos();
        }
        catch (org.openqa.selenium.TimeoutException ex){
           Logger.error("No se pudo loguear");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Logger.error("No se pudo loguear");
        }

    }

    public void goToLogin(String user, String pass) {
        comprobarBadLogin();
        try {
            fastClickId("dialogAcceptButton");
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {

        }
        try {
            typeId( "mail_input_text", user);
            typeId("password_input_text", pass);
            clickId("login_button_container");
            hp.sleep(10);
            if (comprobarFav()){
                chooseYourFav();
                goToPedidos();
            }
            for (int i = 0; i < 3; i++) {
                clickId("dialogAcceptButton");
            }
            goToPedidos();
        }
        catch (org.openqa.selenium.TimeoutException ex){
            Logger.error("No se pudo loguear");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Logger.error("No se pudo loguear");
        }
    }

    public boolean comprobarFav(){
        try {
            fastClick("//*[@class='android.widget.TextView' and @text='Choose your favorites']");
            return true;
        }
        catch (org.openqa.selenium.TimeoutException ex){
            return false;
        }
    }

    public void chooseYourFav () throws InterruptedException {
        hp.sleep(2);
        try {
            for (int i = 0; i < 3; i++) {
                try{
                    scrollAndFind(hp.favs().get(i));
                } catch (org.openqa.selenium.TimeoutException ex) {
                    Logger.error("No se encontró el elemento " + hp.favs().get(i));
                }
            }
            hp.sleep(2);
            clickId("btn_accept");
        } catch (TimeoutException ex){
            Logger.error("No se encontro seleccionar de favoritos");
        }
    }
    public void goToPedidos() {
        try {
            click("//*[@class='android.widget.TextView' and @text='" + hp.orders().get(0) + "']");
        }catch (TimeoutException ex){
            click("//*[@class='android.widget.TextView' and @text='" + hp.orders().get(1) + "']");
        }
    }
}
