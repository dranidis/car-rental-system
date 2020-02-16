package com.asdt.crs.adapters.rentvehicle;

import com.asdt.crs.interactors.rentvehicle.RentVehicleInputBoundary;
import com.asdt.crs.interactors.rentvehicle.RentVehicleRequestModel;

public class RentVehicleController {
    private RentVehicleInputBoundary rentVehicleInteractor;
    private RentVehiclePresenter presenter;
    private RentVehicleView view;

    public RentVehicleController(
        RentVehicleInputBoundary rentVehicleInteractor,
        RentVehiclePresenter presenter,
        RentVehicleView view) {
            this.rentVehicleInteractor = rentVehicleInteractor;
            this.presenter = presenter;
            this.view = view;

    }

    public String handle(RentVehicleRequestModel request) {
        rentVehicleInteractor.rentVehicle(request, presenter);
        return view.genererateView(presenter.getViewModel());
    }
}
