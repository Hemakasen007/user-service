package com.travel.lodge.userservice.controller.error.handler;

import com.travel.lodge.userservice.dto.ErrorDetails;
import com.travel.lodge.userservice.exception.UserCreationException;
import com.travel.lodge.userservice.exception.UserDoesNotExistException;
import com.travel.lodge.userservice.exception.UserExistsException;
import com.travel.lodge.userservice.util.Consts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserExistsException.class)
    public ErrorDetails handleValidationExceptions(UserExistsException ex) {
        return new ErrorDetails(HttpStatus.CONFLICT.name(), Consts.ResponseMessages.FAILED_ADD_USER.name(),
                Consts.ExceptionMessage.USER_EXISTS, null);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDetails handleValidationExceptions(Exception ex) {
        return new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.name(), Consts.ResponseMessages.FAILED.name(),
                ex.getMessage() , null);
    }

    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(UserCreationException.class)
    public ErrorDetails handleUserCreationException(UserCreationException ex) {
        return new ErrorDetails(HttpStatus.EXPECTATION_FAILED.name(), Consts.ResponseMessages.FAILED.name(),
                ex.getMessage() , null);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserDoesNotExistException.class)
    public ErrorDetails handleUserCreationException(UserDoesNotExistException ex) {
        return new ErrorDetails(HttpStatus.NOT_FOUND.name(), Consts.ResponseMessages.FAILED.name(),
                ex.getMessage() , null);
    }


}
