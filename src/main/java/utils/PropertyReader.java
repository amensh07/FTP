package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static final String DEFAULT_PROPERTIES_FILE = "src\\main\\resources\\config.properties";
    private static Properties properties;

    private static Properties getProperties() throws IOException {
        if (properties == null) {
            properties = new Properties();
        }
        FileInputStream fileInputStream = new FileInputStream(DEFAULT_PROPERTIES_FILE);
        properties.load(fileInputStream);

        return properties;
    }

    public static String getServerUrl() throws IOException {
        return getProperties().getProperty("server_URL");
    }

    public static long getSpeedDownload() throws IOException {
        return Long.parseLong(getProperties().getProperty("speed_download"));
    }

    public static long getSpeedUpload() throws IOException {
        return Long.parseLong(getProperties().getProperty("speed_upload"));
    }
}
