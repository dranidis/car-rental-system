package com.asdt.crs;

import com.asdt.crs.adapters.rentvehicle.RentVehicleView;
import com.asdt.crs.adapters.rentvehicle.RentVehicleViewModel;

public class RentVehicleConsoleView implements RentVehicleView {

    @Override
    public String genererateView(RentVehicleViewModel viewModel) {
        if (viewModel.customerFound)
            if (viewModel.rented)
                return "Car rented with rental id: " + viewModel.rentalId;
            else
                return "Category not available";
        else
            return "Customer not found";

    }

}
