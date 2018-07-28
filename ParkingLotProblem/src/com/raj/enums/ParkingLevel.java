package com.raj.enums;


public enum ParkingLevel {

    UB("UpperBasement"),
    LB("LowerBasement");

    private String description;

    private ParkingLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
