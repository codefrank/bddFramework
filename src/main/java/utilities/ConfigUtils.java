package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigUtils {

    static Properties config;
    private static Logger log = Logger.getLogger(ConfigUtils.class.getName());

    public static void loadConfig() {
        try {
            FileInputStream fileInput = new FileInputStream(new File("src/test/resources/config.properties"));
            config = new Properties();
            config.load(fileInput);
            fileInput.close();
        } catch (IOException ex) {
            log.info("Exception reading config: " + ex);
        }
    }


    public static String getConfigValue(String key) {
        return config.getProperty(key);
    }

}
