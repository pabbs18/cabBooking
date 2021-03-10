package com.project.cabBooking.strategies;

import com.project.cabBooking.model.Location;
import lombok.NonNull;

public class DefaultPricingStrategy implements PricingStrategy{
    private final Double FIXED_PRICING_PER_KM = 10.0;
    @Override
    public Double findPrice(@NonNull final Location fromPoint, @NonNull final Location toPoint) {
        return fromPoint.distance(toPoint) * FIXED_PRICING_PER_KM;
    }
}
