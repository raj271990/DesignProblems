package com.raj.cabcompany.entities;

public class Customer extends User {

    public Customer(String id) {
        super(id);
    }

    public void rateDriver(Driver driver, double tripRating) {

        driver.updateAvgRating(tripRating);
    }

    @Override
    public int compareTo(User that) {
        return super.compareTo(that);
    }
}
