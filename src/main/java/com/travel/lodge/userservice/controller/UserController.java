package com.travel.lodge.userservice.controller;

import com.travel.lodge.userservice.dto.AddUserRequest;
import com.travel.lodge.userservice.dto.CommonResponse;
import com.travel.lodge.userservice.dto.HotelUserDetails;
import com.travel.lodge.userservice.dto.UpdateUserRequest;
import com.travel.lodge.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    final UserService userService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse addUser(@RequestBody @Valid AddUserRequest addUserRequest) {
        return userService.addHotelUser(addUserRequest);
    }

    @GetMapping
    public CommonResponse getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public CommonResponse getOneUser(@PathVariable("id") long id) {
        return userService.getUserBasedOnId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse updateUser(@PathVariable("id") long id, @RequestBody UpdateUserRequest body) {
        return userService.updateUser(id, body);
    }

    @GetMapping("/find-by-username")
    public HotelUserDetails getByUsername(@RequestHeader("Authorization") String token, @RequestParam String username) {
        return userService.getDetailsByUsername(username);
    }


}
