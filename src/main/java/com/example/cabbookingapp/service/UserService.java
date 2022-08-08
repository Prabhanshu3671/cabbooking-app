package com.example.cabbookingapp.service;

import com.example.cabbookingapp.model.User;
import com.example.cabbookingapp.repository.UserRepository;
import com.example.cabbookingapp.request.UserRequest;
import com.example.cabbookingapp.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public UserResponse addUser(UserRequest request){
        UserResponse response = new UserResponse();
        User existingUser = repository.findByName(request.getName());
        if(existingUser != null){
            String userName = request.getName();
            response.setMessage("User name " + userName + " already exists.");
            return response;
        }
        User user = new User();
        user.setName(request.getName());
        user.setGender(request.getGender());
        user.setAge(request.getAge());
        user.setLocation(request.getLocation());
        repository.save(user);
        response.setMessage("User successfully created");
        response.setId(user.getId());
        response.setName(user.getName());
        response.setLocation(user.getLocation());
        return response;
    }

    public UserResponse updateUser(String name, UserRequest request){
        UserResponse response = new UserResponse();
        User user = repository.findByName(name);
        if(user == null){
            response.setMessage("User name " + name + " does not exist");
            return response;
        }

        if(request.getAge() != null){
            user.setAge(request.getAge());
        }
        if(request.getGender() != null){
            user.setGender(request.getGender());
        }
        if(request.getLocation() != null){
            user.setLocation(request.getLocation());
        }
        repository.save(user);
        response.setMessage("User successfully updated");
        response.setId(user.getId());
        response.setName(user.getName());
        response.setLocation(user.getLocation());
        return response;
    }

    public UserResponse updateLocation(String name, UserRequest request){
        UserResponse response = new UserResponse();
        User user = repository.findByName(name);

        if(user == null){
            response.setMessage("User name " + name + " does not exist");
            return response;
        }

        if(request.getLocation() != null){
            user.setLocation(request.getLocation());
        }

        repository.save(user);
        response.setMessage("User location successfully updated");
        response.setId(user.getId());
        response.setName(user.getName());
        response.setLocation(user.getLocation());
        return response;
    }
}
