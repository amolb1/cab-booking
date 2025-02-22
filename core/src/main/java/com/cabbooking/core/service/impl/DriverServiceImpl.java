package com.cabbooking.core.service.impl;

import com.cabbooking.core.cache.Cache;
import com.cabbooking.core.model.VehiclePosition;
import com.cabbooking.core.model.VehiclePositionDriverDetails;
import com.cabbooking.core.service.AreaService;
import com.cabbooking.core.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class DriverServiceImpl implements DriverService {

    private AreaService areaService;

    private Cache<String, VehiclePositionDriverDetails> cache;

    DriverServiceImpl(AreaService areaService,Cache<String, VehiclePositionDriverDetails> cache) {
        this.areaService = areaService;
        this.cache = cache;
    }


    @Override
    public Mono<Boolean> updateDriverAvailibity(String driverId, VehiclePosition vehiclePosition) {
         //TODO
        return Mono.just(false);
    }

    @Override
    public Mono<Boolean> updateVehiclePosition(String driverId, VehiclePosition vehiclePosition) {
      VehiclePositionDriverDetails position =  VehiclePositionDriverDetails.builder().vehiclePosition(vehiclePosition).driverId(driverId).build();
       try {
           Mono<String> areaCode = areaService.getAreaCodeForCoordinates(vehiclePosition.getPosition().getLatitude(), vehiclePosition.getPosition().getLongitude());
           areaCode.subscribe( a -> cache.put(a , position));
           return Mono.just(true);
       }catch(Exception exception) {
           log.error("Error for updating driver details:{} and error:{}",driverId, exception.getMessage());
       }
       return Mono.just(false);
    }
}
