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

import java.util.ArrayList;
import java.util.List;



@ExtendWith(MockitoExtension.class)
public class HTTPRestCLIApplicationTest {
    @Mock
    private RESTClient mockRESTClient;


    @Test
    public void testGenerateAirportReport() {
        // Major refactors here to use less clutter

        // Consolidated Object Initialization
        // Uses a single line instead of setting each property with a method call
        List<Airport> airportList = new ArrayList<>();
        airportList.add(new Airport(1, "St. John's Airport", "YYT"));

        //Tell Mock Rest Client to return airportList when method is called
        Mockito.when(mockRESTClient.getAllAirports()).thenReturn(airportList);

        // Inject the mock RESTClient into the application
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication(mockRESTClient);

        //Test Assertion remains unchanged
        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateAirportReport().contains("YYT"));
    }
}
