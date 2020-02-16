package com.asdt.crs.adapters.rentvehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.asdt.crs.InMemoryPersistence;
import com.asdt.crs.entities.Category;
import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Vehicle;
import com.asdt.crs.interactors.rentvehicle.RentVehicleInteractor;
import com.asdt.crs.interactors.rentvehicle.RentVehicleRequestModel;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ConsoleAppTest {
    private InMemoryPersistence mem;
    private RentVehiclePresenter presenter;

    @Before
    public void setup() {
        mem = new InMemoryPersistence();
        presenter =  new RentVehiclePresenter();
    }

    @Test
    public void testRentVehicleMSS() {
        String customerId = "dim";
        Customer c1 = new Customer(customerId, "Dimitris");
        String vehicleId = "Mercedes";
        Vehicle v1 = new Vehicle(vehicleId, new Category("small"));
        mem.addCustomer(c1);
        mem.addVehicle(v1);

        RentVehicleRequestModel request = new RentVehicleRequestModel();
        request.categoryId = "small";
        request.customerId = "dim";

        RentVehicleInteractor interactor = new RentVehicleInteractor(mem);
        interactor.rentVehicle(request, presenter);
        RentVehicleViewModel viewModel = presenter.getViewModel();
        assertTrue(viewModel.customerFound);
        assertTrue(viewModel.rented);
        assertEquals(customerId + ":" + vehicleId, viewModel.rentalId);
    }

    @Test
    public void testRentVehicleNotAvailableCategoryBecauseNoVehicle() {
        Customer c1 = new Customer("dim", "Dimitris");
        mem.addCustomer(c1);

        RentVehicleRequestModel request = new RentVehicleRequestModel();

        request.categoryId = "lux";
        request.customerId = "dim";

        RentVehicleInteractor interactor = new RentVehicleInteractor(mem);
        interactor.rentVehicle(request, presenter);
        RentVehicleViewModel viewModel = presenter.getViewModel();
        assertTrue(viewModel.customerFound);
        assertFalse(viewModel.rented);
        assertNull(viewModel.rentalId);
    }

    @Test
    public void testRentVehicleNotAvailableCategoryBecauseVehicleIsRented() {
        Customer c1 = new Customer("dim", "Dimitris");
        mem.addCustomer(c1);
        String vehicleId = "Mercedes";
        Vehicle v1 = new Vehicle(vehicleId, new Category("small"));
        v1.setRented(true);
        mem.addVehicle(v1);

        RentVehicleRequestModel request = new RentVehicleRequestModel();

        request.categoryId = "small";
        request.customerId = "dim";

        RentVehicleInteractor interactor = new RentVehicleInteractor(mem);
        interactor.rentVehicle(request, presenter);
        RentVehicleViewModel viewModel = presenter.getViewModel();
        assertTrue(viewModel.customerFound);
        assertFalse(viewModel.rented);
        assertNull(viewModel.rentalId);
    }

    @Test
    public void testRentVehicleCustomerNotFound() {
        Vehicle v1 = new Vehicle("Mercedes", new Category("small"));
        mem.addVehicle(v1);

        RentVehicleRequestModel request = new RentVehicleRequestModel();

        request.categoryId = "lux";
        request.customerId = "dim";

        RentVehicleInteractor interactor = new RentVehicleInteractor(mem);
        interactor.rentVehicle(request, presenter);
        RentVehicleViewModel viewModel = presenter.getViewModel();
        assertFalse(viewModel.customerFound);
        assertFalse(viewModel.rented);
        assertNull(viewModel.rentalId);
    }

}
