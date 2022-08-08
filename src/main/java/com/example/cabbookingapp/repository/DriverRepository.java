package com.example.cabbookingapp.repository;

import com.example.cabbookingapp.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Driver findByName(String name);

    List<Driver> findAll();
}
