package com.project.cabBooking.service;

import com.project.cabBooking.exceptions.CabNotFoundException;
import com.project.cabBooking.exceptions.TripNotFoundException;
import com.project.cabBooking.model.Cab;
import com.project.cabBooking.model.Location;
import com.project.cabBooking.model.Rider;
import com.project.cabBooking.model.Trip;
import com.project.cabBooking.strategies.CabMatchingStrategy;
import com.project.cabBooking.strategies.PricingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TripsManager {
    private static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0;

    private Map<String, List<Trip>> trips = new HashMap<>();

    @Autowired
    private CabsManager cabsManager;
    @Autowired
    private RiderManager riderManager;
    @Autowired
    private CabMatchingStrategy cabMatchingStrategy;
    @Autowired
    private PricingStrategy pricingStrategy;

    //to create a new trip, for a Rider, we need all the closeBy available cabs, and pricing of the trip
    //from fromPoint to toPoint
    public void createTrip(Rider rider, Location fromPoint, Location toPoint){
        //first get all the closeBy cabs
        final List<Cab> closeByCabs = cabsManager.getCabs(fromPoint, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);

        //now get only the available closeBy cabs
        final List<Cab> closeByAvailableCabs = getCloseByAvailableCabs(closeByCabs);

        // fromPoint and toPoint are not used with DefaultStrategy. They are here in case we use a new Strategy in future
        final Cab selectedCab = cabMatchingStrategy.matchCabToRider(rider, closeByAvailableCabs, fromPoint, toPoint);
        if(selectedCab == null){
            throw new CabNotFoundException();
        }

        //now that you've selected the first available cab within the allowed distance,
        // get the price of the trip
        Double price = pricingStrategy.findPrice(fromPoint,toPoint);

        //finally you create the trip object
        final Trip newTrip = new Trip(rider, selectedCab, price, fromPoint, toPoint);

        //now you can add this trip to the rider's list of trips, against her id
        if(!trips.containsKey(rider.getId())){
            trips.put(rider.getId(), new ArrayList<Trip>());
        }
        trips.get(rider.getId()).add(newTrip);

        //also for the selected cab, sets it's current trip with this new trip
        selectedCab.setCurrentTrip(newTrip);
    }

    //to get trip history of a rider, just query the trip list from the hashmap
        public List<Trip> getTripHistory(Rider rider){
           return trips.get(rider.getId());
        }

    //end a trip for a cab
    public void endTrip(Cab cab){
        if(cab.getCurrentTrip() == null){
            throw new TripNotFoundException();
        }
        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }

    //helper method which returns all the closeBy cabs, who have no ongoing trips at present
    private List<Cab> getCloseByAvailableCabs(List<Cab> closeByCabs) {
        return closeByCabs.stream()
                   .filter(cab -> cab.getCurrentTrip() == null)
                   .collect(Collectors.toList());
    }

}
