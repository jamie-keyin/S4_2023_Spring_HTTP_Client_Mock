import com.keyin.domain.Airport;
import com.keyin.http.client.RESTClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RESTClientTest {

    @Test
    public void testBuildAirportListFromResponse() throws Exception {
        String jsonResponse = "[\n{\n\"id\": 1,\n\"name\": \"St. John's\",\n\"code\": \"YYT\"\n},\n" +
                "{\n\"id\": 2,\n\"name\": \"Deer Lake\",\n\"code\": \"YDF\"\n}\n]";

        RESTClient restClientUnderTest = new RESTClient();

        List<Airport> airportList = restClientUnderTest.buildAirportListFromResponse(jsonResponse);

        System.out.println("Airport list: " + airportList); // Output the airport list

        Assertions.assertTrue(airportList.contains(new Airport("YYT")));
        Assertions.assertTrue(airportList.contains(new Airport("YDF")));
    }

    @Test
    public void testBuildAirportListFromResponseWithEmptyResponse() throws Exception {
        String jsonResponse = "[]";

        RESTClient restClientUnderTest = new RESTClient();

        List<Airport> airportList = restClientUnderTest.buildAirportListFromResponse(jsonResponse);

        System.out.println("Airport list (empty): " + airportList); // Output the airport list

        Assertions.assertTrue(airportList.isEmpty());
    }

    @Test
    public void testBuildAirportListFromResponseWithInvalidJsonResponse() {
        String jsonResponse = "Invalid JSON Response";

        RESTClient restClientUnderTest = new RESTClient();

        System.out.println("Testing with invalid JSON response...");

        Assertions.assertThrows(Exception.class, () -> {
            restClientUnderTest.buildAirportListFromResponse(jsonResponse);
        });
    }

    @Test
    public void testBuildAirportListFromResponseWithNullResponse() {
        String jsonResponse = null;

        RESTClient restClientUnderTest = new RESTClient();

        System.out.println("Testing with null JSON response...");

        Assertions.assertThrows(Exception.class, () -> {
            restClientUnderTest.buildAirportListFromResponse(jsonResponse);
        });
    }

    @Test
    public void testBuildAirportListFromResponseWithMalformedJsonResponse() {
        String jsonResponse = "[{\"id\": 1, \"name\": \"St. John's\", \"code\": \"YYT\"}, " +
                "{\"id\": 2, \"name\": \"Deer Lake\", \"code\": \"YDF\"}";

        RESTClient restClientUnderTest = new RESTClient();

        System.out.println("Testing with malformed JSON response...");

        Assertions.assertThrows(Exception.class, () -> {
            restClientUnderTest.buildAirportListFromResponse(jsonResponse);
        });
    }
}
