package dataProvider;


import com.google.gson.*;
import utils.Logger;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class jsonDataReader {

    public String dataReader(String data, int index, String key) throws IOException {
        String fileName = "src/test/resources/data/" + data;
        Path path = Paths.get(fileName);
        try (Reader reader = Files.newBufferedReader(path,
                StandardCharsets.UTF_8)) {
            JsonElement tree = JsonParser.parseReader(reader);
            JsonArray array = tree.getAsJsonArray();
            if (array.get(index).getAsJsonObject().get(key).getAsString() != null) {
                return array.get(index).getAsJsonObject().get(key).getAsString();
            } else {
                return null;
            }
        }
        catch (java.lang.IllegalStateException | java.lang.NullPointerException e) {
            Logger.error("ocurrio un error al leer el archivo: " + fileName);
            return null;
        }
    }

}
