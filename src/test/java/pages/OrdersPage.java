package pages;

import android.AndroidBase;
import driver.DriverManager;
import exceptions.ElementoNoVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Logger;

import java.util.Objects;


public class OrdersPage extends AndroidBase {
    public OrdersPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }


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

    public void typeOrder(String string) {
        if (string.equals("McDelivery")) {
            try {
                click("//*[@class='android.widget.TextView' and @text='" + string + "']");
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                Logger.error("No se encontro el elemento Pedidos");
            }
        }
    }

    public void initOrder(String direccion, String pais){
        try {
            click("//*[@class='android.widget.Button' and @text='Start order']");
            try {
                if (findElement("//*[contains(@text, \"Where do we\")]").isDisplayed()) {
                    clickId("btn_accept_delivery");
                    clickId("dialogAcceptButton");
                }
            }
            catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
                Logger.error("No se encontro el mensaje para buscar direccion de delivery");
            }
            try{
                try{
                    if (!(Objects.equals(pais, "Colombia")) && !(Objects.equals(pais, "Costa Rica"))) {
                        fastTypeId("etx_search", direccion + "\n" );
                        try{
                            clickId("dialogAcceptButton");
                        }
                        catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
                            System.out.println("No se encontro el mensaje para buscar direccion de delivery");
                        }
                        try {
                            String[] dir = direccion.split(",");
                            click("(//android.widget.TextView[contains(@text, '" + dir[0] + "')])[1]");
                        }
                        catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
                            try {
                                click("//android.view.ViewGroup[@resource-id='com.mcdo.mcdonalds_debug.debug:id/cl_home_item_profile'][1]");
                            }
                            catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex2){
                                Logger.error("No se encontro la direccion ingresada");
                            }
                        }
                        clickId("btn_confirm_address");
                    }
                    else if (Objects.equals(pais, "Costa Rica")){
                        String[] dir = direccion.split(",");
                        System.out.println("entro a costa rica");
                        fastTypeId("etx_search", direccion );
                        try{
                            clickId("dialogAcceptButton");
                        }
                        catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
                            System.out.println("No se encontro el mensaje para buscar direccion de delivery");
                        }
                        click("(//android.widget.TextView[contains(@text, '" + dir[0] + "')])[1]");
                        type("//android.widget.EditText[@resource-id='com.mcdo.mcdonalds_debug.debug:id/edtNumber']", dir[1]);
                        clickId("btn_confirm_address");
                    }
                    else {
                        String[] dir = direccion.split(",");
                        typeId("edtCityDelivery", dir[1] + "," + dir[2]);
                        typeId("edtStreetDelivery", dir[0]);
                        for (int i = 0; i < 3; i++) {
                            click("//android.widget.Button[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/btnConfirmAddress\"]");
                        }
                    }
                }catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
                    Logger.error("No se encontro el mensaje para buscar direccion de delivery");
                }
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                Logger.error("No se encontro el elemento de direccion");
            }
            try {
                if(fastFindElement("//android.widget.TextView[contains(@text,\"We are sorry!\"]").getText().equals("We are sorry! At this time we do not have coverage in your area")){
                    Logger.error("El restaurante no esta disponible en la direccion ingresada");
                }
            }
            catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
                Logger.pass("Se inicia orden de tipo delivery en la dirección " + direccion);
            }
        }
        catch (TimeoutException | NoSuchElementException  ex){
            Logger.error("No se encontro el boton de iniciar orden");
        }
    }

    public void initOrderPickup(String direccion) {
        try {
            click("//*[@class='android.widget.Button' and @text='Start order']");
            try {
                if (findElement("//*[contains(@text, \"Find your\")]").isDisplayed()) {
                    clickId("btn_accept_delivery");
                }
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                Logger.error("No se encontro el mensaje para buscar restaurante");
            }
            try {
                if (findElement("//*[contains(@text, \"Get the most\")]").isDisplayed()) {
                    clickId("tvSkip");
                }
                clickId("dialogAcceptButton");
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                System.out.println("No se mostro el mensaje obtener localización");
            }
            try {
                typeId("etx_search", direccion + "\n");
                try {
                    String[] dir = direccion.split(",");
                    click("(//android.widget.TextView[contains(@text, '" + dir[0] + "')])[1]");
                } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                    try {
                        click("//android.view.ViewGroup[@resource-id='com.mcdo.mcdonalds_debug.debug:id/cl_home_item_profile'][1]");
                    } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex2) {
                        Logger.error("No se encontro la direccion ingresada");
                    }
                }
                try {
                    clickId("btnAccept");
                } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                    clickId("btYellow");
                }
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                System.out.println("No se pudo seleccionar un restaurante");
            }
            try {
                if (findElement("(//android.widget.TextView[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/stateBubbleText\"])[1]").getText().equals("Closed")) {
                    Logger.error("El restaurante está cerrado");
                }
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                try {
                    if (fastFindElement("//android.widget.TextView[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/txt_alert_title_text\"]").getText().equals("Restaurant closed")) {
                        Logger.error("El restaurante seleccionado está cerrado ");
                    }
                } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex1) {
                    try {
                        if (fastFindElement("//android.widget.TextView[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/noRestaurantAlert\"]").getText().equals("Restaurant not found")) {
                            System.out.println("No se encontro el restaurante" + fastFindElement("//android.widget.TextView[@resource-id=\"com.mcdo.mcdonalds_debug.debug:id/noRestaurantAlert\"]").getText());
                            Logger.error("No se encontraron restaurantes en la direccion ingresada");
                        }
                    } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex2) {
                        Logger.pass("Se inicia orden de tipo pickup en el restaurante de " + direccion);
                    }
                }
            }
        }
        catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
            Logger.error("No se encontro el boton de iniciar orden");
        }
    }

    public void selectOrder(String producto) {
        try {
            scrollAndFind(producto);
            Logger.pass("Se elige el siguiente producto: "+ producto );
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            Logger.error("No se encontro el producto: " + producto);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void removeIngredient(String ingrediente) {
        try {
            scrollAndFind("Customize ingredients");
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            Logger.error("No se encontro Customize ingredients");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            try {
                scrollAndSearch(ingrediente);
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                Logger.error("No se encontro el ingrediente" + ingrediente);
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
            try {
                fastClick("//android.widget.TextView[contains(@text,'" + ingrediente + "')]//following-sibling::android.widget.ImageView[@resource-id='com.mcdo.mcdonalds_debug.debug:id/ivCheck']");
                Logger.pass("Se confirma orden quitando el ingrediente: " + ingrediente);
            }catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                try {
                    fastClick("//android.widget.TextView[contains(@text,'" + ingrediente + "')]//following-sibling::android.widget.FrameLayout//descendant::android.widget.ImageView[@resource-id='com.mcdo.mcdonalds_debug.debug:id/btDec']");
                    Logger.pass("Se confirma orden quitando el ingrediente: " + ingrediente);
                }
                catch (org.openqa.selenium.TimeoutException  | org.openqa.selenium.NoSuchElementException ex2){
                    Logger.error("No se encontro el ingrediente " + ingrediente);
                }
            }
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            Logger.error("No se encontro el ingrediente" + ingrediente);
        }
    }

    public void confirmOrder() {
        try {
            clickId("btConfirm");
            clickId("btAddProduct");
            clickId("btnCart");
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            Logger.error("No se pudo confirmar el pedido");
        }
    }

    public void editIngredient(String ingrediente) {
        try {
            clickId("tvEditProduct");
            try {
                scrollAndFind("Customize ingredients");
            } catch (org.openqa.selenium.NoSuchElementException  ex) {
                Logger.error("No la opcion de editar ingredientes");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                try {
                    scrollAndSearchChild(ingrediente);
                } catch (NoSuchElementException | TimeoutException ex) {
                    Logger.error("No se encontro el ingrediente: " + ingrediente);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    fastClick("//android.widget.TextView[contains(@text,'" + ingrediente + "')]//following-sibling::android.widget.ImageView[@resource-id='com.mcdo.mcdonalds_debug.debug:id/ivCheck']");
                    Logger.pass("Se guarda pedido editando el ingrediente: " + ingrediente);
                }catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                    try {
                        fastClick("//android.widget.TextView[contains(@text,'" + ingrediente + "')]//following-sibling::android.widget.FrameLayout//descendant::android.widget.ImageView[@resource-id='com.mcdo.mcdonalds_debug.debug:id/btAdd']");
                        Logger.pass("Se guarda pedido editando el ingrediente: " + ingrediente);
                    }
                    catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex2){
                        Logger.error("No se encontro el ingrediente: " + ingrediente);
                    }
                }
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                Logger.error("No se encontro el ingrediente" + ingrediente);
            }
            clickId("btConfirm");
            clickId("btAddProduct");
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            Logger.error("No se pudo editar el pedido");
        }

    }

    public void selectDrink(String soda) throws InterruptedException {
        try {
            scrollH("com.mcdo.mcdonalds_debug.debug:id/rvItems" ,soda);
            scrollAndFind(soda);
            Logger.pass("Selecciono bebida: " + soda);
        }catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
            Logger.error("No se pudo cambiar la bebida " + soda);
        }

    }


    public void selectCategory(String categoria) {
        try {
            scrollH("com.mcdo.mcdonalds_debug.debug:id/homeTabLayout",categoria);
            scrollAndFind(categoria);
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            Logger.error("No se encontro la categoria " + categoria);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
