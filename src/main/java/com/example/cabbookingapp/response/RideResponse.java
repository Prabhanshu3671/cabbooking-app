package com.example.cabbookingapp.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class RideResponse {
    String message;
    String driverName;
    String model;
    String driverLocation;
    String destination;
    String status;
    Integer amount;
}
