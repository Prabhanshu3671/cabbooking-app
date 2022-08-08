package com.example.cabbookingapp.controller;

import com.example.cabbookingapp.request.RideRequest;
import com.example.cabbookingapp.response.RideResponse;
import com.example.cabbookingapp.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RideController {

    @Autowired
    private RideService service;

    @PostMapping("/findRides")
    public List<RideResponse> findRides(@RequestBody RideRequest request){
        return service.findRides(request);
    }

    @PostMapping("/chooseRide")
    public String chooseRide(@RequestParam String userName, @RequestParam String driverName){
        return service.chooseRide(userName, driverName);
    }

    @GetMapping("/calculateBill/{userName}")
    public String calculateBill(@PathVariable String userName){
        return service.calculateBill(userName);
    }

    @GetMapping("/findTotalEarning")
    public List<String> findTotalEarning(){
        return service.findTotalEarning();
    }
}
