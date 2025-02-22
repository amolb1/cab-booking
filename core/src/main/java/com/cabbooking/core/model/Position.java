package com.cabbooking.core.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Position {

   private String city;

   private String areaCode;

    private double longitude;

    private double latitude;
}
