package utils;

import java.util.Properties;
import java.io.FileInputStream;

public class ConfigReader {

	public static String getProperty(String key) {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/test/resources/config.properties"));
            return prop.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
