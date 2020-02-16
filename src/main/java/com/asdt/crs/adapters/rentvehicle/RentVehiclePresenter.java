package com.asdt.crs.adapters.rentvehicle;

import com.asdt.crs.interactors.rentvehicle.RentVehicleOutputBoundary;
import com.asdt.crs.interactors.rentvehicle.RentVehicleResponse;

public class RentVehiclePresenter implements RentVehicleOutputBoundary {
    private RentVehicleViewModel rentVehicleViewModel;

    @Override
    public void present(RentVehicleResponse response) {
        rentVehicleViewModel = new RentVehicleViewModel();
        rentVehicleViewModel.rentalId = response.rentalId;
    }

	public RentVehicleViewModel getViewModel() {
        return rentVehicleViewModel;
	}

}
