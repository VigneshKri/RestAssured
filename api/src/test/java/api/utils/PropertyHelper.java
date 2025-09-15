package api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyHelper {
    static Properties properties;
    static FileInputStream fileInputStream;
    private static PropertyHelper instance;
    static String CONFIG_FILE_PATH = "/src/test/resources/config.properties";


    private PropertyHelper() {
        loadProperties();
    }

    public static PropertyHelper getInstance() {
        if (instance == null) {
            synchronized (PropertyHelper.class) {
                if (instance == null) {
                    instance = new PropertyHelper();
                }
            }
        }
        return instance;
    }

    public static void loadProperties() {
        properties = new Properties();
        try {
            String configPath = System.getProperty("user.dir") + CONFIG_FILE_PATH;
            FileInputStream fis = new FileInputStream(configPath);
            properties.load(fis);
            fis.close();
        } catch (Exception e) {
            //Ignore this fucker
            throw new RuntimeException("Config could not be read");
        }
    }

    public static String getReportPath() {
        return getProperty("extent.report.path");
    }

    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }

    public static String getBaseUrl() {
        return getProperty("base-url");
    }


}
