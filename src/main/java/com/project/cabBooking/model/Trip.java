package com.project.cabBooking.model;

import lombok.NonNull;
import lombok.ToString;

@ToString
public class Trip {
    private Rider rider;
    private Cab cab;
    private Double price;
    private TripStatus tripStatus;
    private Location startingLocation;
    private Location endingLocation;

    public Trip(@NonNull final Rider rider, @NonNull final Cab cab,
                @NonNull final Double price,@NonNull final Location startingLocation,
                @NonNull final Location endingLocation) {
        this.rider = rider;
        this.cab = cab;
        this.price = price;
        this.startingLocation = startingLocation;
        this.endingLocation = endingLocation;
        this.tripStatus = TripStatus.IN_PROGRESS;
    }

    public void endTrip(){
        this.tripStatus = TripStatus.FINISHED;
    }


}
