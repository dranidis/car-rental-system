package com.asdt.crs.entities;

public class Vehicle {
    private boolean rented;
    private String id;

    public Vehicle(String string) {
        id = string;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean b) {
        rented = b;
    }

    public String getId() {
        return id;
    }

}
