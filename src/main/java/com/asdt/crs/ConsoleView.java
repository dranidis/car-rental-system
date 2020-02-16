package com.asdt.crs;

import com.asdt.crs.adapters.rentvehicle.RentVehicleView;
import com.asdt.crs.adapters.rentvehicle.RentVehicleViewModel;

public class ConsoleView implements RentVehicleView{

    @Override
    public String genererateView(RentVehicleViewModel viewModel) {
        return "Rental: " + viewModel.rentalId;
    }

}
