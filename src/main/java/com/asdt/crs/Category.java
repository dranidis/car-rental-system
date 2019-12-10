package com.asdt.crs;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private List<Vehicle> vehicles = new ArrayList<>();
    private String id;

    public Category(String string) {
        id = string;
    }

    public Vehicle getAvailableVehicle() {
        for (Vehicle v : vehicles) {
            if (!v.isRented()) {
                return v;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

}
