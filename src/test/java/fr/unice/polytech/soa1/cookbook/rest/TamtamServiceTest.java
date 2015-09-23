package fr.unice.polytech.soa1.cookbook.rest;

import fr.unice.polytech.soa1.cookbook.rest.TamtamService;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

/**
 * Created by Maxime on 9/22/2015.
 */

// Test inspiré du code de Ashot Karakhanyan sur StackOverflow à l'adresse http://stackoverflow.com/questions/21974213/an-exception-in-grizzly-unit-test-with-jersey
public class TamtamServiceTest {

    public TamtamService tamtamService;
    public Response response;

    //
    @Before
    public void setUp() throws Exception {
        tamtamService = new TamtamService();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test to see if we get all the tamtams
     */
    @Test
    public void testGetAvailableTamtams() {
        //response = tamtamService.getAvailableTamtams();
        //assertTrue("Response should be OK", (response.getStatus() == Response.Status.OK.getStatusCode()));
    }
}