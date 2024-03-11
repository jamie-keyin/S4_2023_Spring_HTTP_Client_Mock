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


    public List<Airport> getAllAirports(String url) {
        List<Airport> airports = new ArrayList<Airport>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

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
