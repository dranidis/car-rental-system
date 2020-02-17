package com.asdt.crs.adapters.returnvehicle;

import com.asdt.crs.usecases.returnvehicle.ReturnVehicleUseCaseInterface;
import com.asdt.crs.usecases.returnvehicle.ReturnVehicleResponse;

public class ReturnVehiclePresenter implements ReturnVehicleUseCaseInterface {

	private ReturnVehicleViewModel viewModel;

    public ReturnVehicleViewModel getViewModel() {
		return viewModel;
	}

    @Override
    public void present(ReturnVehicleResponse response) {
        viewModel = new ReturnVehicleViewModel();
        viewModel.alreadyReturned = response.alreadyReturned;
        viewModel.rentalFound = response.rentalFound;
        viewModel.returned = response.returned;
        viewModel.rentalId = response.rentalId;
	}

}
