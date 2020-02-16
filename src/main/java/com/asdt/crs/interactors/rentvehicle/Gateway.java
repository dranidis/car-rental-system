package com.asdt.crs.interactors.rentvehicle;

import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Rental;
import com.asdt.crs.entities.Vehicle;

public interface Gateway {

	Customer getCustomerById(String customerId);

	Vehicle getAvailableVehicleByCategoryId(String categoryId);

	void saveRental(Rental rental);
}
