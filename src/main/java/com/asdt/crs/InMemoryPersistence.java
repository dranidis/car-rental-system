package com.asdt.crs;

import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Rental;
import com.asdt.crs.entities.Vehicle;
import com.asdt.crs.interactors.rentvehicle.Gateway;

public class InMemoryPersistence implements Gateway {

    @Override
    public Customer getCustomerById(String customerId) {
        return new Customer("Dimitris");
    }

    @Override
    public Vehicle getAvailableVehicleByCategoryId(String categoryId) {
        return new Vehicle("Mercedes");
    }

    @Override
    public void saveRental(Rental rental) {
        System.out.println("Saved rental: " + rental.toString());
    }

}
