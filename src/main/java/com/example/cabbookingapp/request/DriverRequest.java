package com.example.cabbookingapp.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class DriverRequest {
    String name;
    Character gender;
    Integer age;
    Boolean status;
    String model;
    String location;
}
