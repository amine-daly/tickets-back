package com.bezkoder.spring.jwt.mongodb.models.userType;

import lombok.Data;

@Data
public class Address {
    private String addressLine;
    private String city;
    private String country;
    private String state;
    private Address postCode;

}
