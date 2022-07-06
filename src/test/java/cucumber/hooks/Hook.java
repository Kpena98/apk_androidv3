package cucumber.hooks;

import dataProvider.jsonDataReader;
import driver.DriverManager;
import exceptions.ElementoNoVisibleException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import utils.ExtentReportHelper;
import utils.Helpers;
import utils.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;

public class Hook {

    private String nombreEscenario;

    private final Helpers hp = new Helpers();


    @Before
    public void setUp(Scenario scenario) throws IOException, ElementoNoVisibleException {
        nombreEscenario = hp.changeNameTest(scenario.getName());
        Logger.iniciarLog(scenario.getName(), "Pruebas Completas en: " + nombreEscenario);
    }

    @Given("Abrir Aplicacion en BrowserStack")
    public void abrir_aplicacion_en_browser_stack() throws MalformedURLException {
        DriverManager dm = new DriverManager();
        dm.iniciarWebDriver(nombreEscenario);
        Logger.info("Se inicia la instancia movil");
    }

    @After
    public void tearDown() {
        DriverManager.cerrarDriver();
        ExtentReportHelper.endReport();
    }

}
