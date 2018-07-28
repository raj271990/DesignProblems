package com.raj.entities;

import com.raj.enums.ParkingLevel;

public class ParkingSpot {

    public enum SpotSize {
        SMALL,
        LARGE
    }

    SpotSize size;
    ParkingLevel parkingLevel;

    public ParkingSpot(SpotSize size, ParkingLevel parkingLevel) {
        this.size = size;
        this.parkingLevel = parkingLevel;
    }

    public SpotSize getSize() {
        return size;
    }

    public ParkingLevel getParkingLevel() {
        return parkingLevel;
    }
}
