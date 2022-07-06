package pages;

import android.AndroidBase;
import driver.DriverManager;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Helpers;
import utils.Logger;

import java.util.Arrays;
import java.util.List;


public class MainPage extends AndroidBase {

    public MainPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }


    Helpers hp = new Helpers();
    WebPage wp = new WebPage();

    public void comprobarBadLogin() {
        try {
            WebElement BadLogin = fastFindElement("//android.widget.TextView[@text=\"McDonald's Q\"]");
            if (BadLogin.isDisplayed()) {
                fastClickId("btn_accept");
            }
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("No se encontró el mensaje de error de login");
        }
    }

    public void comprobarMensajes() {
        try {
            if (fastFindElementId("termsMessage").isDisplayed()) {
                clickId("dialogDismissButton");
            }
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("No se encontró el mensaje para comprobar telefono");
        }
    }

    public void comprobarLogin() {
        try {
            fastFindElement("//android.widget.LinearLayout[@content-desc=\"LOGIN\"]").isDisplayed();
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            goToPedidos();
        }

    }

    public void comprobarPolicy() {
        try {
            if (fastFindElement("//*[@class='android.widget.TextView' and @text='New privacy policy']").isDisplayed()) {
                clickId("dialog_rate_skip_button");
            }
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("No aparece el texto de política de privacidad");
        }
        hp.sleep(2);
    }


    public void login(String user, String pass, String pais) {
        comprobarBadLogin();
        try {
            boolean registrado = comprobarUsuarioNoRegistrado(user, pass);
            if (!registrado) {
                Logger.pass("Login correcto con el usuario: " + user + " y la contraseña: " + pass + " en el pais: " + pais);
            } else {
                Logger.error("Login incorrecto con el usuario: " + user + " y la contraseña: " + pass + " en el pais: " + pais + " se debe registrar usuario");
            }

        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            Logger.error("No se puedo hacer login ja");
        }
        if (comprobarFav()) {
            chooseYourFav();
        }
        comprobarPolicy();
        try {
            for (int i = 0; i < 3; i++) {
                clickId("dialogAcceptButton");
            }
           goToPedidos();
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("No se encontraron los mensajes de las funcionalidades de la app");
        }
    }

    public void login2(String user, String pass, String pais) {
        comprobarBadLogin();
        try {
            boolean registrado = comprobarUsuarioNoRegistrado(user, pass);
            if (!registrado) {
                Logger.pass("Login correcto con el usuario: " + user + " y la contraseña: " + pass + " en el pais: " + pais);
            } else {
                eliminarCuenta(user, pass);
                Logger.error("Login incorrecto con el usuario: " + user + " y la contraseña: " + pass + " en el pais: " + pais + " se debe registrar usuario");
            }

        } catch (TimeoutException | NoSuchElementException | InterruptedException ex) {
            Logger.error("No se puedo hacer login");
        }
        if (comprobarFav()) {
            chooseYourFav();
        }
        comprobarPolicy();
        try {
            for (int i = 0; i < 3; i++) {
                clickId("dialogAcceptButton");
            }
            //goToPedidos();
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("No se encontraron los mensajes de las funcionalidades de la app");
        }
    }

    public boolean comprobarFav() {
        try {
            fastClick("//*[@class='android.widget.TextView' and @text='Choose your favorites']");
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            return false;
        }
    }

    public void chooseYourFav() {
        try {
            for (int i = 0; i < 3; i++) {
                try {
                    scrollAndSFindChild(hp.favs().get(i));
                } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                    Logger.error("No se encontró el elemento " + hp.favs().get(i));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            clickId("btn_accept");
        } catch (TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            Logger.error("No se encontro seleccionar de favoritos");
        }
    }

    public void goToPedidos() {
        try {
            click("//*[@class='android.widget.TextView' and @text='" + hp.orders().get(0) + "']");
        } catch (TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            try {
                click("//*[@class='android.widget.TextView' and @text='" + hp.orders().get(1) + "']");
            } catch (TimeoutException | org.openqa.selenium.NoSuchElementException ex2) {
                System.out.println("No se pudo ingresar a pedidos");
            }
        }
        comprobarBadLogin();
        try {
            fastClickId("dialogAcceptButton");
        } catch (TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("No se encontró el mensaje GoToLogin");
        }
    }

    public boolean comprobarUsuarioNoRegistrado(String user, String pass) {
        boolean userNoExist = false;
        try {
            typeId("mail_input_text", user);
            typeId("password_input_text", pass);
            clickId("login_button_container");
            if (fastFindElement("//android.widget.TextView[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/dialogMessage\"]").isDisplayed()) {
                fastClickId("dialogAcceptButton");
                userNoExist = true;
            }
        } catch (TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("No se encontró el usuario");
        }
        return userNoExist;
    }

    public void registrarUsuario(String user, String pass, String pais) {
        List<String> dataList = Arrays.asList(user, pass, pass);
        List<String> dataXpath = hp.dataRegister(pais);
        String documentId = hp.getDataUser(pais, 0);
        try {
            click("//android.widget.LinearLayout[@content-desc=\"REGISTER\"]/android.widget.TextView");
            try {
                clickId("mail_register_button");
                List<AndroidElement> registerList = findElements("//android.widget.EditText");
                for (int i = 0; i < registerList.size() ; i++)  {
                    if(registerList.get(i).getAttribute("resource-id").equals(dataXpath.get(i))) {
                        if (i == 3){
                            registerList.get(i).sendKeys(documentId);
                        } else {
                            registerList.get(i).sendKeys(dataList.get(i));
                        }
                    }
                }
                clickId("com.mcdo.mcdonalds_debug.debug:id/terms_conditions_check_box");
                clickId("com.mcdo.mcdonalds_debug.debug:id/email_notification_checkbox");
                if(fastFindElementId("com.mcdo.mcdonalds_debug.debug:id/loginButton").isEnabled()){
                    clickId("com.mcdo.mcdonalds_debug.debug:id/loginButton");
                } else {
                    Logger.error("El boton de registro no esta habilitado");
                }
                hp.sleep(10);
                clickId("com.mcdo.mcdonalds_debug.debug:id/closeButton");
            } catch (TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                System.out.println("No se encontró el boton de registro con mail");
            }


        } catch (TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("No se encontró el botón de registro");
        }
    }

    public void eliminarCuenta(String user, String pass) throws InterruptedException {
        try{
        click("//android.widget.TextView[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/navigation_bar_item_small_label_view\" and @text=\"More\"]");
        scrollAndSFindChild("Perfil");
        click("//android.widget.TextView[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/deleteUserLink\"]");
        clickId("dialogAcceptButton");
        }catch (TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("No se encontró el botón de eliminar cuenta");
        }
    }
    public void configurarPerfil(String pais)throws InterruptedException {
        String documentId = hp.getDataUser(pais,0);
        try{
            click("//android.widget.TextView[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/navigation_bar_item_small_label_view\" and @text=\"More\"]");
            scrollAndSFindChild("Perfil");
            type("//android.widget.EditText[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/firstNameText\"]", "Apro");
            type("//android.widget.EditText[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/lastNameText\"]", "Pruebas");
            scrollAndSearchChild("Document");
            type("//android.widget.EditText[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/documentText\"]", documentId);
            scrollAndSearchChild("Phone");
            type("//android.widget.EditText[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/suffixText\"]", "123456789");
            clickId("saveBtn");
            click("//android.widget.TextView[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/deleteUserLink\"]");
            clickId("dialogAcceptButton");


        }catch (TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            System.out.println("No se encontró el botón de eliminar cuenta");
        }
    }
}
