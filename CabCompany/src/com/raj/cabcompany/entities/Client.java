package com.raj.cabcompany.entities;

import java.util.Arrays;

public class Client {

    public static void main(String[] args) {

        CompanyApp application = new CompanyApp();

        Driver driver1 = new Driver("d1");
        Driver driver2 = new Driver("d2");
        Driver driver3 = new Driver("d3");
        Driver driver4 = new Driver("d4");
        Driver driver5 = new Driver("d5");

        application.getDrivers().addAll(Arrays.asList(driver1,driver2,driver3,driver4,driver5));

        application.addTrip(driver1.getId(),3, "c1", 4);
        application.addTrip(driver1.getId(),2, "c1", 5);
        application.addTrip(driver1.getId(),5, "c2", 5);
        application.addTrip(driver2.getId(),5, "c1", 2);
        application.addTrip(driver2.getId(),2, "c2", 5);

        System.out.println(application.getDriverAverageRating(driver1.getId()));
        System.out.println(application.getDriverAverageRating(driver2.getId()));

        System.out.println(application.getCustomerAverageRating("c1"));
        System.out.println(application.getCustomerAverageRating("c2"));


        application.listDriversByRating();

        application.listCustomersByRating();

        application.listEligibleDriversForCustomer("c1");

    }
}
