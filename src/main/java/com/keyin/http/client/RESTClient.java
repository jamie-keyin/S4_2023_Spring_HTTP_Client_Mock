package com.keyin.http.client;

import com.keyin.domain.Airport;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class RESTClient {
    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<Airport>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/airports")).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200) {
                System.out.println("***** " + response.body());
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return airports;
    }
}
