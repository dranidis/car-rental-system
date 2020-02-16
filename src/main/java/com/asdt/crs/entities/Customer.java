package com.asdt.crs.entities;

public class Customer {
    private String id;
    private Rental rental;

    public Customer(String string) {
        id = string;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public String getId() {
        return id;
    }

}
