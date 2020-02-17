package com.asdt.crs.interactors.rentvehicle;

import java.util.Optional;

import com.asdt.crs.VehicleNotFound;
import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Rental;
import com.asdt.crs.entities.Vehicle;

public class RentVehicleInteractor implements RentVehicleInputBoundary {
    private Gateway gateway;

    public RentVehicleInteractor(Gateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void rentVehicle(RentVehicleRequestModel request, RentVehicleOutputBoundary presenter) {
        RentVehicleResponse response = new RentVehicleResponse();
        Optional<Customer> customerOptional = gateway.getCustomerById(request.customerId);

        customerOptional.ifPresent(customer -> {
            response.customerFound = true;

            Optional<Vehicle> availableOptional = gateway.getFirstAvailableVehicleWithCategoryId(request.categoryId);

            availableOptional.ifPresent(available -> {
                Rental rental = new Rental(customer, available);
                available.setRented(true);
                gateway.addRental(rental);
                try {
                    gateway.updateVehicle(available.getId(), available);
                } catch (VehicleNotFound e) {
                    e.printStackTrace();
                }
                response.rentalId = rental.getId();
                response.rented = true;
            });
        });
        presenter.present(response);
    }
}
