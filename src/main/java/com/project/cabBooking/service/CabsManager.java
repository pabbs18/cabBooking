package com.project.cabBooking.service;

import com.project.cabBooking.exceptions.CabAlreadyExistsException;
import com.project.cabBooking.exceptions.CabNotFoundException;
import com.project.cabBooking.model.Cab;
import com.project.cabBooking.model.Location;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CabsManager {
    Map<String, Cab> cabs = new HashMap<>();

    public void createCab(@NonNull final Cab newCab ){
        if(cabs.containsKey(newCab.getId())){
            throw new CabAlreadyExistsException();
        }
        cabs.put(newCab.getId(), newCab);
    }

    public Cab getCab(@NonNull final String cabId){
        if(!cabs.containsKey(cabId)){
            throw new CabNotFoundException();
        }
        return cabs.get(cabId);
    }


    public void updateCabLocation(@NonNull final String cabId, @NonNull final Location newLocation){
        if(!cabs.containsKey(cabId)){
            throw new CabNotFoundException();
        }
        cabs.get(cabId).setCurrentLocation(newLocation);
    }

    public void updateAvailibility(@NonNull final String cabId, @NonNull final Boolean newAvailibility){
        if(!cabs.containsKey(cabId)){
            throw new CabNotFoundException();
        }
        cabs.get(cabId).setIsAvailable(newAvailibility);
    }

    public List<Cab> getCabs(@NonNull final Location summonPoint, @NonNull final Double maxCabMatchingDistance){
        List<Cab> availableCabs = new ArrayList<>();

        for(Cab cab: cabs.values()){
            if(cab.getIsAvailable() && cab.getCurrentLocation().distance(summonPoint) <= maxCabMatchingDistance){
                availableCabs.add(cab);
            }
        }
        return availableCabs;
    }
}
