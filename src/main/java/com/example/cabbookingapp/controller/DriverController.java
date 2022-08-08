package com.example.cabbookingapp.controller;

import com.example.cabbookingapp.request.DriverRequest;
import com.example.cabbookingapp.response.DriverResponse;
import com.example.cabbookingapp.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {

    @Autowired
    private DriverService service;

    @PostMapping("/addDriver")
    public DriverResponse addDriver(@RequestBody DriverRequest request){
        return service.addDriver(request);
    }

    @PostMapping("/updateDriverLocation/{name}")
    public DriverResponse updateDriverLocation(@PathVariable String name, @RequestBody DriverRequest request){
        return service.updateDriverLocation(name, request);
    }

    @PostMapping("/changeDriverStatus/{name}")
    public DriverResponse changeDriverStatus(@PathVariable String name, @RequestBody DriverRequest request){
        return service.changeDriverStatus(name, request);
    }

}
