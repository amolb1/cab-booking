package com.cabbooking.core.service;

import reactor.core.publisher.Mono;

public interface AreaService {


    Mono<String> getAreaCodeForCityCoordinates(double latitude, double longitude, String cityName);

    Mono<String> getAreaCodeForCoordinates(double latitude, double longitude);
}


