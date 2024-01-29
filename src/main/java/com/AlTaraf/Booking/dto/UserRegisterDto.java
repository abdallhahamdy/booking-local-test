package com.AlTaraf.Booking.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserRegisterDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private CityDto city;
    private Set<String> roles;

}
