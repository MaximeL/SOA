package fr.unice.polytech.soa1.cookbook.rest;

import fr.unice.polytech.soa1.cookbook.rest.TamtamService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.net.URI;

/**
 * Created by Maxime on 9/22/2015.
 */

// Test inspiré du code de Ashot Karakhanyan sur StackOverflow à l'adresse http://stackoverflow.com/questions/21974213/an-exception-in-grizzly-unit-test-with-jersey
public class TamtamServiceTest {

    //Build the base URL
    public static final URI BASE_URI = UriBuilder.fromUri("http://{host}/{path}")
            .resolveTemplate("host", "localhost:8181")
            .resolveTemplate("path", "cxf/demo/").build();
    public ResponseBuilder responseBuilder;
    public URI TAMTAM_SERVICE_URI;

    //
    @Before
    public void setUp() throws Exception {
        TAMTAM_SERVICE_URI = new URI(BASE_URI.toString()+"tamtams");
        responseBuilder = Response.created(TAMTAM_SERVICE_URI);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test to see if we get all the tamtams
     */
    @Test
    public void testGetAvailableTamtams() {
        Response response = responseBuilder.build();
        assertTrue("response status should be OK", response.getStatus() > 0);
    }
}