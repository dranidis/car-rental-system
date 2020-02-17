package com.asdt.crs;

import com.asdt.crs.adapters.returnvehicle.ReturnVehicleView;
import com.asdt.crs.adapters.returnvehicle.ReturnVehicleViewModel;

public class ReturnVehicleConsoleView implements ReturnVehicleView {

    @Override
    public String genererateView(ReturnVehicleViewModel viewModel) {
        if (viewModel.returned) {
            return "Rental id: " + viewModel.rentalId + " returned";
        }
        return "Not implemented";
    }

}
