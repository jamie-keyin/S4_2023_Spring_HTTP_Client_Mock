package com.keyin.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.domain.Airport;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class RESTClient {
    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<Airport>();

        HttpClient client = HttpClient.newHttpClient();
        // I would probably want to configure this as an env variable instead of again hardcoding, but this is at least more dynamic
        HttpRequest request = buildHttpRequest("http://localhost:8080/airports");

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

    // Method to build Http request uri. Takes argument URI as string and is then embedded into the request builder.
    public HttpRequest buildHttpRequest(String uri){
        try{
            return HttpRequest.newBuilder().uri(URI.create(uri)).build();
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating URI from given string - " + uri);
            throw new RuntimeException();
        }
    };
}
