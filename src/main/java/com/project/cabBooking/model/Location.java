package com.project.cabBooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Location {
    private Double x;
    private Double y;

    public Double distance(Location toLocation){
        return Math.sqrt(Math.pow((this.x - toLocation.x),2) + Math.pow((this.y - toLocation.y),2));
    }
}
