package com.asdt.crs.interactors.rentvehicle;

import java.util.Optional;

import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Rental;
import com.asdt.crs.entities.Vehicle;

public interface Gateway {

	Optional<Customer> getCustomerById(String customerId);

	Optional<Vehicle> getAvailableVehicleByCategoryId(String categoryId);

	void saveRental(Rental rental);
}
