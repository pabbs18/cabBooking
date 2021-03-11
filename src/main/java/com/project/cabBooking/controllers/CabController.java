package com.project.cabBooking.controllers;

import com.project.cabBooking.model.Cab;
import com.project.cabBooking.model.Location;
import com.project.cabBooking.service.CabsManager;
import com.project.cabBooking.service.TripsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CabController {
    @Autowired
    private CabsManager cabsManager;
    @Autowired
    private TripsManager tripsManager;

    @PostMapping(value = "/register/cab")
    public ResponseEntity registerCab(final String cabId, final String driverName){
        cabsManager.createCab(new Cab(cabId,driverName));
        return ResponseEntity.ok("");
    }

    @PostMapping(value = "/update/cab/location")
    public ResponseEntity updateCabLocation(final String cabId, final Double newX, final Double newY){
        cabsManager.updateCabLocation(cabId, new Location(newX,newY));
        return ResponseEntity.ok("");
    }

    @PostMapping(value="/update/cab/availability")
    public ResponseEntity updateCabAvailability(final String cabId, final Boolean newAvailability){
        cabsManager.updateAvailability(cabId,newAvailability);
        return ResponseEntity.ok("");
    }

    //you end trip for an existing cab
    @PostMapping(value="update/cab/end/trip")
    public ResponseEntity endTrip(final String cabId){
        tripsManager.endTrip(cabsManager.getCab(cabId));
        return ResponseEntity.ok("");
    }
}
