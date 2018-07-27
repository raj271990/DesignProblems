package com.raj.cabcompany.entities;

public class Driver extends User {

    public Driver(String id) {
        super(id);
    }

    public void rateCustomer(Customer customer, double tripRating) {

        customer.updateAvgRating(tripRating);

    }

    @Override
    public int compareTo(User that) {
        return super.compareTo(that);
    }
}
