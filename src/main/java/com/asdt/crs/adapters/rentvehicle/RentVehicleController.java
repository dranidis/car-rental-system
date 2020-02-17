package com.asdt.crs.adapters.rentvehicle;

import com.asdt.crs.usecases.rentvehicle.RentVehicleRequest;
import com.asdt.crs.usecases.rentvehicle.RentVehicleUseCase;

public class RentVehicleController {
    private RentVehicleUseCase rentVehicleInteractor;
    private RentVehiclePresenter presenter;
    private RentVehicleView view;

    public RentVehicleController(
        RentVehicleUseCase rentVehicleInteractor,
        RentVehiclePresenter presenter,
        RentVehicleView view) {
            this.rentVehicleInteractor = rentVehicleInteractor;
            this.presenter = presenter;
            this.view = view;

    }

    public String handle(RentVehicleRequest request) {
        rentVehicleInteractor.rentVehicle(request, presenter);
        return view.genererateView(presenter.getViewModel());
    }
}
