// Jamie Homework - 3/6/2024
// Brandon Lewis
//
// For this refactor, I decided to go with dependency injection.
// Dependency injection allows the rest client used at runtime to be easily swapped based on a given configuration.
// This refactor involves passing the restclient as a parameter to a constructor.

package com.keyin.http.cli;

import com.keyin.config.ApplicationConfig;
import com.keyin.domain.Airport;
import com.keyin.http.client.RESTClient;

import java.util.List;

public class HTTPRestCLIApplication {

    private RESTClient restClient;

    // Constructor that accepts a RESTClient
    public HTTPRestCLIApplication(RESTClient restClient) {
        this.restClient = restClient;
    }

    public String generateAirportReport() {
        List<Airport> airports = restClient.getAllAirports(); //Use Constructor


        StringBuffer report = new StringBuffer();

    // Additional refactor here to have cleaner appearance
    for (Airport airport : airports) {
        report.append(airport.getName())
              .append(" - ")
              .append(airport.getCode())
              .append(airports.indexOf(airport) != (airports.size() - 1) ? "," : "");
        }

        System.out.println(report); // Removed small redundancy

        return report.toString();
    }

    // Removed the getRestClient and setRestClient methods
    // RESTClient is now passed via the constructor

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        
        // URI is now defined in the application.properties file
        // The main method now reads the the configuration and sets the URI

        ApplicationConfig config = new ApplicationConfig(); // Define current configuration
        String airportServiceUri = config.getAirportServiceUri(); // Grab URI
        RESTClient restClient = new RESTClient(airportServiceUri); // Set URI


        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication(restClient);

        cliApp.generateAirportReport();
    }
}
