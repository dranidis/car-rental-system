package com.asdt.crs.interactors.rentvehicle;

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
        Customer customer = gateway.getCustomerById(request.customerId);
        Vehicle available = gateway.getAvailableVehicleByCategoryId(request.categoryId);

        if (available != null) {
            Rental rental = new Rental(customer, available);
            gateway.saveRental(rental);
            response.rentalId = rental.getId();
        }
        presenter.present(response);
    }
}
