package utils;

import driver.BrowserStackHelper;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Logger {

    private static org.slf4j.Logger log;

    public static void iniciarLog(String nombreTest, String ejecucion) throws IOException {
        ExtentReportHelper.startReport( ejecucion, "Mc Donalds Ecommerce", "QA");
        ExtentReportHelper.creteNameTest(nombreTest);
        log = LoggerFactory.getLogger(utils.Logger.class);
        log.info("Se inicia reporte para test[" + nombreTest + "], ejecución[" + ejecucion + "]");
    }

    public static void info(String mensaje) {
        try {
            ExtentReportHelper.stepPass(mensaje);
            log.info(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al registrar mensaje INFO");
        }
    }

    public static void pass(String mensaje) {
        try {
            BrowserStackHelper.passed(mensaje);
            ExtentReportHelper.stepPass(mensaje);
            log.info(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al registrar mensaje PASS");
        }
    }

    public static void error(String mensaje) {
        try {
            BrowserStackHelper.failed(mensaje);
            ExtentReportHelper.stepFail(mensaje);
            log.error(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al registrar mensaje ERROR");
        }
    }


}
