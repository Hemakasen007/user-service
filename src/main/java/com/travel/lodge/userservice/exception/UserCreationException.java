package com.travel.lodge.userservice.exception;

import com.travel.lodge.userservice.util.Consts;

public class UserCreationException extends RuntimeException{
    public UserCreationException(Exception e) {
        super(e.getMessage(), e);
    }

    public UserCreationException() {
        super(Consts.ExceptionMessage.USER_CREATION_FAILED);
    }
}
