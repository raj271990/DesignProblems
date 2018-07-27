package com.raj.cabcompany.entities;

import java.util.*;
import java.util.stream.Collectors;


public class CompanyApp {

    List<Driver> drivers = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    List<Trip> trips = new ArrayList<>();

    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public double getDriverAverageRating(String driverId) {

        Optional<Driver> driver = drivers.stream().filter(d -> d.getId().equals(driverId)).findFirst();
        return driver.get().getAvgRating();
    }

    public double getCustomerAverageRating(String customerId) {

        Optional<Customer> customer = customers.stream().filter(c -> c.getId().equals(customerId)).findFirst();

        return customer.get().getAvgRating();

    }

    public void listDriversByRating() {

        Collections.sort(drivers);
        for(Driver driver : drivers) {
            System.out.println(driver.getId());
        }
    }

    public void listCustomersByRating() {
        Collections.sort(customers);
        for(Customer customer : customers) {
            System.out.println(customer.getId());
        }
    }

    public void addTrip(String driverId, double driverRating, String customerId, double customerRating) {

        Boolean customerExists = false;
        for(Customer customer : customers) {
            if(customer.getId() == customerId) {
                customerExists = true;
                break;
            }
        }

        if(!customerExists) {
            Customer customer = new Customer(customerId);
            customers.add(customer);
        }

        Optional<Driver> matchingDriver = drivers.stream().filter(d -> d.getId().equals(driverId)).findFirst();

        Optional<Customer> matchingCustomer = customers.stream().filter(c -> c.getId().equals(customerId)).findFirst();


        Trip trip = new Trip(matchingDriver.get(),matchingCustomer.get(), driverRating, customerRating);
        trips.add(trip);

    }

    public void listEligibleDriversForCustomer(String customerId) {

        Optional<Customer> matchingCustomer = customers.stream().filter(c -> c.getId().equals(customerId)).findFirst();
        Customer customer = matchingCustomer.get();

        // List of drivers with rating greater than the customer
        List<String> driverRatedHigherThanCustomer = drivers.stream()
                .filter(d -> d.getAvgRating() > customer.getAvgRating())
                .map(Driver::getId)
                .collect(Collectors.toList());

        // List all trips with customerId
        List<Trip> tripsWithGivenCustomerId = trips.stream()
                .filter(t -> t.getCustomerId().equals(customerId))
                .collect(Collectors.toList());


        if(driverRatedHigherThanCustomer == null) {
            tripsWithGivenCustomerId.stream()
                    .map(Trip::getDriverId).distinct()
                    .forEach(id -> System.out.println());
            return;
        }

        // List of drivers not to be considered being rated as 1
        Set<String> nonEligibleDrivers = tripsWithGivenCustomerId.stream()
                    .filter(t -> t.getDriverRating() == 1.00)
                    .map(Trip::getDriverId)
                    .collect(Collectors.toSet());

        driverRatedHigherThanCustomer.removeAll(nonEligibleDrivers);


        driverRatedHigherThanCustomer.stream().forEach(driverId -> System.out.println(driverId));

    }

}
