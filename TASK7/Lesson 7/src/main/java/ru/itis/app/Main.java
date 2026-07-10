package ru.itis.app;

import ru.itis.util.PropertiesReader;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        PropertiesReader propertiesReader = new PropertiesReader("application.properties");

        Properties properties = propertiesReader.loadProperties();

        System.out.println(properties.getProperty("db.url"));
    }
}
