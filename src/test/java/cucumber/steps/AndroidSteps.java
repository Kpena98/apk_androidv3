package cucumber.steps;

import dataProvider.jsonDataReader;
import exceptions.ElementoNoVisibleException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;
import utils.Helpers;
import utils.Logger;

import java.io.IOException;
import java.text.Normalizer;


public class AndroidSteps {

    private final CountrySelectionPage cs = new CountrySelectionPage();
    private final MainPage mp = new MainPage();
    private final OrdersPage op = new OrdersPage();
    private final PaymentPage pp = new PaymentPage();
    private final jsonDataReader jdr = new jsonDataReader();
    private final WebPage wp = new WebPage();

    Helpers hp = new Helpers();

    private String data;

    @When("Ejecuto con la configuracion {string}")
    public void ejecutoConLaConfiguracion(String config) throws ElementoNoVisibleException {
        data = hp.getJsonName(config);
    }

    @And("Seleccionar Pais {int}")
    public void seleccionarPais(int index) throws InterruptedException, IOException {
        String pais = jdr.dataReader( data, index, "Pais");
        cs.selectCountry(pais);
    }

    @And("Presionar boton Next luego de elegir Pais {int}")
    public void presionarBotonNextLuegoDeElegirPais(int index) throws IOException {
        String pais = jdr.dataReader(data, index, "Pais");
        cs.clickNext(pais);
    }

    @When("Dentro del mercado de Pais acceder a pedidos y hacer login con User y Pass registrada {int}")
    public void dentroDelMercadoDePaisAccederAPedidosYHacerLoginConUserYPassRegistrada(int index) throws IOException {
        String pais = jdr.dataReader(data, index, "Pais");
        String user = jdr.dataReader(data, index, "User");
        String pass = jdr.dataReader(data, index, "Pass");
        mp.comprobarMensajes();
        mp.comprobarLogin();
        mp.login(user, pass, pais);
        mp.comprobarPolicy();
    }

    @Then("Iniciar orden tipo pickup indicar Tienda seleccionar Categoria y Producto {int}")
    public void iniciarOrdenTipoPickupIndicarTiendaSeleccionarCategoriaYProducto(int index) throws IOException {
        String direccion = jdr.dataReader(data, index, "Tienda");
        String categoria = jdr.dataReader(data, index, "Categoria");
        String producto = jdr.dataReader(data, index, "Producto");
        op.comprobarBadLogin();
        op.typeOrder("Pickup");
        op.initOrderPickup(direccion);
        op.selectCategory(categoria);
        op.selectOrder(producto);
    }

    @Then("Iniciar orden tipo delivery indicar Direccion seleccionar Categoria y Producto {int}")
    public void iniciarOrdenTipoDeliveryIndicarDireccionSeleccionarCategoriaYProductoIndex(int index) throws IOException, ElementoNoVisibleException {
        String direccion = jdr.dataReader(data, index, "Direccion");
        String categoria = jdr.dataReader(data, index, "Categoria");
        String producto = jdr.dataReader(data, index, "Producto");
        String pais = jdr.dataReader(data, index, "Pais");
        pais = Normalizer.normalize(pais, Normalizer.Form.NFD);
        pais = pais.replaceAll("[^\\p{ASCII}]", "");
        op.comprobarBadLogin();
        op.typeOrder("McDelivery");
        op.initOrder(direccion, pais);
        op.selectCategory(categoria);
        op.selectOrder(producto);
    }

    @And("Seleccionar bebida Bebida {int}")
    public void seleccionarBebidaBebida(int index) throws IOException, InterruptedException {
        String bebida = jdr.dataReader(data, index, "Bebida");
        op.selectDrink(bebida);
    }

    @And("Quitar IngredienteElim y confirmar orden {int}")
    public void quitarIngredienteElimYConfirmarOrdenIndex(int index) throws IOException {
        String ingrediente = jdr.dataReader(data, index, "Ingrediente");
        try {
            op.removeIngredient(ingrediente);
            op.confirmOrder();
        }
        catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            Logger.error("No se pudo quitar el ingrediente: " + ingrediente);
        }
    }

    @And("Editar IngredienteExtra y guardar pedido {int}")
    public void editarIngredienteExtraYGuardarPedidoIndex(int index) throws IOException {
        String ingrediente = jdr.dataReader(data, index, "IngredienteExtra");
        try {
            op.editIngredient(ingrediente);
        }
        catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex) {
            Logger.error("No se pudo editar el ingrediente: " + ingrediente);
        }
    }

    @And("Editar {string} y guardar pedido")
    public void editarYGuardarPedido(String ingrediente) {
        op.editIngredient(ingrediente);
    }

    @When("Realizar pago, anadir codigo promocional, seleccionar tipo de pago y presionar boton  pagar {int}")
    public void realizarPagoAnadirCodigoPromocionalSeleccionarTipoDePagoYPresionarBotonPagarIndex(int index) throws IOException {
        String codigo = jdr.dataReader(data, index, "Codigo");
        String tipoPago = jdr.dataReader(data, index, "TipoPago");
        pp.paymentData(tipoPago, codigo);
    }
    @Then("Dentro de pasarela de pago del pais ingresar datos de tarjeta de credito con numero de tarjeta, fecha de vencimiento, codigo de seguridad y nombre tarjeta {int}")
    public void dentroDePasarelaDePagoDelPaisIngresarDatosDeTarjetaDeCreditoConNumeroDeTarjetaFechaDeVencimientoCodigoDeSeguridadYNombreTarjetaIndex(int index) throws IOException {
        String nTarjeta = jdr.dataReader(data, index, "NumeroTarjeta");
        String pais = jdr.dataReader(data, index, "Pais");
        String Fvencimiento = jdr.dataReader(data, index, "FechaVencimiento");
        String cvv = jdr.dataReader(data, index, "CodigoSeguridad");
        String nombreTarjeta = jdr.dataReader(data, index, "NombreTarjeta");
        pais = Normalizer.normalize(pais, Normalizer.Form.NFD);
        pais = pais.replaceAll("[^\\p{ASCII}]", "");
        pp.pay( pais, nTarjeta, Fvencimiento, cvv, nombreTarjeta);
    }

    @Then("Dentro de pasarela de pago de delivery del pais ingresar datos de tarjeta de credito con numero de tarjeta, fecha de vencimiento, codigo de seguridad y nombre tarjeta {int}")
    public void dentroDePasarelaDePagoDeDeliveryDelPaisIngresarDatosDeTarjetaDeCreditoConNumeroDeTarjetaFechaDeVencimientoCodigoDeSeguridadYNombreTarjetaIndex(int index) throws IOException, InterruptedException {
        String nTarjeta = jdr.dataReader(data, index, "NumeroTarjeta");
        String pais = jdr.dataReader(data, index, "Pais");
        String Fvencimiento = jdr.dataReader(data, index, "FechaVencimiento");
        String cvv = jdr.dataReader(data, index, "CodigoSeguridad");
        String nombreTarjeta = jdr.dataReader(data, index, "NombreTarjeta");
        pais = Normalizer.normalize(pais, Normalizer.Form.NFD);
        pais = pais.replaceAll("[^\\p{ASCII}]", "");
        pp.payDelivery(pais, nTarjeta, Fvencimiento, cvv, nombreTarjeta);
    }

    @When("Dentro del mercado de Pais acceder a pedidos e intentar hacer login con User y Pass no registrada {int}")
    public void dentroDelMercadoDePaisAccederAPedidosEIntentarHacerLoginConUserYPassNoRegistradaIndex(int index) throws IOException, InterruptedException {
        String user = jdr.dataReader(data, index, "User");
        String pass = jdr.dataReader(data, index, "Pass");
        String pais = jdr.dataReader(data, index, "Pais");
        mp.comprobarMensajes();
        mp.comprobarBadLogin();
        mp.login2(user, pass, pais);
        /*if(mp.comprobarUsuarioNoRegistrado(user, pass)){
            Logger.pass("Usuario no registrado se puede hacer registro");
        }
        else{
            System.out.println("el usuario ya se encuentra registrado");
            mp.eliminarCuenta(user, pass);
        }*/
    }

    @Then("Realizar registro con User y Pass no registrada {int}")
    public void realizarRegistroConUserYPassNoRegistradaIndex(int index) throws IOException {
        String user = jdr.dataReader(data, index, "User");
        String pass = jdr.dataReader(data, index, "Pass");
        String pais = jdr.dataReader(data, index, "Pais");
        pais = Normalizer.normalize(pais, Normalizer.Form.NFD);
        pais = pais.replaceAll("[^\\p{ASCII}]", "");
        mp.registrarUsuario(user, pass, pais);
        wp.validarRegistro(user, pass);
    }

    @When("Reintar login con User y Pass ya registrada {int}")
    public void reintarLoginConUserYPassYaRegistradaIndex(int index) throws IOException, InterruptedException {
        String pais = jdr.dataReader(data, index, "Pais");
        String user = jdr.dataReader(data, index, "User");
        String pass = jdr.dataReader(data, index, "Pass");
        pais = Normalizer.normalize(pais, Normalizer.Form.NFD);
        pais = pais.replaceAll("[^\\p{ASCII}]", "");
        System.out.println(pais);
        mp.comprobarMensajes();
        mp.login2(user, pass, pais);
        mp.configurarPerfil(pais);
        mp.comprobarPolicy();
    }

    @And("Elimino cuenta registrada con User y Pass {int}")
    public void eliminoCuentaRegistradaConUserYPassIndex(int index) throws IOException, InterruptedException {
        String user = jdr.dataReader(data, index, "User");
        String pass = jdr.dataReader(data, index, "Pass");
        //mp.eliminarCuenta(user, pass);
    }
}

