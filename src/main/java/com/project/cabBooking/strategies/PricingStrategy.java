package com.project.cabBooking.strategies;

import com.project.cabBooking.model.Location;

public interface PricingStrategy {
    Double findPrice(Location fromPoint, Location toPoint);
}
