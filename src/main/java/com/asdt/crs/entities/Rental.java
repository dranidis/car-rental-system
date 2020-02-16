package com.asdt.crs.entities;

public class Rental {
    private String id;

    public Rental(Customer c, Vehicle available) {
        available.setRented(true);
        c.setRental(this);

        id = c.getId() + ":" + available.getId();
    }

    public String getId() {
        return id;
    }

}
