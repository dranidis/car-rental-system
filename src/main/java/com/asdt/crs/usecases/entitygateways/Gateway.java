package com.asdt.crs.usecases.entitygateways;

import java.util.Optional;

import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Rental;
import com.asdt.crs.entities.Vehicle;

public interface Gateway {

	Optional<Customer> getCustomerById(String customerId);

	Optional<Vehicle> getFirstAvailableVehicleWithCategoryId(String categoryId);

	void addRental(Rental rental);

	void updateVehicle(String id, Vehicle available) throws VehicleNotFound;

	Optional<Rental> getRental(String rentalId);

	void updateRentalReturned(String id) throws RentalNotFound;
}
