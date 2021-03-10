package com.project.cabBooking.strategies;

import com.project.cabBooking.model.Cab;
import com.project.cabBooking.model.Location;
import com.project.cabBooking.model.Rider;
import lombok.NonNull;

import java.util.List;

public class DefaultCabMatchingStratery implements CabMatchingStrategy{

    @Override
    public Cab matchCabToRider(@NonNull final Rider rider,
                               @NonNull final List<Cab> candidateCabs,
                               @NonNull final Location fromPoint,
                               @NonNull final Location toPoint) {
        return candidateCabs.get(0);
    }
}
