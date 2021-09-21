package com.travel.lodge.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KeycloakUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
