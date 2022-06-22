package pages;

import android.AndroidBase;
import driver.DriverManager;
import org.apache.commons.logging.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Helpers;
import utils.Logger;

import java.util.Arrays;

public class OrdersPage extends AndroidBase {
    public OrdersPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    Helpers hp = new Helpers();

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

    public void typeOrder(String string) {
        if (string.equals("McDelivery")) {
            try {
                click("//*[@class='android.widget.TextView' and @text='" + string + "']");
            } catch (org.openqa.selenium.TimeoutException ex) {
                Logger.error("No se encontro el elemento Pedidos");
            }
        }
    }

    public void initOrder(String direccion) {
        hp.sleep(5);
        String[] dir = direccion.split(",");
        try {
            click("//*[@class='android.widget.Button' and @text='Start order']");
            try {
                if (findElement("//*[contains(@text, \"Where do we\")]").isDisplayed()) {
                    clickId("btn_accept_delivery");
                    clickId("dialogAcceptButton");
                }
            }
            catch (org.openqa.selenium.TimeoutException ex){

            }
            try {
                    try{
                        fastTypeId("etx_search", direccion);
                        try {
                            clickId("dialogAcceptButton");
                        }
                        catch (org.openqa.selenium.TimeoutException ex){
                        }
                        try {
                            click("(//android.widget.TextView[contains(@text, '" + dir[0] + "')])[1]");
                        }

                        catch (org.openqa.selenium.TimeoutException  ex){
                            try {
                                click("//*[class='android.widget.TextView' and @id='cl_home__item_profile']");
                            }
                            catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex2){
                                Logger.error("No se encontro la direccion ingresada");
                            }

                        }
                        clickId("btn_confirm_address");
                        Logger.pass("Se inicia orden de tipo delivery en la dirección " + direccion);
                    } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
                        try {
                            typeId("edtCityDelivery", dir[1] + "," + dir[2]);
                            System.out.println(Arrays.toString(dir));
                            typeId("edtStreetDelivery", dir[0]);
                            clickId("btnConfirmAddress");
                        }
                        catch (org.openqa.selenium.TimeoutException| org.openqa.selenium.NoSuchElementException | java.lang.ArrayIndexOutOfBoundsException ex2){
                            Logger.error("No se encontro el elemento direccion");
                        }
                    }


            }
            catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){


            }


        }
        catch (org.openqa.selenium.TimeoutException ex){
        }
    }

    public void initOrderPickup(String direccion) {
        hp.sleep(5);
        String[] dir = direccion.split(",");
        try {
            click("//*[@class='android.widget.Button' and @text='Start order']");
            try {
                if (findElement("//*[contains(@text, \"Find your\")]").isDisplayed()) {
                clickId("btn_accept_delivery");
            }   }
            catch (org.openqa.selenium.TimeoutException ex){

            }
            try {
                if (findElement("//*[contains(@text, \"Get the most\")]").isDisplayed()) {
                    clickId("tvSkip");
                }
            }
            catch (org.openqa.selenium.TimeoutException ex){

            }
            clickId("dialogAcceptButton");
            hp.sleep(2);
            typeId("etx_search", direccion);
            try{
            click("//*[@class='android.widget.TextView' and @text='" + dir[0] + "']");
            //clickId("cl_home_item_profile");
            clickId("btnAccept");
            }catch (TimeoutException ex){
                clickId("btYellow");
            }
            Logger.pass("Se inicia orden de tipo pickup en la tienda de  " + direccion);
        }
        catch (org.openqa.selenium.TimeoutException ex){

        }
    }

    public void selectOrder(String producto) {
        hp.sleep(5);
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
            } catch (org.openqa.selenium.TimeoutException ex) {
                Logger.error("No se encontro el ingrediente" + ingrediente);
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
            try {
                fastClick("//android.widget.TextView[contains(@text,'" + ingrediente + "')]//following-sibling::android.widget.ImageView[@resource-id='com.mcdo.mcdonalds_debug.debug:id/ivCheck']");
            }catch (org.openqa.selenium.TimeoutException ex) {
                try {
                    fastClick("//android.widget.TextView[contains(@text,'" + ingrediente + "')]//following-sibling::android.widget.FrameLayout//descendant::android.widget.ImageView[@resource-id='com.mcdo.mcdonalds_debug.debug:id/btDec']");}
                catch (org.openqa.selenium.TimeoutException ex2){
                    Logger.error("No se encontro el ingrediente " + ingrediente);
                }
            }
        } catch (org.openqa.selenium.TimeoutException ex) {
            Logger.error("No se encontro el ingrediente" + ingrediente);
        }
    }

    public void confirmOrder() {
        try {
            clickId("btConfirm");
            clickId("btAddProduct");
            clickId("btnCart");
        } catch (org.openqa.selenium.TimeoutException ex) {
            Logger.error("No se pudo confirmar el pedido");
        }
    }

    public void editIngredient(String ingrediente) {
        try {
            clickId("tvEditProduct");
            try {
                scrollAndFind("Customize ingredients");
            } catch (org.openqa.selenium.NoSuchElementException ex) {
                Logger.error("No se encontro el ingrediente" + ingrediente);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                try {
                    scrollAndSearchChild(ingrediente);
                } catch (NoSuchElementException | TimeoutException ex) {
                    Logger.error("No se encontro el ingrediente" + ingrediente);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    fastClick("//android.widget.TextView[contains(@text,'" + ingrediente + "')]//following-sibling::android.widget.ImageView[@resource-id='com.mcdo.mcdonalds_debug.debug:id/ivCheck']");
                }catch (org.openqa.selenium.TimeoutException ex) {
                    try {
                        fastClick("//android.widget.TextView[contains(@text,'" + ingrediente + "')]//following-sibling::android.widget.FrameLayout//descendant::android.widget.ImageView[@resource-id='com.mcdo.mcdonalds_debug.debug:id/btAdd']");}
                    catch (org.openqa.selenium.TimeoutException ex2){
                        Logger.error("No se encontro el ingrediente " + ingrediente);
                    }
                }
                Logger.pass("Se guarda pedido editando el ingrediente: " + ingrediente);
            } catch (org.openqa.selenium.TimeoutException ex) {
                Logger.error("No se encontro el ingrediente" + ingrediente);
            }
            clickId("btConfirm");
            clickId("btAddProduct");
        } catch (org.openqa.selenium.TimeoutException ex) {
            Logger.error("No se pudo editar el pedido");
        }

    }

    public void selectDrink(String soda) throws InterruptedException {
        try {
            scrollAndFind(soda);
            Logger.pass("Selecciono bebida: " + soda);
        }catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
            Logger.error("No se pudo cambiar la bebida " + soda);
        }

    }

    public void saveOrder() {
    }

    public void selectCategory(String categoria) {
        hp.sleep(10);
        try {
            scrollH(categoria);
            scrollAndFind(categoria);
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            Logger.error("No se encontro la categoria " + categoria);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
