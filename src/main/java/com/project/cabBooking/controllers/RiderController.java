package com.project.cabBooking.controllers;

import com.project.cabBooking.model.Location;
import com.project.cabBooking.model.Rider;
import com.project.cabBooking.model.Trip;
import com.project.cabBooking.service.RiderManager;
import com.project.cabBooking.service.TripsManager;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RiderController {
    @Autowired
    private RiderManager riderManager;
    @Autowired
    private TripsManager tripsManager;

    @PostMapping(value = "/register/rider")
    public ResponseEntity registerRider(final String riderId, final String riderName){
        riderManager.createRider(new Rider(riderId, riderName));
        return ResponseEntity.ok("");
    }

    @PostMapping(value="/book")
    public ResponseEntity bookARide(final String riderId, final Double sourceX, final Double sourceY,
                                    final Double destX, final Double destY){
        tripsManager.createTrip(riderManager.getRider(riderId), new Location(sourceX, sourceY), new Location(destX, destY));
        return ResponseEntity.ok("");
    }

    /*Get trip history of an existing rider*/
    @GetMapping(value="/ride/history")
    public ResponseEntity getTripHistory(final String riderId){
       List<Trip> trips = tripsManager.getTripHistory(riderManager.getRider(riderId));
        return ResponseEntity.ok(trips);
    }
}
