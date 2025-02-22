package com.cabbooking.core.service;

import com.cabbooking.core.model.VehiclePosition;
import reactor.core.publisher.Mono;

public interface DriverService {

    public Mono<Boolean> updateDriverAvailibity(String driverId, VehiclePosition vehiclePosition);


    public Mono<Boolean> updateVehiclePosition(String driverId, VehiclePosition vehiclePosition);
}
