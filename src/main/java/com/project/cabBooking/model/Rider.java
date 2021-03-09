package com.project.cabBooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*No Setter. Since we don't want to change the values externally, once the rider obj is created*/

@Getter
@AllArgsConstructor
@ToString
public class Rider {
    String id;
    String name;
}
