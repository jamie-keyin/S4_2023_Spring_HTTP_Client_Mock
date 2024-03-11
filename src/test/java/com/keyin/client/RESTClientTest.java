package com.keyin.client;

import com.keyin.domain.Airport;
import com.keyin.http.client.RESTClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RESTClientTest {

    // Provide a base URL for the RESTClient constructor
    private final String baseUrl = "http://localhost:8080/airports";

    @Test
    public void testBuildAirportListFromResponse() throws Exception {
        String jsonResponse = "[\n{\n\"id\": 1,\n\"name\": \"St. John's\",\n\"code\": \"YYT\"\n},\n" +
                "{\n\"id\": 2,\n\"name\": \"Deer Lake\",\n\"code\": \"YDF\"\n}\n]";

        // Pass the base URL when creating the instance of RESTClient
        RESTClient restClientUnderTest = new RESTClient(baseUrl);

        List<Airport> airportList = restClientUnderTest.buildAirportListFromResponse(jsonResponse);

        Assertions.assertTrue(airportList.contains(new Airport("YYT")));
        Assertions.assertTrue(airportList.contains(new Airport("YDF")));
    }

    @Test
    public void testBuildAirportListFromEmptyResponse() throws Exception {
        String emptyJsonResponse = "[]";

        // Pass the base URL when creating the instance of RESTClient
        RESTClient restClientUnderTest = new RESTClient(baseUrl);

        List<Airport> airportList = restClientUnderTest.buildAirportListFromResponse(emptyJsonResponse);

        Assertions.assertTrue(airportList.isEmpty());
    }

    @Test
    public void testBuildAirportListFromInvalidResponse() {
        String invalidJsonResponse = "{\n\"id\": 1,\n\"name\": \"St. John's\",\n\"code\": \"YYT\"\n}";

        // Pass the base URL when creating the instance of RESTClient
        RESTClient restClientUnderTest = new RESTClient(baseUrl);

        Assertions.assertThrows(Exception.class, () -> {
            restClientUnderTest.buildAirportListFromResponse(invalidJsonResponse);
        });
    }
}
