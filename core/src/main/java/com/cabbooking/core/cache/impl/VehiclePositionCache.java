package com.cabbooking.core.cache.impl;

import com.cabbooking.core.cache.Cache;
import com.cabbooking.core.model.VehiclePositionDriverDetails;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class VehiclePositionCache implements Cache<String, VehiclePositionDriverDetails> {
    private final ReactiveRedisTemplate<String, VehiclePositionDriverDetails> redisTemplate;
    private final ReactiveHashOperations<String,String, VehiclePositionDriverDetails> hashOperations;

    public VehiclePositionCache(ReactiveRedisTemplate<String, VehiclePositionDriverDetails> redisTemplate, ReactiveHashOperations<String, String, VehiclePositionDriverDetails> hashOperations) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
        //
    }

    @Override
    public void put(String areaCode, VehiclePositionDriverDetails vehicleLocation) {
         hashOperations.put(areaCode, vehicleLocation.getVehiclePosition().getVehicleId()
                , vehicleLocation);
    }

    @Override
    public Flux<VehiclePositionDriverDetails> get(String areaCode) {
       return hashOperations.entries(areaCode).map( entry -> entry.getValue());

    }
}
