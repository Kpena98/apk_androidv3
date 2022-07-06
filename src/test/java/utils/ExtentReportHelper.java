package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import dataProvider.jsonDataReader;
import driver.DriverManager;
import exceptions.ElementoNoVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentReportHelper {

    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports report;

    private static final Helpers hp = new Helpers();

    private static ThreadLocal<ExtentTest> extent_test = new ThreadLocal<ExtentTest>();
    private static ThreadLocal<Integer> step = new ThreadLocal<Integer>();


    public static void startReport( String context, String host, String ambiente) throws IOException {
        System.out.println("Number of threads " + Thread.activeCount());
        String nameReport = "flujoEndToEnd";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYYHH:mm:ss");
        Date fecha = new Date();
        String date = sdf.format(fecha);
        date = date.replace("/", "");
        date = date.replace(":", "");

        if (nameReport!=null && nameReport.contains(" ")) {
            nameReport = nameReport.replace(" ", "");
        }
        htmlReporter = new ExtentHtmlReporter("src/test/resources/output/report/"
                + nameReport + ".html");

        htmlReporter.config().setDocumentTitle("Ejecucion de pruebas");
        htmlReporter.config().setReportName(nameReport);
        htmlReporter.config().setTheme(Theme.DARK);

        report = new ExtentReports();
        report.attachReporter(htmlReporter);
        report.setSystemInfo("Name", nameReport);
        report.setSystemInfo("Host", host);
        report.setSystemInfo("Environment", ambiente);
        report.setSystemInfo("Ejecutor", System.getProperty("user.name"));
        report.setSystemInfo("Contexto", context);

        step.set(0);
    }

    private static String getScreenShot(WebDriver driver) throws IOException {
        String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        String s = "data:image/png;base64," + scnShot;
        return s;
    }

    public static void creteNameTest(String nameTest) throws IOException, ElementoNoVisibleException {
        String newNameTest = hp.changeNameTest(nameTest);
        extent_test.set(report.createTest(newNameTest));
    }

    public static void stepPass(String desc) throws IOException, URISyntaxException {
        step.set(step.get()+1);
        String url = ExtentReportHelper.getScreenShot(DriverManager.getDriver());
        extent_test.get().createNode("Paso " + step.get() + " Aprobado: ")
                .pass("Step " + step.get() + " :" + desc,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(url).build());
    }

    public static void stepFail(String desc) throws IOException, URISyntaxException {
        step.set(step.get()+1);
        String url = ExtentReportHelper.getScreenShot(DriverManager.getDriver());
        extent_test.get().createNode("Paso " + step.get() + " Reprobado: ")
                .fail("Step " + step.get() + " :" + desc,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(url).build());
    }

    public static void stepWarning(String desc) throws IOException {
        step.set(step.get()+1);
        String url = ExtentReportHelper.getScreenShot(DriverManager.getDriver());
        extent_test.get().createNode("Paso " + step.get() + " Alerta: ")
                .warning("Step " + step.get() + " :" + desc,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(url).build());
    }

    public static void stepInfo(String desc) throws IOException {
        step.set(step.get()+1);
        String url = ExtentReportHelper.getScreenShot(DriverManager.getDriver());
        extent_test.get().createNode("Paso " + step.get() + " Info: ")
                .info("Step " + step.get() + " :" + desc,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(url).build());
    }

    public static void endReport() {
        report.flush();
    }
}
