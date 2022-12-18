package com.asdt.crs;

import com.asdt.crs.adapters.rentvehicle.RentVehicleController;
import com.asdt.crs.adapters.rentvehicle.RentVehiclePresenter;
import com.asdt.crs.usecases.rentvehicle.RentVehicleUseCase;
import com.asdt.crs.usecases.rentvehicle.RentVehicleRequest;

/**
 * Car Rental System exercise!
 */
public final class ConsoleApp {
    private ConsoleApp() {
    }

    /**
     * Runs a simple sample scenario.
     * @param args
     */
    public static void main(String[] args) {
        RentVehicleController rentVehicleController = new RentVehicleController(
            new RentVehicleUseCase(new InMemoryPersistence()),
            new RentVehiclePresenter(),
            new RentVehicleConsoleView());

        RentVehicleRequest request = new RentVehicleRequest();
        request.categoryId = "small";
        request.customerId = "dim";
        String viewString = rentVehicleController.handle(request);
        System.out.println(viewString);
    }
}
