package com.raj.entities;

import com.raj.enums.VehicleType;

public class Vehicle {

    String name;
    String licenceNumber;
    VehicleType vehicleType;

    public Vehicle(String name, String licenceNumber, VehicleType vehicleType) {

        this.name = name;
        this.licenceNumber = licenceNumber;
        this.vehicleType = vehicleType;
    }

    public String getName() {
        return name;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
