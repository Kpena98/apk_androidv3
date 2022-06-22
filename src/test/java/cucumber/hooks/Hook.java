package cucumber.hooks;

import driver.DriverManager;
import enums.DriverType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.ExtentReportHelper;
import utils.Logger;

import java.io.IOException;
import java.net.MalformedURLException;

public class Hook {

    private String nombreEscenario;

    @Before
    public void setUp(Scenario scenario) throws IOException {
        nombreEscenario = scenario.getName();
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
