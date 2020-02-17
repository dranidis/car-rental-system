package com.asdt.crs.interactors.rentvehicle;

import java.util.Optional;

import com.asdt.crs.VehicleNotFound;
import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Rental;
import com.asdt.crs.entities.Vehicle;

public interface Gateway {

	Optional<Customer> getCustomerById(String customerId);

	Optional<Vehicle> getFirstAvailableVehicleWithCategoryId(String categoryId);

	void addRental(Rental rental);

	void updateVehicle(String id, Vehicle available) throws VehicleNotFound;
}
