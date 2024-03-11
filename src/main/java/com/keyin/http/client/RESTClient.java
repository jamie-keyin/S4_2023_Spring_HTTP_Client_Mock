//Written By Jamie Cornick
//Refactored to allow URL to be passed as variable
//Refactored By Tanner Jones
//Date: March 8th 2024

package com.keyin.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.domain.Airport;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class RESTClient {
    private String baseUrl;

    /**
     * Constructs a new REST client with the specified base URL.
     * If no base URL is specified, "http://localhost:8080" is used.
     *
     * @param baseUrl The base URL for the API endpoint
     */
    public RESTClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public RESTClient() {
        // Base URL for the API endpoint
        this("http://localhost:8080");
    }

    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<Airport>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseUrl + "/airports")).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200) {
                System.out.println("***** " + response.body());
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            airports = buildAirportListFromResponse(response.body());


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return airports;
    }

    public List<Airport> buildAirportListFromResponse(String response) throws JsonProcessingException {
        List<Airport> airports = new ArrayList<Airport>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        airports = mapper.readValue(response, new TypeReference<List<Airport>>(){});

        return airports;
    }
}
