package com.asdt.crs.usecases.entitygateways;

public class VehicleNotFound extends Exception {

	/**
     *
     */
    private static final long serialVersionUID = 1L;
    private String vehicleId;

    public VehicleNotFound(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String getMessage() {
        return "Vehicle with id '" + vehicleId + "' not found";
    }

}
