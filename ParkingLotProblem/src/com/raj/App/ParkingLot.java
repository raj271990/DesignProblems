package com.raj.App;

import com.raj.entities.ParkingSpot;
import com.raj.entities.Vehicle;
import com.raj.enums.ParkingLevel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/*
 * Handles parking and unparking for now
 */
public class ParkingLot {

    private Map<ParkingLevel, LinkedList<ParkingSpot>> twoWheelerAvailableSpotsByLevel;
    private Map<ParkingLevel, LinkedList<ParkingSpot>> fourWheelerAvailableSpotsByLevel;
    private Map<String, ParkingSpot> mapOfVehicleLicenceToParkingLot = new HashMap<>();

    public ParkingLot(List<ParkingSpot> parkingSpots) {

        twoWheelerAvailableSpotsByLevel = new HashMap<>();
        fourWheelerAvailableSpotsByLevel = new HashMap<>();

        // add parking spots to maps
        for(ParkingSpot spot : parkingSpots) {
            if(spot.getSize().equals(ParkingSpot.SpotSize.SMALL)) {
                addTomap(twoWheelerAvailableSpotsByLevel, spot);
            } else {
                addTomap(fourWheelerAvailableSpotsByLevel, spot);
            }
        }
    }

    public void parkVehicle(Vehicle vehicle) throws Exception {

        switch (vehicle.getVehicleType()) {

            case TWOWHEELER:
                occupyParkingSpot(vehicle, twoWheelerAvailableSpotsByLevel);
                // when no two wheeler spot available
                if(mapOfVehicleLicenceToParkingLot.containsKey(vehicle.getLicenceNumber())) {
                    System.out.println("Vehicle : "
                            + vehicle.getName() + " with licence plate number : "
                            + vehicle.getLicenceNumber() + " parked successfully");
                } else {
                    System.out.println("All two wheeler parking spots are full");
                }
                break;
            case FOURWHEELER:
                occupyParkingSpot(vehicle, fourWheelerAvailableSpotsByLevel);

                if(mapOfVehicleLicenceToParkingLot.containsKey(vehicle.getLicenceNumber())) {
                    System.out.println("Vehicle : "
                            + vehicle.getName() + " with licence plate number : "
                            + vehicle.getLicenceNumber() + " parked successfully");
                } else {
                    System.out.println("All four wheeler parking spots are full");
                }
                break;
            default:
                throw new Exception("Invalid Vehicle type");

        }
    }

    // Can modify this function to return the charges for parking
    // , depending on (may be for how long the parking was occupied)
    public void unparkVehicle(Vehicle vehicle) throws Exception {

        // remove the vehicle from the vehicle to ParkingSpot Map
        ParkingSpot freedParkingSpot = mapOfVehicleLicenceToParkingLot.remove(vehicle.getLicenceNumber());

        if(freedParkingSpot == null) {
            throw new Exception("No such vehicle parked");
        }

        // add the freedParkingSpot to available spots
        if(freedParkingSpot.getSize().equals(ParkingSpot.SpotSize.SMALL)) {
            twoWheelerAvailableSpotsByLevel.get(freedParkingSpot.getParkingLevel()).add(freedParkingSpot);
        } else {
            fourWheelerAvailableSpotsByLevel.get(freedParkingSpot.getParkingLevel()).addFirst(freedParkingSpot);
        }

        System.out.println("Vehicle : "
                + vehicle.getName() + " with licence plate number : "
                + vehicle.getLicenceNumber() + " unparked successfully");
    }


    private void occupyParkingSpot(Vehicle vehicle, Map<ParkingLevel
            , LinkedList<ParkingSpot>> availableSpotsByLevel) {

        for(ParkingLevel parkingLevel : ParkingLevel.values()) {
            if(!availableSpotsByLevel.get(parkingLevel).isEmpty()) {
                // book a spot at this level
                ParkingSpot availableSpot = availableSpotsByLevel.get(parkingLevel).removeFirst();
                mapOfVehicleLicenceToParkingLot.put(vehicle.getLicenceNumber(),availableSpot);
                break;
            }
        }

    }

    private void addTomap(Map<ParkingLevel, LinkedList<ParkingSpot>> availableSpotsByLevel,ParkingSpot spot) {

        if(!availableSpotsByLevel.containsKey(spot.getParkingLevel())) {
            availableSpotsByLevel.put(spot.getParkingLevel(), new LinkedList<>());
            availableSpotsByLevel.get(spot.getParkingLevel()).addFirst(spot);
        } else {
            availableSpotsByLevel.get(spot.getParkingLevel()).addFirst(spot);
        }
    }

}
