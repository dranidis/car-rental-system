package com.asdt.crs;

public class Customer {
    private Rental rental;
    private String id;

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
