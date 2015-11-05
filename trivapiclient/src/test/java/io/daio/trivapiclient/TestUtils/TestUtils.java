package io.daio.trivapiclient.TestUtils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class TestUtils {

    private static JSONParser parser = new JSONParser();

    private static final String BASE_PATH = System.getProperty("user.dir") + "/src/test/java/io/daio/trivapiclient/fixtures/";

    public static String loadFixture(String filename) {

        try {
            JSONObject json = (JSONObject) parser.parse(new FileReader(BASE_PATH + filename));
            return json.toJSONString();

        } catch (IOException | ParseException ignored) {
            System.out.println(ignored.getLocalizedMessage());
        }

        return "";

    };

}
