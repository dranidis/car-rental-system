package com.asdt.crs.entities;

public class Vehicle {
    private boolean rented;
    private String id;
    private Category category;

    public Vehicle(String id, Category category) {
        this.id = id;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

}
