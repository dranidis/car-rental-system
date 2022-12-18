package com.asdt.crs;

import static org.junit.Assert.assertEquals;

import com.asdt.crs.adapters.rentvehicle.RentVehicleController;
import com.asdt.crs.adapters.rentvehicle.RentVehiclePresenter;
import com.asdt.crs.entities.Category;
import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Vehicle;
import com.asdt.crs.usecases.rentvehicle.RentVehicleRequest;
import com.asdt.crs.usecases.rentvehicle.RentVehicleUseCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ConsoleAppRentVehicleTest {
    private InMemoryPersistence mem;
    private RentVehicleController rentVehicleController;

    @Before
    public void setup() {
        mem = new InMemoryPersistence();
        rentVehicleController = new RentVehicleController(
            new RentVehicleUseCase(mem),
            new RentVehiclePresenter(),
            new RentVehicleConsoleView());

    }

    @Test
    public void testRentVehicleMSS() {
        Customer c1 = new Customer("dim", "Dimitris");
        Vehicle v1 = new Vehicle("Mercedes", new Category("small"));
        mem.addCustomer(c1);
        mem.addVehicle(v1);

        RentVehicleRequest request = new RentVehicleRequest();

        request.categoryId = "small";
        request.customerId = "dim";
        String viewString = rentVehicleController.handle(request);
        assertEquals("Car rented with rental id: dim:Mercedes", viewString);
    }

    @Test
    public void testRentVehicleNotAvailableCategory() {
        Customer c1 = new Customer("dim", "Dimitris");
        mem.addCustomer(c1);

        RentVehicleRequest request = new RentVehicleRequest();

        request.categoryId = "lux";
        request.customerId = "dim";
        String viewString = rentVehicleController.handle(request);
        assertEquals("Category not available", viewString);
    }

    @Test
    public void testRentVehicleCustomerNotFound() {
        Vehicle v1 = new Vehicle("Mercedes", new Category("small"));
        mem.addVehicle(v1);

        RentVehicleRequest request = new RentVehicleRequest();

        request.categoryId = "lux";
        request.customerId = "dim";
        String viewString = rentVehicleController.handle(request);
        assertEquals("Customer not found", viewString);
    }

}
