package com.cabbooking.core.rest;


import com.cabbooking.core.model.Position;
import com.cabbooking.core.model.VehiclePosition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SearchController {


    @GetMapping("/drivers")
    public Mono<Boolean> SearchDrivers(Position position){

        return null;
    }

}
