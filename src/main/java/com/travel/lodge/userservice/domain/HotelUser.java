package com.travel.lodge.userservice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class HotelUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String location;

    private String contactNo;

    private int activityStatus;

}
