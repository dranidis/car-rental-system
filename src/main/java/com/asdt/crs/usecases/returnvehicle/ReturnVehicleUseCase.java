package com.asdt.crs.usecases.returnvehicle;

import java.util.Optional;

import com.asdt.crs.entities.Rental;
import com.asdt.crs.entities.Vehicle;
import com.asdt.crs.usecases.entitygateways.Gateway;
import com.asdt.crs.usecases.entitygateways.RentalNotFound;
import com.asdt.crs.usecases.entitygateways.VehicleNotFound;

public class ReturnVehicleUseCase {

    private Gateway gateway;

    public ReturnVehicleUseCase(Gateway gateway) {
        this.gateway = gateway;
    }

    public void returnVehicle(ReturnVehicleRequest request, ReturnVehicleUseCaseInterface presenter) {
        ReturnVehicleResponse response = new ReturnVehicleResponse();
        response.rentalId = request.rentalId;

        Optional<Rental> rentalOptional = gateway.getRental(request.rentalId);

        rentalOptional.ifPresent(rental -> {
            response.rentalFound = true;

            if (rental.isVehicleReturned()) {
                response.alreadyReturned = true;
            } else {
                rental.setVehicleReturned(true);
                Vehicle vehicle = rental.getVehicle();
                vehicle.setRented(false);
                try {
                    gateway.updateVehicle(vehicle.getId(), vehicle);
                    gateway.updateRentalReturned(rental.getId());
                } catch (VehicleNotFound | RentalNotFound e) {
                    e.printStackTrace();
                }
                    response.returned = true;
            }
        });

        presenter.present(response);
    }

}
