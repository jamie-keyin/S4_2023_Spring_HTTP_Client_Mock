package com.keyin.http.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.keyin.domain.Airport;

public class RESTClient {
    private static final String BASE_URL = "http://localhost:8080";
    private static final String AIRPORTS_ENDPOINT = "/airports";

    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<>();

        try {
            String apiUrl = buildApiUrl(BASE_URL, AIRPORTS_ENDPOINT);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("***** " + response.body());
                airports = buildAirportListFromResponse(response.body());
                // rest of the code
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            handleException(e);
        }

        return airports;
    }

    private String buildApiUrl(String baseUrl, String endpoint) {
        return baseUrl + endpoint;
    }

    private void handleException(Exception e) {
        e.printStackTrace(); // Handle the exception according to your application's needs
    }

    private List<Airport> buildAirportListFromResponse(String responseBody) {
        // Implementation of building Airport list from the response
        // ...
        return new ArrayList<>(); // Placeholder return for demonstration
    }
}

