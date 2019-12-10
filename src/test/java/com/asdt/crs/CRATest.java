package com.asdt.crs;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class CRATest {
    /**
     * Testing two rentals.
     */
    @Test
    public void testApp() {
        CarRentalAgency cra = new CarRentalAgency();

        assertEquals("cust1:Smart 1", cra.rentVehicle("small", "cust1"));
        assertNull(cra.rentVehicle("small", "cust2"));
    }
}
