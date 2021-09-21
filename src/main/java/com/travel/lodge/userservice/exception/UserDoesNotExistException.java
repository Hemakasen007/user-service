package com.travel.lodge.userservice.exception;

import com.travel.lodge.userservice.util.Consts;

public class UserDoesNotExistException extends RuntimeException {

    public UserDoesNotExistException() {
        super(Consts.ExceptionMessage.USER_DOESNT_EXIST);
    }
}
