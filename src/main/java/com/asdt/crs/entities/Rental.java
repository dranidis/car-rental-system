package com.asdt.crs.entities;

public class Rental {
    private Customer customer;
    private Vehicle vehicle;

    public Rental(Customer customer, Vehicle vehicle) {
        this.customer = customer;
        this.vehicle = vehicle;
    }

    public String getId() {
        return customer.getId() + ":" + vehicle.getId();
    }

    public Object clone() throws CloneNotSupportedException {
        Rental rental = new Rental(this.customer, this.vehicle);
        rental.customer = (Customer) customer.clone();
        rental.vehicle = (Vehicle) vehicle.clone();
        return rental;
    }

	public Vehicle getVehicle() {
		return vehicle;
	}
}
