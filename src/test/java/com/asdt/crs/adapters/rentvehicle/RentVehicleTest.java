package com.asdt.crs.adapters.rentvehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import com.asdt.crs.InMemoryPersistence;
import com.asdt.crs.entities.Category;
import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Rental;
import com.asdt.crs.entities.Vehicle;
import com.asdt.crs.usecases.rentvehicle.RentVehicleUseCase;
import com.asdt.crs.usecases.rentvehicle.RentVehicleRequest;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class RentVehicleTest {
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

        RentVehicleRequest request = new RentVehicleRequest();
        request.categoryId = "small";
        request.customerId = "dim";

        RentVehicleUseCase interactor = new RentVehicleUseCase(mem);
        interactor.rentVehicle(request, presenter);
        RentVehicleViewModel viewModel = presenter.getViewModel();
        assertTrue(viewModel.customerFound);
        assertTrue(viewModel.rented);
        String rentalId = customerId + ":" + vehicleId;
        assertEquals(rentalId, viewModel.rentalId);

        Optional<Rental> rentalMem = mem.getRental(rentalId);
        assertTrue(rentalMem.isPresent());
        rentalMem.ifPresent(rental -> {
            assertTrue(rental.getVehicle().isRented());
        });
    }

    @Test
    public void testRentVehicleNotAvailableCategoryBecauseNoVehicle() {
        Customer c1 = new Customer("dim", "Dimitris");
        mem.addCustomer(c1);

        RentVehicleRequest request = new RentVehicleRequest();

        request.categoryId = "lux";
        request.customerId = "dim";

        RentVehicleUseCase interactor = new RentVehicleUseCase(mem);
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

        RentVehicleRequest request = new RentVehicleRequest();

        request.categoryId = "small";
        request.customerId = "dim";

        RentVehicleUseCase interactor = new RentVehicleUseCase(mem);
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

        RentVehicleRequest request = new RentVehicleRequest();

        request.categoryId = "lux";
        request.customerId = "dim";

        RentVehicleUseCase interactor = new RentVehicleUseCase(mem);
        interactor.rentVehicle(request, presenter);
        RentVehicleViewModel viewModel = presenter.getViewModel();
        assertFalse(viewModel.customerFound);
        assertFalse(viewModel.rented);
        assertNull(viewModel.rentalId);
    }

}
