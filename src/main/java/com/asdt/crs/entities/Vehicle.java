package com.asdt.crs.entities;

public class Vehicle implements Cloneable {
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

    public Object clone() throws CloneNotSupportedException {
        Vehicle vehicle = new Vehicle(this.id, this.category);
        vehicle.rented = this.rented;
        vehicle.category = (Category) category.clone();
        return vehicle;
    }
}
