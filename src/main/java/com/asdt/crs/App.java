package com.asdt.crs;

/**
 * Car Rental System exercise!
 */
public final class App {
    private App() {
    }

    /**
     * Runs two sample rentals.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        CarRentalAgency cra = new CarRentalAgency();

        String rentalId = cra.rentVehicle("small", "cust1");
        System.out.println(rentalId);

        rentalId = cra.rentVehicle("small", "cust2");
        System.out.println(rentalId);
    }
}
