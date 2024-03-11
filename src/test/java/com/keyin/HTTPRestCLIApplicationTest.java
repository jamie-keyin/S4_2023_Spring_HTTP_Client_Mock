package com.keyin;

import com.keyin.domain.Airport;
import com.keyin.http.cli.HTTPRestCLIApplication;
import com.keyin.http.client.RESTClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class HTTPRestCLIApplicationTest {
    @Mock
    private RESTClient mockRESTClient;

    @Test
    public void testGenerateAirportReport() throws IOException, InterruptedException {
        // Provide the URL when creating an instance of HTTPRestCLIApplication
        String url = "http://localhost:8080/airports";
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication(url);

        Airport stJohnsAirport = new Airport();
        stJohnsAirport.setCode("YYT");
        stJohnsAirport.setName("St. John's Airport");
        stJohnsAirport.setId(1);

        List<Airport> airportList = new ArrayList<>();
        airportList.add(stJohnsAirport);

        // Provide the URL when setting up the mock behavior for getAllAirports() method
        Mockito.when(mockRESTClient.getAllAirports(url)).thenReturn(airportList);

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

        // Call the method with the URL parameter
        String report = httpRestCLIApplicationUnderTest.generateAirportReport();

        // Assert that the report contains "YYT"
        Assertions.assertTrue(report.contains("YYT"));
    }
}
