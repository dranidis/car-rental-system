package com.asdt.crs.adapters.rentvehicle;

import com.asdt.crs.usecases.rentvehicle.RentVehicleRequest;
import com.asdt.crs.usecases.rentvehicle.RentVehicleUseCase;

public class RentVehicleController {
    private RentVehicleUseCase usecase;
    private RentVehiclePresenter presenter;
    private RentVehicleView view;

    public RentVehicleController(
        RentVehicleUseCase rentVehicleInteractor,
        RentVehiclePresenter presenter,
        RentVehicleView view) {
            this.usecase = rentVehicleInteractor;
            this.presenter = presenter;
            this.view = view;

    }

    public String handle(RentVehicleRequest request) {
        usecase.rentVehicle(request, presenter);
        return view.genererateView(presenter.getViewModel());
    }
}
