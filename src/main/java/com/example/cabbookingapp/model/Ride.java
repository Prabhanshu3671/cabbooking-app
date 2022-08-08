package com.example.cabbookingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ride")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="DRIVER_NAME")
    private String driverName;

    @Column(name="SOURCE")
    private String source;

    @Column(name="DESTINATION")
    private String destination;

    @Column(name="RIDE_PRICE")
    private Integer price;
}
