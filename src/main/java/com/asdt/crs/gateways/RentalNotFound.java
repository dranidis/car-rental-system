package com.asdt.crs.gateways;

public class RentalNotFound extends Exception {

	/**
     *
     */
    private static final long serialVersionUID = 1L;
    private String rentalId;

    public RentalNotFound(String rentalId) {
        this.rentalId = rentalId;
    }

    @Override
    public String getMessage() {
        return "Rental with id '" + rentalId + "' not found";
    }
}
