package com.example.cabbookingapp.service;

import com.example.cabbookingapp.model.Driver;
import com.example.cabbookingapp.model.User;
import com.example.cabbookingapp.repository.DriverRepository;
import com.example.cabbookingapp.request.DriverRequest;
import com.example.cabbookingapp.response.DriverResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    DriverRepository repository;

    public DriverResponse addDriver(DriverRequest request){
        DriverResponse response = new DriverResponse();

        Driver existingDriver = repository.findByName(request.getName());
        if(existingDriver != null){
            String driverName = request.getName();
            response.setMessage("Driver name " + driverName + " already exists.");
            return response;
        }

        Driver driver = new Driver();
        driver.setName(request.getName());
        driver.setGender(request.getGender());
        driver.setAge(request.getAge());
        driver.setModel(request.getModel());
        driver.setLocation(request.getLocation());
        repository.save(driver);

        response.setMessage("Driver added successfully");
        response.setId(driver.getId());
        response.setName(driver.getName());
        response.setStatus(driver.getStatus());
        response.setLocation(driver.getLocation());
        return response;
    }

    public DriverResponse updateDriverLocation(String name, DriverRequest request){
        Driver driver = repository.findByName(name);
        DriverResponse response = new DriverResponse();

        if(driver == null){
            response.setMessage("User name " + name + " does not exist");
            return response;
        }

        driver.setLocation(request.getLocation());
        repository.save(driver);

        response.setMessage("Driver location updated successfully");
        response.setId(driver.getId());
        response.setName(driver.getName());
        response.setStatus(driver.getStatus());
        response.setLocation(driver.getLocation());
        return response;
    }

    public DriverResponse changeDriverStatus(String name, DriverRequest request){
        Driver driver = repository.findByName(name);
        DriverResponse response = new DriverResponse();

        if(driver == null){
            response.setMessage("User name " + name + " does not exist");
            return response;
        }

        if(request.getStatus().equals(true)){
            driver.setStatus('A');
        }else{
            driver.setStatus('N');
        }

        response.setMessage("Driver status updated successfully");
        response.setId(driver.getId());
        response.setName(driver.getName());
        response.setStatus(driver.getStatus());
        response.setLocation(driver.getLocation());
        return response;
    }
}
