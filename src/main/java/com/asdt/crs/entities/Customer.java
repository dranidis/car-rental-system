package com.asdt.crs.entities;

public class Customer {
    private String id;
    private Rental rental;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public String getId() {
        return id;
    }

	public String getName() {
		return name;
	}

}
