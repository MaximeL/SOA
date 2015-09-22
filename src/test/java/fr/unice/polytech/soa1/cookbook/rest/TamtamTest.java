package test.java.fr.unice.polytech.soa1.cookbook.rest;

import fr.unice.polytech.soa1.cookbook.rest.Tamtam;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by Maxime on 9/22/2015.
 */
public class TamtamTest {

    Tamtam tamtam;

    @org.junit.Before
    public void setUp() throws Exception {
        tamtam = new Tamtam(0);
    }

    /*
    @org.junit.After
    public void tearDown() throws Exception {

    }*/

    @org.junit.Test
    public void testGetId() throws Exception {
        int idExpected = 0;
        assertEquals(tamtam.getId(), idExpected);
    }

    @org.junit.Test
    public void testSetId() throws Exception {
        int idExpected = 1;
        tamtam.setId(idExpected);
        assertEquals(tamtam.getId(), idExpected);
    }
    //TODO le reste des tests
/*
    @org.junit.Test
    public void testGetName() throws Exception {

    }

    @org.junit.Test
    public void testSetName() throws Exception {

    }

    @org.junit.Test
    public void testGetDescription() throws Exception {

    }

    @org.junit.Test
    public void testSetDescription() throws Exception {

    }

    @org.junit.Test
    public void testGetImage() throws Exception {

    }

    @org.junit.Test
    public void testSetImage() throws Exception {

    }

    @org.junit.Test
    public void testGetBrand() throws Exception {

    }

    @org.junit.Test
    public void testSetBrand() throws Exception {

    }

    @org.junit.Test
    public void testGetWood() throws Exception {

    }

    @org.junit.Test
    public void testSetWood() throws Exception {

    }

    @org.junit.Test
    public void testGetSkin() throws Exception {

    }

    @org.junit.Test
    public void testSetSkin() throws Exception {

    }

    @org.junit.Test
    public void testGetPrice() throws Exception {

    }

    @org.junit.Test
    public void testSetPrice() throws Exception {

    }

    @org.junit.Test
    public void testAddDecoration() throws Exception {

    }

    @org.junit.Test
    public void testGetDecorations() throws Exception {

    }

    @org.junit.Test
    public void testAddShipment() throws Exception {

    }

    @org.junit.Test
    public void testGetShipments() throws Exception {

    }

    @org.junit.Test
    public void testMinToString() throws Exception {

    }

    @org.junit.Test
    public void testToString() throws Exception {

    }*/
}