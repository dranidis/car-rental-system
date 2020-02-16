package com.asdt.crs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.asdt.crs.adapters.rentvehicle.RentVehicleController;
import com.asdt.crs.adapters.rentvehicle.RentVehiclePresenter;
import com.asdt.crs.entities.Category;
import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Vehicle;
import com.asdt.crs.interactors.rentvehicle.RentVehicleInteractor;
import com.asdt.crs.interactors.rentvehicle.RentVehicleRequestModel;

/**
 * Unit test for simple App.
 */
public class ConsoleAppTest {
    private InMemoryPersistence mem;
    private RentVehicleController rentVehicleController;

    @Before
    public void setup() {
        mem = new InMemoryPersistence();
        rentVehicleController = new RentVehicleController(
            new RentVehicleInteractor(mem),
            new RentVehiclePresenter(),
            new ConsoleView());

    }

    @Test
    public void testRentVehicleMSS() {
        Customer c1 = new Customer("dim", "Dimitris");
        Vehicle v1 = new Vehicle("Mercedes", new Category("small"));
        mem.addCustomer(c1);
        mem.addVehicle(v1);

        RentVehicleRequestModel request = new RentVehicleRequestModel();

        request.categoryId = "small";
        request.customerId = "dim";
        String viewString = rentVehicleController.handle(request);
        assertEquals("Car rented with rental id: dim:Mercedes", viewString);
    }

    @Test
    public void testRentVehicleNotAvailableCategory() {
        Customer c1 = new Customer("dim", "Dimitris");
        mem.addCustomer(c1);

        RentVehicleRequestModel request = new RentVehicleRequestModel();

        request.categoryId = "lux";
        request.customerId = "dim";
        String viewString = rentVehicleController.handle(request);
        assertEquals("Category not available", viewString);
    }

    @Test
    public void testRentVehicleCustomerNotFound() {
        Vehicle v1 = new Vehicle("Mercedes", new Category("small"));
        mem.addVehicle(v1);

        RentVehicleRequestModel request = new RentVehicleRequestModel();

        request.categoryId = "lux";
        request.customerId = "dim";
        String viewString = rentVehicleController.handle(request);
        assertEquals("Customer not found", viewString);
    }


}
