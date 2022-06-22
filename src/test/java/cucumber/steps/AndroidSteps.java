package cucumber.steps;

import exceptions.ElementoNoVisibleException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CountrySelectionPage;
import pages.MainPage;
import pages.OrdersPage;
import pages.PaymentPage;
import utils.Logger;

public class AndroidSteps {

    private final CountrySelectionPage cs = new CountrySelectionPage();
    private final MainPage mp = new MainPage();
    private final OrdersPage op = new OrdersPage();
    private final PaymentPage pp = new PaymentPage();


    @Given("Seleccionar {string}")
    public void seleccionar(String string) throws InterruptedException {
        cs.selectCountry(string);
    }

    @And("Presionar boton Next luego de elegir {string}")
    public void presionarBotonNextLuegoDeElegir(String string) {
        cs.clickNext(string);
    }

    @When("Dentro del mercado de {string} acceder a pedidos y hacer login con {string} y {string} registrada")
    public void dentroDelMercadoDeAccederAPedidosYHacerLoginConYRegistrada(String pais, String user, String pass) throws ElementoNoVisibleException, InterruptedException {
        try {
        mp.comprobarMensajes();
        boolean loginAvaible = mp.comprobarLogin();
        if (loginAvaible) {
            mp.login(user, pass);
            Logger.pass("Login correcto con el usuario: " + user + " y la contraseña: " + pass + " en el pais: " + pais);
        }
        else {
            mp.goToLogin(user, pass);
            Logger.pass("Login correcto con el usuario: " + user + " y la contraseña: " + pass + " en el pais: " + pais);
        }
        mp.comprobarPolicy();}
        catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
            Logger.error("No se puedo hacer login");
        }

    }

    @Then("Iniciar orden tipo delivery indicar {string} seleccionar {string} y {string}")
    public void iniciarOrdenTipoDeliveryIndicarSeleccionarY(String direccion, String categoria , String producto) {
        op.comprobarBadLogin();
        op.typeOrder("McDelivery");
        op.initOrder(direccion);
        op.selectCategory(categoria);
        op.selectOrder(producto);

    }

    @Then("Iniciar orden tipo pickup indicar {string}  seleccionar {string} y {string}")
    public void iniciarOrdenTipoPickupIndicarSeleccionarY(String direccion, String categoria, String producto) {
        op.comprobarBadLogin();
        op.typeOrder("Pickup");
        op.initOrderPickup(direccion);
        op.selectCategory(categoria);
        op.selectOrder(producto);
    }

    @And("Seleccionar bebida {string}")
    public void seleccionarBebida(String bebida) throws InterruptedException {
        op.selectDrink(bebida);

    }


    @And("Quitar {string} y confirmar orden")
    public void quitarYConfirmarOrden(String ingrediente) {
        try {
            op.removeIngredient(ingrediente);
            op.confirmOrder();
            Logger.pass("Se confirma orden quitando el ingrediente: " + ingrediente);
        }
        catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            Logger.error("No se pudo quitar el ingrediente: " + ingrediente);
        }

    }

    @And("Editar {string} y guardar pedido")
    public void editarYGuardarPedido(String ingrediente) {
        op.editIngredient(ingrediente);
        op.saveOrder();
    }


    @Then("Dentro de pasarela de pago ingresar datos de tarjeta de credito con numero de tarjeta {string} y fecha de vencimiento {string} y codigo de seguridad {string}")
    public void dentroDePasarelaDePagoIngresarDatosDeTarjetaDeCreditoConNumeroDeTarjetaYFechaDeVencimientoYCodigoDeSeguridad(String nTarjeta, String Fvencimiento, String cvv) {
        pp.pay(nTarjeta, Fvencimiento, cvv);

    }


    @Then("Dentro de pasarela de delivery pago ingresar datos de tarjeta de credito con numero de tarjeta {string} y fecha de vencimiento {string} y codigo de seguridad {string}")
    public void dentroDePasarelaDeDeliveryPagoIngresarDatosDeTarjetaDeCreditoConNumeroDeTarjetaYFechaDeVencimientoYCodigoDeSeguridad(String nTarjeta, String Fvencimiento, String cvv) {
        pp.payDelivery(nTarjeta, Fvencimiento, cvv);

    }

    @When("Realizar pago, anadir codigo promocional {string}, seleccionar tipo de pago {string} y presionar boton  pagar")
    public void realizarPagoAnadirCodigoPromocionalSeleccionarTipoDePagoYPresionarBotonPagar(String codigo, String metodoPago) {
        pp.paymentData(metodoPago, codigo);

    }

}
