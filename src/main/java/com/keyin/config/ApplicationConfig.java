//This new entity was created to handle using the URI as a property variable

package com.keyin.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {
    private String airportServiceUri;

    public ApplicationConfig() {
        loadProperties();
    }

    private void loadProperties() {
        Properties prop = new Properties();
        try (InputStream input = ApplicationConfig.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            prop.load(input); //Set up stream to parse application.properties text
            this.airportServiceUri = prop.getProperty("airport.service.uri"); // Retrieves URI as defined in application.properties
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getAirportServiceUri() {
        return airportServiceUri;
    }
}