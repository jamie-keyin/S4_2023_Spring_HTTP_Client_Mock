package com.keyin.http.cli;

import com.keyin.domain.Airport;
import com.keyin.http.client.RESTClient;

import java.io.IOException;
import java.util.List;

public class HTTPRestCLIApplication {

    private final String url;
    private RESTClient restClient;

    public HTTPRestCLIApplication(String url) {
        this.url = url;
    }

    public String generateAirportReport() throws IOException, InterruptedException {
        // Pass the URL when creating the RESTClient instance
        List<Airport> airports = getRestClient().getAllAirports(url);

        StringBuffer report = new StringBuffer();

        for (Airport airport : airports) {
            report.append(airport.getName());
            report.append(" - ");
            report.append(airport.getCode());

            if (airports.indexOf(airport) != (airports.size() - 1)) {
                report.append(",");
            }
        }

        System.out.println(report.toString());

        return report.toString();
    }

    public RESTClient getRestClient() {
        if (restClient == null) {
            restClient = new RESTClient();
        }

        return restClient;
    }

    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // Assuming the URL is provided as an argument when running the application
        String url = args.length > 0 ? args[0] : "http://localhost:8080/airports";
        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication(url);

        cliApp.setRestClient(new RESTClient());

        cliApp.generateAirportReport();
    }
}
