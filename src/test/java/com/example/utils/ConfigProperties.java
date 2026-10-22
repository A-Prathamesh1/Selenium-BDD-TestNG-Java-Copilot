package com.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigProperties {
    private static final Properties PROPERTIES = loadProperties();

    private ConfigProperties() {
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = ConfigProperties.class.getResourceAsStream("/config.properties")) {
            if (input == null) {
                throw new IllegalStateException("config.properties was not found on the classpath");
            }
            properties.load(input);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load config.properties", exception);
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        PROPERTIES.setProperty(key, value);
    }
}