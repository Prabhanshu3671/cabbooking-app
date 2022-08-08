package com.example.cabbookingapp.controller;

import com.example.cabbookingapp.request.UserRequest;
import com.example.cabbookingapp.response.UserResponse;
import com.example.cabbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/addUser")
    public UserResponse addUser(@RequestBody UserRequest request){
        return service.addUser(request);
    }

    @PostMapping("/updateUser/{name}")
    public UserResponse updateUser(@PathVariable String name, @RequestBody UserRequest request){
        return service.updateUser(name, request);
    }

    @PostMapping("/updateUserLocation/{name}")
    public UserResponse updateUserLocation(@PathVariable String name, @RequestBody UserRequest request){
        return service.updateLocation(name, request);
    }

}
