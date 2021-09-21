package com.travel.lodge.userservice.exception;

import com.travel.lodge.userservice.util.Consts;

public class UserExistsException extends RuntimeException{
    public UserExistsException(){
        super(Consts.ExceptionMessage.USER_EXISTS);
    }
}

