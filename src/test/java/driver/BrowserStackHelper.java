package driver;

import io.appium.java_client.android.AndroidDriver;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class BrowserStackHelper {

    public static final String USERNAME = "davidlorca_qe4DAR";
    public static final String AUTOMATE_KEY = "dG2YTyZQHmL3VMxdFxiZ";

    public static void passed(String desc) throws URISyntaxException, IOException {
        enviarEstado(desc, "passed");
    }

    public static void failed(String desc) throws URISyntaxException, IOException {
        enviarEstado(desc, "failed");
    }

    public static void enviarEstado(String desc, String status) throws URISyntaxException, IOException {
        String sessionsID = DriverManager.getDriver().getSessionId().toString();
        URI uri = new URI("https://"+USERNAME+":"+AUTOMATE_KEY+"@api.browserstack.com/automate/sessions/" + sessionsID + ".json");
        HttpPut putRequest = new HttpPut(uri);

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add((new BasicNameValuePair("status", status)));
        nameValuePairs.add((new BasicNameValuePair("reason", desc)));
        putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        HttpClientBuilder.create().build().execute(putRequest);
    }
}
