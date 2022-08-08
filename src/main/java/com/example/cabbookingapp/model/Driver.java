package com.example.cabbookingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DRIVER_ID")
    private Integer id;

    @Column(name="DRIVER_NAME")
    private String name;

    @Column(name="GENDER")
    private Character gender;

    @Column(name="AGE")
    private Integer age;

    @Column(name="STATUS")
    private Character status = 'A';

    @Column(name="VEHICLE_MODEL")
    private String model;

    @Column(name="LOCATION")
    private String location;

    @Column(name="TOTAL_EARNING")
    private Integer totalEarning = 0;
}
