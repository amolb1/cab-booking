package com.cabbooking.core.rest;


import com.cabbooking.core.model.VehiclePosition;
import com.cabbooking.core.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DriverController {


    @Autowired
    private DriverService driverService;


    @PostMapping("/driver-position")
    public Mono<Boolean> updateDriverPosition(String driverId, VehiclePosition vehiclePosition){
       return driverService.updateVehiclePosition(driverId,vehiclePosition);
    }
}
