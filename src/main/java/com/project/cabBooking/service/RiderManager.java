package com.project.cabBooking.service;

import com.project.cabBooking.exceptions.RiderAlreadyExistsException;
import com.project.cabBooking.exceptions.RiderNotFoundException;
import com.project.cabBooking.model.Rider;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
/*Map based in-memory database*/
public class RiderManager {

    Map<String, Rider> riderMap = new HashMap<>();

    public void createRider(@NonNull final Rider newRider){
        if(riderMap.containsKey(newRider)){
            throw new RiderAlreadyExistsException();
        }
        riderMap.put(newRider.getId(), newRider);
    }

    public Rider getRider(@NonNull final String riderId){
        if(!riderMap.containsKey(riderId)){
            throw new RiderNotFoundException();
        }
      return riderMap.get(riderId);
    }

}
