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
import java.util.List;

public class RESTClient {
    private final URI BASE_URI;
    private final HttpClient client;
    private final ObjectMapper mapper;

    public RESTClient() {
        this("http://localhost:8080/airports");
    }

    public RESTClient(String uri) {
        this.BASE_URI = URI.create(uri);
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();

        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<Airport> getAllAirports() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(this.BASE_URI).build();
        HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(
                response.statusCode() == 200
                        ? "***** " + response.body()
                        : "Error Status Code: " + response.statusCode()
        );

        return this.buildAirportListFromResponse(response.body());
    }

    public List<Airport> buildAirportListFromResponse(String response) throws JsonProcessingException {
        return this.mapper.readValue(response, new TypeReference<>(){});
    }
}
