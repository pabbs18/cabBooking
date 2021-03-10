package com.project.cabBooking.strategies;

import com.project.cabBooking.model.Cab;
import com.project.cabBooking.model.Location;
import com.project.cabBooking.model.Rider;

import java.util.List;

public interface CabMatchingStrategy {
    //fromPoint and toPoint are used for future changes in CabMatchingStrategy implementation
    Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint);
}
