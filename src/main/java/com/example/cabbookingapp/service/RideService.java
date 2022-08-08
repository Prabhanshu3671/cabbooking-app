package com.example.cabbookingapp.service;

import com.example.cabbookingapp.model.Driver;
import com.example.cabbookingapp.model.Ride;
import com.example.cabbookingapp.repository.DriverRepository;
import com.example.cabbookingapp.repository.RideRepository;
import com.example.cabbookingapp.repository.UserRepository;
import com.example.cabbookingapp.model.User;
import com.example.cabbookingapp.request.RideRequest;
import com.example.cabbookingapp.response.RideResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RideService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RideRepository rideRepository;

    HashMap<String, List<RideResponse>> userRidesAvailabilityMap = new HashMap<>();

    public List<RideResponse> findRides(RideRequest request){
        User user = userRepository.findByName(request.getUserName());
        List<RideResponse> finalResponse = new ArrayList<>();

        if(user == null){
            RideResponse emptyResponse = new RideResponse();
            String name = user.getName();
            emptyResponse.setMessage("User name " + name + " does not exist");
            finalResponse.add(emptyResponse);
            return finalResponse;
        }
        String userLocation = user.getLocation();

        String[] userCoordinates = userLocation.substring(1, userLocation.length() - 1).split(",");
        Integer userXCoordinate = Integer.parseInt(userCoordinates[0]), userYCoordinate = Integer.parseInt(userCoordinates[1]);

        String destination = request.getDestination();
        String[] destinationCoordinates = destination.substring(1, destination.length() - 1).split(",");
        Integer destinationXCoordinate = Integer.parseInt(destinationCoordinates[0]), destinationYCoordinate = Integer.parseInt(destinationCoordinates[1]);

        int amount = (int)Math.ceil(Math.sqrt(Math.pow(destinationXCoordinate - userXCoordinate, 2) + Math.pow(destinationYCoordinate - userYCoordinate, 2)));

        List<Driver> drivers = driverRepository.findAll();
        for(Driver driver : drivers){
            if(driver.getStatus().equals('N')){
                continue;
            }
            String driverLocation = driver.getLocation();

            String[] driverCoordinates = driverLocation.substring(1, driverLocation.length() - 1).split(",");
            Integer driverXCoordinate = Integer.parseInt(driverCoordinates[0]), driverYCoordinate = Integer.parseInt(driverCoordinates[1]);

            Double distance = Math.sqrt(Math.pow(driverXCoordinate - userXCoordinate, 2) + Math.pow(driverYCoordinate - userYCoordinate, 2));
            if(distance > 5.0){
                continue;
            }
            RideResponse response = new RideResponse();
            response.setDriverName(driver.getName());
            response.setDriverLocation(driverLocation);
            response.setDestination(request.getDestination());
            response.setModel(driver.getModel());
            response.setStatus("Ready");
            response.setAmount(amount);
            finalResponse.add(response);
            if(userRidesAvailabilityMap.containsKey(request.getUserName())){
                userRidesAvailabilityMap.get(request.getUserName()).add(response);
            }else{
                List<RideResponse> ridesForUser = new ArrayList<>();
                ridesForUser.add(response);
                userRidesAvailabilityMap.put(request.getUserName(), ridesForUser);
            }
        }
        if(finalResponse.size() == 0){
            RideResponse emptyResponse = new RideResponse();
            emptyResponse.setMessage("No rides available right now!!");
            finalResponse.add(emptyResponse);
        }
        return finalResponse;
    }

    public String chooseRide(String userName, String driverName){
        User user = userRepository.findByName(userName);
        List<RideResponse> rides = userRidesAvailabilityMap.get(userName);

        RideResponse currentRide = null;
        for(RideResponse ride : rides){
            if(ride.getDriverName().equals(driverName)){
                ride.setStatus("Started");
                currentRide = ride;
                break;
            }
        }

        if(currentRide == null){
            return "Ride invalid. Please choose from available rides only";
        }

        Driver driver = driverRepository.findByName(currentRide.getDriverName());
        driver.setStatus('N');
        driverRepository.save(driver);

        Ride ride = new Ride();
        ride.setUserName(userName);
        ride.setDriverName(currentRide.getDriverName());
        ride.setSource(user.getLocation());
        ride.setDestination(currentRide.getDestination());
        ride.setPrice(currentRide.getAmount());
        rideRepository.save(ride);
        return "Ride Started!!";
    }

    public String calculateBill(String userName){
        List<RideResponse> rides = userRidesAvailabilityMap.get(userName);
        RideResponse currentRide = null;
        for(RideResponse ride : rides){
            if(ride.getStatus().equals("Started")){
                currentRide = ride;
                break;
            }
        }
        if(currentRide == null){
            return "Please enter the correct user name";
        }

        updateUserLocation(userName, currentRide);
        updateDriverLocationStatusAndEarning(currentRide);
        userRidesAvailabilityMap.remove(userName);
        return "Ride Ended. Bill Amount : Rs " + currentRide.getAmount();
    }

    public List<String> findTotalEarning(){
        List<Driver> drivers = driverRepository.findAll();
        List<String> response = new ArrayList<>();

        for(Driver driver : drivers){
            String driverOutput = driver.getName() + " earns Rs " + driver.getTotalEarning();
            response.add(driverOutput);
        }
        return response;
    }

    public void updateUserLocation(String userName, RideResponse ride){
        User user = userRepository.findByName(userName);
        user.setLocation(ride.getDestination());
        userRepository.save(user);
    }

    public void updateDriverLocationStatusAndEarning(RideResponse ride){
        Driver driver = driverRepository.findByName(ride.getDriverName());
        driver.setLocation(ride.getDestination());
        driver.setStatus('A');
        int totalDriverEarning = ride.getAmount() + driver.getTotalEarning();
        driver.setTotalEarning(totalDriverEarning);
        driverRepository.save(driver);
    }
}
