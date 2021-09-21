package com.travel.lodge.userservice.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String contactNo;
    private String location;
    private String activityStatus;
}
