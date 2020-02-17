package com.asdt.crs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import com.asdt.crs.adapters.returnvehicle.ReturnVehicleController;
import com.asdt.crs.adapters.returnvehicle.ReturnVehiclePresenter;
import com.asdt.crs.entities.Category;
import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Rental;
import com.asdt.crs.entities.Vehicle;
import com.asdt.crs.usecases.returnvehicle.ReturnVehicleUseCase;
import com.asdt.crs.usecases.returnvehicle.ReturnVehicleRequest;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ConsoleAppReturnVehicleTest {
    private InMemoryPersistence mem;
    private ReturnVehicleController returnVehicleController;

    @Before
    public void setup() {
        mem = new InMemoryPersistence();
        returnVehicleController = new ReturnVehicleController(new ReturnVehicleUseCase(mem),
                new ReturnVehiclePresenter(), new ReturnVehicleConsoleView());

    }

    @Test
    public void testReturnVehicleMSS() {
        Customer c1 = new Customer("dim", "Dimitris");
        Vehicle v1 = new Vehicle("Mercedes", new Category("small"));
        Rental r1 = new Rental(c1, v1);
        mem.addCustomer(c1);
        mem.addVehicle(v1);
        mem.addRental(r1);

        ReturnVehicleRequest request = new ReturnVehicleRequest();

        request.rentalId = r1.getId();

        String viewString = returnVehicleController.handle(request);

        Optional<Rental> rentalOptional = mem.getRental(r1.getId());
        assertTrue(rentalOptional.isPresent());
        rentalOptional.ifPresent(r -> {
            assertTrue(r.isVehicleReturned());
        });

        Optional<Vehicle> vOptional = mem.getVehicle(v1.getId());
        assertTrue(vOptional.isPresent());
        vOptional.ifPresent(v -> {
            assertFalse(v.isRented());
        });

        assertEquals("Rental id: dim:Mercedes returned", viewString);
    }
}
