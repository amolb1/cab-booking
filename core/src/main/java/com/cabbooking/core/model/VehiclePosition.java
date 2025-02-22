package com.cabbooking.core.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class VehiclePosition {

    private String vehicleId;

    private Position position;

}
