package com.asdt.crs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Rental;
import com.asdt.crs.entities.Vehicle;
import com.asdt.crs.interactors.rentvehicle.Gateway;

public class InMemoryPersistence implements Gateway {
    private Map<String, Customer> customers = new HashMap<>();
    private Map<String, Rental> rentals = new HashMap<>();
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public Optional<Customer> getCustomerById(String customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }

    @Override
    public Optional<Vehicle> getAvailableVehicleByCategoryId(String categoryId) {
        return vehicles
            .stream()
            .filter(v -> !v.isRented())
            .filter(v -> v.getCategory().getId().equals(categoryId))
            .findFirst();
    }

    @Override
    public void saveRental(Rental rental) {
        rentals.put(rental.getId(), rental);
        System.out.println("Saved rental: " + rental.toString());
    }

	public void addVehicle(Vehicle v1) {
        vehicles.add(v1);
	}

}
