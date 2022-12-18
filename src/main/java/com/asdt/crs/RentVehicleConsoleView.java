package com.asdt.crs;

import com.asdt.crs.adapters.rentvehicle.RentVehicleView;
import com.asdt.crs.adapters.rentvehicle.RentVehicleViewModel;

public class RentVehicleConsoleView implements RentVehicleView {

    @Override
    public String genererateView(RentVehicleViewModel viewModel) {
        String response;

        if (viewModel.customerFound) {
            if (viewModel.rented) {
                response = "Car rented with rental id: " + viewModel.rentalId;
            } else {
                response = "Category not available";
            }
        } else {
            response = "Customer not found";
        }

        return response;

    }

}
