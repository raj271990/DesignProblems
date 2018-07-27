package com.raj.cabcompany.entities;

public class Trip {

    private String driverId;
    private String customerId;
    private double driverRating;
    private double customerRating;

    public Trip(Driver driver, Customer customer, double driverRating, double customerRating) {
        this.driverId = driver.getId();
        this.customerId = customer.getId();
        this.driverRating = driverRating;
        this.customerRating = customerRating;

        driver.rateCustomer(customer, customerRating);
        customer.rateDriver(driver, driverRating);
    }


    public String getDriverId() {
        return driverId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getDriverRating() {
        return driverRating;
    }

    public double getCustomerRating() {
        return customerRating;
    }
}
