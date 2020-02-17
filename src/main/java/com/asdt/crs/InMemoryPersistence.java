package com.asdt.crs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.asdt.crs.entities.Customer;
import com.asdt.crs.entities.Rental;
import com.asdt.crs.entities.Vehicle;
import com.asdt.crs.usecases.entitygateways.Gateway;
import com.asdt.crs.usecases.entitygateways.RentalNotFound;
import com.asdt.crs.usecases.entitygateways.VehicleNotFound;

public class InMemoryPersistence implements Gateway {
    private Map<String, Customer> customers = new HashMap<>();
    private Map<String, Rental> rentals = new HashMap<>();
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addCustomer(Customer customer) {
        try {
            customers.put(customer.getId(), (Customer) customer.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void addVehicle(Vehicle vehicle) {
        try {
            vehicles.add((Vehicle) vehicle.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRental(Rental rental) {
        try {
            rentals.put(rental.getId(), (Rental) rental.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Customer> getCustomerById(String customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }

    @Override
    public Optional<Vehicle> getFirstAvailableVehicleWithCategoryId(String categoryId) {
        return vehicles.stream().filter(v -> !v.isRented()).filter(v -> v.getCategory().getId().equals(categoryId))
                .findFirst();
    }

    public Optional<Vehicle> getVehicle(String vehicleId) {
        return vehicles.stream().filter(v -> v.getId().equals(vehicleId)).findFirst();
    }

    @Override
    public Optional<Rental> getRental(String rentalId) {
        return Optional.ofNullable(rentals.get(rentalId));
    }

    @Override
    public void updateVehicle(String vehicleId, Vehicle newVehicle) throws VehicleNotFound {
        Optional<Vehicle> vehicle = getVehicle(vehicleId);
        if (vehicle.isPresent()) {
            vehicles.remove(vehicle.get());
            vehicles.add(newVehicle);
        } else {
            throw new VehicleNotFound(vehicleId);
        }
    }

    @Override
    public void updateRentalReturned(String rentalId) throws RentalNotFound {
        Optional<Rental> rentalOptional = getRental(rentalId);
        if (rentalOptional.isPresent()) {
            rentalOptional.get().setVehicleReturned(true);
        } else {
            throw new RentalNotFound(rentalId);
        }
    }

}
