package org.example.novysh.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("File app.properties doesn't find src/main/resources/");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error under uploading file app.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl() {
        return getProperty("base.url.innowise");
    }

    public static int getTimeout(){ return Integer.parseInt(getProperty("timeout"));}
}
