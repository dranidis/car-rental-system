package com.asdt.crs;

import java.util.HashMap;
import java.util.Map;

public class CarRentalAgency {
    private Map<String, Customer> customers = new HashMap<>();
    private Map<String, Category> categories = new HashMap<>();
    private Map<String, Rental> rentals = new HashMap<>();

    /**
     * Rents the first available vehicle of the specified category to the customer.
     *
     * @param catId  vehicle category id
     * @param custId customer id
     * @return rental id
     */
    public String rentVehicle(String catId, String custId) {
        Customer c = customers.get(custId);
        Category cat = categories.get(catId);
        Vehicle available = cat.getAvailableVehicle();

        if (available != null) {
            Rental r = new Rental(c, available);
            rentals.put(r.getId(), r);
            return r.getId();
        }
        return null;
    }

    public CarRentalAgency() {
        Customer c1 = new Customer("cust1");
        Customer c2 = new Customer("cust2");
        customers.put(c1.getId(), c1);
        customers.put(c2.getId(), c2);
        Category cat1 = new Category("small");
        categories.put(cat1.getId(), cat1);

        cat1.addVehicle(new Vehicle("Smart 1"));
    }

}
