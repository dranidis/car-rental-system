package com.asdt.crs;

import com.asdt.crs.adapters.rentvehicle.RentVehicleController;
import com.asdt.crs.adapters.rentvehicle.RentVehiclePresenter;
import com.asdt.crs.interactors.rentvehicle.RentVehicleInteractor;
import com.asdt.crs.interactors.rentvehicle.RentVehicleRequestModel;

/**
 * Car Rental System exercise!
 */
public final class App {
    private App() {
    }

    /**
     * Runs two sample rentals.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        RentVehicleController rentVehicleController = new RentVehicleController(
            new RentVehicleInteractor(new InMemoryPersistence()),
            new RentVehiclePresenter(),
            new ConsoleView());

        RentVehicleRequestModel request = new RentVehicleRequestModel();
        request.categoryId = "small";
        request.customerId = "dim";
        String viewString = rentVehicleController.handle(request);
        System.out.println(viewString);
    }
}
