package com.asdt.crs.adapters.returnvehicle;

import com.asdt.crs.usecases.returnvehicle.ReturnVehicleUseCase;
import com.asdt.crs.usecases.returnvehicle.ReturnVehicleRequest;

public class ReturnVehicleController {

	private ReturnVehicleUseCase returnVehicleInteractor;
    private ReturnVehiclePresenter presenter;
    private ReturnVehicleView view;

    public ReturnVehicleController(
        ReturnVehicleUseCase returnVehicleInteractor,
        ReturnVehiclePresenter returnVehiclePresenter,
        ReturnVehicleView returnVehicleView) {
            this.returnVehicleInteractor = returnVehicleInteractor;
            this.presenter = returnVehiclePresenter;
            this.view = returnVehicleView;
	}

	public String handle(ReturnVehicleRequest request) {
        returnVehicleInteractor.returnVehicle(request, presenter);
        return view.genererateView(presenter.getViewModel());
	}

}
