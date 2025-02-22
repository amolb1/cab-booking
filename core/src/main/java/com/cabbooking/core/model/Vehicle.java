package com.cabbooking.core.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Vehicle {

   private String Id;

   private String model;

   private String registrationNumber;

   private String color;

   private Integer capacity;

   private String vehicleClass;

}
