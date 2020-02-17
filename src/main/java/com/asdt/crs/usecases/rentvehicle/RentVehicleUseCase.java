package com.asdt.crs.usecases.rentvehicle;

import java.util.Optional;

import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Rental;
import com.asdt.crs.entities.Vehicle;
import com.asdt.crs.usecases.entitygateways.Gateway;
import com.asdt.crs.usecases.entitygateways.VehicleNotFound;

public class RentVehicleUseCase {
    private Gateway gateway;

    public RentVehicleUseCase(Gateway gateway) {
        this.gateway = gateway;
    }

    public void rentVehicle(RentVehicleRequest request, RentVehicleUseCaseInterface presenter) {
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
