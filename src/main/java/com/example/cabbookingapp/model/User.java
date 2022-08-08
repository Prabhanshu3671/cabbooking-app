package com.example.cabbookingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Integer id;

    @Column(name="USER_NAME")
    private String name;

    @Column(name="GENDER")
    private Character gender;

    @Column(name="AGE")
    private Integer age;

    @Column(name="LOCATION")
    private String location;
}
