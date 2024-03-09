package com.keyin.http.cli;

import com.keyin.domain.Airport;
import com.keyin.http.client.RESTClient;

import java.io.IOException;
import java.util.List;

public class HTTPRestCLIApplication {

    private RESTClient restClient;

    public String generateAirportReport() throws IOException, InterruptedException {
        List<Airport> airports = getRestClient().getAllAirports();

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
        for (String arg : args) {
            System.out.println(arg);
        }

        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();

        cliApp.setRestClient(new RESTClient());

        cliApp.generateAirportReport();
    }
}
