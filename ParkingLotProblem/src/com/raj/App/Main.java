package com.raj.App;

import com.raj.entities.ParkingSpot;
import com.raj.entities.Vehicle;
import com.raj.enums.ParkingLevel;
import com.raj.enums.VehicleType;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        Vehicle ford = new Vehicle("ford", "US01SE1000", VehicleType.FOURWHEELER);
        Vehicle swift = new Vehicle("swift", "US01SE2000", VehicleType.FOURWHEELER);
        Vehicle lamborgini = new Vehicle("lamborgini50", "US01SE8000", VehicleType.FOURWHEELER);
        Vehicle alto = new Vehicle("alto01", "US01SE9000", VehicleType.FOURWHEELER);
        Vehicle maruti = new Vehicle("maruti01", "US01SE6000", VehicleType.FOURWHEELER);

        Vehicle fz = new Vehicle("fz2.0", "US01SE3000", VehicleType.TWOWHEELER);
        Vehicle harley = new Vehicle("harley", "US01SE4000", VehicleType.TWOWHEELER);

        ParkingSpot spot1 = new ParkingSpot(ParkingSpot.SpotSize.SMALL, ParkingLevel.UB);
        ParkingSpot spot2 = new ParkingSpot(ParkingSpot.SpotSize.SMALL, ParkingLevel.UB);
        ParkingSpot spot3 = new ParkingSpot(ParkingSpot.SpotSize.LARGE, ParkingLevel.UB);
        ParkingSpot spot4 = new ParkingSpot(ParkingSpot.SpotSize.LARGE, ParkingLevel.UB);
        ParkingSpot spot5 = new ParkingSpot(ParkingSpot.SpotSize.SMALL, ParkingLevel.LB);
        ParkingSpot spot6 = new ParkingSpot(ParkingSpot.SpotSize.SMALL, ParkingLevel.LB);
        ParkingSpot spot7 = new ParkingSpot(ParkingSpot.SpotSize.LARGE, ParkingLevel.LB);
        ParkingSpot spot8 = new ParkingSpot(ParkingSpot.SpotSize.LARGE, ParkingLevel.LB);

        List<ParkingSpot> allSpots = Arrays.asList(spot1, spot2, spot3, spot4, spot5, spot6, spot7, spot8);

        ParkingLot parkingLot = new ParkingLot(allSpots);


        parkingLot.parkVehicle(ford);
        parkingLot.parkVehicle(swift);
        parkingLot.parkVehicle(lamborgini);
        parkingLot.parkVehicle(alto);
        parkingLot.parkVehicle(maruti);





    }
}
