package com.cabbooking.core.model;

//used to store in redis

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class VehiclePositionDriverDetails {

    private String driverId;

    private VehiclePosition vehiclePosition;

    //Not required
   // private Vehicle vehicleDetails;

}
