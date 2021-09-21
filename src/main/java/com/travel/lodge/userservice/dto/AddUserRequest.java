package com.travel.lodge.userservice.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AddUserRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Length(max = 28)
    @Pattern(regexp = "^[a-z0-9]+@[a-z]+\\.[a-z]{2,6}$")
    private String email;

    @NotNull
    @Length(max=16)
    private String password;

    private String location;

    @NotNull
    @Length(max=20)
    private String contactNo;
}
